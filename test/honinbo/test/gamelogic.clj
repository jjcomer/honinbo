(ns honinbo.test.gamelogic
  (:use [honinbo.gamelogic]
        [clojure.math.combinatorics]
        [clojure.set :only [difference]])
  (:use [clojure.test]))


(defn- convert-row
  [row]
  (into [] (map (fn [item]
                  (case item
                    0 :empty
                    1 :white
                    2 :black)) row)))

(defn convert-board
  "This function takes the numeric representation of the board
   and converts it into the symbol version"
  [board]
  (into [] (map convert-row board)))

(def empty-board
  (convert-board
   [[0 0 0 0 0 0 0 0 0]
    [0 0 0 0 0 0 0 0 0]
    [0 0 0 0 0 0 0 0 0]
    [0 0 0 0 0 0 0 0 0]
    [0 0 0 0 0 0 0 0 0]
    [0 0 0 0 0 0 0 0 0]
    [0 0 0 0 0 0 0 0 0]
    [0 0 0 0 0 0 0 0 0]
    [0 0 0 0 0 0 0 0 0]]))

(def simple-capture-board
  (convert-board
   [[0 0 0 0 0 0 0 0 0]
    [0 0 0 0 0 0 0 0 0]
    [0 0 0 0 1 1 0 0 0]
    [0 0 0 1 2 2 1 0 0]
    [0 0 0 1 2 2 1 0 0]
    [0 0 0 0 1 2 1 0 0]
    [0 0 0 0 0 1 0 0 0]
    [0 0 0 0 0 0 0 0 0]
    [0 0 0 0 0 0 0 0 0]]))

(def double-capture-board
  (convert-board
   [[0 0 0 0 0 0 0 0 0]
    [0 0 0 1 0 0 0 0 0]
    [0 0 1 2 1 1 0 0 0]
    [0 0 0 1 2 2 1 0 0]
    [0 0 0 1 2 2 1 0 0]
    [0 0 0 0 1 2 1 0 0]
    [0 0 0 0 0 1 0 0 0]
    [0 0 0 0 0 0 0 0 0]
    [0 0 0 0 0 0 0 0 0]]))

(def no-capture-board
  (convert-board
   [[0 0 0 0 0 0 0 0 0]
    [0 0 0 0 0 0 0 0 0]
    [0 0 0 0 1 1 0 0 0]
    [0 0 0 1 2 2 1 0 0]
    [0 0 0 1 2 0 1 0 0]
    [0 0 0 0 1 2 1 0 0]
    [0 0 0 0 0 1 0 0 0]
    [0 0 0 0 0 0 0 0 0]
    [0 0 0 0 0 0 0 0 0]]))

(deftest test-capture
  (is (= #{} (determine-capture [3 3] 9 no-capture-board)))
  (is (= #{[4 4] [5 5] [3 4] [4 5] [3 5]}
         (determine-capture [3 3] 9 simple-capture-board)))
  (is (= #{[4 4] [5 5] [2 3] [3 4] [4 5] [3 5]}
         (determine-capture [3 3] 9 double-capture-board))))

(deftest test-opp-colour
  (is (= :black (get-opp-colour :white)))
  (is (= :white (get-opp-colour :black)))
  (is (= :empty (get-opp-colour :empty)))
  (is (= :empty (get-opp-colour :foobar)) "This will probably never happen"))
