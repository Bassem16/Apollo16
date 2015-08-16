package com.servus.apollo16.Beans;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by basse on 15/08/2015.
 */
public class User implements Serializable{

    private String _id = "";
    private String login = "";
    private String lastName = "";
    private String name = "";
    private String password = "";

    public User(JSONObject json) {
        this.password = "";
        try {
            this.login = json.getString("userPseudo");
            this.lastName= json.getString("userNom");
            this.name = json.getString("userPrenom");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public User(String password, String login) {
        this.password = password;
        this.login = login;
    }

    public User() {
    }

    public User(String login, String lastName, String name, String password) {
        this.login = login;
        this.lastName = lastName;
        this.name = name;
        this.password = password;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
