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
   :files [{:name "File 1" :file "file_0001.jpg" :desc "Some stuff about the file" :size "100KB"}
           {:name "File 2" :file "file_0002.jpg" :desc "Some stuff about the file" :size "23KB"}
           {:name "File 3" :file "file_0003.jpg" :desc "Some stuff about the file" :size "5B"}
           {:name "File 4" :file "file_0004.jpg" :desc "Some stuff about the file" :size "1.2MB"}]})
