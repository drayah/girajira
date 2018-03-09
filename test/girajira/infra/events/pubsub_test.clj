(ns girajira.infra.events.pubsub-test
  (:require [midje.sweet :refer :all]
            [girajira.infra.events.pubsub :refer :all]
            [girajira.infra.events.datetime :as datetime]
            [clojure.core.async :as async]))

(def input-channel (async/chan))
(def output-channel (async/chan))

(defn blocking-read
  [channel]
  (async/<!! channel))

(defn blocking-write
  [channel data]
  (async/>!! channel data))

(defn test-callback
  [event]
  (blocking-write output-channel event))

(facts "when initializing an event handler for a given channel"
  (fact "it sets up the event handler to act whenever data is received on the channel"
    (do
      (initialize-event-handler input-channel test-callback)
      (blocking-write input-channel ..fake-event-data..)
      (blocking-read output-channel) => ..fake-event-data..)))

(facts "when subscribing to a topic given a channel and callback function"
  (fact "it subscribes to a publication given the topic and a channel and sets up an event handler"
    (with-redefs [publication ..publication..]
      (subscribe ..topic.. ..channel.. ..callback..) => ..subscription..
        (provided
          (async/sub ..publication.. ..topic.. ..channel..) => ..many-to-many-channel..
          (initialize-event-handler ..channel.. ..callback..) => ..subscription..))))

(facts "when publishing an event with some data"
  (fact "it puts the event data on a channel"
    (with-redefs [publisher (async/chan)
                  datetime/today (fn [] ..current-time..)]
      (do
        (publish ..event.. ..data..)
        (blocking-read publisher) => {:event ..event.. :time ..current-time.. :data ..data..}))))
