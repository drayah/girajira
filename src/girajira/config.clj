(ns girajira.config
  (:require [aero.core :as aero]))

(defn secrets []
  (aero/read-config "config/.config.edn"))

(defn webserver []
  (aero/read-config "config/.webserver.edn"))
