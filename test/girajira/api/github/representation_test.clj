(ns girajira.api.github.representation-test
  (:require [midje.sweet :refer :all]
            [girajira.api.github.representation :refer :all]))

(facts "when rendering no content"
  (fact "it returns http status 204"
    (no-content) => {:status 204 :headers {} :body ""}))
