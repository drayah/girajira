(ns girajira.config_test
  (:require [midje.sweet :refer :all]
            [girajira.config :refer :all]
            [aero.core :as aero]))

(facts "when reading the environment configuration"
  (facts "and the configuration is the secret configuration"
    (fact "it delegates reading of the file to aero"
      (secrets) => ..config-read..
      (provided
        (aero/read-config "config/.config.edn") => ..config-read..)))

  (facts "and the configuration is the webserver configuration"
    (fact "it returns a map containing a :port key and port number value"
      ((webserver) :port) =not=> nil)))
