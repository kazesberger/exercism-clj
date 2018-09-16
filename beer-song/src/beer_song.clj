(ns beer-song
  (:require [clojure.string :as str]))

; first unoptimized version

(defn bottles-of-beer-phrase [n]
  (case n
    1 (str n " bottle of beer")
    0 "no more bottles of beer"
    (str n " bottles of beer")))

(defn take-down-phrase [n]
  (case n
    1 "Take it down and pass it around, "
    0 "Go to the store and buy some more, "
    "Take one down and pass it around, "))

(defn verse
  "Returns the nth verse of the song."
  [num]
  (apply str
    (update-in
      (vec (concat (bottles-of-beer-phrase num) " on the wall, " (bottles-of-beer-phrase num) ".\n" (take-down-phrase num) (bottles-of-beer-phrase (if (< (dec num) 0) 99 (dec num))) " on the wall.\n"))
      [0]
      str/upper-case)))

(defn sing
  "Given a start and an optional end, returns all verses in this interval. If
  end is not given, the whole song from start is sung."
  ([start] (sing start 0))
  ([start end]
   (str/join \newline (map verse (reverse (range end (inc start)))))))

