(ns {{name}}.debug-level
  (:require [{{name}}.tools :as tools]))

(defn set-level!
  ([] (set-level! 1))
  ([lvl] (tools/set-debug-level! lvl)))
