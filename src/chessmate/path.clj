(ns chessmate.path)

(def ^:private RANGE (map inc (range)))

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
