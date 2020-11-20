/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import INTERFACE.DAO_Interface;
import Model.Colour;
import Model.Producer;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ma-user
 */
public class ColourDAO implements DAO_Interface<Colour> {

    @Override
    public boolean insert(Colour e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Colour e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean dalete(Colour e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Colour selectByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Colour> selectAllByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Colour> selectAll() {
        ArrayList<Colour> cl = new ArrayList<>();
        String sql = "select * from colours";
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
