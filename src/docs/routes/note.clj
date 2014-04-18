(ns docs.routes.note
  (:require [compojure.core :refer [defroutes GET]]
            [docs.views.layout :as layout]
            [docs.models.notes :refer [get-note-list get-note]]))


(defn view-note [id]
  (let [note (get-note id)]
    {:status 200
    :body (layout/common
            [:div.row
              [:div.col-md-2
              (layout/nav-list (get-note-list))]
              [:div.col-md-10
              [:h2 (str "Note " id)]
              [:p (:content note)]
              [:ul
                (for
                  [file (:files note)]
                  [:li (:name file)])
                ]
              [:a
                {:href (str "/note/" id "/edit") :class "btn btn-default"}
                "Edit"]
              ]])}))

(defn edit-note [id]
  {:status 200
   :body (layout/common
           [:div.row
            [:div.col-md-2
             (layout/nav-list (get-note-list))]
            [:div.col-md-10
             [:h2 (str "Note " id)]
             [:form {:method "post"}
              [:div.form-group
               [:textarea]]
              [:div.form-group
               [:ul
               (for
                 [file [{:name "File 1"} {:name "File 2"}]]
                 [:li (:name file)])]]
              [:div.form-group
                [:button.btn.btn-primary "Save"]]]]])})

(defroutes note-routes
  (GET ["/note/:id", :id #"[0-9]+"] [id]
       (view-note id))
  (GET ["/note/:id/edit", :id #"[0-9]+"] [id]
       (edit-note id)))
