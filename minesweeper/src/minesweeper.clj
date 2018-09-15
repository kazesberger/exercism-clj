(ns minesweeper
  (:require [clojure.string :as str]))

(def line-separator (System/getProperty "line.separator"))

(defn measure-board [board]
  {:width (str/index-of board line-separator)
   :height (inc (count (re-seq #"\n" board)))})

;; at least 2 approaches:

;; 1) for each non-mine field get surrounding values, count mines and set the number
;; 2) for each mine increment its surrounding non-mine field value by 1
;; either ways, i'd like to work with 2-dim vector structure to utilize update-in and get-in.

;; meh, this feels very imperative
;; into w/ xform ?

;; mb i could use or-coupled regexes of exact neighbor positions use to check for mines and just count the hits.
;; TODO do some regex katas




(defn top? [index {:keys [width]}]
  (< index width))
(defn left? [index {:keys [width]}]
  (= (mod (+ width index) width) 0))
(defn right? [index {:keys [width]}]
  (= (mod (+ width index) width) (dec width)))
(defn bottom? [index {:keys [width height]}]
  (>= index (- (* width height) width)))


;; FIXME border positions
(defn neighbor-indizes [index {:keys [width height] :as boardsize}]

  (let [top-nb     {:id :top :apply? (not (top? index boardsize)) :coll (map #(- % width) [(dec index) index (inc index)])}
        left-nb    {:id :left :apply? (not (left? index boardsize)) :coll [(- (dec index) width)  (dec index) (+ (dec index) width)]}
        right-nb   {:id :right :apply? (not (right? index boardsize)) :coll [(- (inc index) width)  (inc index) (+ (inc index) width)]}
        bottom-nb  {:id :bot :apply? (not (bottom? index boardsize)) :coll (map #(+ % width) [(dec index) index (inc index)])}
        applyable (filter #(get % :apply?) [top-nb left-nb right-nb bottom-nb])
        remove-these (remove #(get % :apply?) [top-nb left-nb right-nb bottom-nb])
        valid-nb      (remove (into #{} (mapcat :coll remove-these)) (into #{} (mapcat :coll applyable)))]
    ;(println applyable)
    ;(println remove-these)
    valid-nb))

(sort (neighbor-indizes 8 (measure-board "   \n   \n   ")))


(defn count-adjacent-mines [index board]
  (count (filter #{\*}
          (map
              nth
              (repeat (remove #{\newline} board))
              (neighbor-indizes index (measure-board board))))))

(defn transposed-board-char [board index field]
  (println \newline board index field)
  (if (= field \*)
    field
    (count-adjacent-mines index board)))

(if (some #{1 2 3} #{4 5 6}) (println true) (println false))

(defn draw [board] ;; <- arglist goes here
  (map-indexed #(transposed-board-char board %1 %2) (remove #{\newline} board)))

(draw "***\n*x*\nxxx")
(draw (str/join line-separator [" " "*" " " "*" " "]))
(str 5)

(comment
  (defn check-position [index {:keys [width height] :as boardsize}]
    [(top? index boardsize)
     (left? index boardsize)
     (right? index boardsize)
     (bottom? index boardsize)]

    (check-position 8 {:width 3 :height 3})))


(comment
  (map println (repeat [5 7 10]) [0 1 2])
  (map get (repeat [5 7 10]) [0])
  (get [0 1 2] 1))

(comment
  (map
    println
    (repeat 9 (remove #{\newline} "   \n   \n   "))
    (neighbor-indizes 4 (:height (measure-board "   \n   \n   "))))

  (count-adjacent-mines 4 "   \n* *\n   ")

    ; (map-indexed)

  (remove #{\newline} "asdf\nqwer")
  (mapcat nth (take 3 (repeat "0123*56789")) [3 4 5])
  (map nth (take 3 (repeat "0123*56789")) [3 4 5])
  (neighbor-indizes 5 3)
  (nth "0123*56789" 3)
  (filter identity (map #{\*} "0123*567")))  (some #{3} (range 5))
  (map #{3} (range 5))
  ;;
  (map-indexed #(or (% #{\*} "   * * ***")))
  (re-seq #"(^.{2}\*)|(^.{3}\*)" "  **     ")
  (re-seq #"(^.{2}\*.*$)|(^.{3}\*.*$)" "****       ")
  (measure-board (str/join line-separator [" "
                                           "*"
                                           " "
                                           "*"
                                           " "]))
  (get "asdf" 0)
  (str/index-of (str/join ["012" line-separator "345" line-separator "678"]) line-separator)
  (re-seq #"\n" (str/join ["012" line-separator "345" line-separator "678"]))
  (vec "asdf")
