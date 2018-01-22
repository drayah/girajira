(ns girajira.api.github.move-jira-issue
  (:require [girajira.jira.transitions :as jira]))

(defn- move-to-column
  [issue column]
  (jira/move-issue-to-kanban-column issue column))

(defn- opened?
  [action]
  (= action "opened"))

(defn- merged?
  [action]
  (= action "merged"))

(defn move-issue
  [movement]
  (let [action (:action movement)
        issue (:issue movement)
        open-column (get-in movement [:columns :open])
        merge-column (get-in movement [:columns :merge])]
    (cond
      (opened? action) (move-to-column issue open-column)
      (merged? action) (move-to-column issue merge-column))))
