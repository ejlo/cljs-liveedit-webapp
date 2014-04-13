(ns {{name}}.main
    (:require [{{name}}.repl    :as repl]
              [{{name}}.tools   :as tools]
              [reagent.core     :as reagent]))

(def hello-world-text (reagent/atom "Hello world!"))

(def body (.-body js/document))

(defn hello-world-component []
  [:div#wrapper
   [:div#hello @hello-world-text]])

(defn hello-world []
  (reagent/render-component [hello-world-component] body))

(defn ^:export init [dev]
  (tools/log "(init)" dev)
  (repl/connect)
  (hello-world))
