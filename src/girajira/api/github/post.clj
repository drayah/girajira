(ns girajira.api.github.post
  (:require [clojure.walk :as walk]
            [girajira.api.github.validation :as validation]
            [girajira.api.github.representation :as representation]
            [girajira.infra.events.pubsub :as pubsub]
            [girajira.infra.events.definitions :as events]))

(defn keywordize-request-body
  [request-body]
  (walk/keywordize-keys request-body))

(defn- action-from-pull-request
  [action merged?]
  (if (and
        (= action "closed")
        (true? merged?))
    "merged"
    action))

(defn pull-request-data
  [body]
  (let [filtered-data {}
        action (get-in body [:action])
        merged-pull-request (get-in body [:pull_request :merged])]
    (->
      filtered-data
      (assoc :action (action-from-pull-request action merged-pull-request))
      (assoc :body (get-in body [:pull_request :body]))
      (assoc :target (get-in body [:pull_request :base :ref]))
      (assoc :user (get-in body [:pull_request :user :login])))))

(defn- move-issue-data
  [pull-request]
  (let [move-data {}
        tag-data (validation/girajira-tag-data (:body pull-request))]
    (->
      move-data
      (assoc :action (:action pull-request))
      (assoc :issue (:issue tag-data))
      (assoc :columns {:open (:open tag-data)
                       :merge (:merge tag-data)}))))

(defn handle
  [request-body]
  (let [pull-request (pull-request-data (keywordize-request-body request-body))]
    (do
     (if (validation/valid-pull-request? pull-request)
       (pubsub/publish
         events/github-pull-request-received
         (move-issue-data pull-request)))
     (representation/no-content))))
