# girajira

Girajira is a small clojure web app that automates Jira card transitions via opened github pull requests.

## Prerequisites

- JDK 8 or above
- [Leiningen 2.8.1](https://leiningen.org/) or above.

## Testing & Plugins

Girajira uses [Midje](https://github.com/marick/Midje) for automated tests. Add Midje's Leiningen plugin to your `~/.lein/profiles.clj` in order to be able to run the `lein midje` command.

Other recommended user plugins are:
- [CIDER nREPL](https://github.com/clojure-emacs/cider-nrepl)
- [Ultra](https://github.com/venantius/ultra)

A sample `~/.lein/profiles.clj`:
```clojure
{:user {:plugins [[cider/cider-nrepl "0.16.0"]
                  [venantius/ultra "0.5.2"]
                  [lein-midje "3.2.1"]]
        :dependencies [[org.clojure/tools.nrepl "0.2.13"]]}}
```
After having setup your `~/.lein/profiles.clj` you can enter the project directory and run `lein midje :autotest` in a new terminal in order to autorun tests whenever you edit and save your source files.

## Configuring Aero

Girajira uses [Aero](https://github.com/juxt/aero) for enviroment configuration. In order to be able to perform requests to Jira's API you need to setup a `.secrets.edn` configuration file in the projects `config` directory. This file will be ignored by `git`.

A sample `config/.secrets.edn`:
```clojure
{:jira
  {:url "https://YOUR_JIRA_URL"
   :username "JIRA_USERNAME"
   :password "JIRA_PASSWORD"}}
```

## Running

To start a web server for the application, run:

    lein ring server

## License

Copyright © 2017
