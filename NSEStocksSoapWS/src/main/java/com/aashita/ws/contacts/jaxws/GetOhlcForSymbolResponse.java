
package com.aashita.ws.contacts.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getOhlcForSymbolResponse", namespace = "http://contacts.ws.aashita.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getOhlcForSymbolResponse", namespace = "http://contacts.ws.aashita.com/")
public class GetOhlcForSymbolResponse {

    @XmlElement(name = "return", namespace = "")
    private List<com.aashita.ws.contacts.Ohlc5> _return;

    /**
     * 
     * @return
     *     returns List<Ohlc5>
     */
    public List<com.aashita.ws.contacts.Ohlc5> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<com.aashita.ws.contacts.Ohlc5> _return) {
        this._return = _return;
    }

}
