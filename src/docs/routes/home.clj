(ns docs.routes.home
  (:require [compojure.core :refer [defroutes GET]]
            [docs.views.home :refer [layout-home]]
            [docs.models.notes :refer [get-note-list]]))


(defn home []
  {:status 200
   :body (layout-home (get-note-list))})

(defroutes home-routes
  (GET "/" [] (home)))
