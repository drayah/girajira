(ns girajira.infra.events.initialize
  (:require [girajira.infra.events.subscribers.jira-transition :as jira]
            [girajira.infra.events.subscribers.example-subscriber :as example]))

(defn initialize-subscribers []
  (do
    (jira/initialize-subscriber)
    (example/initialize-subscriber)))
