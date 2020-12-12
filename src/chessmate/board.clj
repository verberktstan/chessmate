(ns chessmate.board
  (:require [chessmate.piece :as piece]
            [chessmate.pos :as pos])
  (:import [chessmate.piece Pawn Rook Knight Bishop Queen King]))

(defn make []
  {(pos/make 1 7) (Pawn. :white)
   (pos/make 2 7) (Pawn. :white)
   (pos/make 3 7) (Pawn. :white)
   (pos/make 4 7) (Pawn. :white)
   (pos/make 5 7) (Pawn. :white)
   (pos/make 6 7) (Pawn. :white)
   (pos/make 7 7) (Pawn. :white)
   (pos/make 8 7) (Pawn. :white)
   (pos/make 1 8) (Rook.   :white)
   (pos/make 2 8) (Knight. :white)
   (pos/make 3 8) (Bishop. :white)
   (pos/make 4 8) (Queen.  :white)
   (pos/make 5 8) (King.   :white)
   (pos/make 6 8) (Bishop. :white)
   (pos/make 7 8) (Knight. :white)
   (pos/make 8 8) (Rook.   :white)

   (pos/make 1 2) (Pawn. :black)
   (pos/make 2 2) (Pawn. :black)
   (pos/make 3 2) (Pawn. :black)
   (pos/make 4 2) (Pawn. :black)
   (pos/make 5 2) (Pawn. :black)
   (pos/make 6 2) (Pawn. :black)
   (pos/make 7 2) (Pawn. :black)
   (pos/make 8 2) (Pawn. :black)
   (pos/make 1 1) (Rook.   :black)
   (pos/make 2 1) (Knight. :black)
   (pos/make 3 1) (Bishop. :black)
   (pos/make 4 1) (Queen.  :black)
   (pos/make 5 1) (King.   :black)
   (pos/make 6 1) (Bishop. :black)
   (pos/make 7 1) (Knight. :black)
   (pos/make 8 1) (Rook.   :black)})

(defn- walk* [board my-color path]
  (let [[move [attack]] (split-with (partial (complement contains?) board) path)
        {:keys [color]} (get board attack)]
    (cond-> move
      (and color (not= color my-color)) (concat [attack]))))

(defn walk [board my-color paths]
  (into #{} (mapcat (partial walk* board my-color) paths)))
