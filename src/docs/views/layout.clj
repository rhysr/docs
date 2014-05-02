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
       "//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css"
       "/css/docs.css")]
    [:body
     [:nav.navbar.navbar-default.navbar-fixed-top
      [:div.container-fluid
       [:div.navbar-header
        [:button.navbar-toggle
         {:data-toggle "collapse"
          :data-target ".navbar-collapse.collapse"}
         [:span.sr-only "Toggle navigation"]
         [:span.icon-bar]
         [:span.icon-bar]
         [:span.icon-bar]]
        (link-to {:class "navbar-brand"} "/" "Docs")]
       [:div.navbar-collapse.collapse
        [:ul.nav.navbar-nav]]]]
     [:div.container-fluid

      body]
     (include-js
       "//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"
       "//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"
       "//cdnjs.cloudflare.com/ajax/libs/holder/2.3.1/holder.min.js"
       "/js/docs.js"
       )]))


(defn nav-list [list-items]
  [:div.side-nav
   (link-to
     {:class "btn btn-default btn-block"}
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

(defn note-list-and-content [list-items content]
  (common
    [:div.row.row-offcanvas.row-offcanvas-left
     [:div.col-xs-6.col-sm-3.sidebar-offcanvas (nav-list list-items)]
     [:div.col-xs-12.col-sm-9
      [:p.pull-left.visible-xs
       [:button.btn.btn-primary.btn-xs {:data-toggle "offcanvas"} "Toggle list"]]
      content]
]))
