(ns girajira.integrations.jira.transitions-test
  (:require [midje.sweet :refer :all]
            [girajira.integrations.jira.transitions :refer :all]
            [girajira.integrations.jira.request :as request]))

(facts "when getting the jira api transitions url given an issue-id"
  (fact "it returns the correct api url"
    (transitions-url "fake-issue-id") => "http://jira.api/issue/fake-issue-id/transitions"
    (provided
      (request/jira-api-url) => "http://jira.api")))

(facts "when getting the possible transitions given some issue-id"
  (fact "it performs a jira api request and returns json response data"
    (let [fake-url "http://jira.api/issues"]
      (get-transitions "issue-id") => ..json-response..
      (provided
        (transitions-url "issue-id") => fake-url
        (request/authenticated-get fake-url) => ..json-response..))))
