
package YourProductWS;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the YourProductWS package. 
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

    private final static QName _IOException_QNAME = new QName("http://YourProductModel/", "IOException");
    private final static QName _ParseException_QNAME = new QName("http://YourProductModel/", "ParseException");
    private final static QName _GetProduct_QNAME = new QName("http://YourProductModel/", "getProduct");
    private final static QName _GetProductResponse_QNAME = new QName("http://YourProductModel/", "getProductResponse");
    private final static QName _HasProduct_QNAME = new QName("http://YourProductModel/", "hasProduct");
    private final static QName _HasProductResponse_QNAME = new QName("http://YourProductModel/", "hasProductResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: YourProductWS
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
     * Create an instance of {@link GetProduct }
     * 
     */
    public GetProduct createGetProduct() {
        return new GetProduct();
    }

    /**
     * Create an instance of {@link GetProductResponse }
     * 
     */
    public GetProductResponse createGetProductResponse() {
        return new GetProductResponse();
    }

    /**
     * Create an instance of {@link HasProduct }
     * 
     */
    public HasProduct createHasProduct() {
        return new HasProduct();
    }

    /**
     * Create an instance of {@link HasProductResponse }
     * 
     */
    public HasProductResponse createHasProductResponse() {
        return new HasProductResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://YourProductModel/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://YourProductModel/", name = "ParseException")
    public JAXBElement<ParseException> createParseException(ParseException value) {
        return new JAXBElement<ParseException>(_ParseException_QNAME, ParseException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://YourProductModel/", name = "getProduct")
    public JAXBElement<GetProduct> createGetProduct(GetProduct value) {
        return new JAXBElement<GetProduct>(_GetProduct_QNAME, GetProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://YourProductModel/", name = "getProductResponse")
    public JAXBElement<GetProductResponse> createGetProductResponse(GetProductResponse value) {
        return new JAXBElement<GetProductResponse>(_GetProductResponse_QNAME, GetProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HasProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://YourProductModel/", name = "hasProduct")
    public JAXBElement<HasProduct> createHasProduct(HasProduct value) {
        return new JAXBElement<HasProduct>(_HasProduct_QNAME, HasProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HasProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://YourProductModel/", name = "hasProductResponse")
    public JAXBElement<HasProductResponse> createHasProductResponse(HasProductResponse value) {
        return new JAXBElement<HasProductResponse>(_HasProductResponse_QNAME, HasProductResponse.class, null, value);
    }

}
