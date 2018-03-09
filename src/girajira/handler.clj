(ns girajira.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clj-http.client :as client]
            [ring.util.response :refer [resource-response response]]
            [ring.middleware.json :as middleware]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [girajira.api.github.post :as github-post]
            [girajira.infra.events.pubsub :as pubsub]))

(defroutes app-routes
  (GET "/" [] "Girajira ;)")
  (GET "/test-json-response" [] (response {:fulano "test"}))
  (POST "/api/github/pull-request" {request-body :body} (github-post/handle request-body))
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)
      (wrap-defaults api-defaults)))
