/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import INTERFACE.DAO_Interface;
import Model.Colour;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ma-user
 */
public class ColourDAO implements DAO_Interface<Colour> {

    @Override
    public boolean insert(Colour colour) {
        String sql = "insert into colours(name,stat)\n"
                + "values (?,?)";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, colour.getName());
            stm.setNString(2, "DHD");

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
    public boolean update(Colour colour) {
        String sql = "update colours\n"
                + "set stat = ?\n"
                + "where id = ?";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, "DHD");
            stm.setInt(2, colour.getId());

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
    public boolean delete(Colour colour) {
        String sql = "update colours\n"
                + "set stat = ?\n"
                + "where id = ?";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, "KHD");
            stm.setInt(2, colour.getId());

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
    public Colour selectByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Colour> selectAllByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Colour select(Colour colour) {
        Colour mau = null;
        String sql = "select * from colours\n"
                + "where name like ?";

        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, colour.getName());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                mau = new Colour(rs.getInt("id"), rs.getNString("name"), rs.getNString("stat"));
            }
        } catch (Exception e) {
            mau = null;
        }
        return mau;
    }

    public ArrayList<Colour> selectAll() {
        ArrayList<Colour> cl = new ArrayList<>();
        String sql = "select * from colours\n"
                + "where stat like 'DHD'";
        try {
            Statement stm = Helper.Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                cl.add(new Colour(rs.getInt("id"), rs.getNString("name"), rs.getNString("stat")));
            }

        } catch (Exception e) {
            cl = null;
        }
        return cl;
    }

}
