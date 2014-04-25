(ns docs.models.notes
  (require [docs.models.db :as db]
           [clojure.java.jdbc :as sql]))




(defn get-note-list []
  (sql/with-connection
   db/conn
   (sql/with-query-results res
    ["SELECT * FROM note ORDER BY timestamp DESC"]
    (doall res))))


(defn get-note-files [id]
  [{:name "File 1" :file "file_0001.jpg" :desc "Some stuff about the file" :size "100KB"}
   {:name "File 2" :file "file_0002.jpg" :desc "Some stuff about the file" :size "23KB"}
   {:name "File 3" :file "file_0003.jpg" :desc "Some stuff about the file" :size "5B"}
   {:name "File 4" :file "file_0004.jpg" :desc "Some stuff about the file" :size "1.2MB"}])

; don't forget about files
(defn get-note [id]
  (sql/with-connection
    db/conn
    (sql/with-query-results
      res
      ["select * from note where id = ?" id]
      (if (nil? res)
            nil
            (assoc (first res) :files (get-note-files id))))))

(defn save-note [name content]
  (sql/with-connection
    db/conn
    (sql/insert-values
      :note
      [:name :content :timestamp]
      [name content (new java.util.Date)])))
