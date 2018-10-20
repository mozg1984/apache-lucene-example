Simple example maven project of using Apache Lucene search engine.

# Install maven projects
$ mvn clean install

# Runs file indexer by content of all files in the resources/files
# and creates index in the resources/index 
$ java -cp target/ApacheLucaneSample-0.0.1-SNAPSHOT.jar com.example.lucene.FileIndexer
Indexing file:... /home/radik/java-projects/apachelucene/resources/files/file2.txt
Indexing file:... /home/radik/java-projects/apachelucene/resources/files/file5.txt
Indexing file:... /home/radik/java-projects/apachelucene/resources/files/file4.txt
Indexing file:... /home/radik/java-projects/apachelucene/resources/files/file1.txt
Indexing file:... /home/radik/java-projects/apachelucene/resources/files/file3.txt
Numer of total files indexed:  5

# Runs searching of files by index located in the resources/index by query string 'three'
$ java -cp target/ApacheLucaneSample-0.0.1-SNAPSHOT.jar com.example.lucene.Searcher
/home/radik/java-projects/apachelucene/resources/files/file2.txt
/home/radik/java-projects/apachelucene/resources/files/file1.txt
Found 2
