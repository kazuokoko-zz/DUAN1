/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author ma-user
 */
public class GUIDAO {

    public static Date layNNGN(String type_id) {
        String sql = "select MAX(IMPORTS.im_date) as nngn\n"
                + "from IMPORT_DETAIL inner join IMPORTS on IMPORT_DETAIL.im_id = IMPORTS.im_id\n"
                + "where type_id like ?";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, type_id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getDate("nngn");
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static Date layNXGN(String type_id) {
        String sql = "select MAX(EXPORTS.ex_date) as nxgn\n"
                + "from EXPORT_DETAIL inner join EXPORTS on EXPORTS.ex_id = EXPORT_DETAIL.ex_id\n"
                + "inner join PRODUCTS on EXPORT_DETAIL.serial = PRODUCTS.serial\n"
                + "where type_id like ?";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, type_id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getDate("nxgn");
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    
    

}
