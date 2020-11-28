/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.text.DecimalFormat;

/**
 *
 * @author ma-user
 */
public class Memory {

    private int amount;
    private String stat;

    public Memory() {
    }

    public Memory(int amount, String stat) {
        this.amount = amount;
        this.stat = stat;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    @Override
    public String toString() {
        return (new DecimalFormat("###")).format(amount) + "GB";
    }
}
