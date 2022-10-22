/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daftarperpus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Yosua Octavianus
 */
public class DBAnggota {

    private AnggotaModel dt = new AnggotaModel();

    public AnggotaModel getAnggotaModel() {
        return (dt);
    }

    public void setAnggotaModel(AnggotaModel s) {
        dt = s;
    }

    public ObservableList<AnggotaModel> Load() {
        try {
            ObservableList<AnggotaModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select npm, nama, alamat, angkatan from pendaftaran");
            int i = 1;
            while (rs.next()) {
                AnggotaModel d = new AnggotaModel();
                d.setNpm(rs.getString("npm"));
                d.setNama(rs.getString("nama"));
                d.setAlamat(rs.getString("alamat"));
                d.setAngkatan(rs.getString("angkatan"));

                tableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int validasi(String nomor) {
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from pendaftaran where npm = '");
            while (rs.next()) {
                val = rs.getInt("jml");
            }
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }

    public boolean insert() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into pendaftaran (npm,nama, alamat, angkatan) values (?,?,?,?)");
            con.preparedStatement.setString(1, getAnggotaModel().getNpm());
            con.preparedStatement.setString(2, getAnggotaModel().getNama());
            con.preparedStatement.setString(3, getAnggotaModel().getAlamat());
            con.preparedStatement.setString(4, getAnggotaModel().getAngkatan());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean delete(String nomor) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from pendaftaran where npm  = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update pendaftaran set nama = ?, alamat = ?, angkatan = ?  where  npm = ? ");
            con.preparedStatement.setString(1, getAnggotaModel().getNama());
            con.preparedStatement.setString(2, getAnggotaModel().getAlamat());
            con.preparedStatement.setString(3, getAnggotaModel().getAngkatan());
            con.preparedStatement.setString(4, getAnggotaModel().getNpm());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public ObservableList<AnggotaModel> CariAnggota(String kode, String nama) {
        try {
            ObservableList<AnggotaModel> tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("select * from pendaftaran WHERE npm LIKE '" + kode + "%' OR nama LIKE '" + nama + "%'");
            int i = 1;
            while (rs.next()) {
                AnggotaModel d = new AnggotaModel();
                d.setNpm(rs.getString("npm"));
                d.setNama(rs.getString("nama"));
                d.setAlamat(rs.getString("alamat"));
                d.setAngkatan(rs.getString("angkatan"));

                tableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
