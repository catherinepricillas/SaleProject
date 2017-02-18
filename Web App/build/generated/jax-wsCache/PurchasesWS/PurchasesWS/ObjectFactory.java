
package PurchasesWS;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the PurchasesWS package. 
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

    private final static QName _IOException_QNAME = new QName("http://PurchasesModel/", "IOException");
    private final static QName _ParseException_QNAME = new QName("http://PurchasesModel/", "ParseException");
    private final static QName _GetPurchases_QNAME = new QName("http://PurchasesModel/", "getPurchases");
    private final static QName _GetPurchasesResponse_QNAME = new QName("http://PurchasesModel/", "getPurchasesResponse");
    private final static QName _HasPurchases_QNAME = new QName("http://PurchasesModel/", "hasPurchases");
    private final static QName _HasPurchasesResponse_QNAME = new QName("http://PurchasesModel/", "hasPurchasesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: PurchasesWS
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
     * Create an instance of {@link GetPurchases }
     * 
     */
    public GetPurchases createGetPurchases() {
        return new GetPurchases();
    }

    /**
     * Create an instance of {@link GetPurchasesResponse }
     * 
     */
    public GetPurchasesResponse createGetPurchasesResponse() {
        return new GetPurchasesResponse();
    }

    /**
     * Create an instance of {@link HasPurchases }
     * 
     */
    public HasPurchases createHasPurchases() {
        return new HasPurchases();
    }

    /**
     * Create an instance of {@link HasPurchasesResponse }
     * 
     */
    public HasPurchasesResponse createHasPurchasesResponse() {
        return new HasPurchasesResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://PurchasesModel/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://PurchasesModel/", name = "ParseException")
    public JAXBElement<ParseException> createParseException(ParseException value) {
        return new JAXBElement<ParseException>(_ParseException_QNAME, ParseException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPurchases }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://PurchasesModel/", name = "getPurchases")
    public JAXBElement<GetPurchases> createGetPurchases(GetPurchases value) {
        return new JAXBElement<GetPurchases>(_GetPurchases_QNAME, GetPurchases.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPurchasesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://PurchasesModel/", name = "getPurchasesResponse")
    public JAXBElement<GetPurchasesResponse> createGetPurchasesResponse(GetPurchasesResponse value) {
        return new JAXBElement<GetPurchasesResponse>(_GetPurchasesResponse_QNAME, GetPurchasesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HasPurchases }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://PurchasesModel/", name = "hasPurchases")
    public JAXBElement<HasPurchases> createHasPurchases(HasPurchases value) {
        return new JAXBElement<HasPurchases>(_HasPurchases_QNAME, HasPurchases.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HasPurchasesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://PurchasesModel/", name = "hasPurchasesResponse")
    public JAXBElement<HasPurchasesResponse> createHasPurchasesResponse(HasPurchasesResponse value) {
        return new JAXBElement<HasPurchasesResponse>(_HasPurchasesResponse_QNAME, HasPurchasesResponse.class, null, value);
    }

}
