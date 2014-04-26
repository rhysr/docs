(ns docs.models.db
  (:require [clojure.java.jdbc :as jdbc])
  (:import java.sql.DriverManager))


(def conn {:classname "org.sqlite.JDBC"
           :subprotocol "sqlite"
           :subname "db.sq3"})

(defn create-note-table []
  (jdbc/db-do-commands
    conn
    (jdbc/create-table-ddl
      :note
      [:id "INTEGER PRIMARY KEY AUTOINCREMENT"]
      [:timestamp "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"]
      [:name "TEXT"]
      [:content "TEXT"])
    "CREATE INDEX timestamp_index ON note (timestamp)"))
