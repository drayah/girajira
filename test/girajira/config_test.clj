(ns girajira.config_test
  (:require [midje.sweet :refer :all]
            [girajira.config :refer :all]
            [aero.core :as aero]))

(facts "when reading secret aero configuration"
  (fact "it delegates reading of the file to aero"
    (secrets) => :file-read
    (provided
      (aero/read-config "config/.config.edn") => :file-read)))
