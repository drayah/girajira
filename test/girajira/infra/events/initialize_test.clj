(ns girajira.infra.events.initialize-test
  (:require [midje.sweet :refer :all]
            [girajira.infra.events.initialize :refer :all]
            [girajira.infra.events.subscribers.jira-transition :as jira]
            [girajira.infra.events.subscribers.example-subscriber :as example]
            [girajira.infra.events.pubsub :as pubsub]
            [girajira.infra.events.definitions :as events]))

(facts "when initializing subscribers"
  (fact "it initializes all event subscribers"
    (initialize-subscribers) => ..initialized-all-subscribers..
    (provided
      (pubsub/subscribe events/example-event anything example/subscriber) => ..initialized-example-subscriber..
      (jira/initialize-subscriber) => ..initialized-all-subscribers..)))
