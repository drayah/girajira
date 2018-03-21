(ns girajira.integrations.jira.transitions-test
  (:require [midje.sweet :refer :all]
            [girajira.integrations.jira.transitions :refer :all]
            [girajira.integrations.jira.request :as request]))

(facts "when getting the jira api transitions url given an issue-id"
  (fact "it returns the correct api url"
    (transitions-url "fake-issue-id") => "http://jira.api/issue/fake-issue-id/transitions"
    (provided
      (request/jira-api-url) => "http://jira.api")))

(facts "when getting the possible transitions given some issue-id"
  (fact "it performs a jira api authenticated get request and returns json response data"
    (let [fake-url "http://jira.api/issues"]
      (get-transitions ..issue-id..) => ..json-response..
      (provided
        (transitions-url ..issue-id..) => fake-url
        (request/authenticated-get fake-url) => ..json-response..))))

(facts "when posting a payload to the transitions api given some issue-id"
  (fact "it performs a jira api authenticated post request"
    (let [fake-body {"fake" "json data"}
          fake-url "http://jira.api"]
      (post-transitions ..issue-id.. fake-body) => ..json-response..
      (provided
        (transitions-url ..issue-id..) => fake-url
        (request/authenticated-post fake-url fake-body) => ..json-response..))))

(facts "when extracting the possible issue transitions from the json response"
  (fact "it returns the value of the \"transitions\" key"
    (let [api-response {"expand" "transitions"
                        "transitions" ..transitions-data..}]
      (issue-transitions api-response) => ..transitions-data..)))

(facts "when extracting available (kanban) columns given transitions data"
  (fact "it returns a sequence of column name and column id pairs"
    (let [transitions [{"id" "1" "name" "Todo"}
                       {"id" "2" "name" "Doing"}
                       {"id" "3" "name" "Code Review"}]]
      (extract-kanban-columns transitions) => [{"Todo" "1"} {"Doing" "2"} {"Code Review" "3"}])))

(facts "when getting (kanban) columns for a specific issue-id"
  (fact "it calls the correct functions in order to extract the data"
    (kanban-columns-for-issue ..issue-id..) => ..extracted-column-data..
    (provided
      (get-transitions ..issue-id..) => ..json-response..
      (issue-transitions ..json-response..) => ..transitions-data..
      (extract-kanban-columns ..transitions-data..) => ..extracted-column-data..)))

(facts "when checking a (kanban) column against a given name"
  (let [column {"Todo" "14"}]
    (facts "and the names match"
      (fact "it returns true"
        (kanban-column-with-name? "TODO" column) => truthy
        (kanban-column-with-name? "todo" column) => truthy
        (kanban-column-with-name? "ToDo" column) => truthy))

    (facts "and the names don't match"
      (fact "it returns false"
        (kanban-column-with-name? "bla" column) => falsey))))
