/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import INTERFACE.DAO_Interface;
import Model.Area;
import Model.Shelve;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ma-user
 */
public class ShelveDAO implements DAO_Interface<Shelve> {

    @Override
    public boolean insert(Shelve e) {
        String sql = "insert into shelves(shel_id,shel_stat)\n"
                + "values(?,?)";

        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, e.getShel_id());
            stm.setInt(2, e.getShel_stat());

            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception ex) {
            return false;
        }
    }

    public void insert(String area) {
        String n = String.format("%04d", nextShelve(area));
        for (int i = 1; i < 10; i++) {
            insert(new Shelve(area + "-" + n + "-" + String.valueOf(i), 0));
        }
    }

    @Override
    public boolean update(Shelve e) {
        String sql = "update shelves\n"
                + "set shel_stat = ?\n"
                + "where shel_id like ?";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setInt(1, e.getShel_stat());
            stm.setNString(2, e.getShel_id());

            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean delete(Shelve shelve) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete(Area area, Shelve shelve) {
        for (int i = 1; i < 10; i++) {
            update(new Shelve(area.getId() + "-" + shelve.getShel_id().substring(4, 8) + "-" + String.valueOf(i), 101));
        }
    }

    @Override
    public Shelve selectByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Shelve> selectAllByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getShelveCount(String area) {
        String sql = "select count(shel_id) as num\n"
                + "from shelves\n"
                + "where left(shel_id,3) like ?";

        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, area);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return rs.getInt("num");
            } else {
                return -1;
            }

        } catch (Exception ex) {
            return -2;
        }
    }

    public int nextShelve(String area) {
        String sql = "select iif(max(CAST(right(left(shel_id,8),4) as int)) is null,1,max(CAST(right(left(shel_id,8),4) as int))+1) as max\n"
                + "from shelves\n"
                + "where left(shel_id,3) like ?";

        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, area);

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

    public ArrayList<Shelve> getList() {
        return getList(null);
    }

    public ArrayList<Shelve> getList(String id) {
        ArrayList<Shelve> l = new ArrayList<>();
        String sql = id == null ? "select * from shelves\n"
                + "where left(shel_id,3) like ?"
                : "select distinct left(shel_id,8) as shel_id,shel_stat from shelves\n"
                + "where left(shel_id,3) like ?";

        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, id == null ? "%" : id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                l.add(new Shelve(rs.getNString("shel_id"), rs.getInt("shel_stat")));
            }
            if (l.size() > 0) {
                return l;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<Shelve> getNotFullShelve() {
        ArrayList<Shelve> l = new ArrayList<>();
        String sql = "select * from shelves\n"
                + "where shel_stat>=0 and shel_stat<100";

        try {
            Statement stm = Helper.Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                l.add(new Shelve(rs.getNString("shel_id"), rs.getInt("shel_stat")));
            }
        } catch (Exception e) {
        }
        return l;
    }

    public ArrayList<Shelve> getActiveShelve() {
        ArrayList<Shelve> l = new ArrayList<>();
        String sql = "select * from shelves\n"
                + "where shel_stat>=0 and shel_stat<=100";

        try {
            Statement stm = Helper.Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                l.add(new Shelve(rs.getNString("shel_id"), rs.getInt("shel_stat")));
            }
        } catch (Exception e) {
        }
        return l;
    }

    public ArrayList<Shelve> getProductShelves(String type_id) {
        ArrayList<Shelve> l = new ArrayList<>();
        String sql = "select distinct shelves.shel_id,shelves.shel_stat\n"
                + "from shelves inner join products on shelves.shel_id = products.shel_id\n"
                + "where products.type_id like ? and PRODUCTS.stat like 'sansang'";

        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, type_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                l.add(new Shelve(rs.getNString("shel_id"), rs.getInt("shel_stat")));
            }
        } catch (Exception e) {
        }
        return l;
    }
}
