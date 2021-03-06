package com.example.lucene;
 
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Random;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoubleDocValuesField;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.SortedDocValuesField;
import org.apache.lucene.document.SortedNumericDocValuesField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;
 
public class FileIndexer {
    // Input folder
    private static final String INDEX_DIRECTORY = "resources/index";
    
    // Output folder
    private static final String FILES_DIRECTORY = "resources/files";

    public static void main(String[] args) {
        //Input folder
        String docsPath = FILES_DIRECTORY;
         
        //Output folder
        String indexPath = INDEX_DIRECTORY;
 
        //Input Path Variable
        final Path docDir = Paths.get(docsPath);
 
        try {
            //org.apache.lucene.store.Directory instance
            Directory dir = FSDirectory.open( Paths.get(indexPath) );
             
            //analyzer with the default stop words
            Analyzer analyzer = new StandardAnalyzer();
             
            //IndexWriter Configuration
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
             
            //IndexWriter writes new index files to the directory
            IndexWriter writer = new IndexWriter(dir, iwc);
             
            //Its recursive method to iterate all files and directories
            indexDocs(writer, docDir);
 
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    static void indexDocs(final IndexWriter writer, Path path) throws IOException {
        //Directory?
        // if (Files.isDirectory(path)) {
        //     //Iterate directory
        //     Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
        //         @Override
        //         public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        //             try {
        //                 //Index this file
        //                 indexDoc(writer, file, attrs.lastModifiedTime().toMillis());
        //             } catch (IOException ioe) {
        //                 ioe.printStackTrace();
        //             }
                    
        //             return FileVisitResult.CONTINUE;
        //         }
        //     });
        // } else {
        //     //Index this file
        //     indexDoc(writer, path, Files.getLastModifiedTime(path).toMillis());
        // }

        indexDoc(writer, Paths.get(FILES_DIRECTORY + "/Biblija.txt"), 0.1);
        indexDoc(writer, Paths.get(FILES_DIRECTORY + "/Koran.txt"), 0.1);
        indexDoc(writer, Paths.get(FILES_DIRECTORY + "/WarAndPeace1.txt"), 0.1);
        indexDoc(writer, Paths.get(FILES_DIRECTORY + "/WarAndPeace2.txt"), 0.1);
        indexDoc(writer, Paths.get(FILES_DIRECTORY + "/WarAndPeace3.txt"), 0.1);
        indexDoc(writer, Paths.get(FILES_DIRECTORY + "/WarAndPeace4.txt"), 0.1);
        indexDoc(writer, Paths.get(FILES_DIRECTORY + "/Garri_Potter_1.txt"), 0.1);
        indexDoc(writer, Paths.get(FILES_DIRECTORY + "/Garri_Potter_2.txt"), 0.1);
        indexDoc(writer, Paths.get(FILES_DIRECTORY + "/Chelovek_bez_svojstv_1.txt"), 0.1);
        indexDoc(writer, Paths.get(FILES_DIRECTORY + "/Chelovek_bez_svojstv_2.txt"), 0.1);
    }
 
    static void indexDoc(IndexWriter writer, Path file, double freq) throws IOException {
        try (InputStream stream = Files.newInputStream(file)) {
            //Create lucene Document
            Document doc = new Document();
            
            System.out.println(file.toString());
            //System.out.println(new String(Files.readAllBytes(file)));

            doc.add(new StringField("path", file.toString(), Field.Store.YES));
            //doc.add(new LongPoint("modified", lastModified));
            doc.add(new TextField("contents", new String(Files.readAllBytes(file)), Store.YES));
             
            //doc.add (new Field ("byNumber", Integer.toString(freq), Field.Store.NO, Field.Index.NOT_ANALYZED));

            //doc.add(new SortedDocValuesField("freq", new BytesRef(freq)));
            //doc.add(new DoubleDocValuesField("freq", freq));
            
            //doc.add(new StoredField("freq", freq));

            //Updates a document by first deleting the document(s)
            //containing <code>term</code> and then adding the new
            //document.  The delete and then add are atomic as seen
            //by a reader on the same index
            
            //writer.updateDocument(new Term("path", file.toString()), doc);
            writer.addDocument(doc);
        }
    }
}