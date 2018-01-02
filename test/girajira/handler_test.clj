(ns girajira.handler-test
  (:require [ring.mock.request :as mock]
            [midje.sweet :refer :all]
            [girajira.handler :refer :all]))

(fact "responds to /"
  (let [response (app (mock/request :get "/"))]
    (:body response) => "Hello github"
    (:status response) => 200))

(fact "invalid route responds 404"
  (:status (app (mock/request :get "/invalid"))) => 404)
