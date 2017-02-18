
package DelProductWS;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the DelProductWS package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _IOException_QNAME = new QName("http://DelProductWS/", "IOException");
    private final static QName _ParseException_QNAME = new QName("http://DelProductWS/", "ParseException");
    private final static QName _DelProduct_QNAME = new QName("http://DelProductWS/", "delProduct");
    private final static QName _DelProductResponse_QNAME = new QName("http://DelProductWS/", "delProductResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: DelProductWS
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link ParseException }
     * 
     */
    public ParseException createParseException() {
        return new ParseException();
    }

    /**
     * Create an instance of {@link DelProduct }
     * 
     */
    public DelProduct createDelProduct() {
        return new DelProduct();
    }

    /**
     * Create an instance of {@link DelProductResponse }
     * 
     */
    public DelProductResponse createDelProductResponse() {
        return new DelProductResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://DelProductWS/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://DelProductWS/", name = "ParseException")
    public JAXBElement<ParseException> createParseException(ParseException value) {
        return new JAXBElement<ParseException>(_ParseException_QNAME, ParseException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DelProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://DelProductWS/", name = "delProduct")
    public JAXBElement<DelProduct> createDelProduct(DelProduct value) {
        return new JAXBElement<DelProduct>(_DelProduct_QNAME, DelProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DelProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://DelProductWS/", name = "delProductResponse")
    public JAXBElement<DelProductResponse> createDelProductResponse(DelProductResponse value) {
        return new JAXBElement<DelProductResponse>(_DelProductResponse_QNAME, DelProductResponse.class, null, value);
    }

}
