(ns collatz-conjecture)

(defn collatz
  ([num]
   (when (not (pos? num)) (throw (Throwable. "Number needs to be a positive integer.")))
   (collatz 0 num))
  ([i num]
   (println i num)
   (if (= num 1)
     i
     (if (even? num)
        (recur (inc i) (/ num 2))
        (recur (inc i) (inc (* num 3)))))))
