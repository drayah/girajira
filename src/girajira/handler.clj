(ns girajira.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [girajira.github.hook :as github]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] (github/pull-request))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
