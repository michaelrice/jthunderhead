package com.toastcoders.vcumeter.datatypes;

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

public class Rule {

    private String vcServerHost;
    private String customerName;
    private String objectType;
    private String valueType;
    private String value;

    public Rule(String vcServerHost, String customerName, String objectType, String valueType, String value) {
        this.vcServerHost = vcServerHost;
        this.customerName = customerName;
        this.objectType = objectType;
        this.valueType = valueType;
        this.value = value;
    }

    public String getVcServerHost() {
        return vcServerHost;
    }

    public void setVcServerHost(String vcServerHost) {
        this.vcServerHost = vcServerHost;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
