/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author ma-user
 */
public class Account {

    private String user, pass, role, stat;

    public Account() {
    }

    public Account(String user, String pass, String role, String stat) {
        this.user = user;
        this.pass = pass;
        this.role = role;
        this.stat = stat;
    }

    public String getPass() {
        return pass;
    }

    public String getRole() {
        return role;
    }

    public String getUser() {
        return user;
    }

    public String getStat() {
        return stat;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
