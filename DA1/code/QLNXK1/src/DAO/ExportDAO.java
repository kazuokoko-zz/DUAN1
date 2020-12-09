/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.Helper;
import INTERFACE.DAO_Interface;
import Model.Export;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author ma-user
 */
public class ExportDAO implements DAO_Interface<Export> {

    @Override
    public boolean insert(Export export) {
        String sql = "insert into exports(ex_id,ex_date,[user],ex_type,ex_receiver,ex_publisher)\n"
                + "values (?,?,?,?,?,?)";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, export.getEx_id());
            stm.setNString(2, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(export.getEx_date()));
            stm.setNString(3, export.getUser());
            stm.setNString(4, export.getEx_type());
            stm.setNString(5, export.getEx_receiver());
            stm.setNString(6, export.getEx_publisher());
            int i = stm.executeUpdate();
            return i > 0;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Export e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Export e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Export selectByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Export> selectAllByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Export select(String id) {
        Export export = null;
        String sql = "select * from exports\n"
                + "where ex_id like ?";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, id);
            ResultSet rs = stm.executeQuery();
            rs.next();
            export = new Export(rs.getNString("ex_id"),
                    rs.getDate("ex_date"),
                    rs.getNString("user"),
                    rs.getNString("ex_type"),
                    rs.getNString("ex_receiver"),
                    rs.getNString("ex_publisher"));
        } catch (Exception e) {
            export = null;
            e.printStackTrace();
        }
        return export;
    }

    public Date getNearestExportDate() {
        String sql = "select MAX(ex_date) as d from exports";
        try {
            Statement stm = Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            return rs.getDate("d");
        } catch (SQLException throwables) {
            return null;
        }
    }

    public int getNextExport() {
        String sql = "select iif(max(Right(ex_id,18)) is null , 1 ,cast(max(Right(ex_id,18))as int) +1)  as next\n"
                + "from exports \n";
        try {
            Statement stm = Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            return rs.getInt("next");
        } catch (SQLException throwables) {
            return -1;
        }
    }

    public ArrayList<Export> getList() {
        return getList(null, null, null);
    }

    public ArrayList<Export> getList(String id, Date from, Date to) {
        ArrayList<Export> l = new ArrayList<>();
        String sql = "select * from exports\n"
                + "where ex_id like ? and (ex_date between ? and ?)\n"
                + "order by ex_date desc";
        try {
            id = id == null ? "%" : "%" + id + "%";
            String fdate = from == null ? "1900-01-01" : new SimpleDateFormat("yyyy-MM-dd").format(from);
            String tdate = to == null ? new SimpleDateFormat("yyyy-MM-dd").format(Date.from(LocalDate.now().plusDays(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                    : new SimpleDateFormat("yyyy-MM-dd").format(to);

            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, id);
            stm.setNString(2, fdate);
            stm.setNString(3, tdate);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                l.add(new Export(
                        rs.getNString("ex_id"),
                        Date.from((rs.getTimestamp("ex_date")).toLocalDateTime().atZone(ZoneId.systemDefault()).toInstant()),
                        rs.getNString("user"),
                        rs.getNString("ex_type"),
                        rs.getNString("ex_receiver"),
                        rs.getNString("ex_publisher")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
}
