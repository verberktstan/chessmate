(ns chessmate.piece-test
  (:require [chessmate.piece :as sut]
            [chessmate.pos :as pos]
            [clojure.test :refer [deftest testing is]])
  (:import [chessmate.piece Pawn Rook Knight Bishop Queen King]))

(deftest pawn-test
  (let [w (Pawn. :white)
        b (Pawn. :black)]
    (testing "Pawns can move 2 steps if it's their first move"
      (is (= [(list (pos/make 1 5) (pos/make 1 4))] (sut/paths w (pos/make 1 6) true)))
      (is (= [(list (pos/make 1 3) (pos/make 1 4))] (sut/paths b (pos/make 1 2) true))))
    (testing "Pawns can move 1 step if it's not their first move"
      (is (= [(list (pos/make 1 3))] (sut/paths w (pos/make 1 4) false)))
      (is (= [(list (pos/make 1 4))] (sut/paths b (pos/make 1 3) false))))
    (testing "Pawns can attack 1 step diagonally"
      (is (= [(list (pos/make 1 5)) (list (pos/make 3 5))]
             (sut/attacks w (pos/make 2 6))))
      (is (= [(list (pos/make 2 7))] (sut/attacks b (pos/make 1 6)))))))

(deftest rook-test
  (let [piece (Rook. :white)]
    (is (= [(list (pos/make 1 6) (pos/make 1 5) (pos/make 1 4) (pos/make 1 3) (pos/make 1 2) (pos/make 1 1))
            (list (pos/make 2 7) (pos/make 3 7) (pos/make 4 7) (pos/make 5 7) (pos/make 6 7) (pos/make 7 7) (pos/make 8 7))
            (list (pos/make 1 8))]
         (sut/paths piece (pos/make 1 7) false)))))

(deftest knight-test
  (let [piece (Knight. :black)]
    (is (= [(list (pos/make 2 5))
            (list (pos/make 3 6))
            (list (pos/make 3 8))]
         (sut/paths piece (pos/make 1 7) false)))))

(deftest bishop-test
  (let [piece (Bishop. :white)]
    (is (= [(list (pos/make 2 6) (pos/make 3 5) (pos/make 4 4) (pos/make 5 3) (pos/make 6 2) (pos/make 7 1))
            (list (pos/make 2 8))]
           (sut/paths piece (pos/make 1 7) false)))))

(deftest queen-test
  (let [piece (Queen. :black)]
    (is (= [(list (pos/make 1 6) (pos/make 1 5) (pos/make 1 4) (pos/make 1 3) (pos/make 1 2) (pos/make 1 1))
            (list (pos/make 2 6) (pos/make 3 5) (pos/make 4 4) (pos/make 5 3) (pos/make 6 2) (pos/make 7 1))
            (list (pos/make 2 7) (pos/make 3 7) (pos/make 4 7) (pos/make 5 7) (pos/make 6 7) (pos/make 7 7) (pos/make 8 7))
            (list (pos/make 2 8))
            (list (pos/make 1 8))]
           (sut/paths piece (pos/make 1 7) false)))))

(deftest king-test
  (let [piece (King. :black)]
    (is (= [(list (pos/make 1 6))
            (list (pos/make 2 6))
            (list (pos/make 2 7))
            (list (pos/make 2 8))
            (list (pos/make 1 8))]
           (sut/paths piece (pos/make 1 7) false)))))
