(ns docs.models.notes)

(defn get-note-list []
  [{:id 1
    :name "Note 1"}
   {:id 2
    :name "Note 2"}
   {:id 3
    :name "Note 3"}
   {:id 4
    :name "Note 4"}
   {:id 5
    :name "Note 5"}
   {:id 6
    :name "Note 6"}])

(defn get-note [id]
  {:id id
   :name (str "Note " id)
   :content "Note content"
   :files [{:name "File 1"}
           {:name "File 2"}
           {:name "File 3"}
           {:name "File 4"}]})
