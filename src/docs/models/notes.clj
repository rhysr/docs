(ns docs.models.notes
  (require [docs.models.db :as db]
           [clojure.java.io :refer [copy file]]
           [clojure.java.jdbc :as jdbc]))

(defn get-note-list []
  (jdbc/query
    db/conn
    ["SELECT * FROM note ORDER BY timestamp DESC"]))


(defn get-note-files [note-id]
  (map
    (fn [file] {:file (.getName file) :name (.getName file) :desc "Placeholder desc" :size "23MB"})
    (filter
      #(.isFile %)
      (file-seq
        (clojure.java.io/file
          (str "files" "/" (str note-id)))))))

(defn get-note [id]
  (jdbc/query
    db/conn
    ["select * from note where id = ?" id]
    :result-set-fn (fn [res]
                     (if (empty? res)
                       nil
                       (assoc (first res) :files (get-note-files id))))))


(defn save-note! [name content]
  (let [r (jdbc/insert!
    db/conn
    :note
    {:name name
     :content content
     :timestamp (new java.util.Date) }
    )]
    {:note-id (
               (keyword "last_insert_rowid()") (first r))
     }))

(defn update-note! [id name content]
  (jdbc/update!
    db/conn
    :note
    {:name name :content content :timestamp (new java.util.Date)}
    ["id = ?" id]))

(defn add-attachment! [note-id filename file-stream]
  (.mkdir (java.io.File. (str "files" "/" (str note-id))))
  (copy file-stream (file "files" (str note-id) filename)))
