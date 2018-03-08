(ns girajira.infra.events.subscribers.jira-transition
  (:require [clojure.core.async :as async :refer [chan]]
            [girajira.infra.events.pubsub :as pubsub]
            [girajira.infra.events.definitions :as events]
            [girajira.integrations.jira.move-issue :as jira]))

(def subscriber (chan))

(defn on-event
  [event]
  (let [move-data (:data event)]
    (do
      (println (str "jira-transition: " event))
      (jira/move-issue move-data))))

(defn initialize-subscriber []
  (pubsub/subscribe
    events/github-pull-request-received
    subscriber
    on-event))
