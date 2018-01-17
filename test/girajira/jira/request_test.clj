(ns girajira.jira.request-test
  (:require [midje.sweet :refer :all]
            [girajira.jira.request :refer :all]
            [girajira.jira.authentication :as jira-authentication]))

(facts "when getting the jira api url"
  (fact "returns the url with /rest/api/2 appended"
    (jira-api-url) => "http://url/rest/api/2"
    (provided (jira-authentication/url) => "http://url")))
