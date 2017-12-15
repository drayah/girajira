(ns girajira.jira.transitions
  (:require [girajira.jira.request :as request]
            [clj-http.client :as client]
            [cheshire.core :refer :all]))

(defn transitions-url
  [card-id] (str request/jira-url "/issue/" card-id  "/transitions"))

(defn get-transitions
  [card-id]
  (:body (client/get
    (transitions-url card-id) {:basic-auth ["matheus.caceres" "KHZqCcaN28qcAC"]})))

(defn parse-body
  [body] (parse-string body))
