/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import INTERFACE.DAO_Interface;
import Model.Area;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ma-user
 */
public class AreaDAO implements DAO_Interface<Area> {

    @Override
    public boolean insert(Area e) {
        String sql = "insert into Areas(id,name,stat)\n"
                + "values(?,?,?)";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, e.getId());
            stm.setNString(2, e.getName());
            stm.setNString(3, e.getStat());

            int i = stm.executeUpdate();
            if (i > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean update(Area e) {
        String sql = "update areas\n"
                + "set name = ?\n"
                + "where id like ?";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, e.getName());
            stm.setNString(2, e.getId());

            int i = stm.executeUpdate();
            if (i > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean delete(Area e) {
        String sql = "update areas\n"
                + "set stat = N'DA'\n"
                + "where id like ?";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, e.getId());

            int i = stm.executeUpdate();
            if (i > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Area selectByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Area> selectAllByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Area> getList() {
        return getList("", false, false);
    }

    public ArrayList<Area> getList(String find, boolean byname, boolean isfind) {
        ArrayList<Area> l = new ArrayList<>();
        String sql = "select * from AREAS\n"
                + "where id like ? and name like ? and stat like 'AC'";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            String name = (byname ? "%".concat(find).concat("%") : "%");
            String id = (byname ? "%" : "%".concat(find).concat("%"));
            stm.setNString(1, isfind ? id : "%");
            stm.setNString(2, isfind ? name : "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                l.add(new Area(rs.getNString("id"), rs.getNString("name"), rs.getNString("stat")));
            }
            rs.close();
            stm.close();
            if (l.size() > 0) {
                return l;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public String getAreaName(String id) {
        String sql = "select name from AREAS\n"
                + "where id like ?";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getNString("name");
            } else {
                return "";
            }

        } catch (Exception e) {
            return "";
        }
    }

}
