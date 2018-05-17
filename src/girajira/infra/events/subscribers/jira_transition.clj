(ns girajira.infra.events.subscribers.jira-transition
  (:require [girajira.integrations.jira.move-issue :as jira]))

(def console-write! (partial println))

(def log-statement "Github pull request received:")

(defn subscriber [event]
  (let [jira-transition-data (:data event)]
    (do
      (console-write! (str log-statement " " event))
      (jira/move-issue jira-transition-data))))
