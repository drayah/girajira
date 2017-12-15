(ns girajira.jira.request
  (:require [clj-http.client :as client]))

(def jira-url (str (System/getenv "JIRA_URL") "/rest/api/2"))

(defn reporter-url
  [reporter] (str jira-url "/search?jql=reporter=" reporter))

(defn raw-issues
  [reporter] (client/get (reporter-url reporter) {:basic-auth ["" ""]}))

(defn issues
  [] (:body (raw-issues)))
