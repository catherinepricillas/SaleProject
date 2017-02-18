
package CatalogWS;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the CatalogWS package. 
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

    private final static QName _IOException_QNAME = new QName("http://CatalogWSModel/", "IOException");
    private final static QName _ParseException_QNAME = new QName("http://CatalogWSModel/", "ParseException");
    private final static QName _DislikeProduct_QNAME = new QName("http://CatalogWSModel/", "dislikeProduct");
    private final static QName _DislikeProductResponse_QNAME = new QName("http://CatalogWSModel/", "dislikeProductResponse");
    private final static QName _LikeProduct_QNAME = new QName("http://CatalogWSModel/", "likeProduct");
    private final static QName _LikeProductResponse_QNAME = new QName("http://CatalogWSModel/", "likeProductResponse");
    private final static QName _SearchProduct_QNAME = new QName("http://CatalogWSModel/", "searchProduct");
    private final static QName _SearchProductResponse_QNAME = new QName("http://CatalogWSModel/", "searchProductResponse");
    private final static QName _ViewCatalog_QNAME = new QName("http://CatalogWSModel/", "viewCatalog");
    private final static QName _ViewCatalogResponse_QNAME = new QName("http://CatalogWSModel/", "viewCatalogResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: CatalogWS
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
     * Create an instance of {@link DislikeProduct }
     * 
     */
    public DislikeProduct createDislikeProduct() {
        return new DislikeProduct();
    }

    /**
     * Create an instance of {@link DislikeProductResponse }
     * 
     */
    public DislikeProductResponse createDislikeProductResponse() {
        return new DislikeProductResponse();
    }

    /**
     * Create an instance of {@link LikeProduct }
     * 
     */
    public LikeProduct createLikeProduct() {
        return new LikeProduct();
    }

    /**
     * Create an instance of {@link LikeProductResponse }
     * 
     */
    public LikeProductResponse createLikeProductResponse() {
        return new LikeProductResponse();
    }

    /**
     * Create an instance of {@link SearchProduct }
     * 
     */
    public SearchProduct createSearchProduct() {
        return new SearchProduct();
    }

    /**
     * Create an instance of {@link SearchProductResponse }
     * 
     */
    public SearchProductResponse createSearchProductResponse() {
        return new SearchProductResponse();
    }

    /**
     * Create an instance of {@link ViewCatalog }
     * 
     */
    public ViewCatalog createViewCatalog() {
        return new ViewCatalog();
    }

    /**
     * Create an instance of {@link ViewCatalogResponse }
     * 
     */
    public ViewCatalogResponse createViewCatalogResponse() {
        return new ViewCatalogResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CatalogWSModel/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CatalogWSModel/", name = "ParseException")
    public JAXBElement<ParseException> createParseException(ParseException value) {
        return new JAXBElement<ParseException>(_ParseException_QNAME, ParseException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DislikeProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CatalogWSModel/", name = "dislikeProduct")
    public JAXBElement<DislikeProduct> createDislikeProduct(DislikeProduct value) {
        return new JAXBElement<DislikeProduct>(_DislikeProduct_QNAME, DislikeProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DislikeProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CatalogWSModel/", name = "dislikeProductResponse")
    public JAXBElement<DislikeProductResponse> createDislikeProductResponse(DislikeProductResponse value) {
        return new JAXBElement<DislikeProductResponse>(_DislikeProductResponse_QNAME, DislikeProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LikeProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CatalogWSModel/", name = "likeProduct")
    public JAXBElement<LikeProduct> createLikeProduct(LikeProduct value) {
        return new JAXBElement<LikeProduct>(_LikeProduct_QNAME, LikeProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LikeProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CatalogWSModel/", name = "likeProductResponse")
    public JAXBElement<LikeProductResponse> createLikeProductResponse(LikeProductResponse value) {
        return new JAXBElement<LikeProductResponse>(_LikeProductResponse_QNAME, LikeProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CatalogWSModel/", name = "searchProduct")
    public JAXBElement<SearchProduct> createSearchProduct(SearchProduct value) {
        return new JAXBElement<SearchProduct>(_SearchProduct_QNAME, SearchProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CatalogWSModel/", name = "searchProductResponse")
    public JAXBElement<SearchProductResponse> createSearchProductResponse(SearchProductResponse value) {
        return new JAXBElement<SearchProductResponse>(_SearchProductResponse_QNAME, SearchProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewCatalog }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CatalogWSModel/", name = "viewCatalog")
    public JAXBElement<ViewCatalog> createViewCatalog(ViewCatalog value) {
        return new JAXBElement<ViewCatalog>(_ViewCatalog_QNAME, ViewCatalog.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ViewCatalogResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CatalogWSModel/", name = "viewCatalogResponse")
    public JAXBElement<ViewCatalogResponse> createViewCatalogResponse(ViewCatalogResponse value) {
        return new JAXBElement<ViewCatalogResponse>(_ViewCatalogResponse_QNAME, ViewCatalogResponse.class, null, value);
    }

}
