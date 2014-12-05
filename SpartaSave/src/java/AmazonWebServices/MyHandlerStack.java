package AmazonWebServices;

import Model.*;

import java.util.ArrayList;
import java.util.Stack;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class MyHandlerStack extends DefaultHandler {
    private final StringBuilder content = new StringBuilder(); 
    private final Stack<String> tagStack = new Stack<String>();
    
    private int totalResults;
    private int totalPages;
    private Book currentBook;
    private ArrayList<Book> bookList;
    
    
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        pushTag(qName);
        content.setLength(0);
    	
        String tag = null;
    	String parentTag = null;
        if(!tagStack.empty()) {
        	tag = peekTag();
        	popTag();
        }
    	
    	if(!tagStack.empty()) {
    		parentTag = peekTag();
        }
        
    	pushTag(tag);
    	
        switch (tag) {
        	case "Items":
        		if (parentTag.equals("ItemSearchResponse")) {
        			bookList = new ArrayList<Book>();
        		}
        		break;
        	
        	case "Item":
        		if (parentTag.equals("Items")) {
        			System.out.println("Start Element: " + qName);
                	currentBook = new Book();
        		}
                break;
        	
        	default:
        		// do nothing
        		break;
        }
    }

    
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	String tag = null;
    	String parentTag = null;
    	String grandParentTag = null;
    	
    	if(!tagStack.empty()) {
        	tag = peekTag();
        	popTag();
        }
    	
    	if(!tagStack.empty()) {
    		parentTag = peekTag();
        }
        
    	switch (tag) {
        	case "Item":
        		if (parentTag.equals("Items")) {
	        		System.out.println("End Element: " + tag);
	            	System.out.println();
	            	bookList.add(currentBook);
        		}	
            	break;
        	
        	case "DetailPageURL":
        		if (parentTag.equals("Item")) {
        			String detailPageURL = content.toString().trim();
        			System.out.println("DetailPageURL: " + detailPageURL);
        			currentBook.setDetailPageURL(detailPageURL);
        		}
        		break;
    			
    		case "URL":
    	    	if (!tagStack.empty()) {
    	    		popTag();
    	    		grandParentTag = peekTag();
    	    		pushTag(parentTag);
    	        }
    	    	if (parentTag.equals("MediumImage") && grandParentTag.equals("Item")) {
    	    		String mediumImageURL = content.toString().trim();
    	    		System.out.println("MediumImageURL: " + mediumImageURL);
            		currentBook.setImageURL(mediumImageURL);
    	    	}
    			break;
    			
    		case "Author":
    			if (parentTag.equals("ItemAttributes")) {
	    			String author = content.toString().trim();
	    			System.out.println("Author: " + author);
	    			currentBook.getAuthors().add(author);
    			}
    			break;
    		
    		case "ISBN":
    			if (parentTag.equals("ItemAttributes")) {
	    			String isbn = content.toString().trim();
	    			System.out.println("ISBN: " + isbn);
	    			currentBook.setISBN(isbn);
    			}
    			break;
    		
    		case "Title":
    			if (parentTag.equals("ItemAttributes")) {
	    			String title = content.toString().trim();
	    			System.out.println("Title: " + title);
	    			currentBook.setTitle(title);
	    		}
    			break;
    			
    		case "FormattedPrice":
    			if (parentTag.equals("ListPrice")) {
    				String listPrice = content.toString().trim();
            		System.out.println("ListPrice: " + listPrice);
            		currentBook.setListPrice(listPrice);
            	} else if (parentTag.equals("LowestNewPrice")) {
            		String lowestNewPrice = content.toString().trim();
            		System.out.println("LowestNewPrice: " + lowestNewPrice);
            		currentBook.setLowestNewPrice(lowestNewPrice);
            	} else if (parentTag.equals("LowestUsedPrice")) {
            		String lowestUsedPrice = content.toString().trim();
            		System.out.println("LowestUsedPrice: " + lowestUsedPrice);
            		currentBook.setLowestPrice(lowestUsedPrice);
            	}
    			break;
    		
    		case "TotalResults":
    			if (parentTag.equals("Items")) {
    				totalResults = Integer.valueOf(content.toString().trim());
    			}
    			break;
    			
    		case "TotalPages":
    			if (parentTag.equals("Items")) {
    				totalPages = Integer.valueOf(content.toString().trim());
    			}
    			break;
    			
    		default:
    			// do nothing
    			break;
        }
    }
     
    public void characters(char ch[], int start, int length) throws SAXException {
    	content.append(ch, start, length);
    }
    
    private void pushTag(String tag) {
    	tagStack.push(tag);
    }

    private String popTag() {
        return tagStack.pop();
    }

    private String peekTag() {
        return tagStack.peek();
    }
    
    public ArrayList<Book> getBookList() {
    	return this.bookList;
    }

    public int getTotalResults() {
    	return this.totalResults;
    }
    
    public int getTotalPages() {
    	return this.totalPages;
    }
    
    public void printBookDetail(Book bk) {
    	System.out.println("DetailPageURL: " + bk.getDetailPageURL());
    	System.out.println("MediumImageURL: " + bk.getImageURL());
    	for (int i = 0; i < bk.getAuthors().size(); i++) {
    		System.out.println("Author: " + bk.getAuthors().get(i));
    	}
    	System.out.println("ISBN: " + bk.getISBN());
    	System.out.println("ListPrice: " + bk.getListPrice());
    	System.out.println("Title: " + bk.getTitle());
    	System.out.println("LowestNewPrice: " + bk.getLowestNewPrice());
    	System.out.println("LowestUsedPrice: " + bk.getLowestPrice());
    	System.out.println();
    }
    
    public static void main(String[] args) {
        String url = "test.xml";
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            MyHandlerStack handler = new MyHandlerStack();
            parser.parse(url, handler);
            for(Book bk : handler.getBookList()) {
            	handler.printBookDetail(bk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}