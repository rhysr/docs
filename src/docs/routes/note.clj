(ns docs.routes.note
  (:require [compojure.core :refer [defroutes GET]]
            [docs.views.layout :as layout]
            [docs.models.notes :refer [get-note-list]]))


(defn view-note [id]
  {:status 200
   :body (layout/common
           [:div.row
            [:div.col-md-2
             (layout/nav-list (get-note-list))]
            [:div.col-md-10 [:h2 (str "Note " id)]]])})

(defroutes note-routes
  (GET ["/note/:id", :id #"[0-9]+"] [id] (view-note id)))
