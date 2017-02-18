
package ConfirmWS;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ConfirmWS package. 
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

    private final static QName _IOException_QNAME = new QName("http://ConfirmModel/", "IOException");
    private final static QName _ParseException_QNAME = new QName("http://ConfirmModel/", "ParseException");
    private final static QName _ConfirmPurchase_QNAME = new QName("http://ConfirmModel/", "confirmPurchase");
    private final static QName _ConfirmPurchaseResponse_QNAME = new QName("http://ConfirmModel/", "confirmPurchaseResponse");
    private final static QName _GetProductData_QNAME = new QName("http://ConfirmModel/", "getProductData");
    private final static QName _GetProductDataResponse_QNAME = new QName("http://ConfirmModel/", "getProductDataResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ConfirmWS
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
     * Create an instance of {@link ConfirmPurchase }
     * 
     */
    public ConfirmPurchase createConfirmPurchase() {
        return new ConfirmPurchase();
    }

    /**
     * Create an instance of {@link ConfirmPurchaseResponse }
     * 
     */
    public ConfirmPurchaseResponse createConfirmPurchaseResponse() {
        return new ConfirmPurchaseResponse();
    }

    /**
     * Create an instance of {@link GetProductData }
     * 
     */
    public GetProductData createGetProductData() {
        return new GetProductData();
    }

    /**
     * Create an instance of {@link GetProductDataResponse }
     * 
     */
    public GetProductDataResponse createGetProductDataResponse() {
        return new GetProductDataResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ConfirmModel/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ConfirmModel/", name = "ParseException")
    public JAXBElement<ParseException> createParseException(ParseException value) {
        return new JAXBElement<ParseException>(_ParseException_QNAME, ParseException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmPurchase }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ConfirmModel/", name = "confirmPurchase")
    public JAXBElement<ConfirmPurchase> createConfirmPurchase(ConfirmPurchase value) {
        return new JAXBElement<ConfirmPurchase>(_ConfirmPurchase_QNAME, ConfirmPurchase.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmPurchaseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ConfirmModel/", name = "confirmPurchaseResponse")
    public JAXBElement<ConfirmPurchaseResponse> createConfirmPurchaseResponse(ConfirmPurchaseResponse value) {
        return new JAXBElement<ConfirmPurchaseResponse>(_ConfirmPurchaseResponse_QNAME, ConfirmPurchaseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ConfirmModel/", name = "getProductData")
    public JAXBElement<GetProductData> createGetProductData(GetProductData value) {
        return new JAXBElement<GetProductData>(_GetProductData_QNAME, GetProductData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ConfirmModel/", name = "getProductDataResponse")
    public JAXBElement<GetProductDataResponse> createGetProductDataResponse(GetProductDataResponse value) {
        return new JAXBElement<GetProductDataResponse>(_GetProductDataResponse_QNAME, GetProductDataResponse.class, null, value);
    }

}
