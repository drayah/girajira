(ns girajira.jira.transitions
  (:require [girajira.jira.request :as request]))

(defn transitions-url
  [card-id]
  (str (request/jira-api-url) "/issue/" card-id  "/transitions"))

(defn get-transitions
  [card-id]
  (request/authenticated-get (transitions-url card-id)))

(defn possible-transitions
  [api-response]
  (api-response "transitions"))

(defn columns
  [possible-transitions]
  (map (fn [element] {(element "name") (element "id")}) possible-transitions))

(defn find-column-id
  [column-name possible-transitions]
  (str "moises"))

(defn move-card-to-column
  [card-id column-id]
  (str "moises"))
