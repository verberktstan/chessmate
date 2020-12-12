(ns chessmate.path
  (:require [chessmate.pos :as pos]))

(def ^:private RANGE (map inc (range)))

(def NORTHWARD
  (map #(pos/make 0 (* % -1)) RANGE))
(def EASTWARD
  (map #(pos/make % 0) RANGE))
(def SOUTHWARD
  (map #(pos/make 0 %) RANGE))
(def WESTWARD
  (map #(pos/make (* % -1) 0) RANGE))

(def NORTHEASTWARD
  (map (partial mapv +) NORTHWARD EASTWARD))
(def SOUTHEASTWARD
  (map (partial mapv +) SOUTHWARD EASTWARD))
(def SOUTHWESTWARD
  (map (partial mapv +) SOUTHWARD WESTWARD))
(def NORTHWESTWARD
  (map (partial mapv +) NORTHWARD WESTWARD))

(defn take-on-board
  "Returns a path, containing only the positions of path that are on the board"
  [path]
  (take-while pos/on-board? path))

(defn translate
  "Returns a path, each position in the path is translated by pos"
  [pos path]
  (map (partial pos/translate pos) path))

(def translate-on-board (comp seq take-on-board translate))
