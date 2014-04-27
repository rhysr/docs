(ns docs.models.notes
  (require [docs.models.db :as db]
           [clojure.java.jdbc :as jdbc]))

(defn get-note-list []
  (jdbc/query
    db/conn
    ["SELECT * FROM note ORDER BY timestamp DESC"]))


(defn get-note-files [id]
  [{:name "File 1" :file "file_0001.jpg" :desc "Some stuff about the file" :size "100KB"}
   {:name "File 2" :file "file_0002.jpg" :desc "Some stuff about the file" :size "23KB"}
   {:name "File 3" :file "file_0003.jpg" :desc "Some stuff about the file" :size "5B"}
   {:name "File 4" :file "file_0004.jpg" :desc "Some stuff about the file" :size "1.2MB"}])

(defn get-note [id]
  (jdbc/query
    db/conn
    ["select * from note where id = ?" id]
    :result-set-fn (fn [res]
                     (if (empty? res)
                       nil
                       (assoc (first res) :files (get-note-files id))))))


(defn save-note! [name content]
  (jdbc/insert!
    db/conn
    :note
    [:name :content :timestamp]
    [name content (new java.util.Date)]))
