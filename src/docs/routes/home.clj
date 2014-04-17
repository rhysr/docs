(ns docs.routes.home
  (:require [compojure.core :refer [defroutes GET]]))


(defn home []
  {:status 200
   :body "<!DOCTYPE html>\n<html><head></head><body><h1>Docs</h1></body></html>"})


(defroutes home-routes
  (GET "/" [] (home)))
