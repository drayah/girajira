(ns girajira.infra.events.datetime
  (:require [clj-time.core :as clj-time]
            [clj-time.format :as date-format]))

(def formatter (date-format/formatters :date-hour-minute-second-ms))

(defn today []
  (let [utc-time (clj-time/now)]
    (date-format/unparse formatter utc-time)))
