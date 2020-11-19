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
public class Export {

    private String ex_id, ex_date, user, ex_type, ex_receiver, ex_publisher;

    public Export() {
    }

    public Export(String ex_id, String ex_date, String user, String ex_type, String ex_receiver, String ex_publisher) {
        this.ex_id = ex_id;
        this.ex_date = ex_date;
        this.user = user;
        this.ex_type = ex_type;
        this.ex_receiver = ex_receiver;
        this.ex_publisher = ex_publisher;
    }

    public String getEx_date() {
        return ex_date;
    }

    public String getEx_id() {
        return ex_id;
    }

    public String getEx_publisher() {
        return ex_publisher;
    }

    public String getEx_receiver() {
        return ex_receiver;
    }

    public String getEx_type() {
        return ex_type;
    }

    public String getUser() {
        return user;
    }

    public void setEx_date(String ex_date) {
        this.ex_date = ex_date;
    }

    public void setEx_id(String ex_id) {
        this.ex_id = ex_id;
    }

    public void setEx_publisher(String ex_publisher) {
        this.ex_publisher = ex_publisher;
    }

    public void setEx_receiver(String ex_receiver) {
        this.ex_receiver = ex_receiver;
    }

    public void setEx_type(String ex_type) {
        this.ex_type = ex_type;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
