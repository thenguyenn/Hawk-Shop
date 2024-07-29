
package model;

import java.sql.Date;
/**
 *
 * @author lenovo
 */
public class ProductDetails {
    private Integer id;
    private Integer idNSX;
    private Integer idMau;
    private Integer idSanPham;
    private Integer idMoTa;
    private Integer idHinhAnh;
    private Integer idThuongHieu;
    private Integer idHeDieuHanh;
    private Integer idChatLieu;
    private Integer idIMEI;
    private Double gia;
    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
    private Boolean deleted;
    private Long idPin;
    private Long idManHinh;
    private Long idCPU;
    private Long idRAM;
    private Long idROM;

    public ProductDetails() {
    }

    public ProductDetails(Integer id, Integer idNSX, Integer idMau, Integer idSanPham, Integer idMoTa, Integer idHinhAnh, Integer idThuongHieu, Integer idHeDieuHanh, Integer idChatLieu, Integer idIMEI, Double gia, Date createdAt, String createdBy, Date updatedAt, String updatedBy, Boolean deleted, Long idPin, Long idManHinh, Long idCPU, Long idRAM, Long idROM) {
        this.id = id;
        this.idNSX = idNSX;
        this.idMau = idMau;
        this.idSanPham = idSanPham;
        this.idMoTa = idMoTa;
        this.idHinhAnh = idHinhAnh;
        this.idThuongHieu = idThuongHieu;
        this.idHeDieuHanh = idHeDieuHanh;
        this.idChatLieu = idChatLieu;
        this.idIMEI = idIMEI;
        this.gia = gia;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.deleted = deleted;
        this.idPin = idPin;
        this.idManHinh = idManHinh;
        this.idCPU = idCPU;
        this.idRAM = idRAM;
        this.idROM = idROM;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdNSX() {
        return idNSX;
    }

    public void setIdNSX(Integer idNSX) {
        this.idNSX = idNSX;
    }

    public Integer getIdMau() {
        return idMau;
    }

    public void setIdMau(Integer idMau) {
        this.idMau = idMau;
    }

    public Integer getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(Integer idSanPham) {
        this.idSanPham = idSanPham;
    }

    public Integer getIdMoTa() {
        return idMoTa;
    }

    public void setIdMoTa(Integer idMoTa) {
        this.idMoTa = idMoTa;
    }

    public Integer getIdHinhAnh() {
        return idHinhAnh;
    }

    public void setIdHinhAnh(Integer idHinhAnh) {
        this.idHinhAnh = idHinhAnh;
    }

    public Integer getIdThuongHieu() {
        return idThuongHieu;
    }

    public void setIdThuongHieu(Integer idThuongHieu) {
        this.idThuongHieu = idThuongHieu;
    }

    public Integer getIdHeDieuHanh() {
        return idHeDieuHanh;
    }

    public void setIdHeDieuHanh(Integer idHeDieuHanh) {
        this.idHeDieuHanh = idHeDieuHanh;
    }

    public Integer getIdChatLieu() {
        return idChatLieu;
    }

    public void setIdChatLieu(Integer idChatLieu) {
        this.idChatLieu = idChatLieu;
    }

    public Integer getIdIMEI() {
        return idIMEI;
    }

    public void setIdIMEI(Integer idIMEI) {
        this.idIMEI = idIMEI;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getIdPin() {
        return idPin;
    }

    public void setIdPin(Long idPin) {
        this.idPin = idPin;
    }

    public Long getIdManHinh() {
        return idManHinh;
    }

    public void setIdManHinh(Long idManHinh) {
        this.idManHinh = idManHinh;
    }

    public Long getIdCPU() {
        return idCPU;
    }

    public void setIdCPU(Long idCPU) {
        this.idCPU = idCPU;
    }

    public Long getIdRAM() {
        return idRAM;
    }

    public void setIdRAM(Long idRAM) {
        this.idRAM = idRAM;
    }

    public Long getIdROM() {
        return idROM;
    }

    public void setIdROM(Long idROM) {
        this.idROM = idROM;
    }

    
    
}
