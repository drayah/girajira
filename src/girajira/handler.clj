(ns girajira.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clj-http.client :as client]
            [girajira.github.hook :as github]
            [girajira.jira.request :as jira]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] (github/hello))
  (POST "/pull-request" [payload] (str payload))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes
    (assoc-in site-defaults [:security :anti-forgery] false)))
