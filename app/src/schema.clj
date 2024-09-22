(ns schema
  (:require [clojure.java.io :as io]
            [com.walmartlabs.lacinia.util :as util]
            [com.walmartlabs.lacinia.schema :as schema]
            [clojure.edn :as edn]))

(defn resolve-user-by-email
  [users context args value]
  (let [{:keys [email]} args]
    (get users email)))

(defn resolver-map
  []
  (let [users-mock (-> (io/resource "mock/users.edn")
                       slurp
                       edn/read-string)
        users (->> users-mock
                   :users
                   (reduce #(assoc %1 (:email %2) %2) {}))]
    {:query/user-by-email (partial resolve-user-by-email users)}))

(defn load-schema
  []
  (-> (io/resource "home-meters-schema.edn")
      slurp
      edn/read-string
      (util/attach-resolvers (resolver-map))
      schema/compile))