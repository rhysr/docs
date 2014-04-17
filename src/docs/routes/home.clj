(ns docs.routes.home
  (:require [compojure.core :refer [defroutes GET]]
            [docs.views.layout :as layout]))


(defn home []
  {:status 200
   :body (layout/common [:h1 "Doc"])})

(defroutes home-routes
  (GET "/" [] (home)))
