(ns girajira.jira.transitions
  (:require [girajira.jira.request :as request]
            [clojure.string :as string]))

(defn transitions-url
  [issue-id]
  (str (request/jira-api-url) "/issue/" issue-id  "/transitions"))

(defn get-transitions
  [issue-id]
  (request/authenticated-get (transitions-url issue-id)))

(defn post-transitions
  [issue-id body]
  (request/authenticated-post (transitions-url issue-id) body))

(defn issue-transitions
  [api-response]
  (api-response "transitions"))

(defn extract-kanban-columns
  [transitions]
  (map #(hash-map (% "name") (% "id")) transitions))

(defn kanban-columns-for-issue
  [issue-id]
  (->>
    (get-transitions issue-id)
    (issue-transitions)
    (extract-kanban-columns)))

(defn kanban-column-with-name?
  [column-name kanban-column]
  (let [key (first (keys kanban-column))]
    (= (string/lower-case column-name)
       (string/lower-case key))))

(defn filter-kanban-columns-by
  [column-name columns]
  (->>
    (filter #(kanban-column-with-name? column-name %) columns)
    (first)))

(defn kanban-column-id
  [column]
  (first (vals column)))

(defn issue-transition-body
  [column-id]
  {"transition" {"id" column-id}})

(defn kanban-column-id-by-issue
  [issue-id column-name]
  (->>
    (kanban-columns-for-issue issue-id)
    (filter-kanban-columns-by column-name)
    (kanban-column-id)))

(defn move-issue-to-kanban-column
  [issue-id column-name]
  (->>
    (kanban-column-id-by-issue issue-id column-name)
    (issue-transition-body)
    (post-transitions issue-id)))
