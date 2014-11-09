package com.toastcoders.vcumeter.datatypes;

import com.toastcoders.vcumeter.exceptions.InvalidCountryCode;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

    @Test(expected = InvalidCountryCode.class)
    public void testCreateCustomerThrowsInvalidCountryCode() throws Exception {
        Customer customer = new Customer("test", "USA Time", "78232");
    }

    @Test
    public void testCreateCustomerWorksAsExpected() throws InvalidCountryCode {
        Customer customer = new Customer("test", "US", "78232");
    }

    @Test
    public void testCreateCustomerWorksWithCountryName() throws InvalidCountryCode {
        Customer customer = new Customer("test", "United States", "78232");
    }
}