/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import INTERFACE.DAO_Interface;
import Model.Producer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ma-user
 */
public class ProducerDAO implements DAO_Interface<Producer> {

    @Override
    public boolean insert(Producer e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Producer e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean dalete(Producer e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Producer selectByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Producer> selectAllByColumn(Object... param) {
        ArrayList<Producer> prd = new ArrayList<>();
        String sql = "select * from producers\n"
                + "where id like ? and name like ?";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, "%" + ((Producer) param[0]).getId() + "%");
            stm.setNString(2, "%" + ((Producer) param[0]).getName() + "%");

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                prd.add(new Producer(rs.getNString("id"), rs.getNString("name"), rs.getNString("stat")));
            }

        } catch (Exception e) {
            prd = null;
        }
        return prd;
    }

    public ArrayList<Producer> selectAll() {
        ArrayList<Producer> prd = new ArrayList<>();
        String sql = "select * from producers";
        try {
            Statement stm = Helper.Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                prd.add(new Producer(rs.getNString("id"), rs.getNString("name"), rs.getNString("stat")));
            }

        } catch (Exception e) {
            prd = null;
        }
        return prd;
    }

}
