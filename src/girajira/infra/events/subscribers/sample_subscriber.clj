(ns girajira.infra.events.subscribers.sample-subscriber
  (:require [clojure.core.async :as async :refer [chan]]
            [girajira.infra.events.pubsub :as pubsub]
            [girajira.infra.events.definitions :as events]))

(def subscriber (chan))

(defn on-event
  [event]
  (println (str "hey this is a sample subscriber: " event)))

(defn initialize-subscriber []
  (pubsub/subscribe
    events/sample-event
    subscriber
    on-event))
