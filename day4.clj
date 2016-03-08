(ns adventofcode.day4
  (:require [clojure.test :refer [is]])
  (:import java.security.MessageDigest
           javax.xml.bind.DatatypeConverter))

(def input
  "bgvyzdsv")

(def md (MessageDigest/getInstance "MD5"))

(defn ^String md5-hex [s]
  (DatatypeConverter/printHexBinary
   (.digest md (.getBytes s "ASCII"))))

(defn mine-salt [sk salt start]
  (let [hash (md5-hex (str sk salt))]
    (when (.startsWith hash start)
      [hash (str salt)])))

(defn mine [sk start]
  (some #(mine-salt sk % start) (range)))

(is (= "609043" (second (mine "abcdef" "00000"))))
(is (= "1048970" (second (mine "pqrstuv" "00000"))))

(mine input "00000")

;; => ["000004B30D481662B9CB0C105F6549B2" "254575"]

(mine input "000000")

;; => ["000000B1B64BF5EB55AAD89986126953" "1038736"]
