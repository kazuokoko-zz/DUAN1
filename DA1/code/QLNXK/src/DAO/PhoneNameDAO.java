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
        String sql = "insert into phonenames(id_prd,name,stat,num_order)\n"
                + "values (?,?,?,?)";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, phoneName.getId());
            stm.setNString(2, phoneName.getName());
            stm.setNString(3, phoneName.getStat());
            stm.setInt(4, phoneName.getNum_order());

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

    public boolean update(PhoneName phoneName, String newName) {
        String sql = "update phonenames\n"
                + "set name = ?\n"
                + "where id_prd like ? and name like ?";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, newName);
            stm.setNString(2, phoneName.getId());
            stm.setNString(3, phoneName.getName());

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
                + "where id_prd like and num_order = ?";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, phoneName.getId());
            stm.setInt(2, phoneName.getNum_order());

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
                + "where id_prd like ?";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, (String) param[0]);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                pn.add(new PhoneName(rs.getNString("id_prd"), rs.getNString("name"), rs.getNString("stat"), rs.getInt("num_order")));
            }

        } catch (Exception e) {
            pn = null;
        }
        return pn;
    }

    public int nextName(String prd_id) {
        String sql = "select iif(max(num_order) is null,1,max(num_order)+1) as max\n"
                + "from phonenames\n"
                + "where id_prd like ?";

        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, prd_id);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return rs.getInt("max");
            } else {
                return -1;
            }

        } catch (Exception ex) {
            return -2;
        }
    }

    public PhoneName select(PhoneName phoneName) {
        PhoneName pn = null;
        String sql = "select * from phonenames\n"
                + "where id_prd like ? and name like ? and stat like 'DHD'";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, phoneName.getId());
            stm.setNString(2, phoneName.getName());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                pn = new PhoneName(rs.getNString("id_prd"), rs.getNString("name"), rs.getNString("stat"), rs.getInt("num_order"));
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
                pn.add(new PhoneName(rs.getNString("id_prd"), rs.getNString("name"), rs.getNString("stat"), rs.getInt("num_order")));
            }

        } catch (Exception e) {
            pn = null;
        }
        return pn;
    }

    @Override
    public boolean update(PhoneName e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
