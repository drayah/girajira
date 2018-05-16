(ns girajira.infra.events.subscribers.example-subscriber
  (:require [clojure.core.async :as async :refer [chan]]
            [girajira.infra.events.pubsub :as pubsub :refer [defsubscriber]]
            [girajira.infra.events.definitions :as events]))

(def console-write! (partial println))

(defn subscriber [event]
  (console-write! (str "Subscriber performing work for event: " event)))
