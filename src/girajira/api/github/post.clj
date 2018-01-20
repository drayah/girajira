(ns girajira.api.github.post
  (:require [clojure.walk :as walk]
            [girajira.api.github.validation :as validation]
            [girajira.api.github.representation :as representation]))
            ;[girajira.api.github.move-jira-issue]

(defn keywordize-request-body
  [request-body]
  (walk/keywordize-keys request-body))

(defn pull-request-data
  [body]
  (let [filtered-data {}]
    (->
      filtered-data
      (assoc :action (get-in body [:action]))
      (assoc :body (get-in body [:pull_request :body]))
      (assoc :target (get-in body [:pull_request :base :ref]))
      (assoc :user (get-in body [:pull_request :user :login])))))

(defn handle
  [request-body]
  (let [pull-request (pull-request-data (keywordize-request-body request-body))]
    (if (validation/valid-pull-request? pull-request) (println "perform jira!"))
    (representation/no-content)))
