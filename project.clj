(defproject girajira "0.1.0-SNAPSHOT"
  :description "Girajira - github and jira integration for development process automation"
  :url "https://github.com/oborba/girajira"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/core.async "0.4.474"]
                 [compojure "1.5.1"]
                 [clj-http "3.7.0"]
                 [clj-http-fake "1.0.3"]
                 [clj-time "0.14.2"]
                 [cheshire "5.8.0"]
                 [aero "1.1.2"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-json "0.5.0-beta1"]
                 [ring/ring-defaults "0.2.1"]
                 [metosin/ring-http-response "0.9.0"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-cloverage "1.0.10"]]
  :ring {:handler girajira.handler/app
         :init girajira.infra.events.initialize/initialize-subscribers}
  :resource-paths ["config"]
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]
                        [midje "1.9.1"]]}})
