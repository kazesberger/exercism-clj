(ns hamming)

(defn distance [strand1 strand2] ; <- arglist goes here
  (when (apply = (map count [strand1 strand2]))
    (count (remove #{0} (map #(- (int %1) (int %2)) strand1 strand2)))))