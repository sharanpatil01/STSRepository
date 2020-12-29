
package com.aashita.ws.contacts.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getLastPriceForStockResponse", namespace = "http://contacts.ws.aashita.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getLastPriceForStockResponse", namespace = "http://contacts.ws.aashita.com/")
public class GetLastPriceForStockResponse {

    @XmlElement(name = "return", namespace = "")
    private com.aashita.ws.contacts.Ohlc5 _return;

    /**
     * 
     * @return
     *     returns Ohlc5
     */
    public com.aashita.ws.contacts.Ohlc5 getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.aashita.ws.contacts.Ohlc5 _return) {
        this._return = _return;
    }

}
