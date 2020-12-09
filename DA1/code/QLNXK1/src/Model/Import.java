/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 * @author ma-user
 */
public class Import {

    private String im_id, user, im_checker, im_deliver, im_stat;
    private Date im_date;
    private int sup_id;
    private double im_sum_price;

    public Import() {
    }

    public Import(String im_id, Date im_date, String user, String im_checker, String im_deliver, int sup_id, double im_sum_price, String im_stat) {
        this.im_id = im_id;
        this.im_date = im_date;
        this.user = user;
        this.im_checker = im_checker;
        this.im_deliver = im_deliver;
        this.sup_id = sup_id;
        this.im_sum_price = im_sum_price;
        this.im_stat = im_stat;
    }

    public String getIm_checker() {
        return im_checker;
    }

    public Date getIm_date() {
        return im_date;
    }

    public String getIm_deliver() {
        return im_deliver;
    }

    public String getIm_id() {
        return im_id;
    }

    public int getSup_id() {
        return sup_id;
    }

    public double getIm_sum_price() {
        return im_sum_price;
    }

    public String getUser() {
        return user;
    }

    public void setIm_checker(String im_checker) {
        this.im_checker = im_checker;
    }

    public void setIm_date(Date im_date) {
        this.im_date = im_date;
    }

    public void setIm_deliver(String im_deliver) {
        this.im_deliver = im_deliver;
    }

    public void setIm_id(String im_id) {
        this.im_id = im_id;
    }

    public void setIm_sum_price(double im_sum_price) {
        this.im_sum_price = im_sum_price;
    }

    public void setSup_id(int sup_id) {
        this.sup_id = sup_id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIm_stat() {
        return im_stat;
    }

    public void setIm_stat(String im_stat) {
        this.im_stat = im_stat;
    }

    @Override
    public String toString() {
        return im_id;
    }
}
