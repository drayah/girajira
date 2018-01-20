(ns girajira.api.github.post-test
  (:require [midje.sweet :refer :all]
            [girajira.api.github.post :refer :all]))

(def payload
  {"action" "opened"
   "pull_request" {"url" "some_url"
                   "id" 1234
                   "user" {"login" "theusername"}
                   "body" "the pull request body"
                   "base" {"ref" "master"}
                   "created_at" "some date"
                   "updated_at" "some other date"}})

(def expected-transformed-request
  {:action "opened"
   :pull_request {:url "some_url"
                  :id 1234
                  :user {:login "theusername"}
                  :body "the pull request body"
                  :base {:ref "master"}
                  :created_at "some date"
                  :updated_at "some other date"}})

(def expected-pull-request-data
  {:action "opened"
   :body "the pull request body"
   :target "master"
   :user "theusername"})

(facts "when keywordizing the raw github payload"
  (fact "it returns the payload with keys transformed to keywords"
    (keywordize-request-body payload) => expected-transformed-request))

(facts "when getting pull request data from a transformed payload"
  (fact "it returns a hash containing pull request data"
    (pull-request-data expected-transformed-request) => expected-pull-request-data)) 
