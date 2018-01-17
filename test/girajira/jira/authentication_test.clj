(ns girajira.jira.authentication-test
  (:require [midje.sweet :refer :all]
            [girajira.jira.authentication :refer :all]
            [girajira.config :as config]))

(def secrets
  {:jira-url "www.jira.com"
   :jira-user "some user"
   :jira-pass "mypassword"})

(facts "when getting the jira url"
  (fact "it returns the url that was saved in the aero configuration"
    (url) => "www.jira.com"
    (provided (config/secrets) => secrets)))

(facts "when getting the basic auth params"
  (fact "it returns a vector containing the configured jira username and password"
    (basic-auth-params) => ["some user" "mypassword"]
    (provided (config/secrets) => secrets)))
