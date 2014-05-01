(ns docs.handler
  (:require [compojure.core :refer [defroutes routes]]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [noir.validation :refer [wrap-noir-validation]]
            [docs.routes.home :refer [home-routes]]
            [docs.routes.note :refer [note-routes]]
            [docs.models.db :as db]))

(defn init []
  (println "Starting application")
  (if-not (.exists (java.io.File. "./db.sq3"))
    (db/create-note-table)))

(defn destroy []
  (println "Shutting down application"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (wrap-noir-validation
    (handler/site
      (routes
        home-routes
        note-routes
        app-routes))))

