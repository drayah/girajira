(ns girajira.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clj-http.client :as client]
            [girajira.github.hook :as github]
            [girajira.jira.request :as jira]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] (github/pull-request))
  (GET "/jira" [] (jira/issues))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
