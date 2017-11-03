package adaptiveweb;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.document.TextDocument;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import de.l3s.boilerpipe.extractors.DefaultExtractor;
import de.l3s.boilerpipe.sax.BoilerpipeSAXInput;
import de.l3s.boilerpipe.sax.HTMLDocument;
import de.l3s.boilerpipe.sax.HTMLFetcher;



/*
 * Used BoilerPipe, JSOUP for crawling Pages 
 * 
 */
public class Crawler {
	private final static Logger LOGGER = Logger.getLogger(Crawler.class.getName());

	public boolean check(String s) {
		if (s.equals("ON"))
			return true;

		else
			return false;
	}
	
	
	/*
	 * crawl function to crawl wikibook and oracle pages
	 */
	public void crawl() throws IOException, SAXException  {
		String urlString = "https://en.wikibooks.org/wiki/Java_Programming";
		String OracleString = "https://docs.oracle.com/javase/tutorial/java/TOC.html";
		
		Document document = Jsoup.connect(urlString).get();
		Elements qlinks= document.select("a[href]");
		
		for(Element link :qlinks) {
			if(link.equals("https:\\en.wikibooks.org\\wiki\\Java_Programming\\Print_version")) continue;
		   
			if(link.attr("href").contains("Print_version") )continue;
			if (link.attr("abs:href").contains("wiki/Java_Programming")) {
				this.crawlwiki(link.attr("abs:href"));
			}
		
		}
		
		this.crawlOracle(OracleString);

	}
	
	
	/*
	 * called by crawl function to crawl wiki pages
	 */
	public  void crawlwiki(String link) throws SAXException {
		try {
		String urlString = link;
		LOGGER.info("Crawling Wiki");
		
		String title=urlString.replace("https://en.wikibooks.org/wiki/Java_Programming/","");
		String titles[]=title.split("/");
		title=titles[titles.length-1];
		
		Document doc = Jsoup.connect(urlString).get();
		doc.select("div#mw-content-text>table.wikitable.noprint").remove();
		doc.select("div#mw-content-text>table.wikitable").remove();
		doc.select("h2>span.mw-editsection").remove();
		Elements page = doc.select("div#mw-content-text>*");

		List<String> pageparts = Arrays.asList(page.toString().replaceAll("^.*?<h[0-3]>", "").split("<h[0-9]>.*?h[0-9]>|<p><br></p>"));

		ClassLoader cl = getClass().getClassLoader();
		URL path = cl.getResource("/Library");
		String configPath = URLDecoder.decode(path.getFile(), "UTF-8");
		File Library = new File(configPath);
		
		int p=0;
		for(String s: pageparts) {
			if(s.split("\\s+").length > 300) {
				p++;
				File file = new File(Library+"\\" + title +p+ "_wikibook.txt");

				PrintWriter writer = new PrintWriter(file);
				String article = DefaultExtractor.INSTANCE.getText(s);
				writer.println(article);
				LOGGER.info("Content extracted from Java WikiBooks ");
				LOGGER.info(file.getAbsolutePath());
				writer.close();
			}
			
		}
		

     } catch (MalformedURLException e) {
         LOGGER.severe("Exception thrown for invalid url " + e);
     } catch (BoilerpipeProcessingException e) {
         LOGGER.severe("Exception thrown during scraping process " + e);
     } catch (IOException e) {
         LOGGER.severe("Exception thrown" + e);
     }
	}


	/*
	 * called by crawl function to crawl oracle pages
	 */
public  void crawlOracle(String link) throws SAXException {
	try {
	LOGGER.info("Started crawling Oracle");	
	Document oracledocs= Jsoup.connect(link).get();
	Elements oracleqlinks= oracledocs.select("div#PageContent>ul>li>ul>li>a[href],div#PageContent>ul>li>ul>li>ul>li>a[href]");
	
	//parsing title of page url..
	int a = 0;
	String[] titles = new String[oracleqlinks.size()];
	for (Element links : oracleqlinks) {
		String[] splits = links.attr("abs:href").split("/");
		titles[a] = splits[splits.length - 1];
		titles[a] = titles[a].replace(".html", "");
		a++;
	}
	
	//creating text article document for each link
	int parts = 0;
	
	for (Element links : oracleqlinks) {
		
		Document document = Jsoup.connect(links.attr("abs:href")).get();
		
		document.select("div#MainFlow>div.PrintHeaders,div#MainFlow>div.BreadCrumbs,div#MainFlow>div.NavBit,div#MainFlow>div.PageTitle")
				.remove();
		
		Elements page = document.select("div#MainFlow>*");
		
		List<String> pageParts = Arrays.asList(page.toString().replaceAll("../../", "https://docs.oracle.com/javase/tutorial/").split("<h[0-9]>.*?h[0-9]>|<p><br></p>"));
		
		int totalPageParts=pageParts.size();
		
		ClassLoader cl = getClass().getClassLoader();
		URL path = cl.getResource("/Library");
		String configPath = URLDecoder.decode(path.getFile(), "UTF-8");
		File Library = new File(configPath);
		
		//for each link...process
		for (int x = 0; x < totalPageParts; x++) {
			
			if ( pageParts.get(x).split("\\s+").length > 300) {
				
				pageParts.get(x).replaceAll("../../", "https://docs.oracle.com/javase/tutorial/");
				
				
				//Fetching article through boilerpipe library with Default extractor
				
				String article = DefaultExtractor.INSTANCE.getText(pageParts.get(x));
				
				PrintWriter writer = new PrintWriter(Library + "/" + titles[parts] + "_part" + x + "_oracle.txt",
						"UTF-8");
				writer.println(article);
				writer.close();
			}
		}
		parts++;
	}
	}
	
	catch (MalformedURLException e) {
        LOGGER.severe("Exception thrown for invalid url " + e);
    } catch (BoilerpipeProcessingException e) {
        LOGGER.severe("Exception thrown during scraping process " + e);
    } catch (IOException e) {
        LOGGER.severe("Exception thrown" + e);
    }
	
	
}
}

