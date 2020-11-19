/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import INTERFACE.DAO_Interface;
import Model.PhoneName;
import Model.Producer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ma-user
 */
public class PhoneNameDAO implements DAO_Interface<PhoneName> {

    @Override
    public void insert(PhoneName e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(PhoneName e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dalete(PhoneName e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PhoneName selectByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<PhoneName> selectAllByColumn(Object... param) {
        ArrayList<PhoneName> pn = new ArrayList<>();
        String sql = "select * from phonenames\n"
                + "where id like ?";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, (String) param[0]);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                pn.add(new PhoneName(rs.getNString("id"), rs.getNString("name"), rs.getNString("stat")));
            }

        } catch (Exception e) {
            pn = null;
        }
        return pn;
    }

    public ArrayList<PhoneName> selectAll() {
        ArrayList<PhoneName> pn = new ArrayList<>();
        String sql = "select * from producers";
        try {
            Statement stm = Helper.Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                pn.add(new PhoneName(rs.getNString("id"), rs.getNString("name"), rs.getNString("stat")));
            }

        } catch (Exception e) {
            pn = null;
        }
        return pn;
    }

}
