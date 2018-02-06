(ns girajira.infra.events.initialize
  (:require [girajira.infra.events.subscribers.jira-transition :as jira]
            [girajira.infra.events.subscribers.sample-subscriber :as sample]))

(defn initialize-subscribers []
  (do
    (jira/initialize-subscriber)
    (sample/initialize-subscriber)))
