(ns docs.models.db
  (:require [clojure.java.jdbc :as sql])
  (:import java.sql.DriverManager))


(def conn {:classname "org.sqlite.JDBC"
         :subprotocol "sqlite"
         :subname "db.sq3"})

(defn create-note-table []
  (sql/with-connection
    conn
    (sql/create-table
      :note
      [:id "INTEGER PRIMARY KEY AUTOINCREMENT"]
      [:timestamp "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"]
      [:name "TEXT"]
      [:content "TEXT"])
    (sql/do-commands "CREATE INDEX timestamp_index ON note (timestamp)")))
