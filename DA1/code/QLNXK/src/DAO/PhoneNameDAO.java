/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import INTERFACE.DAO_Interface;
import Model.PhoneName;
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
    public boolean insert(PhoneName phoneName) {
        String sql = "insert into phonenames(id,name,stat)\n"
                + "values (?,?,?)";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, phoneName.getId());
            stm.setNString(2, phoneName.getName());
            stm.setNString(3, phoneName.getStat());

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
    public boolean update(PhoneName phoneName) {
        String sql = "update phonenames\n"
                + "set name = ?\n"
                + "where id like ?";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, phoneName.getName());
            stm.setNString(2, phoneName.getId());

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
    public boolean delete(PhoneName phoneName) {
        String sql = "update phonenames\n"
                + "set stat = N'KHD'\n"
                + "where id like ?";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, phoneName.getId());

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

    public PhoneName select(PhoneName phoneName) {
        PhoneName pn = null;
        String sql = "select * from phonenames\n"
                + "where id like ? and name like ? and stat like 'DHD'";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, phoneName.getId());
            stm.setNString(2, phoneName.getName());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                pn = new PhoneName(rs.getNString("id"), rs.getNString("name"), rs.getNString("stat"));
            }

        } catch (Exception e) {
            pn = null;
        }
        return pn;
    }

    public ArrayList<PhoneName> selectAll() {
        ArrayList<PhoneName> pn = new ArrayList<>();
        String sql = "select * from phonenames where stat like 'DHD'";
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
