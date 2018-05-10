(ns girajira.infra.events.subscribers.example-subscriber-test
  (:require [midje.sweet :refer :all]
            [girajira.infra.events.subscribers.example-subscriber :refer :all]
            [girajira.infra.events.pubsub :as pubsub :refer [subscribe]]))

(facts "when initializing the subscriber"
  (fact "it calls subscribe passing the correct event, subscriber channel and event handler"
    (with-redefs [subscribe (fn [topic channel event-fn] ..fake-subscriber..)]
      (initialize-subscriber) => ..fake-subscriber..)))
