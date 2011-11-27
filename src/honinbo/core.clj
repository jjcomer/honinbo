;; #Honinbo
;; A multi-platform Go client written in Clojure
;;
(ns honinbo.core
  (:use [seesaw.core]))

(defn -main
  "This is the main entry point for launching honinbo **place holder**"
  [ & args ]
  (-> (frame :title "hoinibo"
             :on-close :exit
             :content "Hello World!")
      pack!
      show!))