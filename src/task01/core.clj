(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:gen-class))

; don't see how to check the name of the element is possible in this situation
;(defn isLinkTag? [arg1]
;  (and (vector? arg1) (= (first arg1) :a)))

(defn extractLinksFromVector [ vect ] ;vector represents an html tag
  (let [attrMap (second vect)] ; attribute map
    (if (empty? vect)
      []
      ;check if a "class" attribute with the value "r" exist 
      (if  (= (get attrMap :class) "r")
        ;true
        ;seek for a link tag (presumably one, the first children tag) and return "href" attribute
        [(get (second (get vect 2)) :href)]
        ;false
        (reduce into [] (concat (map extractLinksFromVector  (filter vector? vect) )))
      );end if
    );end if
  );end let
);end defn

(defn get-links []
" 1) Find all elements containing {:class \"r\"}.

Example:
[:h3 {:class \"r\"} [:a {:shape \"rect\", :class \"l\",
                         :href \"https://github.com/clojure/clojure\",
                         :onmousedown \"return rwt(this,'','','','4','AFQjCNFlSngH8Q4cB8TMqb710dD6ZkDSJg','','0CFYQFjAD','','',event)\"}
                     [:em {} \"clojure\"] \"/\" [:em {} \"clojure\"] \" · GitHub\"]]

   2) Extract href from the element :a.

The link from the example above is 'https://github.com/clojure/clojure'.

  3) Return vector of all 10 links.

Example: ['https://github.com/clojure/clojure', 'http://clojure.com/', . . .]
"
  (let [data (parse "clojure_google.html") result []]
    (extractLinksFromVector data)))

(defn -main []
  (println (str "Found " (count (get-links)) " links!")))


