(ns girajira.jira.request-test
  (:require [midje.sweet :refer :all]
            [girajira.jira.request :refer :all]
            [girajira.jira.authentication :as jira-authentication]))

(fact "returns a jira api url"
  (jira-api-url) => "http://url/rest/api/2"
  (provided (jira-authentication/url) => "http://url"))
