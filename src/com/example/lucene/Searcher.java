package com.example.lucene;
 
import java.io.IOException;
import java.nio.file.Paths;
 
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.search.SortField.Type;
 
public class Searcher {
    //directory contains the lucene indexes
    private static final String INDEX_DIRECTORY = "resources/index";
 
    public static void main(String[] args) throws Exception {
        //Create lucene searcher. It search over a single IndexReader.
        IndexSearcher searcher = createSearcher();
         
        //Search indexed contents using search term
        TopDocs foundDocs = searchInContent("когда", searcher);
         
        //Total found documents
        System.out.println("Total Results :: " + foundDocs.totalHits);
         
        //Let's print out the path of files which have searched term
        for (ScoreDoc sd : foundDocs.scoreDocs) {
            Document d = searcher.doc(sd.doc);
            System.out.println("Path : "+ d.get("path") + ", freq: " + d.get("freq") + ", Score : " + sd.score);
        }
    }
     
    private static TopDocs searchInContent(String textToFind, IndexSearcher searcher) throws Exception {
        //Create search query
        // QueryParser qp = new QueryParser("contents", new StandardAnalyzer());
        // Query query = qp.parse(textToFind);
        
        Term term = new Term("contents", textToFind);
        TermQuery query = new TermQuery(term);

        //search the index
        //TopDocs hits = searcher.search(query, 10);

        //TopDocs hits = searcher.search(query, 10, sort, true, true);
        //TopDocs hits = searcher.search(query, 10, new Sort(new SortField("freq", SortField.Type.STRING)));
        
        //TopDocs hits = searcher.search(query, 10, new Sort(new SortField("freq", SortField.Type.STRING)), true, true);
        TopDocs hits = searcher.search(query, 10, new Sort(new SortField("freq", SortField.Type.DOUBLE)), true, true);

        return hits;
    }
 
    private static IndexSearcher createSearcher() throws IOException {
        Directory dir = FSDirectory.open(Paths.get(INDEX_DIRECTORY));
         
        //It is an interface for accessing a point-in-time view of a lucene index
        IndexReader reader = DirectoryReader.open(dir);
         
        //Index searcher
        IndexSearcher searcher = new IndexSearcher(reader);
        return searcher;
    }
}