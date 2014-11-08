package com.toastcoders.vcumeter.ws;

import com.toastcoders.vcumeter.exceptions.MissingPropertyException;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionTest {

    Connection connection;

    @Before
    public void setUp() throws Exception {
        this.connection = new Connection("hostname", "apiToken");
    }

    @Test(expected = MissingPropertyException.class)
    public void testBuildUrlThrowsMissingPropertyException() throws MalformedURLException, MissingPropertyException {
        this.connection.buildUrl();
    }

    @Test(expected = MalformedURLException.class)
    public void testBuildUrlThrowsMalformedURLException() throws MalformedURLException, MissingPropertyException {
        this.connection.setCommand("someCommand");
        this.connection.setProtocol(null);
        this.connection.buildUrl();
    }

    @Test
    public void testBuildUrlWorksAsExpected() throws MalformedURLException, MissingPropertyException {
        String expected = "https://hostname:8443/um/api/someCommand";
        this.connection.setCommand("someCommand");
        URL myUrl = this.connection.buildUrl();
        System.out.println(myUrl.toString());
        assert myUrl.toString().equals(expected);
    }

    @Test
    public void testBuildUrlRemovesTrailingSlash() throws MalformedURLException, MissingPropertyException {
        this.connection.setCommand("someCommand/");
        URL myUrl = this.connection.buildUrl();
        assert !myUrl.toString().endsWith("/");
    }
}