package adaptiveweb;

import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.tartarus.snowball.ext.PorterStemmer;

public class QueryIndex {

	private final static Logger LOGGER = Logger.getLogger(QueryIndex.class.getName());
	
	
	public  List<String> generateRecommendations(String query) throws IOException, ParseException {
		LOGGER.info("Running get recommendations method");
		query = query.replaceAll("[^A-Za-z\\s]", "");
		StandardAnalyzer analyzer;
		analyzer= new StandardAnalyzer();
		
		int len = query.split("\\s+").length;
		
		//Stemming here..query
		if (len > 3) {
			PorterStemmer stem = new PorterStemmer();
			stem.setCurrent(query);
			stem.stem();
			query = stem.getCurrent();
		}
		
		ClassLoader cl = getClass().getClassLoader();
		URL path = cl.getResource("/indexLibrary");
		String configPath = URLDecoder.decode(path.getFile(), "UTF-8");
		File indexdir = new File(configPath);

		Directory dir = FSDirectory.open(indexdir.toPath());
		Query q = new QueryParser("contents", analyzer).parse(query);

		int hitsPerPage = 10;
		IndexReader reader = null;

		TopScoreDocCollector collector = null;
		IndexSearcher searcher = null;
		reader = DirectoryReader.open(dir);
		searcher = new IndexSearcher(reader);
		collector = TopScoreDocCollector.create(hitsPerPage);
		searcher.search(q, collector);

		ScoreDoc[] hits = collector.topDocs().scoreDocs;
		List<String> output = new ArrayList<String>();

		for (int i = 0; i < hits.length; ++i) {
			int docId = hits[i].doc;
			org.apache.lucene.document.Document d;
			d = searcher.doc(docId);
			output.add(d.get("contents"));

		}
		reader.close();
		return output;
	}
	
	public  void indexDirectory(IndexWriter writer, File dir) throws IOException {
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			if (f.isDirectory()) {
				indexDirectory(writer, f); 
			} else if (f.getName().endsWith(".txt")) {
				
				indexFile(writer, f);
			}
		}
	}
	
	public  void indexFile(IndexWriter writer, File f) throws IOException {
        LOGGER.info("IndexFile Function Started");
        
		org.apache.lucene.document.Document doc = new org.apache.lucene.document.Document();
		doc.add(new TextField("filename", f.getName(), TextField.Store.YES));

		// open each file to index the content
		try {

			FileInputStream is = new FileInputStream(f);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuffer stringBuffer = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				stringBuffer.append(line).append("\n");
			}
			reader.close();
			doc.add(new TextField("contents", stringBuffer.toString(), TextField.Store.YES));

		} catch (Exception e) {

		}
		writer.addDocument(doc);

	}
	
	public  void indexer() throws IOException, ParseException {
        LOGGER.info("indexer function started");
		ClassLoader cl = getClass().getClassLoader();
		URL path = cl.getResource("/Library");
		String configPath = URLDecoder.decode(path.getFile(), "UTF-8");
		File dataDirectory = new File(configPath);
		
		//File dataDirectory = new File("Library");
		
		if (!dataDirectory.exists() || !dataDirectory.isDirectory()) {
			throw new IOException(dataDirectory + " does not exist..");
		}
        

        path = cl.getResource("/indexLibrary");
		configPath = URLDecoder.decode(path.getFile(), "UTF-8");
		File indexdir = new File(configPath);
		//File indexdir = new File("indexLibrary");
		
		Directory dir = FSDirectory.open(indexdir.toPath());

		//analyzer and tockenizing......
		StandardAnalyzer analyzer = new StandardAnalyzer();
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		IndexWriter writer = new IndexWriter(dir, config);

	
		indexDirectory(writer, dataDirectory);
		writer.close();
	}
	
	
	
}
