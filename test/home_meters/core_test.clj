(ns home-meters.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [ring.mock.request :as mock]
            [home-meters.core :refer [home-meters-app]]))

(deftest index-test
  (testing "Index page"
    (let [response (home-meters-app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello"))))

  (testing "Error page"
    (let [response (home-meters-app (mock/request :get "/abc"))]
      (is (= (:status response) 404)))))
