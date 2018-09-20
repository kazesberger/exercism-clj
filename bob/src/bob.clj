(ns bob
  (:require [clojure.string :as str]))

(defn yell? [s]
  (and (= s (str/upper-case s)) (not-empty (re-find #"[A-Z]" s))))
(defn yell-question? [s]
  (and (yell? s) (str/ends-with? s "?")))
(defn question? [s]
  (and (not (yell? s)) (str/ends-with? s "?")))
(defn silence? [s]
  (= 0 (count (str/trim s))))

(defn response-for [s] ;; <- arglist goes here
  (cond
    (yell-question? s) "Calm down, I know what I'm doing!"
    (question? s) "Sure."
    (yell? s) "Whoa, chill out!"
    (silence? s) "Fine. Be that way!"
    :default "Whatever."))

