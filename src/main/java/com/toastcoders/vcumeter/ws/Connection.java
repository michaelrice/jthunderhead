package com.toastcoders.vcumeter.ws;

import com.toastcoders.vcumeter.exceptions.MissingPropertyException;
import org.apache.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;

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
public class Connection {

    /**
     * API token for usage meter
     */
    private String token;

    /**
     * Hostname or IP of the usage meter
     */
    private String hostName;

    /**
     * API header required when sending requests to the usage meter
     * This value should likely not have to be changed.
     */
    private String headerKey = "x-usagemeter-authorization";

    /**
     * Base path to the API end point for the usage meter. This value
     * should likely not have to be changed.
     */
    private String basePath = "um/api";

    /**
     * The default protocol used is https, this should only ever have to be
     * changed if you have made strange modifications to the usage meter.
     */
    private String protocol = "https";

    /**
     * This is the command path for the URL. For example:
     * https://usagemeter/um/api/vcenter
     * vcenter would be the command
     */
    private String command;


    /**
     * Tell the system to ignore invalid SSL or not. This is defaulted to trust all
     * SSL because by default the usage meter serves up self signed certs.
     */
    private boolean ignoreSSL = true;

    /**
     * Port that the usage meter is running on. This is 8443 by default
     */
    private int port = 8443;

    /**
     * Content-Type for connection
     */
    private static String contentType = "text/xml";

    /**
     * Logger
     */
    private Logger log = Logger.getLogger(Connection.class);

    public Connection(String hostName, String token) {
        this.hostName = hostName;
        this.token = token;
    }

    public Connection(String hostName, String token, boolean ignoreSSL) {
        this.hostName = hostName;
        this.token = token;
        this.ignoreSSL = ignoreSSL;
    }

    public Connection(String hostName, String token, String headerKey, String basePath,
                      String protocol, String command, boolean ignoreSSL, int port) {
        this.hostName = hostName;
        this.token = token;
        this.headerKey = headerKey;
        this.basePath = basePath;
        this.protocol = protocol;
        this.command = command;
        this.ignoreSSL = ignoreSSL;
        this.port = port;
    }

    public static String getContentType() {
        return contentType;
    }

    public static void setContentType(String contentType) {
        Connection.contentType = contentType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHeaderKey() {
        return headerKey;
    }

    public void setHeaderKey(String headerKey) {
        this.headerKey = headerKey;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public boolean isIgnoreSSL() {
        return ignoreSSL;
    }

    public void setIgnoreSSL(boolean ignoreSSL) {
        this.ignoreSSL = ignoreSSL;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private String stripTrailingSlash(String path) {
        if(path.endsWith("/")) {
            path = path.substring(0, path.length() -1);
        }
        return path;
    }

    private void checkRequiredProperties() throws MissingPropertyException {
        if(this.hostName == null) {
            log.error("Missing hostName when trying to build URL.");
            throw new MissingPropertyException("hostName can not be null");
        }
        if(this.command == null) {
            log.error("Missing command when trying to build URL.");
            throw new MissingPropertyException("command can not be null");
        }
    }

    /**
     * Builds a java.net.URL from the info contained in this class.
     *
     * @return URL
     * @throws MalformedURLException
     * @throws MissingPropertyException
     */
    public URL buildUrl() throws MalformedURLException, MissingPropertyException {
        checkRequiredProperties();
        StringBuilder url = new StringBuilder();
        url.append(this.protocol).append("://").append(this.hostName).append(":").append(this.port);
        url.append("/").append(this.basePath).append("/").append(this.command);
        try {
            return new URL(stripTrailingSlash(url.toString()));
        }
        catch (MalformedURLException e) {
            log.error("Malformed URL: " + url, e);
            throw e;
        }
    }
}
