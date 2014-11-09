package com.toastcoders.vcumeter.ws.util;

import com.toastcoders.vcumeter.datatypes.Customer;
import com.toastcoders.vcumeter.datatypes.Rule;
import com.toastcoders.vcumeter.datatypes.Vcentner;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * Copyright 2014 Michael Rice <michael@michaelrice.org>
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class Builder {

    private final String attr = "xmlns";
    private final String attrVal = "http://www.vmware.com/UM";

    /**
     * Build a Vcenter payload from a given Vcenter object
     *
     * @param vcentner
     * @return String
     * @throws ParserConfigurationException
     * @throws TransformerException
     */
    public String buildVcenter(Vcentner vcentner) throws ParserConfigurationException, TransformerException {
        Document doc = makeDoc();
        Attr attr = doc.createAttribute(this.attr);
        attr.setValue(this.attrVal);

        // Add elements
        Element rootElement = doc.createElement("vcServer");
        rootElement.setAttributeNode(attr);
        doc.appendChild(rootElement);

        // hostname element
        Element host = doc.createElement("hostname");
        host.appendChild(doc.createTextNode(vcentner.getHostName()));
        rootElement.appendChild(host);

        // username element
        Element username = doc.createElement("username");
        username.appendChild(doc.createTextNode(vcentner.getUserName()));
        rootElement.appendChild(username);

        // password element
        Element password = doc.createElement("password");
        password.appendChild(doc.createTextNode(vcentner.getPassword()));
        rootElement.appendChild(password);

        Element monitor = doc.createElement("monitor");
        monitor.appendChild(doc.createTextNode(
            String.valueOf(vcentner.isMonitor())
        ));

        return docToString(doc);
    }

    /**
     * Build a payload to create a customer in the usage meter
     *
     * @param customer
     * @return
     * @throws ParserConfigurationException
     */
    public String buildCustomer(Customer customer) throws ParserConfigurationException {
        Document doc = makeDoc();
        Attr attr = doc.createAttribute(this.attr);
        attr.setValue(this.attrVal);

        // Add elements
        Element rootElement = doc.createElement("customer");
        rootElement.setAttributeNode(attr);
        doc.appendChild(rootElement);

        // name element
        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(customer.getName()));
        rootElement.appendChild(name);

        // country element
        Element country = doc.createElement("country");
        country.appendChild(doc.createTextNode(customer.getCountryCode()));
        rootElement.appendChild(country);

        // postalCode element
        Element postalCode = doc.createElement("postalCode");
        postalCode.appendChild(doc.createTextNode(customer.getPostalCode()));
        rootElement.appendChild(postalCode);

        return docToString(doc);
    }

    public String buildVcenterRule(Rule rule) throws ParserConfigurationException {
        Document doc = makeDoc();
        Attr attr = doc.createAttribute(this.attr);
        attr.setValue(this.attrVal);

        // Add elements
        Element rootElement = doc.createElement("rule");
        rootElement.setAttributeNode(attr);
        doc.appendChild(rootElement);

        // vcServerHost element
        Element vcServerHost = doc.createElement("vcServerHost");
        vcServerHost.appendChild(doc.createTextNode(rule.getVcServerHost()));
        rootElement.appendChild(vcServerHost);

        // customerName element
        Element customerName = doc.createElement("customerName");
        customerName.appendChild(doc.createTextNode(rule.getCustomerName()));
        rootElement.appendChild(customerName);

        // objectType element
        Element objectType = doc.createElement("objectType");
        objectType.appendChild(doc.createTextNode(rule.getObjectType()));
        rootElement.appendChild(objectType);

        // valueType element
        Element valueType = doc.createElement("valueType");
        valueType.appendChild(doc.createTextNode(rule.getValueType()));
        rootElement.appendChild(valueType);

        // value element
        Element value = doc.createElement("value");
        value.appendChild(doc.createTextNode(rule.getValue()));
        rootElement.appendChild(value);

        return docToString(doc);
    }

    private Document makeDoc() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        return docBuilder.newDocument();
    }

    private String docToString(Document document) {
        DOMImplementationLS domImplementation = (DOMImplementationLS) document.getImplementation();
        LSSerializer lsSerializer = domImplementation.createLSSerializer();
        return lsSerializer.writeToString(document);
    }

}
