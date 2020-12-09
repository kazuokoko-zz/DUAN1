/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Helper.Helper;
import INTERFACE.DAO_Interface;
import Model.Import_Detail;
import Model.Type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author ma-user
 */
public class TypeDAO implements DAO_Interface<Type> {

    @Override
    public boolean insert(Type type) {
        String sql = "insert into types(type_id,type_name,type_stat)\n"
                + "values (?,?,?)";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, type.getType_id());
            stm.setNString(2, type.getType_name());
            stm.setNString(3, type.getType_stat());

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
    public boolean update(Type type) {
        String sql = "update types\n"
                + "set type_stat = 'KD'\n"
                + "where type_id like ?";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, type.getType_id());

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
    public boolean delete(Type type) {
        String sql = "update types\n"
                + "set type_stat = 'NB'\n"
                + "where type_id like ?";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, type.getType_id());

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
    public Type selectByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Type> selectAllByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Type select(Type type) {
        Type rt = null;
        String sql = "select * from types\n"
                + "where type_id like ?";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, type.getType_id());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                rt = new Type(rs.getNString("type_id"), rs.getNString("type_name"), rs.getNString("type_stat"));
            }
        } catch (Exception e) {
            rt = null;
        }
        return rt;
    }

    public Type select(Import_Detail import_Detail) {
        Type rt = null;
        String sql = "select * from types\n"
                + "where type_id like ?";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, import_Detail.getType_id());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                rt = new Type(rs.getNString("type_id"), rs.getNString("type_name"), rs.getNString("type_stat"));
            }
        } catch (Exception e) {
            rt = null;
        }
        return rt;
    }

    public Type select(String serial) {
        Type rt = null;
        String sql = "select * from types\n"
                + "where type_id in(select type_id from products where serial like ?)";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, serial);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                rt = new Type(rs.getNString("type_id"), rs.getNString("type_name"), rs.getNString("type_stat"));
            }
        } catch (Exception e) {
            rt = null;
        }
        return rt;
    }

    public ArrayList<Type> selectAll(boolean fillAll) {
        ArrayList<Type> l = new ArrayList();
        String sql = "select * from types\n" + (fillAll ? ""
                : "where type_stat like 'KD'");
        try {
            Statement stm = Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                l.add(new Type(rs.getNString("type_id"), rs.getNString("type_name"), rs.getNString("type_stat")));
            }
            if (l.size() <= 0) {
                l = null;
            }
        } catch (Exception e) {
            l = null;
        }
        return l;
    }

    public ArrayList<Type> selectAll(ArrayList<String> name, Integer ram, Integer rom, String producer, Integer phonename) {
        ArrayList<Type> l = new ArrayList();
        String sql = "select distinct types.type_id as type_id,types.type_name as type_name,types.type_stat as type_stat\n"
                + "from types ,producers,phonenames, memories \n"
                + "where \n"
                + "((left(types.type_id,2) = producers.id \n"
                + "and left(types.type_id,2) = phonenames.id_prd \n"
                + "and cast(substring(types.type_id,3,3)as int) = phonenames.num_order and\n"
                + "cast(substring(types.type_id,7,5) as int) = memories.amount \n"
                + "or cast(substring(types.type_id,13,2) as int) = memories.amount))\n"
                + "and\n"
                + "type_stat like 'KD'\n";
        int len = name == null ? 0 : name.size(), j = 1;
        if (len > 0) {
            if (len == 1) {
                sql = sql.concat("and dbo.f_RemoveAccent(types.type_name) like dbo.f_RemoveAccent(?)\n");
            } else {
                sql = sql.concat("and (dbo.f_RemoveAccent(types.type_name) like dbo.f_RemoveAccent(?)\n");
                for (int i = 2; i < len; i++) {
                    sql = sql.concat("or dbo.f_RemoveAccent(types.type_name) like dbo.f_RemoveAccent(?)\n");
                }
                sql = sql.concat("or dbo.f_RemoveAccent(types.type_name) like dbo.f_RemoveAccent(?))\n");
            }
        }
        if (rom != null || ram != null || producer != null || phonename != null) {
            sql = sql.concat("and (");
            boolean added = false;
            if (rom != null) {
                sql = sql.concat("cast(substring(types.type_id,7,5) as int) = ? \n");
                added = true;
            }
            if (ram != null) {
                sql = sql.concat(added ? "or " : "").concat("cast(substring(types.type_id,13,2) as int) = ?\n");
                added = true;
            }
            if (producer != null) {
                sql = sql.concat(added ? "or " : "").concat("SUBSTRING(types.type_id,1,2) like ?\n");
                added = true;
            }
            if (phonename != null) {
                sql = sql.concat(added ? "or " : "").concat("or cast(substring(types.type_id,3,3)as int) = ?\n");
            }
            sql = sql.concat(")\n");
        }
        sql = sql.concat("order by type_name");
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            if (len > 0) {
                for (int i = 0; i < len; i++) {
                    stm.setNString(j + i, "%" + name.get(i) + "%");
                }
            }
            if (rom != null) {
                stm.setInt(j, (int) rom);
                j++;
            }
            if (ram != null) {
                stm.setInt(j, (int) ram);
                j++;
            }
            if (producer != null) {
                stm.setNString(j, producer);
                j++;
            }
            if (phonename != null) {
                stm.setInt(j, (int) phonename);
                j++;
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                l.add(new Type(rs.getNString("type_id"), rs.getNString("type_name"), rs.getNString("type_stat")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }

    public String getRemainType() {
        String sql = "select top 1 types.type_id as type_id, Min(iif(ex_date is null,CAST('1900-01-01' as date),ex_date)) as d\n"
                + "from types left outer join products on types.type_id = products.type_id\n"
                + "left outer join export_detail on products.serial = export_detail.serial\n"
                + "left outer  join  exports on exports.ex_id = export_detail.ex_id\n"
                + "where type_stat like 'KD' and types.type_id in(\n"
                + "					select  PRODUCTS.type_id \n"
                + "					from PRODUCTS right outer join Types on PRODUCTS.type_id = TYPES.type_id\n"
                + "					where stat like 'sansang'  \n"
                + "					group by PRODUCTS.type_id\n"
                + "					having COUNT(PRODUCTS.serial) > 0 )\n"
                + "group by types.type_id\n"
                + "order by d asc";
//         "select top 1 products.type_id as type_id, MIN(ex_date) as d\n"
//                + "from  products left join export_detail on products.serial = export_detail.serial\n"
//                + "inner  join  exports on exports.ex_id = export_detail.ex_id\n"
//                + "where products.type_id in (select  type_id from types where type_stat like 'KD')\n"
//                + "group  by  products.type_id\n"
//                + "order  by d asc";
        try {
            Statement stm = Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                return rs.getNString("type_id");
            } else {
                return null;
            }

        } catch (SQLException throwables) {
            return null;
        }
    }

    public String getNearestType() {
        String sql = "select top 1 products.type_id as type_id, Max(ex_date) as d\n"
                + "from  products left join export_detail on products.serial = export_detail.serial\n"
                + "inner  join  exports on exports.ex_id = export_detail.ex_id\n"
                + "where products.type_id in (select  type_id from types where type_stat like 'KD')\n"
                + "group  by  products.type_id\n"
                + "order  by d Desc";
        try {
            Statement stm = Helper.connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            if (rs.next()) {
                return rs.getNString("type_id");
            } else {
                return null;
            }

        } catch (SQLException throwables) {
            return null;
        }
    }

    public String getTypeName(String type_id) {
        String sql = "select type_name from types\n"
                + "where type_id like ?";
        try {
            PreparedStatement stm = Helper.connection.prepareStatement(sql);
            stm.setNString(1, type_id);
            ResultSet rs = stm.executeQuery();
            rs.next();
            return rs.getNString("type_name");

        } catch (Exception e) {
            return "";
        }
    }

}
