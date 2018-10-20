Simple example of maven project of using Apache Lucene search engine.

[Taken from Lucene tutorials - https://howtodoinjava.com/lucene/]

#### Steps to try it &nbsp; :shipit:

1. Install maven projects

```$ mvn clean install```

2. Runs file indexer by content of all files in the `resources/files` and creates index in the `resources/index`

```$ java -cp target/ApacheLucaneSample-0.0.1-SNAPSHOT.jar com.example.lucene.FileIndexer```

3. Runs searching of files by index located in the `resources/index` by query string `'agreeable'`

```
$ java -cp target/ApacheLucaneSample-0.0.1-SNAPSHOT.jar com.example.lucene.Searcher
Total Results :: 2
Path : resources/files/data3.txt, Score : 0.4808459
Path : resources/files/data2.txt, Score : 0.41517237
```
