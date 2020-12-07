/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.Helper;
import INTERFACE.DAO_Interface;
import Model.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author ma-user
 */
public class SupplierDAO implements DAO_Interface<Supplier> {

    @Override
    public boolean insert(Supplier supplier) {
        String sql = "insert into suppliers(sup_name,sup_phone,sup_email,sup_address,sup_fax,sup_bank,sup_evaluate,sup_stat)\n"
                + "values(?,?,?,?,?,?,?,N'DHD')";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, supplier.getSup_name());
            stm.setNString(2, supplier.getSup_phone());
            stm.setNString(3, supplier.getSup_email());
            stm.setNString(4, supplier.getSup_address());
            stm.setNString(5, supplier.getSup_fax());
            stm.setNString(6, supplier.getSup_bank());
            stm.setNString(7, supplier.getSup_evaluate());
            int i = stm.executeUpdate();
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean update(Supplier supplier) {
        String sql = "update suppliers\n"
                + "set sup_name = ?, sup_phone= ?,sup_email= ?,sup_address= ?,sup_fax= ?,sup_bank= ?,sup_evaluate= ?\n"
                + "where sup_id =?";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, supplier.getSup_name());
            stm.setNString(2, supplier.getSup_phone());
            stm.setNString(3, supplier.getSup_email());
            stm.setNString(4, supplier.getSup_address());
            stm.setNString(5, supplier.getSup_fax());
            stm.setNString(6, supplier.getSup_bank());
            stm.setNString(7, supplier.getSup_evaluate());
            stm.setInt(8, supplier.getSup_id());
            int i = stm.executeUpdate();
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean delete(Supplier supplier) {
        String sql = "update suppliers\n"
                + "set sup_stat = N'KHD'\n"
                + "where sup_id = ?";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setInt(1, supplier.getSup_id());
            int i = stm.executeUpdate();
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public Supplier selectByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Supplier> selectAllByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Supplier select(String phone) {
        Supplier supplier = null;
        String sql = "select * from suppliers\n"
                + "where sup_phone like ?";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, phone);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                supplier = new Supplier(
                        rs.getInt("sup_id"),
                        rs.getNString("sup_name"),
                        rs.getNString("sup_phone"),
                        rs.getNString("sup_email"),
                        rs.getNString("sup_address"),
                        rs.getNString("sup_fax"),
                        rs.getNString("sup_bank"),
                        rs.getNString("sup_evaluate"),
                        rs.getNString("sup_stat")
                );
            }
        } catch (SQLException throwables) {
        }
        return supplier;
    }

    public Supplier select(int id) {
        Supplier supplier = null;
        String sql = "select * from suppliers\n"
                + "where sup_id = ?";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                supplier = new Supplier(
                        rs.getInt("sup_id"),
                        rs.getNString("sup_name"),
                        rs.getNString("sup_phone"),
                        rs.getNString("sup_email"),
                        rs.getNString("sup_address"),
                        rs.getNString("sup_fax"),
                        rs.getNString("sup_bank"),
                        rs.getNString("sup_evaluate"),
                        rs.getNString("sup_stat")
                );
            }
        } catch (SQLException throwables) {
        }
        return supplier;
    }

    public ArrayList<Supplier> selectAll() {
        ArrayList<Supplier> l = new ArrayList<>();
        String sql = "select * from suppliers";
        try {
            Statement stm = Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                l.add(new Supplier(
                        rs.getInt("sup_id"),
                        rs.getNString("sup_name"),
                        rs.getNString("sup_phone"),
                        rs.getNString("sup_email"),
                        rs.getNString("sup_address"),
                        rs.getNString("sup_fax"),
                        rs.getNString("sup_bank"),
                        rs.getNString("sup_evaluate"),
                        rs.getNString("sup_stat")
                ));
            }
            if (l.size() <= 0) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return l;
    }
}
