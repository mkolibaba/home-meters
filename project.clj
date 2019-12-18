(defproject home-meters "0.1.0-SNAPSHOT"
  :description "Home Meters web application"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 ; compojure is a simple routing lib
                 [compojure "1.6.1"]
                 ; ring-defaults provides default settings for web application
                 [ring/ring-defaults "0.3.2"]]

  ; plugin for running 'ring' task
  ; https://github.com/weavejester/lein-ring
  :plugins [[lein-ring "0.12.5"]
            [lein-npm "0.7.0-rc2"]]

  ; handler for 'ring' task
  :ring {:handler home-meters.core/home-meters-app}

  ; scripts and dependencies from svelte-template
  :npm {:ephemeral? false
        :package {:scripts {:build "rollup -c"
                            :dev "rollup -c -w"
                            :start "sirv public"}}
        :dependencies [["navaid" "^1.0.2"]
                       ["sirv-cli" "^0.4.4"]]
        :devDependencies [["@rollup/plugin-node-resolve" "^6.0.0"]
                          ["rollup" "^1.12.0"]
                          ["rollup-plugin-commonjs" "^10.0.0"]
                          ["rollup-plugin-livereload" "^1.0.0"]
                          ["rollup-plugin-svelte" "^5.0.3"]
                          ["rollup-plugin-terser" "^5.1.2"]
                          ["svelte" "^3.0.0"]]}

  :main ^:skip-aot home-meters.core
  :source-paths ["src/core"]
  :test-paths ["test/core"]
  :target-path "target/%s"

  ; TODO remove uberjar creation if exists
  :profiles {:uberjar {:aot :all}
             ; ring-mock is using for testing web applications
             :dev {:dependencies [[ring/ring-mock "0.4.0"]]}})
