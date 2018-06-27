package com.academy.automation.practice.ddt.manager.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("data")
public class LoginData {
    private String login;
    private String password;
    private String msg;

    public LoginData(String login, String password, String msg) {
        this.login = login;
        this.password = password;
        this.msg = msg;
    }

    public Object[] toArray() {
        return new Object[]{login, password, msg};
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
