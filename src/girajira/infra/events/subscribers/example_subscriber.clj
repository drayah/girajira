(ns girajira.infra.events.subscribers.example-subscriber)

(def console-write! (partial println))

(defn subscriber [event]
  (console-write! (str "Subscriber performing work for event: " event)))
