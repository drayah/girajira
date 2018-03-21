(ns girajira.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clj-http.client :as client]
            [ring.util.response :refer [resource-response response]]
            [ring.middleware.json :as middleware]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.adapter.jetty :refer [run-jetty]]
            [girajira.config :as config]
            [girajira.api.github.post :as github-post]
            [girajira.infra.events.pubsub :as pubsub])
  (:gen-class))

(defroutes app-routes
  (GET "/" [] "Girajira ;)")
  (GET "/test-json-response" [] (response {:fulano "test"}))
  (context "/api" []
           (POST "/github/pull-request" {request-body :body} (github-post/handle request-body)))
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)
      (wrap-defaults api-defaults)))

(def port
  (let [port-number ((config/webserver) :port)]
    {:port (Integer. port-number)}))

(defn -main []
  (run-jetty app port))
