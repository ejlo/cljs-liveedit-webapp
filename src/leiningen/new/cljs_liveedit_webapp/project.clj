(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2202"]
                 [prismatic/dommy "0.1.2"]
                 #_[om "0.5.3"]
                 [reagent "0.4.2"]
                 [tailrecursion/javelin "3.1.1"]
                 [figwheel "0.1.2-SNAPSHOT"]]
  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-exec "0.3.3"]
            [lein-externs "0.1.3"]
            [com.cemerick/clojurescript.test "0.2.3-SNAPSHOT"]
            [lein-figwheel "0.1.2-SNAPSHOT"]
            [com.cemerick/austin "0.1.4"]]

  :profiles
  {:dev {:cljsbuild
         {:builds
          [{:id           "main"
            :source-paths ["src/{{sanitized}}" "src/dev"]
            :compiler     {:output-to     "resources/public/js/{{sanitized}}.js"
                           :output-dir    "resources/public/js/out"
                           :optimizations :none
                           :pretty-print  true
                           :preamble      ["reagent/react.js"]
                           :source-map    true}}
           {:id             "test"
            :source-paths   ["src/{{sanitized}}" "test"]
            :notify-command ["scripts/run_tests.rb" :cljs.test/runner]
            :compiler       {:output-to     "resources/public/test/js/test.js"
                             :optimizations :whitespace
                             :pretty-print  true
                             :preamble      ["templates/js/function_prototype_polyfill.js" "reagent/react.js"]}}]
          :test-commands {"unit-tests" ["scripts/run_tests.rb" :runner]}
          }

         :repl-options {:init (println "To start the browser-repl, run:\n"
                                       "(browser-repl)")}
         :injections [(require '[cljs.repl.browser :as brepl]
                               '[cemerick.piggieback :as pb])
                      (defn browser-repl []
                        (pb/cljs-repl :repl-env (brepl/repl-env :port 9000)))]
         :figwheel {:http-server-root "public" ;; assumes "resources"
                    :server-port 3449
                    :css-dirs ["resources/public/style/"]}
         }

   :release {:cljsbuild
             {:builds
              [{:id           "main"
                :source-paths ["src/{{sanitized}}" "src/release"]
                :compiler     {:output-to     "resources/public/js/{{sanitized}}.js"
                               :optimizations :advanced
                               :preamble      ["reagent/react.min.js"]
                               :externs       ["externs/jquery.js"
                                               "externs/app.js"]}}]}}
   })
