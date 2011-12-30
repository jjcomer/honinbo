(ns honinbo.gamelogic
  (:use [clojure.set :only [union]]))

(defn neighbours
  "This private function will return the valid neighbour of a given board
   co-ordinate"
  [size yx]
  (let [deltas [[-1 0] [1 0] [0 -1] [0 1]]]
    (filter (fn [new-yx]
              (every? #(< -1 % size) new-yx))
            (map #(map + yx %) deltas))))

(defn get-opp-colour
  [colour]
  (case colour
    :white :black
    :black :white
    :empty))

(defn only-match-neighbours
  "Only get the neighbours which match yx's colour"
  [yx size board]
  (let [colour (get-in board yx)]
    (filter (fn [yx]
              (let [c (get-in board yx)]
                (= colour c)))
            (neighbours size yx))))

(defn gather-group
  [yx size board]
  (let [colour (get-in board yx)
        opp-colour (get-opp-colour colour)]
    (loop [group #{} to-visit [yx] board board]
      (let [visit (peek to-visit)
            visit-colour (get-in board (peek to-visit))]
        (cond
         (nil? visit) group
         (= :visited visit-colour) (recur group (pop to-visit) board)
         (= opp-colour visit-colour) (recur group (pop to-visit) board)
         :else
         (recur (conj group (into [] visit))
                (apply conj (pop to-visit) (neighbours size visit))
                (assoc-in board visit :visited)))))))

(defn determine-capture
  "Given the latest move location, this function will determine which
   locations have been captured"
  [yx size board]
  (let [colour (get-opp-colour (get-in board yx))
        n (filter #(= colour (get-in board %)) (neighbours size yx))]
    (apply union
           (filter (fn [locs] (every? #(= colour (get-in board %)) locs))
                   (map #(gather-group % size board) n)))))