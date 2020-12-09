/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import INTERFACE.DAO_Interface;
import Model.Import;
import Model.Import_Detail;
import Model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author ma-user
 */
public class ProductDAO implements DAO_Interface<Product> {

    @Override
    public boolean insert(Product product) {
        String sql = "insert into products(im_id,type_id,serial,shel_id,stat)\n"
                + "values (?,?,?,?,?)";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, product.getIm_id());
            stm.setNString(2, product.getType_id());
            stm.setNString(3, product.getSerial());
            stm.setNString(4, product.getShel_id());
            stm.setNString(5, product.getStat());

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
    public boolean update(Product product) {
        String sql = "update  products\n"
                + "set stat = ?\n"
                + "where serial like ?";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, product.getStat());
            stm.setNString(2, product.getSerial());

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
    public boolean delete(Product product) {
        String sql = "delete from products\n"
                + "where serial like ?";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, product.getSerial());

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

    public void updateAll(ArrayList<Product> lst) {
        for (Product product : lst) {
            product.setStat("sansang");
            update(product);
        }
    }

    @Override
    public Product selectByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Product> selectAllByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Product> selectByImport(Import anImport) {
        String sql = "select * from products\n"
                + "where im_id like ?";
        ArrayList<Product> l = new ArrayList<>();
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, anImport.getIm_id());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                l.add(new Product(rs.getInt("id"),
                        rs.getNString("im_id"),
                        rs.getNString("type_id"),
                        rs.getNString("serial"),
                        rs.getNString("shel_id"),
                        rs.getNString("stat")));
            }

        } catch (Exception e) {
        }
        return l;
    }

    public int getNumOfPhoneInStorage(String type_id) {
        String sql = "select count(*) as sl from products\n"
                + "where stat like 'sansang' and id like ?";
        int i;
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, type_id);
            ResultSet rs = stm.executeQuery();
            rs.next();
            int j = rs.getInt("sl");
            i = Math.max(j, 0);
        } catch (Exception e) {
            i = -1;
        }
        return i;
    }

    public int getAllNumOfPhoneInStorage() {
        String sql = "select count(*) as sl from products\n"
                + "where stat like 'sansang'";
        int i;
        try {
            Statement stm = Helper.Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            int j = rs.getInt("sl");
            i = Math.max(j, 0);
        } catch (Exception e) {
            i = -1;
        }
        return i;
    }

    public int getNumberProductOfImport(Import anImport) {
        String sql = "select count(*) as sl from products\n"
                + "where im_id like ?";
        int i;
        try {
            PreparedStatement statement = Helper.Helper.connection.prepareStatement(sql);
            statement.setNString(1, anImport.getIm_id());
            ResultSet rs = statement.executeQuery();
            rs.next();
            int j = rs.getInt("sl");
            i = Math.max(j, 0);
        } catch (Exception e) {
            i = -1;
        }
        return i;
    }

    public int getNumberProductOfImportDetail(Import_Detail import_Detail) {
        String sql = "select count(*) as sl from products\n"
                + "where im_id like ? and type_id like ?";
        int i;
        try {
            PreparedStatement statement = Helper.Helper.connection.prepareStatement(sql);
            statement.setNString(1, import_Detail.getIm_id());
            statement.setNString(2, import_Detail.getType_id());
            ResultSet rs = statement.executeQuery();
            rs.next();
            int j = rs.getInt("sl");
            i = Math.max(j, 0);
        } catch (Exception e) {
            i = -1;
        }
        return i;
    }

}
