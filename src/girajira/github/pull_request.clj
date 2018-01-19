(ns girajira.github.pull-request
  (:require [clojure.walk :as walk]
            [ring.util.http-response :as response]))

(defn- pull-request-data
  [body]
  (let [filtered-data {}]
    (->
      filtered-data
      (assoc :action (:action body))
      (assoc :body (get-in body [:pull_request :body]))
      (assoc :target (get-in body [:pull_request :base :ref]))
      (assoc :user (get-in body [:pull_request :user :login])))))

(defn pull-request
  [request-body]
  (let [body-with-keywords (walk/keywordize-keys request-body)
        data (pull-request-data body-with-keywords)]
    (response/ok data)))
