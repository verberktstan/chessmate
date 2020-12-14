(ns chessmate.piece
  (:require [chessmate.path :as path]
            [chessmate.pos :as pos]))

(defprotocol Piece
  "Every Piece should implement;
   paths, a fn that returns the paths (list of possible positions) that a piece can traverse.
  attacks, a fn that returns the positions that a piece can attack."
  (paths [piece pos first-move?])
  (attacks [piece pos]))

(defrecord Pawn [color]
  Piece
  (paths [_ pos first-move?]
    (->> (if (#{:black} color)
           (if first-move?
             [(list pos/SOUTH (pos/translate pos/SOUTH pos/SOUTH))]
             [(list pos/SOUTH)])
           (if first-move?
             [(list pos/NORTH (pos/translate pos/NORTH pos/NORTH))]
             [(list pos/NORTH)]))
         (keep (partial path/translate-on-board pos))))
  (attacks [_ pos]
    (->> (if (#{:black} color)
           [(list pos/SOUTHWEST) (list pos/SOUTHEAST)]
           [(list pos/NORTHWEST) (list pos/NORTHEAST)])
         (keep (partial path/translate-on-board pos)))))

(defrecord Rook [color]
  Piece
  (paths [_ pos _]
    (keep
     (partial path/translate-on-board pos)
     [path/NORTHWARD path/EASTWARD path/SOUTHWARD path/WESTWARD]))
  (attacks [piece pos]
    (paths piece pos false)))

(defrecord Knight [color]
  Piece
  (paths [_ pos _]
    (keep
     (partial path/translate-on-board pos)
     [(list (pos/make 1 -2))
      (list (pos/make 2 -1))
      (list (pos/make 2  1))
      (list (pos/make 1  2))
      (list (pos/make -1 2))
      (list (pos/make -2 1))
      (list (pos/make -2 -1))
      (list (pos/make -1 -2))]))
  (attacks [piece pos]
    (paths piece pos false)))

(defrecord Bishop [color]
  Piece
  (paths [_ pos _]
    (keep
     (partial path/translate-on-board pos)
     [path/NORTHEASTWARD path/SOUTHEASTWARD path/SOUTHWESTWARD path/NORTHWESTWARD]))
  (attacks [piece pos]
    (paths piece pos false)))

(defrecord Queen [color]
  Piece
  (paths [_ pos _]
    (keep
     (partial path/translate-on-board pos)
     [path/NORTHWARD path/NORTHEASTWARD
      path/EASTWARD  path/SOUTHEASTWARD
      path/SOUTHWARD path/SOUTHWESTWARD
      path/WESTWARD  path/NORTHWESTWARD]))
  (attacks [piece pos]
    (paths piece pos false)))

(defrecord King [color]
  Piece
  (paths [_ pos _]
    (keep
     (partial path/translate-on-board pos)
     [(list (pos/make  0 -1))
      (list (pos/make  1 -1))
      (list (pos/make  1  0))
      (list (pos/make  1  1))
      (list (pos/make  0  1))
      (list (pos/make -1  1))
      (list (pos/make -1  0))
      (list (pos/make -1 -1))]))
  (attacks [piece pos]
    (paths piece pos false)))
