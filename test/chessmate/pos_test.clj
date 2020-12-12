(ns chessmate.pos-test
  (:require [chessmate.pos :as sut]
            [clojure.test :refer [deftest testing is]]))

(deftest pos-test
  (let [pos (sut/make 1 2)]
    (testing "make returns a pos where x is x and y is y"
      (is (= 1 (sut/x pos)))
      (is (= 2 (sut/y pos))))))

(deftest translate-test
  (let [pos (sut/make 1 2)]
    (testing "translate sums x's and y's for 2 positions"
      (is (= (sut/make 2 4)
             (sut/translate pos pos))))))

(deftest on-board?-test
  (let [on-board (sut/make 1 2)
        not-on-board (sut/make 9 5)]
    (testing "on-board? checks if position is on the board"
      (is (sut/on-board? on-board))
      (is (not (sut/on-board? not-on-board))))))
