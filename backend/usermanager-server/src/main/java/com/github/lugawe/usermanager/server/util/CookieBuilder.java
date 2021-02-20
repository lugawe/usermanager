package com.github.lugawe.usermanager.server.util;

import org.apache.commons.lang3.time.DateUtils;

import javax.ws.rs.core.NewCookie;
import java.util.Date;

public class CookieBuilder {

    private String name;
    private String value;
    private String path;
    private String domain;
    private int version = NewCookie.DEFAULT_VERSION;
    private String comment;
    private int maxAge = NewCookie.DEFAULT_MAX_AGE;
    private Date expiry;
    private boolean secure;
    private boolean httpOnly;

    public CookieBuilder() {
    }

    public CookieBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CookieBuilder value(String value) {
        this.value = value;
        return this;
    }

    public CookieBuilder path(String path) {
        this.path = path;
        return this;
    }

    public CookieBuilder domain(String domain) {
        this.domain = domain;
        return this;
    }

    public CookieBuilder version(int version) {
        this.version = version;
        return this;
    }

    public CookieBuilder comment(String comment) {
        this.comment = comment;
        return this;
    }

    public CookieBuilder maxAge(int maxAge) {
        this.maxAge = maxAge;
        return this;
    }

    public CookieBuilder expiry(Date expiry) {
        this.expiry = expiry;
        return this;
    }

    public CookieBuilder lifetime(int seconds) {
        if (seconds < 0) {
            throw new IllegalArgumentException("param seconds can't be negative");
        }
        Date date = DateUtils.addSeconds(new Date(), seconds);
        return expiry(date);
    }

    public CookieBuilder secure(boolean secure) {
        this.secure = secure;
        return this;
    }

    public CookieBuilder httpOnly(boolean httpOnly) {
        this.httpOnly = httpOnly;
        return this;
    }

    public NewCookie build() {
        return new NewCookie(name, value, path, domain, version, comment, maxAge, expiry, secure, httpOnly);
    }

}
