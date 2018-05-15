(ns girajira.infra.events.subscribers.example-subscriber
  (:require [clojure.core.async :as async :refer [chan]]
            [girajira.infra.events.pubsub :as pubsub :refer [defsubscriber]]
            [girajira.infra.events.definitions :as events]))

(defn subscriber [event]
  (println (str "Example subscriber for event: " event)))
