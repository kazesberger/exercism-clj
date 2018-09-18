(ns nucleotide-count
  (:refer-clojure :exclude [count]))

(def sample-strand (seq "ATCGATCGATCGAAAX"))
(def nucleotides (set "ATCG"))

(defn count [nucleotide strand] ;; <- Arglist goes here
  (println nucleotide strand)
  (if (some #{nucleotide} nucleotides)
    (clojure.core/count (filter #{nucleotide} strand))
    (throw (IllegalArgumentException. (str "Only allowed nucleotides are: " nucleotides)))))


(defn nucleotide-counts [strand] ;; <- Arglist goes here
  (println (set strand))
  (reduce #(assoc %1 %2 (count %2 strand)) {\A 0, \T 0, \C 0, \G 0} (set strand)))

;(nucleotide-counts sample-strand)