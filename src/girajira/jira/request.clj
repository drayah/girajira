(ns girajira.jira.request
  (:require [clj-http.client :as client]))

(def jira-url
  "https://url/rest/api/2/search?jql=reporter=param")

(defn raw-issues
  [] (client/get jira-url {:basic-auth ["user" "pass"]}))

(defn issues
  [] (:body (raw-issues)))


