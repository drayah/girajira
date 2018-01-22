(ns girajira.api.github.representation
  (:require [ring.util.http-response :as response]))

(defn no-content []
  (response/no-content))
