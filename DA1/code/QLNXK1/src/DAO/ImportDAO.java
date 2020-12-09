/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.Helper;
import INTERFACE.DAO_Interface;
import Model.Import;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author ma-user
 */
public class ImportDAO implements DAO_Interface<Import> {

    @Override
    public boolean insert(Import anImport) {
        String sql = "insert into imports(im_id,im_date,im_sum_price,sup_id,[user],im_checker,im_deliver,im_stat)\n"
                + "values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, anImport.getIm_id());
            stm.setNString(2, new SimpleDateFormat("yyyy-MM-dd").format(anImport.getIm_date()));
            stm.setDouble(3, anImport.getIm_sum_price());
            stm.setInt(4, anImport.getSup_id());
            stm.setNString(5, anImport.getUser());
            stm.setNString(6, "");
            stm.setNString(7, "");
            stm.setNString(8, anImport.getIm_stat());
            int i = stm.executeUpdate();

            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Import anImport) {
        String sql = "update imports\n"
                + "set im_date = ?, im_sum_price = ?, sup_id = ?, [user] = ?, im_checker = ?, im_deliver = ?, im_stat = ?\n"
                + "where im_id like ?";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(8, anImport.getIm_id());
            stm.setNString(1, new SimpleDateFormat("yyyy-MM-dd").format(anImport.getIm_date()));
            stm.setDouble(2, anImport.getIm_sum_price());
            stm.setInt(3, anImport.getSup_id());
            stm.setNString(4, anImport.getUser());
            stm.setNString(5, anImport.getIm_checker());
            stm.setNString(6, anImport.getIm_deliver());
            stm.setNString(7, anImport.getIm_stat());
            int i = stm.executeUpdate();

            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public boolean delete(Import anImport) {
        String sql = "update imports\n"
                + "set im_stat = 'DX'\n"
                + "where im_id like ?";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, anImport.getIm_id());
            int i = stm.executeUpdate();

            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException throwables) {
            return false;
        }
    }

    @Override
    public Import selectByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Import> selectAllByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean confirm(Import anImport) {
        String sql = "update imports\n"
                + "set im_stat = 'NDH',im_deliver = ?,im_checker = ?\n"
                + "where im_id like ?";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, anImport.getIm_deliver());
            stm.setNString(2, anImport.getIm_checker());
            stm.setNString(3, anImport.getIm_id());
            int i = stm.executeUpdate();

            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public boolean closeEdit(Import anImport) {
        String sql = "update imports\n"
                + "set im_stat = 'DNX'\n"
                + "where im_id like ?";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, anImport.getIm_id());
            int i = stm.executeUpdate();

            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public ArrayList<Import> getConfirmedImport() {
        ArrayList<Import> lst = new ArrayList<>();
        String sql = "select * from imports\n"
                + "where im_id in (select distinct imports.im_id\n"
                + "from imports left outer join products on imports.im_id = products.im_id\n"
                + "inner join import_detail on imports.im_id = import_detail.im_id\n"
                + "where im_stat like N'NDH'"
                + "group by imports.im_id\n"
                + "having not(count(products.im_id) = sum(import_detail.imd_amount)))";

        try {
            Statement stm = Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                lst.add(new Import(rs.getNString("im_id"),
                        rs.getDate("im_date"),
                        rs.getNString("user"),
                        rs.getNString("im_checker"),
                        rs.getNString("im_deliver"),
                        rs.getInt("sup_id"),
                        rs.getDouble("im_sum_price"),
                        rs.getNString("im_stat")));
            }
        } catch (Exception e) {
        }
        return lst;
    }

    public ArrayList getCountImport(int sup_id) {
        ArrayList r = new ArrayList();
        String sql = "select count(imports.sup_id) as sl, iif(sum(im_sum_price) is null,0.0,sum(im_sum_price)) as tt\n"
                + " from suppliers left outer join imports on suppliers.sup_id = imports.sup_id\n"
                + "where suppliers.sup_id = ? and im_stat like 'DNX'";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setInt(1, sup_id);
            ResultSet rs = stm.executeQuery();
            rs.next();
            int sl = rs.getInt("sl");
            double tt = rs.getDouble("tt");
            r.add(sl);
            r.add(tt);
        } catch (SQLException throwables) {

        }
        return r;
    }

    public Date getNearestImportDate() {
        String sql = "select MAX(im_date) as d from imports\n"
                + "where im_stat like 'DNX'";
        try {
            Statement stm = Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            return rs.getDate("d");

        } catch (SQLException throwables) {
            return null;
        }
    }

    public Integer getThisMonthImportCount() {
        String sql = "select count(*) as sl from imports\n"
                + "where month(getDate()) = month(im_date) and year(getdate())=year(im_date) and im_stat like 'DNX'";
        try {
            Statement stm = Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            return rs.getInt("sl");

        } catch (SQLException throwables) {
            return null;
        }
    }

    public int getNextImport() {
        String sql = "select iif(max(Right(im_id,18)) is null , 1 ,cast(max(Right(im_id,18))as int) +1)  as next\n"
                + "from imports \n";
        try {
            Statement stm = Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            return rs.getInt("next");
        } catch (SQLException throwables) {
            return -1;
        }
    }

    public boolean updateSumPrice(Import anImport) {
        String sql = "update imports\n"
                + "set im_sum_price = ?\n"
                + "where im_id like ?";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setDouble(1, anImport.getIm_sum_price());
            stm.setNString(2, anImport.getIm_id());
            int i = stm.executeUpdate();

            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public ArrayList<Import> getList() {
        return getList(null, null, null, null);
    }

    public ArrayList<Import> getList(String im_id, String sup_name, Date from, Date to) {
        ArrayList<Import> l = new ArrayList<>();
        String sql = "select IMPORTS.im_id as im_id,im_date,im_sum_price,im_checker,im_deliver,im_stat,[user],Imports.sup_id as sup_id\n"
                + " from IMPORTS inner join SUPPLIERS on IMPORTS.sup_id = SUPPLIERS.sup_id\n"
                + " where im_id like ? and dbo.f_RemoveAccent(sup_name) like dbo.f_RemoveAccent(?) and ( im_date  between ? and ?)\n"
                + "order by imports.im_stat desc, imports.im_date desc";
        try {
            String id = im_id == null ? "%" : "%" + im_id + "%";
            String name = sup_name == null ? "%" : "%" + sup_name + "%";
            String fdate = from == null ? "1900-01-01" : new SimpleDateFormat("yyyy-MM-dd").format(from);
            String tdate = to == null ? new SimpleDateFormat("yyyy-MM-dd").format(Date.from(LocalDate.now().plusDays(1).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                    : new SimpleDateFormat("yyyy-MM-dd").format(to);

            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, id);
            stm.setNString(2, name);
            stm.setNString(3, fdate);
            stm.setNString(4, tdate);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                l.add(new Import(
                        rs.getNString("im_id"),
                        rs.getDate("im_date"),
                        rs.getNString("user"),
                        rs.getNString("im_checker"),
                        rs.getNString("im_deliver"),
                        rs.getInt("sup_id"),
                        rs.getDouble("im_sum_price"),
                        rs.getNString("im_stat")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
}
