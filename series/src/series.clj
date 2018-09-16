(ns series)

(defn slices [string length] ;; <- arglist goes here
  (cond
    (= 0 length) [""]
    ;(< (count string) length) []
    :default (map #(apply str %) (partition length 1 string))))

(partition 10 "123")