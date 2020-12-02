(ns chessmate.piece-test
  (:require [chessmate.piece :as sut]
            [clojure.test :refer [deftest testing is]])
  (:import [chessmate.piece Pawn Rook Knight Bishop Queen King]))

(deftest pawn-test
  (let [w (Pawn. :white)
        b (Pawn. :black)]
    (is (= [(list [1 6])] (sut/paths w [1 7])))
    (is (= [(list [1 3])] (sut/paths b [1 2])))))

(deftest rook-test
  (let [piece (Rook. :white)]
    (is (= [(list [1 6] [1 5] [1 4] [1 3] [1 2] [1 1])
            (list [2 7] [3 7] [4 7] [5 7] [6 7] [7 7] [8 7])
            (list [1 8])]
         (sut/paths piece [1 7])))))

(deftest knight-test
  (let [piece (Knight. :black)]
    (is (= [(list [2 5])
            (list [3 6])
            (list [3 8])]
         (sut/paths piece [1 7])))))

(deftest bishop-test
  (let [piece (Bishop. :white)]
    (is (= [(list [2 6] [3 5] [4 4] [5 3] [6 2] [7 1])
            (list [2 8])]
           (sut/paths piece [1 7])))))

(deftest queen-test
  (let [piece (Queen. :black)]
    (is (= [(list [1 6] [1 5] [1 4] [1 3] [1 2] [1 1])
            (list [2 6] [3 5] [4 4] [5 3] [6 2] [7 1])
            (list [2 7] [3 7] [4 7] [5 7] [6 7] [7 7] [8 7])
            (list [2 8])
            (list [1 8])]
           (sut/paths piece [1 7])))))

(deftest king-test
  (let [piece (King. :black)]
    (is (= [(list [1 6])
            (list [2 6])
            (list [2 7])
            (list [2 8])
            (list [1 8])]
           (sut/paths piece [1 7])))))



