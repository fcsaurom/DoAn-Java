package Model;

import Model.Interface.DAOSinhVien;
import View.ListSinhVien;
import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DataModel implements DAOSinhVien {

    @Override
    public void Insert(SinhVien sinhVien) {
        Database db = new Database();
        final String SQL_CREATE_SINHVIEN = "INSERT INTO SinhVienTable(FullName, Mark, Date,Avatar)" +
                "VALUES(?,?,?,?)";

        try {
            PreparedStatement ps = db.getConnection().prepareStatement(SQL_CREATE_SINHVIEN, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, sinhVien.getFullName());
            ps.setFloat(2, sinhVien.getMark());
            ps.setLong(3,sinhVien.getDate());
            ps.setString(4,sinhVien.getImage());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                System.out.println("Inserted id: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.CloseConnect();

    }

    @Override
    public SinhVien getSinhVienById(int id) {
        Database db = new Database();
        SinhVien sinhVien = null;
        final String SQL_SELECT_SINHVIEN_BY_ID = "SELECT * FROM SinhVienTable WHERE id=?";
        PreparedStatement ps = null;
        try {
            ps = db.getConnection().prepareStatement(SQL_SELECT_SINHVIEN_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int ids = rs.getInt(1);
                String fullname = rs.getString(2);
                float mark = rs.getFloat(3);
                long date = rs.getLong(4);
                sinhVien = new SinhVien(ids,fullname,mark,date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.CloseConnect();
        return sinhVien;

    }

    @Override
    public List<SinhVien> getAllSinhVien() {
        List<SinhVien> sinhVienList = new ArrayList<>();
        Database db = new Database();
        final String SQL_SELECT_ALL_SINHVIEN = "SELECT * FROM SinhVienTable";
        try {
            Statement statement = db.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL_SINHVIEN);
            while (rs.next()) {

                int id = rs.getInt(1);
                String fullname = rs.getString(2);
                float mark = rs.getFloat(3);
                long date = rs.getLong(4);
                String link =rs.getString(5);
                sinhVienList.add(new SinhVien(id,fullname,mark,date,link));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.CloseConnect();
        return sinhVienList;
    }

    @Override
    public void Update(SinhVien sinhVien) {
        Database db = new Database();
        final String SQL_UPDATE_SINHVIEN_BY_ID = "UPDATE SinhVienTable SET FullName = ?, Mark = ?, Date = ?, Avatar = ? WHERE Id = ?";
        PreparedStatement ps = null;
        try {
            ps = db.getConnection().prepareStatement(SQL_UPDATE_SINHVIEN_BY_ID);
            ps.setString(1, sinhVien.getFullName());
            ps.setFloat(2, sinhVien.getMark());
            ps.setLong(3,sinhVien.getDate());
            ps.setInt(5, sinhVien.getID());
            ps.setString(4,sinhVien.getImage());
            ps.executeUpdate();
            if (ps.executeUpdate() != 0)
            {
                JOptionPane.showMessageDialog(null, "Sussess !");
            }
            else{
                JOptionPane.showMessageDialog(null, "UnSussess !");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        db.CloseConnect();
    }



    @Override
    public void Delete(int id) {
        Database db = new Database();
        final String SQL_DELETE_SINHVIEN_BY_ID = "DELETE FROM SinhVienTable WHERE id = ?";
        PreparedStatement ps = null;
        try {
            ps = db.getConnection().prepareStatement(SQL_DELETE_SINHVIEN_BY_ID);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        db.CloseConnect();
    }
}
