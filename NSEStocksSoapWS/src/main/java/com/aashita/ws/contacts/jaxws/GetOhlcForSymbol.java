
package com.aashita.ws.contacts.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getOhlcForSymbol", namespace = "http://contacts.ws.aashita.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOhlcForSymbol", namespace = "http://contacts.ws.aashita.com/")
public class GetOhlcForSymbol {

    @XmlElement(name = "arg0", namespace = "")
    private String arg0;

    /**
     * 
     * @return
     *     returns String
     */
    public String getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(String arg0) {
        this.arg0 = arg0;
    }

}
