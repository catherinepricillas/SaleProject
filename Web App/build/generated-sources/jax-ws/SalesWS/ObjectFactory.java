
package SalesWS;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the SalesWS package. 
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

    private final static QName _IOException_QNAME = new QName("http://SalesModel/", "IOException");
    private final static QName _ParseException_QNAME = new QName("http://SalesModel/", "ParseException");
    private final static QName _GetSales_QNAME = new QName("http://SalesModel/", "getSales");
    private final static QName _GetSalesResponse_QNAME = new QName("http://SalesModel/", "getSalesResponse");
    private final static QName _HasSales_QNAME = new QName("http://SalesModel/", "hasSales");
    private final static QName _HasSalesResponse_QNAME = new QName("http://SalesModel/", "hasSalesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: SalesWS
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
     * Create an instance of {@link GetSales }
     * 
     */
    public GetSales createGetSales() {
        return new GetSales();
    }

    /**
     * Create an instance of {@link GetSalesResponse }
     * 
     */
    public GetSalesResponse createGetSalesResponse() {
        return new GetSalesResponse();
    }

    /**
     * Create an instance of {@link HasSales }
     * 
     */
    public HasSales createHasSales() {
        return new HasSales();
    }

    /**
     * Create an instance of {@link HasSalesResponse }
     * 
     */
    public HasSalesResponse createHasSalesResponse() {
        return new HasSalesResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SalesModel/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SalesModel/", name = "ParseException")
    public JAXBElement<ParseException> createParseException(ParseException value) {
        return new JAXBElement<ParseException>(_ParseException_QNAME, ParseException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSales }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SalesModel/", name = "getSales")
    public JAXBElement<GetSales> createGetSales(GetSales value) {
        return new JAXBElement<GetSales>(_GetSales_QNAME, GetSales.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSalesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SalesModel/", name = "getSalesResponse")
    public JAXBElement<GetSalesResponse> createGetSalesResponse(GetSalesResponse value) {
        return new JAXBElement<GetSalesResponse>(_GetSalesResponse_QNAME, GetSalesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HasSales }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SalesModel/", name = "hasSales")
    public JAXBElement<HasSales> createHasSales(HasSales value) {
        return new JAXBElement<HasSales>(_HasSales_QNAME, HasSales.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HasSalesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://SalesModel/", name = "hasSalesResponse")
    public JAXBElement<HasSalesResponse> createHasSalesResponse(HasSalesResponse value) {
        return new JAXBElement<HasSalesResponse>(_HasSalesResponse_QNAME, HasSalesResponse.class, null, value);
    }

}
