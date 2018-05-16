(ns girajira.infra.events.subscribers.example-subscriber-test
  (:require [midje.sweet :refer :all]
            [girajira.infra.events.subscribers.example-subscriber :refer :all]))

(facts "when calling the subscriber with an event"
  (fact "it prints the event to standard out"
    (subscriber ..fake-event-data..) => ..fake-output..
      (provided
        (console-write! "Subscriber performing work for event: ..fake-event-data..") => ..fake-output..)))
