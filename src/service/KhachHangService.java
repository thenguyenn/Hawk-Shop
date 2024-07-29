/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.KhachHang;
import repository.Dbconnect;
import repository.KhachHangRepo;

public class KhachHangService {
    KhachHangRepo repoKH = new KhachHangRepo();
    public ArrayList<KhachHang> getAll(){
        return repoKH.getAll();
    }
    
    public String addNew(KhachHang khachHang){
        if(repoKH.addNew(khachHang)== true){
            return "Them Thanh Cong";
        }
        else{
            return "Them That Bai";
        }
    }
}
