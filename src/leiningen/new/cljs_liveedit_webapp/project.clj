(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://FIXME"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2030"]
                 [prismatic/dommy "0.1.2"]
                 [tailrecursion/javelin "2.3.0"]]
  :plugins [[lein-cljsbuild "1.0.0-alpha2"]
            [lein-exec "0.3.1"]
            [lein-externs "0.1.1"]
            [com.cemerick/clojurescript.test "0.2.2-SNAPSHOT"]]
  :cljsbuild
  {:builds
   {:dev {:source-paths ["src"]
          :compiler     {:output-to     "resources/public/js/{{sanitized}}.js"
                         :output-dir    "resources/public/js/out"
                         :optimizations nil
                         :pretty-print  true}}
    :release {:source-paths ["src"]
              :compiler     {:output-to     "resources/public/js/{{sanitized}}.js"
                             :optimizations :advanced
                             :externs       ["externs/jquery-1.8.js"
                                             "externs/{{name}}.js"]}}
    :test {:source-paths   ["src" "test"]
           :notify-command ["scripts/run_tests.rb" :cljs.test/runner]
           :compiler       {:output-to     "resources/public/test/js/test.js"
                            :optimizations :whitespace
                            :pretty-print  true}}}
   :test-commands {"unit-tests" ["scripts/run_tests.rb" :runner]}}
)
