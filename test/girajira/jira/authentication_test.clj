(ns girajira.jira.authentication-test
  (:require [midje.sweet :refer :all]
            [girajira.jira.authentication :refer :all]
            [girajira.config :as config]))

(def secrets
  {:jira-url "www.jira.com"
   :jira-user "some user"
   :jira-pass "mypassword"})

(fact "returns a jira username from given secrets"
  (username secrets) => "some user")

(fact "returns a jira password from given secrets"
  (password secrets) => "mypassword")

(fact "returns a jira url from given secrets"
  (url) => "www.jira.com"
  (provided (config/secrets) => secrets))

(fact "returns basic auth params from given secrets"
  (basic-auth-params) => ["some user" "mypassword"]
  (provided (config/secrets) => secrets))
