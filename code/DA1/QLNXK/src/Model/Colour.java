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
public class Colour {

    private int id;
    private String name, stat;

    public Colour() {
    }

    public Colour(int id, String name, String stat) {
        this.id = id;
        this.name = name;
        this.stat = stat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount() {
        return name;
    }

    public void setAmount(String name) {
        this.name = name;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    @Override
    public String toString() {
        return name;
    }
}
