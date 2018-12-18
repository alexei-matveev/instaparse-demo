(ns parse-demo.core
  (:require [instaparse.core :as insta])
  (:gen-class))

(def as-and-bs
  (insta/parser
   "S = AB*
    AB = A B
    A = 'a'+
    B = 'b'+"))

(defn -main
  "I don't do a whole lot ... yet."
  [input & more]
  (println "Hello, World!")
  (prn (as-and-bs input)))
