(ns girajira.infra.events.pubsub
  (:require [clojure.core.async
             :as async
             :refer [>! <! >!! <!! chan go pub sub go-loop]]
            [girajira.infra.events.definitions :as events]))

(def publisher (chan))

(def publication
  (pub publisher :event))

(defn- event-handler
  [channel event-fn]
  (go-loop []
           (let [event (<! channel)]
             (event-fn event))
           (recur)))

(defn subscribe
  [topic channel event-fn]
  (do
    (sub publication topic channel)
    (event-handler channel event-fn)))

(defn publish
  [event data]
  (go
    (>! publisher (assoc data :event event))))

(defn test-pub-sub []
  (do
    (publish events/sample-event {:person "test"})
    "stub render"))
