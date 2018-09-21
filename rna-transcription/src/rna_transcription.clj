(ns rna-transcription)

(def DNA->RNA-Dict {\G \C
                    \C \G
                    \T \A
                    \A \U})

(defn to-rna [dna] ;; <- arglist goes here
  (if (seq (remove (set (keys DNA->RNA-Dict)) dna))
    (throw (AssertionError.))
    (apply str (map DNA->RNA-Dict dna))))
