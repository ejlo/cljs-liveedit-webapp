(ns leiningen.new.cljs-liveedit-webapp
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(defn cljs-liveedit-webapp
  "Create a new cljs webapp with live edit features"
  [name]
  (let [render (renderer "cljs-liveedit-webapp")
        data   {:name name
                :sanitized (name-to-path name)}
        templ  #(str "resources/templates/" %)
        r #(render % data)]
    (main/info "Generating fresh cljs-webapp project:" name)
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             ["Gruntfile.js" (render "Gruntfile.js" data)]
             ["bower.json" (render "bower.json" data)]
             ["package.json" (render "package.json" data)]
             ["externs/jquery.js" (render "externs/jquery.js")]
             ["externs/app.js" (render "externs/app.js" data)]
             [".gitignore" (render ".gitignore" data)]
             [(templ "style/app.scss") (render (templ "style/app.scss") data)]
             [(templ "style/_layout.scss") (render (templ "style/_layout.scss") data)]
             [(templ "style/_font.scss") (render (templ "style/_font.scss") data)]
             [(templ "style/_hello.scss") (render (templ "style/_hello.scss") data)]
             [(templ "style/_reset.scss") (render (templ "style/_reset.scss") data)]
             [(templ "index.html.whiskers") (render (templ "index.html.whiskers") data)]
             [(templ "js/function_prototype_polyfill.js") (render (templ "js/function_prototype_polyfill.js") data)]
             ["scripts/install_deps.sh" (render "scripts/install_deps.sh" data) :executable true]
             ["scripts/run_tests.rb" (render "scripts/run_tests.rb" data) :executable true]
             ["scripts/start_build.sh" (render "scripts/start_build.sh" data) :executable true]
             ["scripts/generate_externs.sh" (render "scripts/generate_externs.sh" data) :executable true]
             ["scripts/clojurescript.el" (render "scripts/clojurescript.el" data)]
             ["src/{{sanitized}}/main.cljs" (render "src/main.cljs" data)]
             ["src/{{sanitized}}/tools.cljs" (render "src/tools.cljs" data)]
             ["src/dev/{{sanitized}}/repl.cljs" (render "src/dev/repl.cljs" data)]
             ["src/dev/{{sanitized}}/debug_level.cljs" (render "src/dev/debug_level.cljs" data)]
             ["src/release/{{sanitized}}/repl.cljs" (render "src/release/repl.cljs" data)]
             ["src/release/{{sanitized}}/debug_level.cljs" (render "src/release/debug_level.cljs" data)]
             ["test/{{sanitized}}/test/main.cljs" (render "test/main.cljs" data)]
             ["test/{{sanitized}}/test/repl.cljs" (render "test/repl.cljs" data)]
             ["test/{{sanitized}}/test/debug_level.cljs" (render "test/debug_level.cljs" data)])))
