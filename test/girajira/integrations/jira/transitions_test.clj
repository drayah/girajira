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

(facts "when getting the (kanban) column id given an issue id and column name"
  (fact "it calls the correct functions in order to get the column id"
    (kanban-column-id-by-issue ..issue-id.. ..column-name..) => ..column-id..
    (provided
      (kanban-columns-for-issue ..issue-id..) => ..kanban-columns..
      (filter-kanban-columns-by ..column-name.. ..kanban-columns..) => ..column-data..
      (kanban-column-id ..column-data..) => ..column-id..)))

(facts "when moving an issue to a (kanban) column"
  (fact "it calls the correct functions in order to perform a POST request"
    (move-issue-to-kanban-column ..issue-id.. ..column-name..) => ..json-response..
    (provided
      (kanban-column-id-by-issue ..issue-id.. ..column-name..) => ..column-id..
      (issue-transition-body ..column-id..) => ..payload..
      (post-transitions ..issue-id.. ..payload..) => ..json-response..)))

(let [column {"Todo" "14"}]
  (facts "when checking a (kanban) column against a given name"
    (facts "and the names match"
      (fact "it returns true"
        (kanban-column-with-name? "TODO" column) => truthy
        (kanban-column-with-name? "todo" column) => truthy
        (kanban-column-with-name? "ToDo" column) => truthy))

    (facts "and the names don't match"
      (fact "it returns false"
        (kanban-column-with-name? "bla" column) => falsey))))

(let [columns [{"Todo" "1"} {"Doing" "2"} {"Code Review" "3"}]]
  (facts "when filtering (kanban) columns by name"
    (facts "and there is no column with given name"
      (fact "it returns nil"
        (filter-kanban-columns-by "invalid" columns) => nil))

    (facts "and there is a column with given name"
      (fact "it returns the column"
        (filter-kanban-columns-by "doing" columns) => {"Doing" "2"}))))

(facts "when getting the (kanban) column id"
  (fact "it returns the correct value"
    (let [column {"Doing" "14"}]
      (kanban-column-id column) => "14")))

(facts "when creating a transitions POST payload"
  (fact "it returns a map to be used as the POST body"
    (let [column-id "15"]
      (issue-transition-body column-id) => {"transition" {"id" "15"}})))
