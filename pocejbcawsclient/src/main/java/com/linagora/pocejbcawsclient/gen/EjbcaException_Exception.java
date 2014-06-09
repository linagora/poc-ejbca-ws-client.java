
package com.linagora.pocejbcawsclient.gen;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "EjbcaException", targetNamespace = "http://ws.protocol.core.ejbca.org/")
public class EjbcaException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private EjbcaException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public EjbcaException_Exception(String message, EjbcaException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public EjbcaException_Exception(String message, EjbcaException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.linagora.pocejbcawsclient.gen.EjbcaException
     */
    public EjbcaException getFaultInfo() {
        return faultInfo;
    }

}
