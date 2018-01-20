(ns girajira.api.github.move-jira-issue-test
  (:require [midje.sweet :refer :all]
            [girajira.api.github.move-jira-issue :refer :all]
            [girajira.jira.transitions :as jira]))

(def opened-pull-request-move-data {:action "opened"
                                    :issue "ca-123"
                                    :columns {:open "doing done"
                                              :merge "done"}})

(def merged-pull-request-move-data {:action "merged"
                                    :issue "ca-123"
                                    :columns {:open "doing done"
                                              :merge "done"}})

(facts "when moving an issue for an opened pull request"
  (fact "it moves the issue to the open column on jira"
    (move-issue opened-pull-request-move-data) => :ok
    (provided
      (jira/move-issue-to-kanban-column "ca-123" "doing done") => :ok)))

(facts "when moving an issue for a merged pull request"
  (fact "it moves the issues to the merged column on jira"
    (move-issue merged-pull-request-move-data) => :ok
    (provided
      (jira/move-issue-to-kanban-column "ca-123" "done") => :ok)))
