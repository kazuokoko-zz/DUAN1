/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import INTERFACE.DAO_Interface;
import Model.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ma-user
 */
public class TypeDAO implements DAO_Interface<Type> {

    @Override
    public void insert(Type e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Type e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dalete(Type e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Type selectByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Type> selectAllByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<ArrayList> getAllDetail(String type_stat) {
        ArrayList<ArrayList> l = new ArrayList();
        String sql = "select TYPES.type_id as id,type_name,COUNT(PRODUCTS.type_id) as sl\n"
                + "from TYPES inner join PRODUCTS on TYPES.type_id = PRODUCTS.type_id\n"
                + "where PRODUCTS.stat like 'SANSANG' and TYPES.type_stat like ?\n"
                + "GROUP by TYPES.type_id,type_name";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, type_stat);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ArrayList tmp = new ArrayList();
                tmp.add(rs.getNString("id"));
                tmp.add(rs.getNString("type_name"));
                tmp.add(rs.getInt("sl"));
                tmp.add(GUIDAO.layNNGN((String) tmp.get(0)));
                tmp.add(GUIDAO.layNXGN((String) tmp.get(0)));
                l.add(tmp);
            }

        } catch (Exception e) {
            l = null;
        }
        return l;
    }

}
