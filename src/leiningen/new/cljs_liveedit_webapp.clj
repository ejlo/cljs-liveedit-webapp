(ns leiningen.new.cljs-liveedit-webapp
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(defn cljs-liveedit-webapp
  "Create a new cljs webapp with live edit features"
  [name]
  (let [render (renderer "cljs-liveedit-webapp")
        data   {:name name
                :sanitized (name-to-path name)}
        r      (fn [file & options]
                 (apply vector file (render file data) options))
        r2     (fn [out in & options]
                 (apply vector out (render in data) options))]
    (main/info "Generating fresh cljs-liveedit-webapp project:" name)
    (->files data
             (r "project.clj")
             (r "README.md")
             (r "Gruntfile.js")
             (r "bower.json")
             (r "package.json")
             ["externs/jquery.js" (render "externs/jquery.js")]
             (r "externs/app.js")
             (r ".gitignore")
             (r "resources/templates/style/app.scss")
             (r "resources/templates/style/_layout.scss")
             (r "resources/templates/style/_font.scss")
             (r "resources/templates/style/_hello.scss")
             (r "resources/templates/style/_reset.scss")
             (r "resources/templates/index.html.whiskers")
             (r "resources/templates/js/function_prototype_polyfill.js")
             (r "scripts/install_deps.sh" :executable true)
             (r "scripts/run_tests.rb" :executable true)
             (r "scripts/generate_externs.sh" :executable true)
             (r "scripts/clojurescript.el")
             (r2 "src/{{sanitized}}/main.cljs" "src/main.cljs")
             (r2 "src/{{sanitized}}/tools.cljs" "src/tools.cljs")
             (r2 "src/dev/{{sanitized}}/repl.cljs" "src/dev/repl.cljs")
             (r2 "src/dev/{{sanitized}}/debug_level.cljs" "src/dev/debug_level.cljs")
             (r2 "src/release/{{sanitized}}/repl.cljs" "src/release/repl.cljs")
             (r2 "src/release/{{sanitized}}/debug_level.cljs" "src/release/debug_level.cljs")
             (r2 "test/{{sanitized}}/test/main.cljs" "test/main.cljs")
             (r2 "test/{{sanitized}}/test/repl.cljs" "test/repl.cljs")
             (r2 "test/{{sanitized}}/test/debug_level.cljs" "test/debug_level.cljs"))))
