(ns phone-number)

; my first attempt to make use of defrecord.
; kinda try-hard-apply this - bet there are way better solutions to this puz

(defrecord Phonenumber [country area exchange subscriber])

(defn phonenumber [string]
  (let [digits (filter (set (map char (range 48 58))) (seq string))
        country (when (= (count digits) 11) (first digits))
        digits-no-country (cond
                            (< (count digits) 10) (apply str (repeat 10 \0))
                            (and (= (count digits) 11) (not (= \1 (first digits)))) (apply str (repeat 10 \0))
                            (> (count digits) 11) (apply str (repeat 10 \0))
                            :default (take-last 10 digits))
        area (take 3 digits-no-country)
        exchange (take 3 (drop 3 digits-no-country))
        subscriber (take-last 4 digits-no-country)]
    (println digits)
    (println country)
    (println digits-no-country)
    (println area)
    (println exchange)
    (println subscriber)
    (map->Phonenumber {:country country
                       :area area
                       :exchange exchange
                       :subscriber subscriber})))

(defn number [num-string] ;; <- arglist goes here
    (let [pn (phonenumber num-string)]
      (apply str
        (concat
          (:area pn)
          (:exchange pn)
          (:subscriber pn)))))


(defn area-code [num-string] ;; <- arglist goes here
  (let [pn (phonenumber num-string)]
    (apply str (:area pn))))


(defn pretty-print [num-string] ;; <- arglist goes here
  (let [pn (phonenumber num-string)]
    (apply str (concat "(" (:area pn) ") " (:exchange pn) "-" (:subscriber pn)))))