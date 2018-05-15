(ns girajira.infra.events.initialize
  (:require [girajira.infra.events.definitions :as events]
            [girajira.infra.events.pubsub :as pubsub :refer [defsubscriber]]
            [girajira.infra.events.subscribers.jira-transition :as jira]
            [girajira.infra.events.subscribers.example-subscriber :as example]))

(defn initialize-subscribers []
  (do
    (defsubscriber events/example-event example/subscriber)
    (jira/initialize-subscriber)))
    ;(defsubscriber events/github-pull-request-received jira/subscriber)
