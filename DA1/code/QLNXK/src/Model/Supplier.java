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
public class Supplier {

    private int sup_id;
    private String sup_name, sup_phone, sup_email, sup_address, sup_taxcode, sup_fax, sup_bank, sup_evaluate;

    public Supplier() {
    }

    public Supplier(int sup_id, String sup_name, String sup_phone, String sup_email, String sup_address, String sup_taxcode, String sup_fax, String sup_bank, String sup_evaluate) {
        this.sup_id = sup_id;
        this.sup_name = sup_name;
        this.sup_phone = sup_phone;
        this.sup_email = sup_email;
        this.sup_address = sup_address;
        this.sup_taxcode = sup_taxcode;
        this.sup_fax = sup_fax;
        this.sup_bank = sup_bank;
        this.sup_evaluate = sup_evaluate;
    }

    public String getSup_address() {
        return sup_address;
    }

    public String getSup_bank() {
        return sup_bank;
    }

    public int getSup_id() {
        return sup_id;
    }

    public void setSup_address(String sup_address) {
        this.sup_address = sup_address;
    }

    public void setSup_bank(String sup_bank) {
        this.sup_bank = sup_bank;
    }

    public void setSup_id(int sup_id) {
        this.sup_id = sup_id;
    }

    public String getSup_name() {
        return sup_name;
    }

    public void setSup_name(String sup_name) {
        this.sup_name = sup_name;
    }

    public String getSup_phone() {
        return sup_phone;
    }

    public void setSup_phone(String sup_phone) {
        this.sup_phone = sup_phone;
    }

    public String getSup_email() {
        return sup_email;
    }

    public void setSup_email(String sup_email) {
        this.sup_email = sup_email;
    }

    public String getSup_taxcode() {
        return sup_taxcode;
    }

    public void setSup_taxcode(String sup_taxcode) {
        this.sup_taxcode = sup_taxcode;
    }

    public String getSup_fax() {
        return sup_fax;
    }

    public void setSup_fax(String sup_fax) {
        this.sup_fax = sup_fax;
    }

    public String getSup_evaluate() {
        return sup_evaluate;
    }

    public void setSup_evaluate(String sup_evaluate) {
        this.sup_evaluate = sup_evaluate;
    }

}
