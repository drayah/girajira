(ns girajira.infra.events.subscribers.jira-transition-test
  (:require [midje.sweet :refer :all]
            [girajira.infra.events.subscribers.jira-transition :refer :all]
            [girajira.infra.events.pubsub :as pubsub]
            [girajira.integrations.jira.move-issue :as jira]))

(facts "when running the event handler"
  (fact "it calls the move-issue function"
    (on-event {:data "fake event data"}) => ..ok..
    (provided
      (jira/move-issue "fake event data") => ..ok..)))

(facts "when initializing the subscriber"
  (fact "it calls subscribe passing the correct event, subscriber channel and event handler"
    (with-redefs [subscriber ..fake-channel..
                  on-event ..fake-event-handler..]
      (initialize-subscriber) => ..ok..
        (provided
          (pubsub/subscribe
            :github-pull-request-received
            ..fake-channel..
            ..fake-event-handler..) => ..ok..))))
