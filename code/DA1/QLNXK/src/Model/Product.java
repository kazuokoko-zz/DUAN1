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
public class Product {

    private int id;
    private String im_id, type_id, serial, shel_id, stat;

    public Product() {
    }

    public Product(int id, String im_id, String type_id, String serial, String shel_id, String stat) {
        this.id = id;
        this.im_id = im_id;
        this.type_id = type_id;
        this.serial = serial;
        this.shel_id = shel_id;
        this.stat = stat;
    }

    public int getId() {
        return id;
    }

    public String getIm_id() {
        return im_id;
    }

    public String getShel_id() {
        return shel_id;
    }

    public String getStat() {
        return stat;
    }

    public String getSerial() {
        return serial;
    }

    public String getType_id() {
        return type_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIm_id(String im_id) {
        this.im_id = im_id;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setShel_id(String shel_id) {
        this.shel_id = shel_id;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

}
