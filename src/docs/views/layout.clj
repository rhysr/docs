(ns docs.views.layout
  (:require [hiccup.page :refer [html5 include-css]])
  (:require [hiccup.element :refer [link-to]]))


(defn common [& body]
  (html5
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
     [:div.container
      [:div.row
       [:h1
        (link-to
          {}
          "/"
          "Docs")]
       [:hr]]
      body]]))

(defn nav-list [list-items]
  (for
    [list-item list-items]
    [:p
     (link-to
       {}
       (str "/note/" (:id list-item))
       (:name list-item))]))
