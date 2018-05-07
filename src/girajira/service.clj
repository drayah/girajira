(ns girajira.service
  (:require [compojure.core :refer :all]))

(defn handle
  [request handler]
  (->> (http/deserialize request)
       (handler)))

(defn perform-github-pull-request
  [{body :body}]
  (controller/perform-github-pullrequest body))

(defroutes service-routes
  (GET "/" [] "Hey there")
  (POST "/api/github/pull-request"
        request (handle request peform-github-pull-request))
  (route/not-found "Not Found"))
