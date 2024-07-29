    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Product;

/**
 *
 * @author lenovo
 */
public class ProductDAO {

    private static final int IMEI_LENGTH = 15;
    private static final String IMEI_PREFIX = "35";

    public String generateIMEI() {
        StringBuilder imeiBuilder = new StringBuilder(IMEI_PREFIX);
        Random random = new Random();
        for (int i = IMEI_PREFIX.length() + 1; i <= IMEI_LENGTH; i++) {
            imeiBuilder.append(random.nextInt(10));
        }
        return imeiBuilder.toString();
    }

    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM SanPham";
        try {
            ResultSet rs = JdbcUtils.executeQuery(query);
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"), rs.getString("ten"), rs.getInt("soLuong"),
                        rs.getDate("created_at"), rs.getString("created_by"),
                        rs.getDate("updated_at"), rs.getString("updated_by"), rs.getBoolean("deleted")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Product getById(int id) {
        Product product = null;
        String query = "SELECT * FROM SanPham WHERE id = ?";
        try {
            ResultSet rs = JdbcUtils.executeQuery(query, id);
            while (rs.next()) {
                product = new Product(rs.getInt("id"), rs.getString("ten"), rs.getInt("soLuong"),
                        rs.getDate("created_at"), rs.getString("created_by"),
                        rs.getDate("updated_at"), rs.getString("updated_by"), rs.getBoolean("deleted"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public boolean insert(Product p) {
        String query = "INSERT INTO [QuanLyBanHang].[dbo].[SanPham] \n"
                + "            ([ten]\n"
                + "            ,[soLuong]\n"
                + "            ,[created_at]\n"
                + "            ,[created_by]\n"
                + "            ,[updated_at]\n"
                + "            ,[updated_by]\n"
                + "            ,[deleted])\n"
                + "VALUES      (?, ?, ?, ?, ?, ?, ?);";
        try {
            JdbcUtils.excuteUpdate(query, p.getTen(), p.getSoLuong(), p.getCreatedAt(),
                    p.getCreatedBy(), p.getUpdatedAt(), p.getUpdatedBy(), p.isDeleted());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(Product p) {
        String query = "UPDATE [QuanLyBanHang].[dbo].[SanPham] \n"
                + "SET ten = ?,\n"
                + " soLuong = ?,\n"
                + " created_at = ?,\n"
                + " created_by = ?,\n"
                + " updated_at = ?,\n"
                + " updated_by = ?,\n"
                + " deleted = ?\n"
                + " WHERE id = ?;";
        try {
            JdbcUtils.excuteUpdate(query, p.getTen(), p.getSoLuong(), p.getCreatedAt(),
                    p.getCreatedBy(), p.getUpdatedAt(), p.getUpdatedBy(), p.isDeleted(), p.getId());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(Integer id) {
        String query = "DELETE FROM SanPham WHERE id = ?";
        try {
            JdbcUtils.excuteUpdate(query, id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Object> getProductTable() {
        List<Object> list = new ArrayList<>();
        String query = "SELECT sp.ten, p.[name], mh.[name], cpu.[name], ram.[name], rom.[name], m.tenMau, spct.gia, sp.soLuong,\n"
                + "sp.id, spct.id, im.ten, th.ten \n"
                + "FROM SanPham sp\n"
                + "INNER JOIN SanPhamChiTiet spct ON spct.id_SanPham = sp.id\n"
                + "INNER JOIN MauSac m ON m.id = spct.id_Mau\n"
                + "inner join IMEI im on im.id = spct.id_IMEI\n"
                + "inner join ThuongHieu th on th.id = spct.id_ThuongHieu\n"
                + "inner join Pin p on p.id = spct.id_pin\n"
                + "inner join ManHinh mh on mh.id = spct.id_manHinh\n"
                + "inner join CPU cpu on cpu.id = spct.id_cpu\n"
                + "inner join RAM ram on ram.id = spct.id_ram\n"
                + "inner join ROM rom on rom.id = spct.id_rom;";

        try {
            ResultSet rs = JdbcUtils.executeQuery(query);
            while (rs.next()) {
                list.add(new Object[]{
                    rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getString(12), rs.getString(13)
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Product getByName(String name) {
        String query = "SELECT Top 1 * FROM SanPham WHERE [ten] = ?";
        Product product = null;
        try {
            ResultSet rs = JdbcUtils.executeQuery(query, name);
            while (rs.next()) {
                product = new Product(rs.getInt("id"), rs.getString("ten"), rs.getInt("soLuong"),
                        rs.getDate("created_at"), rs.getString("created_by"),
                        rs.getDate("updated_at"), rs.getString("updated_by"), rs.getBoolean("deleted"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }
}
