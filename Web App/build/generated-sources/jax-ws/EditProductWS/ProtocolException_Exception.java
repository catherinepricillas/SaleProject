
package EditProductWS;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.11-b150120.1832
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "ProtocolException", targetNamespace = "http://EditProductModel/")
public class ProtocolException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ProtocolException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ProtocolException_Exception(String message, ProtocolException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public ProtocolException_Exception(String message, ProtocolException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: EditProductWS.ProtocolException
     */
    public ProtocolException getFaultInfo() {
        return faultInfo;
    }

}