
package com.aashita.ws.contacts.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "addOhlc", namespace = "http://contacts.ws.aashita.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addOhlc", namespace = "http://contacts.ws.aashita.com/")
public class AddOhlc {

    @XmlElement(name = "arg0", namespace = "")
    private com.aashita.ws.contacts.Ohlc5 arg0;

    /**
     * 
     * @return
     *     returns Ohlc5
     */
    public com.aashita.ws.contacts.Ohlc5 getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(com.aashita.ws.contacts.Ohlc5 arg0) {
        this.arg0 = arg0;
    }

}
