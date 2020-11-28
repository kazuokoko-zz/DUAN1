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
    private int num_order;

    public PhoneName() {
    }

    public PhoneName(String id, String name, String stat, int num_order) {
        this.id = id;
        this.name = name;
        this.stat = stat;
        this.num_order = num_order;
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

    public int getNum_order() {
        return num_order;
    }

    public void setNum_order(int num_order) {
        this.num_order = num_order;
    }

    @Override
    public String toString() {
        return name;
    }
}
