package AmazonWebServices;

import AmazonWebServices.MyHandlerStack;
import AmazonWebServices.SignedRequestsHelper;
import Model.Book;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class AmazonWebservice {

    private static final String SECRET_KEY = "";
    private static final String AWS_KEY = "";
    private static final String ASSOCIATE_TAG = "spart00-20";
    private static final String ENDPOINT = "ecs.amazonaws.com";
    
    private String searchCategory;  // Author, Keywords, Power, Title
    private String sortOrder; // sort results by: relevancerank, pricerank, titlerank, daterank
    private String currentPage; // display search result page number: 1 to 10 max pages
    private int totalPages = 0;
    private int numberResults = 0;
    public ArrayList<Book> searchResults;
    
    
    public AmazonWebservice() {
    	
    }
    
    
    public ArrayList<Book> search(String query) throws InvalidKeyException, IllegalArgumentException, UnsupportedEncodingException, NoSuchAlgorithmException {
    	SignedRequestsHelper helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_KEY, SECRET_KEY);
    	Map<String, String> params = new HashMap<String, String>();
        params.put("Service", "AWSECommerceService");
        params.put("Version", "2009-03-31");
        params.put("Operation", "ItemSearch");
        params.put("Keywords", query);
        //params.put("Author", "Murach");
        //params.put("Sort", "pricerank");
        //params.put("ItemPage", pageNumber);
        params.put("SearchIndex", "Books");
        params.put("ResponseGroup", "Medium");
        params.put("AssociateTag", ASSOCIATE_TAG);

        String url = helper.sign(params);
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            MyHandlerStack handler = new MyHandlerStack();
            parser.parse(url, handler);
            searchResults = handler.getBookList();
            for (Book bk : handler.getBookList()) {
            	handler.printBookDetail(bk);
            }
            totalPages = handler.getTotalPages();
            numberResults = handler.getTotalResults();
        } catch (Exception ex) {
            Logger.getLogger(AmazonWebservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    	return searchResults;
    }

    private static Document getResponse(String url) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(url);
        return doc;
    }

    private static void printResponse(Document doc) throws TransformerException, FileNotFoundException {
    	Transformer trans = TransformerFactory.newInstance().newTransformer();
        Properties props = new Properties();
        props.put(OutputKeys.INDENT, "yes");
        trans.setOutputProperties(props);
        StreamResult res = new StreamResult(new StringWriter());
        DOMSource src = new DOMSource(doc);
        trans.transform(src, res);
        String toString = res.getWriter().toString();
        System.out.println(toString);
    }
    
}