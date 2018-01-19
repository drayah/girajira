(ns girajira.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clj-http.client :as client]
            [girajira.github.hook :as github]
            [girajira.jira.request :as jira]
            [ring.middleware.json :as middleware]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] (github/hello))
  (POST "/pull-request" [payload] (github/pull-request payload))
  (route/not-found "Not Found"))


(def app
  (-> app-routes
      (middleware/wrap-json-body)))
;
;(def app)
;  (wrap-json-body app-routes
;    (assoc-in site-defaults [:security :anti-forgery] false)))
