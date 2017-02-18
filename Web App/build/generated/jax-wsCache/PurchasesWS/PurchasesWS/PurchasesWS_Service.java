
package PurchasesWS;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.11-b150120.1832
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "PurchasesWS", targetNamespace = "http://PurchasesModel/", wsdlLocation = "http://localhost:8081/Marketplace_Web_Service/PurchasesWS?wsdl")
public class PurchasesWS_Service
    extends Service
{

    private final static URL PURCHASESWS_WSDL_LOCATION;
    private final static WebServiceException PURCHASESWS_EXCEPTION;
    private final static QName PURCHASESWS_QNAME = new QName("http://PurchasesModel/", "PurchasesWS");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8081/Marketplace_Web_Service/PurchasesWS?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PURCHASESWS_WSDL_LOCATION = url;
        PURCHASESWS_EXCEPTION = e;
    }

    public PurchasesWS_Service() {
        super(__getWsdlLocation(), PURCHASESWS_QNAME);
    }

    public PurchasesWS_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), PURCHASESWS_QNAME, features);
    }

    public PurchasesWS_Service(URL wsdlLocation) {
        super(wsdlLocation, PURCHASESWS_QNAME);
    }

    public PurchasesWS_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PURCHASESWS_QNAME, features);
    }

    public PurchasesWS_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PurchasesWS_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns PurchasesWS
     */
    @WebEndpoint(name = "PurchasesWSPort")
    public PurchasesWS getPurchasesWSPort() {
        return super.getPort(new QName("http://PurchasesModel/", "PurchasesWSPort"), PurchasesWS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PurchasesWS
     */
    @WebEndpoint(name = "PurchasesWSPort")
    public PurchasesWS getPurchasesWSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://PurchasesModel/", "PurchasesWSPort"), PurchasesWS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PURCHASESWS_EXCEPTION!= null) {
            throw PURCHASESWS_EXCEPTION;
        }
        return PURCHASESWS_WSDL_LOCATION;
    }

}
