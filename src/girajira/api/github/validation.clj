(ns girajira.api.github.validation
  (:require [clojure.string :as string]))

(def tag "girajira:")
(def number-of-tag-parts 4)

(defn- required-keys?
  [pull-request-data]
  (and
    (contains? pull-request-data :action)
    (contains? pull-request-data :body)
    (contains? pull-request-data :target)
    (contains? pull-request-data :user)))

(defn- valid-action?
  [action]
  (or
    (= action "opened")
    (= action "merged")))

(defn- girajira-tag
  [body]
  (->>
    (string/split body #" ")
    (map #(string/trim %))
    (filter #(string/includes? % tag))
    (first)))

(defn- valid-tag-format?
  [tag]
  (let [tag-parts (string/split tag #":")]
    (and
      (= (count tag-parts) number-of-tag-parts)
      (every? #(> (count %) 0) tag-parts))))

(defn- valid-tag?
  [body]
  (let [tag (girajira-tag body)]
    (if (nil? tag)
      false
      (valid-tag-format? tag))))

(defn valid-pull-request?
  [pull-request-data]
  (and
    (required-keys? pull-request-data)
    (valid-action? (:action pull-request-data))
    (valid-tag? (:body pull-request-data))))
