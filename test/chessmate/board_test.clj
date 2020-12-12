(ns chessmate.board-test
  (:require [chessmate.board :as sut]
            [chessmate.piece :as piece]
            [chessmate.pos :as pos]
            [clojure.test :refer [deftest testing is]])
  (:import [chessmate.piece Rook Pawn]))

(deftest walk-test
  (let [rook (Rook. :white)
        pos (pos/make 1 7)
        enemy-pos (pos/make 1 5)
        board {pos rook
               (pos/make 5 7) (Pawn. :white)
               enemy-pos (Pawn. :black)}
        walked (sut/walk board :white (piece/paths rook pos))]
    (testing "walk"
      (testing "returns possible move positions"
        (is (every?
             (partial contains? walked)
             [(pos/make 2 7) (pos/make 3 7) (pos/make 4 7)
              (pos/make 1 6)])))
      (testing "returns possible attack positions"
        (is (contains? walked enemy-pos))))))
