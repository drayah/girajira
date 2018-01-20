(ns girajira.api.github.post-test
  (:require [midje.sweet :refer :all]
            [girajira.api.github.post :refer :all]
            [girajira.api.github.representation :as representation]))

(def payload
  {"action" "opened"
   "pull_request" {"url" "some_url"
                   "id" 1234
                   "user" {"login" "theusername"}
                   "body" "the pull request body girajira:ca-123:doing_done:done"
                   "base" {"ref" "master"}
                   "created_at" "some date"
                   "updated_at" "some other date"}})

(def expected-transformed-payload
  {:action "opened"
   :pull_request {:url "some_url"
                  :id 1234
                  :user {:login "theusername"}
                  :body "the pull request body girajira:ca-123:doing_done:done"
                  :base {:ref "master"}
                  :created_at "some date"
                  :updated_at "some other date"}})

(def expected-pull-request-data
  {:action "opened"
   :body "the pull request body girajira:ca-123:doing_done:done"
   :target "master"
   :user "theusername"})

(facts "when keywordizing the raw github payload"
  (fact "it returns the payload with keys transformed to keywords"
    (keywordize-request-body payload) => expected-transformed-payload))

(facts "when getting pull request data from a transformed payload"
  (fact "it returns a hash containing pull request data"
    (pull-request-data expected-transformed-payload) => expected-pull-request-data))

(facts "when handling the post request"
  (fact "it returns http status 204 no content"
    (handle payload) => 204
    (provided (representation/no-content) => 204)))
