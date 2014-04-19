(ns docs.views.layout
  (:require [hiccup.page :refer [html5 include-css include-js]])
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
     (include-css
       "//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"
       "//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap-theme.min.css"
       "/css/docs.css")]
    [:body
     [:div.container
      [:div.row
       [:h1
        (link-to
          {}
          "/"
          "Docs")]
       [:hr]]
      body]
     (include-js "/js/holder.js")]))


(defn nav-list [list-items]
  [:div.side-nav
   (link-to
     {:class "btn btn-default"}
     "/note/create"
     "Create Note")
   [:br]
   [:div.note-list
    (for
      [list-item list-items]
      [:p
      (link-to
        {}
        (str "/note/" (:id list-item))
        (:name list-item))])]])
