(ns girajira.jira.transitions
  (:require [girajira.jira.request :as request]
            [clojure.string :as string]))

(defn transitions-url
  [card-id]
  (str (request/jira-api-url) "/issue/" card-id  "/transitions"))

(defn get-transitions
  [card-id]
  (request/authenticated-get (transitions-url card-id)))

(defn post-transitions
  [card-id body]
  (request/authenticated-post (transitions-url card-id) body))

(defn card-transitions
  [api-response]
  (api-response "transitions"))

(defn extract-kanban-columns
  [transitions]
  (map #(hash-map (% "name") (% "id")) transitions))

(defn kanban-columns-for-card
  [card-id]
  (->>
    (get-transitions card-id)
    (card-transitions)
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

(defn card-transition-body
  [column-id]
  {"transition" {"id" column-id}})

(defn move-card-to-kanban-column
  [card-id column-name]
  (->>
    (kanban-column-id-by-card card-id column-name)
    (card-transition-body)
    (post-transitions card-id)))

(defn kanban-column-id-by-card
  [card-id column-name]
  (->>
    (kanban-columns-for-card card-id)
    (filter-kanban-columns-by column-name)
    (kanban-column-id)))
