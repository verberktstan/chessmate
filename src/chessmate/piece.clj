(ns chessmate.piece)

(def RANGE (map inc (range)))

(def NORTHWARD
  (map #(vector 0 (* % -1)) RANGE))
(def EASTWARD
  (map #(vector % 0) RANGE))
(def SOUTHWARD
  (map #(vector 0 %) RANGE))
(def WESTWARD
  (map #(vector (* % -1) 0) RANGE))

(def NORTHEASTWARD
  (map (partial mapv +) NORTHWARD EASTWARD))
(def SOUTHEASTWARD
  (map (partial mapv +) SOUTHWARD EASTWARD))
(def SOUTHWESTWARD
  (map (partial mapv +) SOUTHWARD WESTWARD))
(def NORTHWESTWARD
  (map (partial mapv +) NORTHWARD WESTWARD))

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
     [NORTHWARD EASTWARD SOUTHWARD WESTWARD])))

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
     [NORTHEASTWARD SOUTHEASTWARD SOUTHWESTWARD NORTHWESTWARD])))

(defrecord Queen [color]
  Piece
  (paths [queen pos]
    (keep
     (partial translate-on-board pos)
     [NORTHWARD NORTHEASTWARD
      EASTWARD  SOUTHEASTWARD
      SOUTHWARD SOUTHWESTWARD
      WESTWARD  NORTHWESTWARD])))

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
