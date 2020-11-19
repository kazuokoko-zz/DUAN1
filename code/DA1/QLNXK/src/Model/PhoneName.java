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
public class PhoneName {

    private String id, name, stat;

    public PhoneName() {
    }

    public PhoneName(String id, String name, String stat) {
        this.id = id;
        this.name = name;
        this.stat = stat;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStat() {
        return stat;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    @Override
    public String toString() {
        return name;
    }
}
