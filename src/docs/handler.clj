(ns docs.handler
  (:require [compojure.core :refer [defroutes routes]]
            [compojure.route :as route]
            [docs.routes.home :refer [home-routes]]))

(defn init []
  (println "Starting application"))

(defn destroy []
  (println "Shutting down application"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (routes home-routes app-routes))

