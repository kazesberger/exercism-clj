(ns minesweeper
  (:require [clojure.string :as str]))

(def sample1-board (str/join \newline
                             ["       "
                              "       "
                              "       "]))

(defn str->2dVec [board-str]
  (mapv vec (str/split-lines board-str)))

(def sample1-board-vec (str->2dVec sample1-board))


(defn draw [board]
  (let [board-vec (str->2dVec board)
        minecounts (for [x (range (count board-vec))]
                     (for [y (range (count (first board-vec)))]
                       (if (= \* (get-in board-vec [x y]))
                         \*
                         (let [c (count (filter #{\*}
                                                (for [dx [-1 0 1]
                                                      dy [-1 0 1]]
                                                  (get-in board-vec [(+ x dx) (+ y dy)]))))]
                           (if (= c 0) \space c)))))]

    (str/join \newline (map str/join minecounts))))



(map inc (for [num (range 10)]
           num))

(println (draw sample1-board))