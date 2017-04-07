
package com.x.entity;

import java.io.Serializable;

/**
 * @author http://blog.csdn.net/java2000_wl
 * @version <b>1.0</b>
 */
public class User implements Serializable {

    private static final long serialVersionUID = -6011241820070393952L;

    private String id;

    private String name;

    private String password;

    /**
     * <br>------------------------------<br>
     */
    public User() {

    }

    /**
     * <br>------------------------------<br>
     */
    public User(final String id, final String name, final String password) {
        super();
        this.id = id;
        this.name = name;
        this.password = password;
    }

    /**
     * 获得id
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     * @param id the id to set
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * 获得name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * 获得password
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置password
     * @param password the password to set
     */
    public void setPassword(final String password) {
        this.password = password;
    }
}
