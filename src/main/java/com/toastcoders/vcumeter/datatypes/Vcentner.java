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

public class Vcentner {

    private String userName;
    private String instanceUuid;
    private String version;
    private String fullName;
    private String hostName;
    private String password;
    private int id;
    private boolean monitor;
    private boolean meter;
    private boolean active;


    public Vcentner(String userName, String password, String hostName, boolean monitor) {
        setUserName(userName);
        setPassword(password);
        setHostName(hostName);
        setMonitor(monitor);
    }

    public Vcentner(String userName, String password, String hostName) {
        this(userName, password, hostName, true);
    }

    public Vcentner(String userName, String instanceUuid, String version,
                    String fullName, String hostName, String password,
                    int id, boolean monitor, boolean meter, boolean active) {
        this.userName = userName;
        this.instanceUuid = instanceUuid;
        this.version = version;
        this.fullName = fullName;
        this.hostName = hostName;
        this.password = password;
        this.id = id;
        this.monitor = monitor;
        this.meter = meter;
        this.active = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInstanceUuid() {
        return instanceUuid;
    }

    public void setInstanceUuid(String instanceUuid) {
        this.instanceUuid = instanceUuid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isMonitor() {
        return monitor;
    }

    public void setMonitor(boolean monitor) {
        this.monitor = monitor;
    }

    public boolean isMeter() {
        return meter;
    }

    public void setMeter(boolean meter) {
        this.meter = meter;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
