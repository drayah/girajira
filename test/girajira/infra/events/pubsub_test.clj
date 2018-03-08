(ns girajira.infra.events.pubsub-test
  (:require [midje.sweet :refer :all]
            [girajira.infra.events.pubsub :refer :all]
            [girajira.infra.events.datetime :as datetime]
            [clojure.core.async :as async]))

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
      (let [blocking-read (fn [] (async/<!! publisher))]
        (do
          (publish ..event.. ..data..)
          (blocking-read) => {:event ..event.. :time ..current-time.. :data ..data..})))))
