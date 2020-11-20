/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import INTERFACE.DAO_Interface;
import Model.Memory;
import Model.Producer;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ma-user
 */
public class MemoryDAO implements DAO_Interface<Memory> {

    @Override
    public boolean insert(Memory e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Memory e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean dalete(Memory e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Memory selectByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Memory> selectAllByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Memory> selectAll() {
        ArrayList<Memory> mmr = new ArrayList<>();
        String sql = "select * from memories";
        try {
            Statement stm = Helper.Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                mmr.add(new Memory(rs.getInt("id"), rs.getInt("amount"), rs.getNString("stat")));
            }

        } catch (Exception e) {
            mmr = null;
        }
        return mmr;
    }

}
