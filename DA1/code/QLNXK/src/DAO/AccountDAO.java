/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import INTERFACE.DAO_Interface;
import Model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ma-user
 */
public class AccountDAO implements DAO_Interface<Account> {

    public AccountDAO() {
    }

    @Override
    public void insert(Account e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Account e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dalete(Account e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Account selectByColumn(Object... param) {
        Account acc;
        acc = (Account) param[0];
        String sql = "Select * from ACCOUNTS\n"
                + "where [user] like ? collate SQL_Latin1_General_CP1_CS_AS "
                + "and pass like ? collate SQL_Latin1_General_CP1_CS_AS "
                + "and stat like 'AC'";
        try {
            PreparedStatement stm = Helper.Helper.connection.prepareStatement(sql);
            stm.setNString(1, acc.getUser());
            stm.setNString(2, acc.getPass());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                acc.setRole(rs.getNString("role"));
                acc.setStat(rs.getNString("stat"));
                return acc;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ArrayList<Account> selectAllByColumn(Object... param) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
