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
public class Import {

    private String im_id, im_date, user, im_checker, im_diliver;
    private int sup_id;
    private double im_sum_price;

    public Import() {
    }

    public Import(String im_id, String im_date, String user, String im_checker, String im_diliver, int sup_id, double im_sum_price) {
        this.im_id = im_id;
        this.im_date = im_date;
        this.user = user;
        this.im_checker = im_checker;
        this.im_diliver = im_diliver;
        this.sup_id = sup_id;
        this.im_sum_price = im_sum_price;
    }

    public String getIm_checker() {
        return im_checker;
    }

    public String getIm_date() {
        return im_date;
    }

    public String getIm_diliver() {
        return im_diliver;
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

    public void setIm_date(String im_date) {
        this.im_date = im_date;
    }

    public void setIm_diliver(String im_diliver) {
        this.im_diliver = im_diliver;
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

}
