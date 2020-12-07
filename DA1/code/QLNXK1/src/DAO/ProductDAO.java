/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import INTERFACE.DAO_Interface;
import Model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author ma-user
 */
public class ProductDAO implements DAO_Interface<Product> {

    @Override
    public boolean insert(Product e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Product e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Product e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product selectByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Product> selectAllByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getNumOfPhoneInStorage(String type_id) {
        String sql = "select count(*) as sl from products\n"
                + "where stat like 'sansang' and id like ?";
        int i;
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, type_id);
            ResultSet rs = stm.executeQuery();
            rs.next();
            int j = rs.getInt("sl");
            i = Math.max(j, 0);
        } catch (Exception e) {
            i = -1;
        }
        return i;
    }

    public int getAllNumOfPhoneInStorage() {
        String sql = "select count(*) as sl from products\n"
                + "where stat like 'sansang'";
        int i;
        try {
            Statement stm = Helper.Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            int j = rs.getInt("sl");
            i = Math.max(j, 0);
        } catch (Exception e) {
            i = -1;
        }
        return i;
    }
}
