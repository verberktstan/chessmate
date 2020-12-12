(ns chessmate.piece
  (:require [chessmate.path :as path]))

(defn on-board? [[x y]]
  (and (< 0 x 9) (< 0 y 9)))

(defn take-on-board [path]
  (take-while on-board? path))

(defn translate [pos path]
  (map (partial mapv + pos) path))

(def translate-on-board
  (comp seq take-on-board translate))

(defprotocol Piece
  (paths [piece pos]))

(defrecord Pawn [color]
  Piece
  (paths [pawn pos]
    (let [paths (if (#{:black} color)
                  [(list [0 1])]
                  [(list [0 -1])])]
      (keep (partial translate-on-board pos) paths))))

(defrecord Rook [color]
  Piece
  (paths [rook pos]
    (keep
     (partial translate-on-board pos)
     [path/NORTHWARD path/EASTWARD path/SOUTHWARD path/WESTWARD])))

(defrecord Knight [color]
  Piece
  (paths [knight pos]
    (keep
     (partial translate-on-board pos)
     [(list [1 -2])
      (list [2 -1])
      (list [2  1])
      (list [1  2])
      (list [-1 2])
      (list [-2 1])
      (list [-2 -1])
      (list [-1 -2])])))

(defrecord Bishop [color]
  Piece
  (paths [bishop pos]
    (keep
     (partial translate-on-board pos)
     [path/NORTHEASTWARD path/SOUTHEASTWARD path/SOUTHWESTWARD path/NORTHWESTWARD])))

(defrecord Queen [color]
  Piece
  (paths [queen pos]
    (keep
     (partial translate-on-board pos)
     [path/NORTHWARD path/NORTHEASTWARD
      path/EASTWARD  path/SOUTHEASTWARD
      path/SOUTHWARD path/SOUTHWESTWARD
      path/WESTWARD  path/NORTHWESTWARD])))

(defrecord King [color]
  Piece
  (paths [king pos]
    (keep
     (partial translate-on-board pos)
     [(list [ 0 -1])
      (list [ 1 -1])
      (list [ 1  0])
      (list [ 1  1])
      (list [ 0  1])
      (list [-1  1])
      (list [-1  0])
      (list [-1 -1])])))
