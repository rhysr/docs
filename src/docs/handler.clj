(ns docs.handler)


(defn init []
  (println "Starting application"))

(defn destroy []
  (println "Shutting down application"))

(defn app [request-map]
  {:status 200
   :body "doc"})

