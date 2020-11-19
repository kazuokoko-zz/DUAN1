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
public class Type {

    private String type_id, type_name, type_stat;

    public Type() {
    }

    public Type(String type_id, String type_name, String type_stat) {
        this.type_id = type_id;
        this.type_name = type_name;
        this.type_stat = type_stat;
    }

    public String getType_id() {
        return type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public String getType_stat() {
        return type_stat;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public void setType_stat(String type_stat) {
        this.type_stat = type_stat;
    }

}
