(ns {{name}}.repl
  (:require [clojure.browser.repl :as repl]
            [{{name}}.tools :as tools]))

(defn connect []
  (tools/log "(repl/connect)")
  (repl/connect "http://localhost:9000/repl"))
