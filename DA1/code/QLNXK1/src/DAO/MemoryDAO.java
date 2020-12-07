/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import INTERFACE.DAO_Interface;
import Model.Memory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ma-user
 */
public class MemoryDAO implements DAO_Interface<Memory> {

    @Override
    public boolean insert(Memory memory) {
        String sql = "insert into memories(amount,stat)\n"
                + "values (?,?)";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setInt(1, memory.getAmount());
            stm.setNString(2, memory.getStat());

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
    public boolean update(Memory memory) {
         String sql = "update memories\n"
                + "set stat = ?\n"
                + "where amount = ?";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, "DHD");
            stm.setInt(2, memory.getAmount());

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
    public boolean delete(Memory memory) {
        String sql = "update memories\n"
                + "set stat = ?\n"
                + "where amount = ?";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, "KHD");
            stm.setInt(2, memory.getAmount());

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
    public Memory selectByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Memory> selectAllByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Memory select(Memory memory) {
        Memory mmr = null;
        String sql = "select * from memories\n"
                + "where amount = ?";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setInt(1, memory.getAmount());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                mmr = new Memory(rs.getInt("amount"), rs.getNString("stat"));
            }
        } catch (Exception e) {
            mmr = null;
        }
        return mmr;
    }

    public ArrayList<Memory> selectAll() {
        ArrayList<Memory> mmr = new ArrayList<>();
        String sql = "select * from memories\n"
                + "where stat like 'DHD'";
        try {
            Statement stm = Helper.Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                mmr.add(new Memory(rs.getInt("amount"), rs.getNString("stat")));
            }

        } catch (Exception e) {
            mmr = null;
        }
        return mmr;
    }

}
