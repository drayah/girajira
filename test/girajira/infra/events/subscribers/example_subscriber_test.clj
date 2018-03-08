(ns girajira.infra.events.subscribers.example-subscriber-test
  (:require [midje.sweet :refer :all]
            [girajira.infra.events.subscribers.example-subscriber :refer :all]
            [girajira.infra.events.pubsub :as pubsub]))

(facts "when initializing the subscriber"
  (fact "it calls subscribe passing the correct event, subscriber channel and event handler"
    (with-redefs [subscriber ..fake-channel..
                  on-event ..fake-event-handler..]
      (initialize-subscriber) => ..ok..
        (provided
          (pubsub/subscribe
            :example-event
            ..fake-channel..
            ..fake-event-handler..) => ..ok..))))
