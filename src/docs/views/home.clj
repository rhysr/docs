(ns docs.views.home
  (:require [docs.views.layout :as layout]))

(defn layout-home [note-list]
  (layout/common
    [:div.row
     [:div.col-md-2
      (layout/nav-list note-list)]
     [:div.col-md-10
      [:h2 "Things"]]]))


