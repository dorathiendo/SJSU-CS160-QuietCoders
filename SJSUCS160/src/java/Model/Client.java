import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Client {

    private static final String SECRET_KEY = "";
    private static final String AWS_KEY = "";
    private static final String ASSOCIATE_TAG = "spart00-20";
    private static final String ENDPOINT = "ecs.amazonaws.com";

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
        params.put("Title", "Java Servlets and JSP");
        params.put("Author", "Murach");
        params.put("SearchIndex", "Books");
        params.put("ResponseGroup", "Large");
        params.put("AssociateTag", ASSOCIATE_TAG);

        String url = helper.sign(params);
        System.out.println("Signed Request is \"" + url + "\"");
        try {
            Document response = getResponse(url);
            printResponse(response);
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
}