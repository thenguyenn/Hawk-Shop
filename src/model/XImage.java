/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
public class XImage {
    public static Image getAppIcon(){
       URL url = XImage.class.getResource("/images/icons/welcome.jpg");
       return new ImageIcon(url).getImage();
    }
}
