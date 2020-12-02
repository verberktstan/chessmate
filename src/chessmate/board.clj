(ns chessmate.board
  (:require [chessmate.piece :as piece])
  (:import [chessmate.piece Pawn Rook Knight Bishop Queen King]))

(defn make []
  {[1 7] (Pawn. :white)
   [2 7] (Pawn. :white)
   [3 7] (Pawn. :white)
   [4 7] (Pawn. :white)
   [5 7] (Pawn. :white)
   [6 7] (Pawn. :white)
   [7 7] (Pawn. :white)
   [8 7] (Pawn. :white)
   [1 8] (Rook.   :white)
   [2 8] (Knight. :white)
   [3 8] (Bishop. :white)
   [4 8] (Queen.  :white)
   [5 8] (King.   :white)
   [6 8] (Bishop. :white)
   [7 8] (Knight. :white)
   [8 8] (Rook.   :white)

   [1 2] (Pawn. :black)
   [2 2] (Pawn. :black)
   [3 2] (Pawn. :black)
   [4 2] (Pawn. :black)
   [5 2] (Pawn. :black)
   [6 2] (Pawn. :black)
   [7 2] (Pawn. :black)
   [8 2] (Pawn. :black)
   [1 1] (Rook.   :black)
   [2 1] (Knight. :black)
   [3 1] (Bishop. :black)
   [4 1] (Queen.  :black)
   [5 1] (King.   :black)
   [6 1] (Bishop. :black)
   [7 1] (Knight. :black)
   [8 1] (Rook.   :black)})

(defn- walk* [board my-color path]
  (let [[move [attack]] (split-with (partial (complement contains?) board) path)
        {:keys [color]} (get board attack)]
    (cond-> move
      (and color (not= color my-color)) (concat [attack]))))

(defn walk [board my-color paths]
  (map (partial walk* board my-color) paths))

(let [board {[1 7] (Rook. :black)
             [1 5] (Pawn. :black)}]
  (walk board :black (piece/paths (Rook. :black) [1 7])))
