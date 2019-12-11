(ns home-meters.core
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] "Hello")
  (route/not-found "Error"))

; declare app
(def home-meters-app
  ; refering to https://github.com/ring-clojure/ring-defaults/blob/master/src/ring/middleware/defaults.clj
  ; wrap-defaults just inits some default settings for app-routes and
  ; site-defaults provides this default settings 
  (wrap-defaults app-routes site-defaults))

; (defn -main
;   "I don't do a whole lot ... yet."
;   [& args]
;   (println "Hello, World!"))
