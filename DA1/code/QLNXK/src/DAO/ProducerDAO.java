/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import INTERFACE.DAO_Interface;
import Model.Producer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ma-user
 */
public class ProducerDAO implements DAO_Interface<Producer> {

    @Override
    public boolean insert(Producer producer) {
        String sql = "insert into producers(id,name,stat)\n"
                + "values (?,?,?)";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, producer.getId());
            stm.setNString(2, producer.getName());
            stm.setNString(3, producer.getStat());

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
    public boolean update(Producer producer) {
        String sql = "update producers\n"
                + "set name = ?\n"
                + "where id like ?";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, producer.getName());
            stm.setNString(2, producer.getId());

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
    public boolean delete(Producer producer) {
        String sql = "update producers\n"
                + "set stat = N'KHD'\n"
                + "where id like ?";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, producer.getId());

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
    public Producer selectByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Producer> selectAllByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Producer> selectAll() {
        ArrayList<Producer> prd = new ArrayList<>();
        String sql = "select * from PRODUCERS\n"
                + "where stat like 'DHD'";
        try {
            Statement stm = Helper.Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                prd.add(new Producer(rs.getNString("id"), rs.getNString("name"), rs.getNString("stat")));
            }
        } catch (Exception e) {
            prd = null;
        }
        return prd.size() > 0 ? prd : null;
    }
}
