(ns girajira.jira.request
  (:require [clj-http.client :as client]
            [girajira.jira.authentication :as jira-authentication]
            [cheshire.core :as cheshire]))

(defn jira-api-url
  []
  (str (jira-authentication/url) "/rest/api/2"))

(defn authenticated-get
  [url]
  (->>
    (client/get url {:basic-auth (jira-authentication/basic-auth-params)})
    (:body)
    (cheshire/parse-string)))
