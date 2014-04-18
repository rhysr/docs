(ns docs.routes.note
  (:require [compojure.core :refer [defroutes GET]]
            [docs.views.note :refer [layout-note-view layout-note-edit]]
            [docs.models.notes :refer [get-note-list get-note]]))


(defn view-note [id]
  (let [note (get-note id)]
    {:status 200
     :body (layout-note-view (get-note-list) note)}))

(defn edit-note [id]
  (let [note (get-note id)]
    {:status 200
     :body (layout-note-edit (get-note-list) note)}))

(defroutes note-routes
  (GET ["/note/:id", :id #"[0-9]+"] [id]
       (view-note id))
  (GET ["/note/:id/edit", :id #"[0-9]+"] [id]
       (edit-note id)))