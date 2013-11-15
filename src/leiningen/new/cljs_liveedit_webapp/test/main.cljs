(ns test.{{name}}.main
  (:require-macros [cemerick.cljs.test :refer [deftest is]]
                   [dommy.macros :as dm :refer [sel1]])
  (:require [cemerick.cljs.test :as t]
            [dommy.core :as d]
            [{{name}}.main :as main]))

(deftest trivial
  (is (= 1 1)))

(deftest hello-world-div
  (main/hello-world)
  (is (= (d/text (sel1 :#hello)) "Hello world!")))
