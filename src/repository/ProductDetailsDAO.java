/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;


import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import model.ProductDetails;

/**
 *
 * @author lenovo
 */
public class ProductDetailsDAO {

    public List<ProductDetails> getAll() {
        List<ProductDetails> list = new ArrayList<>();
        String query = "SELECT * FROM SanPhamChiTiet";
        try {
            ResultSet rs = JdbcUtils.executeQuery(query);
            while (rs.next()) {
                list.add(new ProductDetails(
                        rs.getInt("id"),
                        rs.getInt("id_NSX"),
                        rs.getInt("id_Mau"),
                        rs.getInt("id_SanPham"),
                        rs.getInt("id_MoTa"),
                        rs.getInt("id_HinhAnh"),
                        rs.getInt("id_ThuongHieu"),
                        rs.getInt("id_HeDieuHanh"),
                        rs.getInt("id_ChatLieu"),
                        rs.getInt("id_IMEI"),
                        rs.getDouble("gia"),
                        rs.getDate("created_at"),
                        rs.getString("created_by"),
                        rs.getDate("updated_at"),
                        rs.getString("updated_by"),
                        rs.getBoolean("deleted"),
                        rs.getLong("id_pin"),
                        rs.getLong("id_manHinh"),
                        rs.getLong("id_cpu"),
                        rs.getLong("id_ram"),
                        rs.getLong("id_rom")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ProductDetails> getByidSanPham(Integer idSanPham) {
        List<ProductDetails> list = new ArrayList<>();
        String query = "SELECT * FROM SanPhamChiTiet WHERE id_SanPham = ?";
        try {
            ResultSet rs = JdbcUtils.executeQuery(query, idSanPham);
            while (rs.next()) {
                list.add(new ProductDetails(
                        rs.getInt("id"),
                        rs.getInt("id_NSX"),
                        rs.getInt("id_Mau"),
                        rs.getInt("id_SanPham"),
                        rs.getInt("id_MoTa"),
                        rs.getInt("id_HinhAnh"),
                        rs.getInt("id_ThuongHieu"),
                        rs.getInt("id_HeDieuHanh"),
                        rs.getInt("id_ChatLieu"),
                        rs.getInt("id_IMEI"),
                        rs.getDouble("gia"),
                        rs.getDate("created_at"),
                        rs.getString("created_by"),
                        rs.getDate("updated_at"),
                        rs.getString("updated_by"),
                        rs.getBoolean("deleted"),
                        rs.getLong("id_pin"),
                        rs.getLong("id_manHinh"),
                        rs.getLong("id_cpu"),
                        rs.getLong("id_ram"),
                        rs.getLong("id_rom")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(ProductDetails pd) {
        String query = "INSERT INTO SanPhamChiTiet (id_NSX, id_Mau, id_SanPham, id_MoTa,"
                + " id_HinhAnh, id_ThuongHieu, id_HeDieuHanh, id_ChatLieu, id_IMEI, "
                + "gia, created_at, created_by, updated_at, updated_by, deleted, id_pin, "
                + "id_manHinh, id_cpu, id_ram, id_rom) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            JdbcUtils.excuteUpdate(query, pd.getIdNSX(), pd.getIdMau(), pd.getIdSanPham(),
                    pd.getIdMoTa(), pd.getIdHinhAnh(), pd.getIdThuongHieu(), pd.getIdHeDieuHanh(),
                    pd.getIdChatLieu(), pd.getIdIMEI(), pd.getGia(), pd.getCreatedAt(),
                    pd.getCreatedBy(), pd.getUpdatedAt(), pd.getUpdatedBy(), pd.getDeleted(),
                    pd.getIdPin(), pd.getIdManHinh(), pd.getIdCPU(), pd.getIdRAM(), pd.getIdROM());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(ProductDetails pd) {
        String query = "UPDATE SanPhamChiTiet SET  id_Mau=?, id_SanPham=?, "
                + " id_ThuongHieu=?, "
                + "gia=?, updated_at=?, updated_by=?, deleted=?, id_pin=?, "
                + "id_manHinh=?, id_cpu=?, id_ram=?, id_rom=? WHERE id =?";

        try {
            JdbcUtils.excuteUpdate(query,  pd.getIdMau(), pd.getIdSanPham(),
                    pd.getIdThuongHieu(),pd.getGia(), pd.getUpdatedAt(),
                    pd.getUpdatedBy(), pd.getDeleted(), pd.getIdPin(), pd.getIdManHinh(),
                    pd.getIdCPU(), pd.getIdRAM(), pd.getIdROM(), pd.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(Integer productDetailId) {
        String query = "DELETE FROM SanPhamChiTiet WHERE id = ?";
        try {
            JdbcUtils.excuteUpdate(query, productDetailId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
