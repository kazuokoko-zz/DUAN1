/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.Helper;
import INTERFACE.DAO_Interface;
import Model.Export;
import Model.Export_Detail;
import Model.Product;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author ma-user
 */
public class Export_DetailDAO implements DAO_Interface<Export_Detail> {

    @Override
    public boolean insert(Export_Detail export_detail) {
        String sql = "insert into export_detail(ex_id,serial)\n"
                + "values (?,?)";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, export_detail.getEx_id());
            stm.setNString(2, export_detail.getSerial());
            int i = stm.executeUpdate();
            if (i > 0) {
                String ty = (new ExportDAO()).select(export_detail.getEx_id()).getEx_type();
                Product t = new Product(0, "", "", export_detail.getSerial(), "",
                        ty.equals("XB") ? "daban" : "dangtrungbay");
                (new ProductDAO()).update(t);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Export_Detail export_detail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Export_Detail export_detail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Export_Detail selectByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Export_Detail> selectAllByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Export_Detail> getList(Export export) {
        ArrayList<Export_Detail> l = new ArrayList<>();
        String sql = "select * from export_detail\n"
                + "where ex_id like ?";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, export.getEx_id());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                l.add(new Export_Detail(
                        rs.getInt("exd_id"),
                        rs.getNString("ex_id"),
                        rs.getNString("serial")));
            }
        } catch (Exception e) {
        }
        return l;
    }

    public int getMonthExportCount() {
        String sql = "select count(serial) as sl \n"
                + "from export_detail inner join exports on exports.ex_id = export_detail.ex_id\n"
                + "where month(getDate()) = month(ex_date) and year(getdate())=year(ex_date)";
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
