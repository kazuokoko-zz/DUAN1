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
public class Export_Detail {

    private int exd_id;
    private String ex_id, serial;

    public Export_Detail() {
    }

    public Export_Detail(int exd_id, String ex_id, String serial) {
        this.exd_id = exd_id;
        this.ex_id = ex_id;
        this.serial = serial;
    }

    public String getEx_id() {
        return ex_id;
    }

    public int getExd_id() {
        return exd_id;
    }

    public String getSerial() {
        return serial;
    }

    public void setEx_id(String ex_id) {
        this.ex_id = ex_id;
    }

    public void setExd_id(int exd_id) {
        this.exd_id = exd_id;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

}
