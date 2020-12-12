(ns chessmate.path-test
  (:require [chessmate.path :as sut]
            [chessmate.pos :as pos]
            [clojure.test :refer [deftest testing is]]))

(deftest path-test
  (let [path (sut/translate (pos/make 1 3) sut/NORTHWARD)]
    (testing "translate 'moves' the positions in a path"
      (is (= [(pos/make 1 2) (pos/make 1 1) (pos/make 1 0) (pos/make 1 -1)]
             (take 4 path))))
    (testing "take-on-board returns only the positions that are on the board"
      (is (= [(pos/make 1 2) (pos/make 1 1)]
             (sut/take-on-board path))))))
