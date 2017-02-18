
package EditProductWS;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the EditProductWS package. 
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

    private final static QName _IOException_QNAME = new QName("http://EditProductModel/", "IOException");
    private final static QName _ParseException_QNAME = new QName("http://EditProductModel/", "ParseException");
    private final static QName _ProtocolException_QNAME = new QName("http://EditProductModel/", "ProtocolException");
    private final static QName _EditProduct_QNAME = new QName("http://EditProductModel/", "editProduct");
    private final static QName _EditProductResponse_QNAME = new QName("http://EditProductModel/", "editProductResponse");
    private final static QName _SetCurrentProduct_QNAME = new QName("http://EditProductModel/", "setCurrentProduct");
    private final static QName _SetCurrentProductResponse_QNAME = new QName("http://EditProductModel/", "setCurrentProductResponse");
    private final static QName _ValidasiTokenEdit_QNAME = new QName("http://EditProductModel/", "validasiTokenEdit");
    private final static QName _ValidasiTokenEditResponse_QNAME = new QName("http://EditProductModel/", "validasiTokenEditResponse");
    private final static QName _EditProductImgByte_QNAME = new QName("", "img_byte");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: EditProductWS
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
     * Create an instance of {@link ProtocolException }
     * 
     */
    public ProtocolException createProtocolException() {
        return new ProtocolException();
    }

    /**
     * Create an instance of {@link EditProduct }
     * 
     */
    public EditProduct createEditProduct() {
        return new EditProduct();
    }

    /**
     * Create an instance of {@link EditProductResponse }
     * 
     */
    public EditProductResponse createEditProductResponse() {
        return new EditProductResponse();
    }

    /**
     * Create an instance of {@link SetCurrentProduct }
     * 
     */
    public SetCurrentProduct createSetCurrentProduct() {
        return new SetCurrentProduct();
    }

    /**
     * Create an instance of {@link SetCurrentProductResponse }
     * 
     */
    public SetCurrentProductResponse createSetCurrentProductResponse() {
        return new SetCurrentProductResponse();
    }

    /**
     * Create an instance of {@link ValidasiTokenEdit }
     * 
     */
    public ValidasiTokenEdit createValidasiTokenEdit() {
        return new ValidasiTokenEdit();
    }

    /**
     * Create an instance of {@link ValidasiTokenEditResponse }
     * 
     */
    public ValidasiTokenEditResponse createValidasiTokenEditResponse() {
        return new ValidasiTokenEditResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://EditProductModel/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParseException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://EditProductModel/", name = "ParseException")
    public JAXBElement<ParseException> createParseException(ParseException value) {
        return new JAXBElement<ParseException>(_ParseException_QNAME, ParseException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProtocolException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://EditProductModel/", name = "ProtocolException")
    public JAXBElement<ProtocolException> createProtocolException(ProtocolException value) {
        return new JAXBElement<ProtocolException>(_ProtocolException_QNAME, ProtocolException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://EditProductModel/", name = "editProduct")
    public JAXBElement<EditProduct> createEditProduct(EditProduct value) {
        return new JAXBElement<EditProduct>(_EditProduct_QNAME, EditProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EditProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://EditProductModel/", name = "editProductResponse")
    public JAXBElement<EditProductResponse> createEditProductResponse(EditProductResponse value) {
        return new JAXBElement<EditProductResponse>(_EditProductResponse_QNAME, EditProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetCurrentProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://EditProductModel/", name = "setCurrentProduct")
    public JAXBElement<SetCurrentProduct> createSetCurrentProduct(SetCurrentProduct value) {
        return new JAXBElement<SetCurrentProduct>(_SetCurrentProduct_QNAME, SetCurrentProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetCurrentProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://EditProductModel/", name = "setCurrentProductResponse")
    public JAXBElement<SetCurrentProductResponse> createSetCurrentProductResponse(SetCurrentProductResponse value) {
        return new JAXBElement<SetCurrentProductResponse>(_SetCurrentProductResponse_QNAME, SetCurrentProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidasiTokenEdit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://EditProductModel/", name = "validasiTokenEdit")
    public JAXBElement<ValidasiTokenEdit> createValidasiTokenEdit(ValidasiTokenEdit value) {
        return new JAXBElement<ValidasiTokenEdit>(_ValidasiTokenEdit_QNAME, ValidasiTokenEdit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidasiTokenEditResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://EditProductModel/", name = "validasiTokenEditResponse")
    public JAXBElement<ValidasiTokenEditResponse> createValidasiTokenEditResponse(ValidasiTokenEditResponse value) {
        return new JAXBElement<ValidasiTokenEditResponse>(_ValidasiTokenEditResponse_QNAME, ValidasiTokenEditResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "img_byte", scope = EditProduct.class)
    public JAXBElement<byte[]> createEditProductImgByte(byte[] value) {
        return new JAXBElement<byte[]>(_EditProductImgByte_QNAME, byte[].class, EditProduct.class, ((byte[]) value));
    }

}
