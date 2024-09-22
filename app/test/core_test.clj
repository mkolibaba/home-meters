(ns core-test
 (:require [clojure.test :refer [deftest testing is]]
           [ring.mock.request :as mock]
           [core :refer [home-meters-app]]))

(deftest index-test
  (testing "Index page"
    (let [response (home-meters-app (mock/request :get "/"))]
      (is (= (:status response) 200))))

  (testing "Error page"
    (let [response (home-meters-app (mock/request :post "/abc"))]
      (is (= (:status response) 403)))))
