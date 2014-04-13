(ns {{name}}.debug-level
  (:require [{{name}}.tools :as tools]))

(defn set-level!
  ([] (set-level! 2))
  ([lvl] (tools/set-debug-level! lvl)))
