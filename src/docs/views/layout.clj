(ns docs.views.layout
  (:require [hiccup.page :refer [html5 include-css]]))


(defn common [& body]
  (html5 [:head]
    [:head
     [:title "Docs"]
     ; get rid of charset tag as it kills IE preprocessor
     ; replace with header
     [:meta {:charset "utf-8"}]
     [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
     (include-css "//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css")
     (include-css "//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css")]
    [:body
     [:div.container body]]))
