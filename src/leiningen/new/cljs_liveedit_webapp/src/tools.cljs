(ns {{name}}.tools)

(def debug-level (atom nil))

(defn set-debug-level! [lvl]
  (declare echo)
  (echo "(tools/set-debug-level)" lvl)
  (reset! debug-level lvl))

(defn echo [& args]
  (.log js/console (pr-str-with-opts args (assoc (pr-opts) :readably false))))

(defn log [& args]
  (when (> @debug-level 2)
    (apply echo args)))

(defn warn [& args]
  (when (> @debug-level 1)
    (apply echo args)))

(def error echo)
