/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.Helper;
import INTERFACE.DAO_Interface;
import Model.Type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author ma-user
 */
public class TypeDAO implements DAO_Interface<Type> {

    @Override
    public boolean insert(Type type) {
        String sql = "insert into types(type_id,type_name,type_stat)\n"
                + "values (?,?,?)";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, type.getType_id());
            stm.setNString(2, type.getType_name());
            stm.setNString(3, type.getType_stat());

            int i = stm.executeUpdate();
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Type type) {
        String sql = "update types\n"
                + "set type_stat = 'KD'\n"
                + "where type_id like ?";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, type.getType_id());

            int i = stm.executeUpdate();
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(Type type) {
        String sql = "update types\n"
                + "set type_stat = 'NB'\n"
                + "where type_id like ?";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, type.getType_id());

            int i = stm.executeUpdate();
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Type selectByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Type> selectAllByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Type select(Type type) {
        Type rt = null;
        String sql = "select * from types\n"
                + "where type_id like ?";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, type.getType_id());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                rt = new Type(rs.getNString("type_id"), rs.getNString("type_name"), rs.getNString("type_stat"));
            }
        } catch (Exception e) {
            rt = null;
        }
        return rt;
    }

    public ArrayList<Type> selectAll() {
        ArrayList<Type> l = new ArrayList();
        String sql = "select * from types\n";
        //  + "where type_stat like 'KD'";
        try {
            Statement stm = Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                l.add(new Type(rs.getNString("type_id"), rs.getNString("type_name"), rs.getNString("type_stat")));
            }
            if (l.size() <= 0) {
                l = null;
            }
        } catch (Exception e) {
            l = null;
        }
        return l;
    }

    public String getRemainType() {
        String sql = "select top 1 products.type_id as type_id, MIN(ex_date) as d\n" +
                "from  products left join export_detail on products.serial = export_detail.serial\n" +
                "inner  join  exports on exports.ex_id = export_detail.ex_id\n" +
                "where products.type_id in (select  type_id from types where type_stat like 'KD')\n" +
                "group  by  products.type_id\n" +
                "order  by d asc";
        try {
            Statement stm = Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                return rs.getNString("type_id");
            } else {
                return null;
            }

        } catch (SQLException throwables) {
            return null;
        }
    }
    public String getNearestType() {
        String sql = "select top 1 products.type_id as type_id, Max(ex_date) as d\n" +
                "from  products left join export_detail on products.serial = export_detail.serial\n" +
                "inner  join  exports on exports.ex_id = export_detail.ex_id\n" +
                "where products.type_id in (select  type_id from types where type_stat like 'KD')\n" +
                "group  by  products.type_id\n" +
                "order  by d Desc";
        try {
            Statement stm = Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                return rs.getNString("type_id");
            } else {
                return null;
            }

        } catch (SQLException throwables) {
            return null;
        }
    }


}
