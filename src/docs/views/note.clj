(ns docs.views.note
  (:require [docs.views.layout :as layout]))

(defn note-view [note]
  [:div
   [:h2 (:name note)]
   [:p (:content note)]
   [:ul
    (for
      [file (:files note)]
      [:li (:name file)])]
   [:a.btn.btn-default
    {:href (str "/note/" (:id note) "/edit")}
    "Edit"]])

(defn note-edit [note]
  [:div
   [:h2 (:name note)]
   [:div.form-group
    [:textarea.form-control {:rows 10} (:content note)]]
   [:div.form-group
    [:input.form-control {:type "file"}]]
   [:ul
    (for
      [file (:files note)]
      [:li (:name file)])]
   [:button.btn.btn-primary "Save"]])

(defn note-create []
  [:div
   [:h2 "Create Note"]
   [:div.form-group
    [:textarea.form-control {:rows 10}]]
   [:div.form-group
    [:input.form-control {:type "file"}]]
   [:ul]
   [:button.btn.btn-primary "Create"]])

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

(defn layout-note-create [note-list]
  (layout/common
    [:div.row
     [:div.col-md-2
      (layout/nav-list note-list)]
     [:div.col-md-10
      (note-create)]]))
