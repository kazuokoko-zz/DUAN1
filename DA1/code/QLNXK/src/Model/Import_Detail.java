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
public class Import_Detail {

    private String imd_id, imd_amount;
    private String im_id, type_id;
    private double imd_price;

    public Import_Detail() {
    }

    public Import_Detail(String imd_id, String imd_amount, String im_id, String type_id, double imd_price) {
        this.imd_id = imd_id;
        this.imd_amount = imd_amount;
        this.im_id = im_id;
        this.type_id = type_id;
        this.imd_price = imd_price;
    }

    public String getIm_id() {
        return im_id;
    }

    public String getImd_amount() {
        return imd_amount;
    }

    public String getImd_id() {
        return imd_id;
    }

    public double getImd_price() {
        return imd_price;
    }

    public String getType_id() {
        return type_id;
    }

    public void setIm_id(String im_id) {
        this.im_id = im_id;
    }

    public void setImd_amount(String imd_amount) {
        this.imd_amount = imd_amount;
    }

    public void setImd_id(String imd_id) {
        this.imd_id = imd_id;
    }

    public void setImd_price(double imd_price) {
        this.imd_price = imd_price;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

}
