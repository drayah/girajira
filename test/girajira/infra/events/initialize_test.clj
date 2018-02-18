(ns girajira.infra.events.initialize-test
  (:require [midje.sweet :refer :all]
            [girajira.infra.events.initialize :refer :all]
            [girajira.infra.events.subscribers.jira-transition :as jira]
            [girajira.infra.events.subscribers.example-subscriber :as example]))

(facts "when initializing subscribers"
  (fact "it initializes all event subscribers"
    (initialize-subscribers) => :initialize-example-subscriber
    (provided
      (jira/initialize-subscriber) => :initialize-jira-subscriber
      (example/initialize-subscriber) => :initialize-example-subscriber)))
