(ns girajira.infra.events.datetime-test
  (:require [midje.sweet :refer :all]
            [girajira.infra.events.datetime :refer :all]
            [clj-time.core :as clj-time]))

(facts "when calculating today's date"
  (fact "it returns a string representation of today's date"
    (today) => "2018-12-01T14:00:00.000"
    (provided
      (clj-time/now) => (clj-time/date-time 2018 12 01 14 0 0))))
