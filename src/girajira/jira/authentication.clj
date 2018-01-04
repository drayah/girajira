(ns girajira.jira.authentication
  (:require [girajira.config :as config]))

(defn- base-url
  [secrets]
  (:jira-url secrets))

(defn- username
  [secrets]
  (:jira-user secrets))

(defn- password
  [secrets]
  (:jira-pass secrets))

(defn url
  []
  (base-url (config/secrets)))

(defn basic-auth-params
  []
  (let [secrets (config/secrets)]
    [(username secrets) (password secrets)]))
