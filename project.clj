(defproject docs "0.1.0-SNAPSHOT"
  :description "Document Storage App"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [ring-server "0.3.1"]
                 [hiccup "1.0.5"]]
  :plugins [[lein-ring "0.8.10"]]
  :ring {:handler docs.handler/app
         :init docs.handler/init
         :destroy docs.handler/destroy}
  :aot :all
  :profiles
  {:production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.2.1"]]
    :ring {:open-browser? false}}})
