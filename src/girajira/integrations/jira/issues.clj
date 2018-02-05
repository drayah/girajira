(ns girajira.integrations.jira.issues
  (:require [girajira.integrations.request :as request]))

(defn- issues-url
  [project-name]
  (str "url " project-name))
