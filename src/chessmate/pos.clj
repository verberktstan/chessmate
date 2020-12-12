(ns chessmate.pos)

;; pos is implemented as a simple vector with an x and y integer

(def make
  "When called with 2 arguments, returns a pos vector.
   `(make 1 2) => [1 2]`"
  vector)

(def NORTH     (make  0 -1))
(def NORTHEAST (make  1 -1))
(def SOUTHEAST (make  1  1))
(def SOUTH     (make  0  1))
(def SOUTHWEST (make -1  1))
(def NORTHWEST (make -1 -1))

(def x "Returns the x value of the pos" first)
(def y "Returns the y value of the pos" second)

(defn on-board?
  "Returns true if x and y are valid positions on the board"
  [pos]
  (every? #(< 0 % 9) pos))

(def translate
  "Returns a pos vector. Sums the x's and y's of all given pos arguments,
   thus 'moving' the pos."
  (partial mapv +))

(defn flip-vertical [pos]
  (make (x pos) (* (y pos) -1)))
