/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import DAO.Import_DetailDAO;
import DAO.SupplierDAO;
import DAO.TypeDAO;
import Model.Import;
import Model.Import_Detail;
import Model.Type;
import UI.Main;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * @author ma-user
 */
public class Helper {

    public static Connection connection;

    public static synchronized boolean openConnection() {
        if (connection != null) {
            return true;
        } else {
            try {
                String u = System.getenv("sa_user");
                String pw = System.getenv("sa_pass");
                String h = System.getenv("sa_host");
                String p = System.getenv("sa_port");
                String d = System.getenv("sa_db");
                String url = "jdbc:sqlserver://" + h + ":" + p + ";databaseName=" + d;
                connection = DriverManager.getConnection(url, u, pw);
            } catch (Exception e) {
                connection = null;
                return false;
            }
        }
        return true;
    }

    public static synchronized void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
        } finally {
            connection = null;
        }
    }

    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replace("đ", "d");
    }

    public static void showPanel(JPanel panel) {
        Main.rootP.removeAll();
        javax.swing.GroupLayout rootPLayout = new javax.swing.GroupLayout(Main.rootP);
        Main.rootP.setLayout(rootPLayout);
        rootPLayout.setHorizontalGroup(
                rootPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel)
        );
        rootPLayout.setVerticalGroup(
                rootPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel)
        );
    }

    public static void printImport(Import anImport) {
        ArrayList<Import_Detail> lst = new Import_DetailDAO().select(anImport.getIm_id());
        Document pdf = new Document();
        pdf.setMargins(25, 25, 25, 25);
        try {
            File dir = new File(System.getProperty("user.home") + "/appdata/local/QLXNK/");
            if (!dir.exists() || !dir.isDirectory()) {
                Files.createDirectories(dir.toPath());
            }
            File f = new File(System.getProperty("user.home") + "/appdata/local/QLXNK/" + anImport.getIm_id() + ".pdf");
            if (f.exists()) {
                f.delete();
            }
            PdfWriter.getInstance(pdf, new FileOutputStream(f));
            pdf.open();
            Paragraph p = new Paragraph(new Phrase("MHD: " + anImport.getIm_id()));
            pdf.add(p);
            p = new Paragraph(new Phrase("NCC: " + removeAccent(new SupplierDAO().select(anImport.getSup_id()).getSup_name())));
            pdf.add(p);
            p = new Paragraph(new Phrase("Ngay: " + new SimpleDateFormat("dd-MM-yyyy").format(anImport.getIm_date())));
            pdf.add(p);
            p = new Paragraph(new Phrase("Tao boi: " + anImport.getUser()));
            pdf.add(p);
            p = new Paragraph(new Phrase("Giao hang: " + anImport.getIm_deliver()));
            pdf.add(p);
            p = new Paragraph(new Phrase("Kiem tra: " + anImport.getIm_checker()));
            pdf.add(p);
            p = new Paragraph(new Phrase("----------------------"));
            pdf.add(p);

            PdfPTable tbl = new PdfPTable(3);
            tbl.addCell(new PdfPCell(new Paragraph(Element.ALIGN_CENTER, "Ten dien thoai")));
            tbl.addCell(new PdfPCell(new Paragraph(Element.ALIGN_CENTER, "So luong")));
            tbl.addCell(new PdfPCell(new Paragraph(Element.ALIGN_CENTER, "Gia nhap")));
            for (Import_Detail import_Detail : lst) {
                tbl.addCell(new PdfPCell(new Paragraph(Element.ALIGN_RIGHT, removeAccent(new TypeDAO().select(new Type(import_Detail.getType_id(), "", "")).getType_name()))));
                tbl.addCell(new PdfPCell(new Paragraph(Element.ALIGN_RIGHT, removeAccent(String.valueOf(import_Detail.getImd_amount())))));
                tbl.addCell(new PdfPCell(new Paragraph(Element.ALIGN_RIGHT, removeAccent(new DecimalFormat("#").format(import_Detail.getImd_price())))));
            }
            pdf.add(tbl);
            pdf.add(p);
            p = new Paragraph(new Phrase("Tong tien: " + new DecimalFormat("#").format(anImport.getIm_sum_price())));
            pdf.add(p);

            pdf.close();
            Desktop.getDesktop().open(f);
        } catch (Exception e) {
        }

    }
//    public static void main(String[] args) {
//        String ng = JOptionPane.showInputDialog(null, "in","11");
//        System.out.println(ng);
//    }
}
