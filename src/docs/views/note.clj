(ns docs.views.note
  (:require [docs.views.layout :as layout])
  (:require [hiccup.element :refer [link-to]])
  (:require [hiccup.form :refer [form-to]]))

(defn file-container [file]
  [:div.file-container
   [:div.file-image
    [:img {:data-src "/holder.js/100x100/sky"}]]
   [:div.file-details
    [:span.file-title (:name file)]
    [:span.file-name (str "(" (:file file) ")")]
    [:span.file-size (:size file)]
    [:span.file-desc (:desc file)]]])


(defn note-view [note]
  [:div
   [:div.note-container
    [:h1.page-header (:name note)]
    [:p (:content note)]]
   [:div.files
    (for
      [file (:files note)]
        (file-container file))]
   (link-to
     {:class "btn btn-default"}
     (str "/note/" (:id note) "/edit")
     "Edit")])

(defn note-not-found []
  [:div
   [:div.note-container
    [:h1.page-header "Can't find this note"]]])

(defn note-edit [note]
  [:div.note-container
   [:h1.page-header (:name note)]
   (form-to
     [:post (str "/note/" (note :id) "/edit")]
     [:div.form-group
      [:input.form-control {:name "name" :value (note :name)}]]
     [:div.form-group
      [:textarea.form-control {:rows 10 :name "content"} (:content note)]]
     [:div.form-group
      [:input.form-control {:type "file"}]]
     [:div.files
      (for
        [file (:files note)]
        (file-container file))]
     [:button.btn.btn-primary.btn-lg "Save"])])

(defn note-create [& [params errors]]
  [:div.note-container
   [:h1.page-header "Create Note"]
   (form-to
     [:post "/note/create"]
     (if (not (empty? errors))
       [:div.has-error
        (for [error errors]
          [:p.help-block error])])
     [:div.form-group
      [:input.form-control {:name "name" :value (get params "name" "")}]]
     [:div.form-group
      [:textarea.form-control {:rows 10 :name "content"} (get params "content" "")]]
     [:div.form-group
      [:input.form-control {:type "file"}]]
     [:ul]
     [:button.btn.btn-primary.btn-lg "Create"])])

(defn layout-note-view [note-list note]
  (layout/common
    [:div.row
     [:div.col-md-2
      (layout/nav-list note-list)]
     [:div.col-md-10
      (note-view note)]]))

(defn layout-note-edit [note-list note]
  (layout/common
    [:div.row
     [:div.col-md-2
      (layout/nav-list note-list)]
     [:div.col-md-10
      (note-edit note)]]))

(defn layout-note-create [note-list & [params errors]]
  (layout/common
    [:div.row
     [:div.col-md-2
      (layout/nav-list note-list)]
     [:div.col-md-10
      (note-create params errors)]]))

(defn layout-note-not-found [note-list]
  (layout/common
    [:div.row
     [:div.col-md-2
      (layout/nav-list note-list)]
     [:div.col-md-10
      (note-not-found)]]))
