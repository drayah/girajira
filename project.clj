(defproject girajira "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [compojure "1.5.1"]
                 [clj-http "3.7.0"]
                 [cheshire "5.8.0"]
                 [aero "1.1.2"]
                 [ring/ring-defaults "0.2.1"]
                 [clj-http-fake "1.0.3"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-cloverage "1.0.10"]]
  :ring {:handler girajira.handler/app}
  :resource-paths ["config"]
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]
                        [midje "1.9.1"]]}})
