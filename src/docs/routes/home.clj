(ns docs.routes.home
  (:require [compojure.core :refer [defroutes GET]]
            [docs.views.layout :as layout]
            [docs.models.notes :refer [get-note-list]]))


(defn home []
  {:status 200
   :body (layout/common
           [:div.row
            [:div.col-md-2
             (layout/nav-list (get-note-list))]
            [:div.col-md-10 [:h2 "Things"]]])})

(defroutes home-routes
  (GET "/" [] (home)))
