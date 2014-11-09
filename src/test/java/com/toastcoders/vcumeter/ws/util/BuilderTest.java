package com.toastcoders.vcumeter.ws.util;

import com.toastcoders.vcumeter.datatypes.Customer;
import com.toastcoders.vcumeter.datatypes.Rule;
import com.toastcoders.vcumeter.datatypes.Vcentner;
import org.junit.Test;

import static org.junit.Assert.*;

public class BuilderTest {

    @Test
    public void testBuildVcenter() throws Exception {
        Vcentner vcentner = new Vcentner("root", "password", "vc00.home.local");
        Builder builder = new Builder();
        String vcPayload = builder.buildVcenter(vcentner);
        String expectedPayload = "<?xml version=\"1.0\" encoding=\"UTF-16\"?>\n<vcServer xmlns=\"http://www.vmware.com/UM\"><hostname>vc00.home.local</hostname><username>root</username><password>password</password></vcServer>";
        assert vcPayload.equals(expectedPayload);
    }

    @Test
    public void testBuildCustomer() throws Exception {
        Customer customer = new Customer("testCustomer", "US", "79762");
        Builder builder = new Builder();
        String expectedPayload = "<?xml version=\"1.0\" encoding=\"UTF-16\"?>\n<customer xmlns=\"http://www.vmware.com/UM\"><name>testCustomer</name><country>US</country><postalCode>79762</postalCode></customer>";
        String actualPayload = builder.buildCustomer(customer);
        assertEquals(expectedPayload, actualPayload);
    }

    @Test
    public void testBuildVcenterRule() throws Exception {
        Rule rule = new Rule("vs00.home.lab", "123456", "Datacenter", "Unique ID", "datacenter-123");
        Builder builder = new Builder();
        String actualPayload = builder.buildVcenterRule(rule);
        String expectedPayload = "<?xml version=\"1.0\" encoding=\"UTF-16\"?>\n<rule xmlns=\"http://www.vmware.com/UM\"><vcServerHost>vs00.home.lab</vcServerHost><customerName>123456</customerName><objectType>Datacenter</objectType><valueType>Unique ID</valueType><value>datacenter-123</value></rule>";
        assertEquals(expectedPayload, actualPayload);
    }
}