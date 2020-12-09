/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.AreaDAO;

/**
 *
 * @author ma-user
 */
public class Shelve {

    private String shel_id;
    private int shel_stat;

    public Shelve() {
    }

    public Shelve(String shel_id, int shel_stat) {
        this.shel_id = shel_id;
        this.shel_stat = shel_stat;
    }

    public String getShel_id() {
        return shel_id;
    }

    public int getShel_stat() {
        return shel_stat;
    }

    public void setShel_id(String shel_id) {
        this.shel_id = shel_id;
    }

    public void setShel_stat(int shel_stat) {
        this.shel_stat = shel_stat;
    }

    @Override
    public String toString() {
        return "Kệ " + shel_id.substring(4, 8);
    }

    public String toStringName() {
        return Helper.Helper.toShelveName(shel_id);
    }
}
