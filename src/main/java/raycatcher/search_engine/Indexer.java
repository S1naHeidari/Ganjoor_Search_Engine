package raycatcher.search_engine;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.fa.PersianAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;



public class Indexer {
    public void doIndexing() throws IOException{
        Constants c = new Constants();
        String indexPath = c.indexPath;
        String docsPath = c.docsPath;
        boolean create = true;
        final Path docDir = Paths.get(docsPath);
        if(!Files.isReadable(docDir)){
            System.out.println("Document directory '" +docDir.toAbsolutePath()+ "' does not exist or is not readable, please check the path");
            System.exit(1);
        }
        Date start = new Date();
        try{
            System.out.println("Indexing to directory'" + indexPath + "'...");
            Directory dir = FSDirectory.open(Paths.get(indexPath));
            Analyzer analyzer = new PersianAnalyzer();
            IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
            if (create){
                // Create an index in the directory, removing any
                // previously indexed documents
                iwc.setOpenMode(OpenMode.CREATE);
            }else{
                // add new documents to an existing index
                iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
            }
            IndexWriter writer = new IndexWriter(dir, iwc);
            indexDocs(writer, docDir);
            writer.close();
            Date end = new Date();
            System.out.println(end.getTime()-start.getTime()+" total miliseconds");
        }
        catch(IOException e){
            System.out.println(" caught a " + e.getClass() +
                               "\n with message: " + e.getMessage());
        }
    }
    static void indexDocs(final IndexWriter writer, Path path) throws IOException {
        if (Files.isDirectory(path)) {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                try {
                    indexDoc(writer, file, attrs.lastModifiedTime().toMillis());
                    }
                    catch (IOException ignore) {
                        // don't index files that can't be read.
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        else{
            indexDoc(writer, path, Files.getLastModifiedTime(path).toMillis());
        }
    }
    static void indexDoc(IndexWriter writer, Path file, long lastModified) throws IOException{
        Document doc = new Document();
        Field pathField = new StringField("path", file.toString(), Field.Store.YES);
        doc.add(pathField);
        doc.add(new LongPoint("modified", lastModified));
        doc.add(new TextField("contents", new FileReader(file.toString(), StandardCharsets.UTF_8)));
        System.out.println(doc.getFields());
        if(writer.getConfig().getOpenMode() == OpenMode.CREATE){
            System.out.println("adding" + file);
            writer.addDocument(doc);
        }else{
            System.out.println("updating" + file);
        }
    }
}
        


