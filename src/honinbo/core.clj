;; #Honinbo
;; A multi-platform Go client written in Clojure
;;
(ns honinbo.core
  (:use [seesaw.core]))

(defn build-board
  "This function will add the game board to the UI

  _size_: The size of the game board. Can be 9, 13, or 19

  **TODO** This function still needs to be written"
  [size]
  :true)

(defn build-controls
  "This function will create the non board elements of the UI
   **TODO** This function still needs to be written"
  [ ]
  :true )

(defn build-ui
  "Build the main ui frame."
  [ ]
  (frame :title "honinbo"
         :on-close :exit
         :content (border-panel :center (build-board)
                                :south (build-controls))))

(defn -main
  "This is the main entry point for launching honinbo **place holder**"
  [ & args ]
  (-> (build-ui)
      pack!
      show!))