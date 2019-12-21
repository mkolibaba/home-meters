(ns home-meters.core
  (:require [compojure.core :refer [defroutes routes GET]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.response :as resp]
            [cheshire.core :as chesire]))

(defn to-json
  [form]
  (-> form
      chesire/encode
      resp/response
      (resp/content-type "application/json; charset=utf-8")))

(defn respond-with-page
  [page-file]
  (resp/content-type (resp/resource-response page-file {:root "public"}) "text/html"))

(defroutes api-routes
  (GET "/api/user" [] (to-json {:firstName "Foo"
                                :lastName "Bar"
                                :email "foo@bar.baz"}))
  (GET "/api/*" [] (to-json {:error "Incorrect API mapping"})))

(defroutes spa-route
  (GET "/*" [] (respond-with-page "index.html")))

(defroutes not-found-route
  (route/not-found "Error"))

; declare app
(def home-meters-app
  ; refering to https://github.com/ring-clojure/ring-defaults/blob/master/src/ring/middleware/defaults.clj
  ; wrap-defaults just inits some default settings for app-routes and
  ; site-defaults provides this default settings 
  (wrap-defaults (routes api-routes spa-route not-found-route) site-defaults))
