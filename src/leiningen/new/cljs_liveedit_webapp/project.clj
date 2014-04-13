(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://FIXME"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2202"]
                 [prismatic/dommy "0.1.2"]
                 #_[om "0.5.3"]
                 #_[reagent "0.4.2"]
                 [tailrecursion/javelin "3.1.1"]]
  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-exec "0.3.3"]
            [lein-externs "0.1.3"]
            [com.cemerick/clojurescript.test "0.2.3-SNAPSHOT"]]
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
