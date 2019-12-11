(defproject home-meters "0.1.0-SNAPSHOT"
  :description "Home Meters web application"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 ; compojure is a simple routing lib
                 [compojure "1.6.1"]
                 ; ring-defaults provides default settings for web application
                 [ring/ring-defaults "0.3.2"]]

  ; plugin for running 'ring' task
  ; https://github.com/weavejester/lein-ring
  :plugins [[lein-ring "0.12.5"]]

  ; handler for 'ring' task
  :ring {:handler home-meters.core/home-meters-app}

  :main ^:skip-aot home-meters.core
  :target-path "target/%s"
  
  ; TODO remove uberjar creation if exists 
  :profiles {:uberjar {:aot :all}})
