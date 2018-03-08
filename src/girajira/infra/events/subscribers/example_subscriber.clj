(ns girajira.infra.events.subscribers.example-subscriber
  (:require [clojure.core.async :as async :refer [chan]]
            [girajira.infra.events.pubsub :as pubsub]
            [girajira.infra.events.definitions :as events]))

(def subscriber (chan))

(defn on-event
  [event]
  (println (str "Example subscriber: " event)))

(defn initialize-subscriber []
  (pubsub/subscribe
    events/example-event
    subscriber
    on-event))
