(ns docs.routes.note
  (:require [compojure.core :refer [defroutes GET POST]]
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

(defn create-note-page [& [params message]]
  {:status 200
    :body (layout-note-create (get-note-list) params message)})

(defn create-note [params]
  (cond
    (not (contains? params "content"))
    (create-note-page params "Missing content")
    (not (contains? params "name"))
    (create-note-page params "Missing name")
    (empty? (params "name"))
    (create-note-page params "Fill in the name")
    (empty? (params "content"))
    (create-note-page params "Fill in the note content")
    :else
    (do
      (println "Create new note and redirect")
      (create-note-page))))



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
