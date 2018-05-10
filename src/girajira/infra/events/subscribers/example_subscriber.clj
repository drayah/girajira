(ns girajira.infra.events.subscribers.example-subscriber
  (:require [clojure.core.async :as async :refer [chan]]
            [girajira.infra.events.pubsub :as pubsub :refer [defsubscriber]]
            [girajira.infra.events.definitions :as events]))

(defn initialize-subscriber []
  (defsubscriber events/example-event
    (fn [event]
      (println (str "An example subscriber for event: " event)))))
