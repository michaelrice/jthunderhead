package com.toastcoders.vcumeter.datatypes;

import com.toastcoders.vcumeter.exceptions.InvalidCountryCode;
import com.neovisionaries.i18n.CountryCode;

import java.util.List;

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

public class Customer {

    private String name;
    private String countryCode;
    private String postalCode;
    private int id;

    public Customer(String name, String countryCode, String postalCode) throws InvalidCountryCode {
        this.name = name;
        setCountryCode(countryCode);
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) throws InvalidCountryCode {
        CountryCode cc = CountryCode.getByCode(countryCode);
        if(cc == null) {
            // There is a chance it came in as United States for example.
            List<CountryCode> results = CountryCode.findByName(countryCode);
            if(results.size() < 1 || results.size() > 1) {
                throw new InvalidCountryCode("Invalid Country Code detected: " + countryCode);
            }
            cc = results.get(0);
        }

        if(cc.toString().length() != 2) {
            throw new InvalidCountryCode("Invalid Country Code detected: " + countryCode);
        }

        this.countryCode = cc.toString();
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
