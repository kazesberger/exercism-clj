(ns clock
  (:require [clojure.string :as str]))

(defrecord Clock [hours minutes])

(defn clock->string [clock] ;; <- arglist goes here
  (str/join \: (map (comp
                      #(apply str (take-last 2 (cons \0 (seq (str %)))))
                      #(get % 1))
                    clock)))

(defn clock [hours minutes] ;; <- arglist goes here
  (let [time (mod (+ (* hours 60) minutes) (* 60 24))
        h (quot time 60)
        min (mod time 60)]
    (map->Clock {:hours h :minutes min})))

(defn add-time [{:keys [hours minutes] :as c} time] ;; <- arglist goes here
  (clock hours (+ minutes time)))
