package AmazonWebServices;

import Model.*;
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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Client {

    private static final String SECRET_KEY = "";
    private static final String AWS_KEY = "";
    private static final String ASSOCIATE_TAG = "spart00-20";
    private static final String ENDPOINT = "ecs.amazonaws.com";
    
    private String operation = "ItemSearch";
    private String searchIndex = "Title";  // Author, Keywords, Power, Title

    public static void main(String[] args) throws InvalidKeyException, IllegalArgumentException, UnsupportedEncodingException, NoSuchAlgorithmException {
        SignedRequestsHelper helper = SignedRequestsHelper.getInstance(ENDPOINT, AWS_KEY, SECRET_KEY);

        Map<String, String> params = new HashMap<String, String>();
        params.put("Service", "AWSECommerceService");
        params.put("Version", "2009-03-31");
        /*
        params.put("Operation", "ItemLookup");
        params.put("ItemId", "1451648537");
        */
        params.put("Operation", "ItemSearch");
        
        /*
        // search by Title
        params.put("Title", "Harry Potter");
        // search by Author
        params.put("Author", "Rowling");
        */
        
        // search by Keywords, such as ISBN
        //params.put("Keywords", "1890774782");
        params.put("Keywords", "java servlets and jsp murach");
        
        // sort results by: relevancerank, pricerank, titlerank, daterank 
        params.put("Sort", "pricerank");
        
        // display search result page number: 1 to 10 max pages
        params.put("ItemPage", "1");
        
        params.put("SearchIndex", "Books");
        params.put("ResponseGroup", "Medium");
        params.put("AssociateTag", ASSOCIATE_TAG);

        String url = helper.sign(params);
        System.out.println("Signed Request is \"" + url + "\"");
        try {
            Document response = getResponse(url);
            printResponse(response);
            //printItems(response);
            try
            {
                SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                MyHandlerStack handler = new MyHandlerStack();
                parser.parse(url, handler);
                System.out.println("Total Results: " + handler.getTotalResults());
                System.out.println("Total Pages: " + handler.getTotalPages());
                // print item objects with attributes from search result
                for (Book bk : handler.getBookList()) {
                	handler.printBookDetail(bk);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            
        } catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    private static void printItems(Document doc) {
    	System.out.println("Print Items");
    	NodeList itemsList = doc.getElementsByTagName("Item");
        for (int i = 0; i < itemsList.getLength(); i++) {
    		Node itemNode = itemsList.item(i);
    		if(itemNode.getNodeType() == Node.ELEMENT_NODE) {
    			Element itemElement = (Element) itemNode;
    			
    			// Title
    			NodeList titleList = itemElement.getElementsByTagName("Title");
    			Element titleElement = (Element) titleList.item(0);
    			NodeList textTitleList = titleElement.getChildNodes();
    			String title = textTitleList.item(0).getNodeValue().trim();
    			System.out.println("Title: " + title);
    			
    			// List Price
    			NodeList priceList = itemElement.getElementsByTagName("ListPrice");
    			if(priceList.getLength() > 0) {
    				Element listPriceElement = (Element) priceList.item(0);
    				NodeList formattedPriceList = listPriceElement.getElementsByTagName("FormattedPrice");
    				Element formattedPriceElement = (Element) formattedPriceList.item(0);
	    			NodeList textFormattedPriceList = formattedPriceElement.getChildNodes();
	    			String formattedPrice = textFormattedPriceList.item(0).getNodeValue().trim();
	    			System.out.println("List Price: " + formattedPrice);
    			} else {
    				System.out.println("List Price: N/A");
    			}
    			
    			// Lowest New Price
    			NodeList lowNewPriceList = itemElement.getElementsByTagName("");
    		}
        }
    }
}