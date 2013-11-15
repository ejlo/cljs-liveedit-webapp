(ns {{name}}.main
  (:require-macros [dommy.macros :as dm :refer [node sel sel1]])
  (:require [dommy.core :as d]))

(defn hello-world []
  (d/append! (sel1 :body)
             [:#wrapper
              [:#hello "Hello world!"]]))

(defn ^:export init [dev]
  (.log js/console "Dev:" dev)
  (hello-world))
