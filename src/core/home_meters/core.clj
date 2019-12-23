(ns home-meters.core
  (:require [home-meters.schema :as schema]
            [com.walmartlabs.lacinia :as lacinia]
            [compojure.core :refer [defroutes context routes GET POST]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.response :as resp]
            [cheshire.core :as chesire]))

(def gql-schema (schema/load-schema))

(defn q
  [querystring]
  (lacinia/execute gql-schema querystring nil nil))

(defn json-response
  [form & opts]
  (-> form
      chesire/generate-string
      resp/response
      (into opts)
      (resp/content-type "application/json; charset=utf-8")))

(defn page-response
  [page-file]
  (-> page-file
      (resp/resource-response {:root "public"})
      (resp/content-type "text/html")))

; api routes
(defroutes api-routes
  (context "/api" []
    (POST "/login" [])
    (GET "/user" [] (json-response {:firstName "Foo"
                                    :lastName "Bar"
                                    :email "foo@bar.baz"}))
    (GET "/*" [] (json-response {:error "Incorrect API mapping"} {:status 400}))))

; spa serving route
(defroutes spa-route
  (GET "/*" [] (page-response "index.html")))

; not found route
(defroutes not-found-route
  (route/not-found "Error"))

; declare app
(def home-meters-app
  ; refering to https://github.com/ring-clojure/ring-defaults/blob/master/src/ring/middleware/defaults.clj
  ; wrap-defaults just inits some default settings for app-routes and
  ; site-defaults provides this default settings 
  (wrap-defaults (routes api-routes spa-route not-found-route) site-defaults))
