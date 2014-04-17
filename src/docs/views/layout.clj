(ns docs.views.layout
  (:require [hiccup.page :refer [html5]]))


(defn common [& body]
  (html5
    [:head]
    [:body [:div body]]))
