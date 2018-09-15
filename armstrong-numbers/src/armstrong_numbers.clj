(ns armstrong-numbers)

(defn num->digits
  ([num] (num->digits [] num))
  ([acc num]
   (if (< num 10)
     (cons num acc)
     (recur (cons (mod num 10) acc) (quot num 10)))))

(defn long-pow [base exp]
  (if (= 0 exp)
    1
    (nth (iterate (partial * base) base) (dec exp))))

(defn armstrong? [num] ;; <- arglist goes here
  (let [digits (num->digits num)]
    (= num (reduce + (map #(long-pow %1 (count digits)) digits)))))



(comment
  (num->digits 1234)
  (def digits (num->digits 21897142587612075))
  (map #(Math/pow %1 (count digits)) digits)
  (reduce + (map #(long (Math/pow %1 (count digits))) digits))
  (long (reduce + (map #(Math/pow %1 (count digits)) digits)))
  (int 0.9)
  (long-pow 9 7))
