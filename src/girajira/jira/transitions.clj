(ns girajira.jira.transitions
  (:require [girajira.jira.request :as request]))

(defn transitions-url
  [card-id]
  (str (request/jira-api-url) "/issue/" card-id  "/transitions"))

(defn get-transitions
  [card-id]
  (request/authenticated-get (transitions-url card-id)))
