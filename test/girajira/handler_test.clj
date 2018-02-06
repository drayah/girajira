(ns girajira.handler-test
  (:require [ring.mock.request :as mock]
            [midje.sweet :refer :all]
            [girajira.handler :refer :all]))

(facts "when responding to routes"
  (facts "when responding to /"
    (fact "it returns http status 200"
      (let [response (app (mock/request :get "/"))]
        (:body response) => "Girajira ;)"
        (:status response) => 200))))

(facts "when responding to invalid routes"
  (fact "it returns http status 404"
    (:status (app (mock/request :get "/invalid"))) => 404))
