(ns {{name}}.main
  (:require [reagent.core :as reagent]
            [{{name}}.repl        :as repl]
            [{{name}}.tools       :as tools]
            [{{name}}.debug-level :as debug-level]))

(def hello-world-text (reagent/atom "Hello world!"))

(def body (.-body js/document))

(defn hello-world-component []
  [:div#wrapper
   [:div#hello @hello-world-text]])

(defn hello-world []
  (reagent/render-component [hello-world-component] body))

(defn ^:export init [dev]
  (debug-level/set-level!)
  (tools/log "(init) dev:" dev)
  (repl/connect)
  (hello-world))
