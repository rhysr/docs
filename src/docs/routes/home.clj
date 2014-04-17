(ns docs.routes.home
  (:require [compojure.core :refer [defroutes GET]]
            [hiccup.page :refer [html5]]))


(defn home []
  {:status 200
   :body (html5
           [:head]
           [:body
            [:h1 "Docs"]])})


(defroutes home-routes
  (GET "/" [] (home)))
