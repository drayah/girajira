(ns girajira.infra.events.subscribers.jira-transition-test
  (:require [midje.sweet :refer :all]
            [girajira.infra.events.subscribers.jira-transition :refer :all]
            [girajira.integrations.jira.move-issue :as jira]))

(facts "when calling the subscriber with an event"
  (fact "it calls the move jira issue use case passing the event data"
    (let [fake-event {:data ..some-fake-event-data..}]
      (subscriber fake-event) => ..performed-jira-transition..
        (provided
          (jira/move-issue ..some-fake-event-data..) => ..performed-jira-transition..))))
