(ns docs.routes.note
  (:require [compojure.core :refer [defroutes GET POST]]
            [noir.validation :refer [rule errors? get-errors has-value?]]
            [docs.views.note :refer [layout-note-view layout-note-edit layout-note-create]]
            [docs.models.notes :refer [get-note-list get-note]]))


(defn view-note-page [id]
  (let [note (get-note id)]
    {:status 200
     :body (layout-note-view (get-note-list) note)}))

(defn edit-note-page [id]
  (let [note (get-note id)]
    {:status 200
     :body (layout-note-edit (get-note-list) note)}))

(defn create-note-page [& [params errors]]
  {:status 200
    :body (layout-note-create (get-note-list) params errors)})

(defn create-note [params]
  (cond
    (not (contains? params "name"))
    (rule false [:name "Missing name"])
    (not (has-value? (params "name")))
    (rule false [:name "Fill in the name"]))

  (cond
    (not (contains? params "content"))
    (rule false [:content "Missing content"])
    (not (has-value? (params "content")))
    (rule false [:content "Fill in the note content"]))

  (if (errors?)
    (create-note-page params (get-errors))
    (create-note-page)))


(defroutes note-routes
  (GET ["/note/:id", :id #"[0-9]+"] [id]
       (view-note-page id))
  (GET ["/note/:id/edit", :id #"[0-9]+"] [id]
       (edit-note-page id))
  (GET "/note/create" []
       (create-note-page))
  (POST "/note/create" {form-params :form-params}
        (create-note form-params))
  )
