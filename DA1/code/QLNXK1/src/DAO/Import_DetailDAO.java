/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.Helper;
import INTERFACE.DAO_Interface;
import Model.Import_Detail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author ma-user
 */
public class Import_DetailDAO implements DAO_Interface<Import_Detail> {

    @Override
    public boolean insert(Import_Detail import_detail) {
        String sql = "insert into import_detail(im_id,type_id,imd_amount,imd_price)\n" +
                "values (?,?,?,?)";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, import_detail.getIm_id());
            stm.setNString(2, import_detail.getType_id());
            stm.setInt(3, import_detail.getImd_amount());
            stm.setDouble(4, import_detail.getImd_price());
            int i = stm.executeUpdate();

            if (i > 0) return true;
            else return false;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean update(Import_Detail import_detail) {
        String sql = "update import_detail\n" +
                "set imd_amount = ?,imd_price = ? , type_id = ?\n" +
                "where imd_id = ?";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setInt(4, import_detail.getImd_id());
            stm.setNString(3, import_detail.getType_id());
            stm.setInt(1, import_detail.getImd_amount());
            stm.setDouble(2, import_detail.getImd_price());
            int i = stm.executeUpdate();
            if (i > 0) return true;
            else return false;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean delete(Import_Detail import_detail) {
        String sql = "delete  from import_detail \n" +
                "where imd_id = ?";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setInt(1, import_detail.getImd_id());
            int i = stm.executeUpdate();
            if (i > 0) return true;
            else return false;
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public Import_Detail selectByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Import_Detail> selectAllByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Import_Detail> select(String im_id) {
        ArrayList<Import_Detail> l = new ArrayList<>();

        String sql = "select * from import_detail\n" +
                "where im_id like ?";

        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, im_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                l.add(new Import_Detail(rs.getInt("imd_id"),
                        rs.getInt("imd_amount"),
                        rs.getNString("im_id"),
                        rs.getNString("type_id"),
                        rs.getDouble("imd_price")));
            }
            if (l.size() <= 0) l = null;
        } catch (SQLException throwables) {
            l = null;
        }
        return l;
    }

    public int getMonthImportCount() {
        String sql = "select Sum(imd_amount) as sl \n" +
                "from import_detail inner join imports on imports.im_id = import_detail.im_id\n" +
                "where month(getDate()) = month(im_date) and year(getdate())=year(im_date)";
        try {
            Statement stm = Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            return rs.getInt("sl");
        } catch (SQLException throwables) {
            return 0;
        }
    }

}
