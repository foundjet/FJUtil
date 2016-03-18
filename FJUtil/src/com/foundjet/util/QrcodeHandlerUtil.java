package com.foundjet.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import javax.imageio.ImageIO;
import com.swetake.util.Qrcode;

public class QrcodeHandlerUtil {
	public static void encodeQRCodeToStream(String content, BufferedOutputStream output) { 
        try { 
            Qrcode qrcodeHandler = new Qrcode(); 
            qrcodeHandler.setQrcodeErrorCorrect('M'); 
            qrcodeHandler.setQrcodeEncodeMode('B'); 
            qrcodeHandler.setQrcodeVersion(7); 
 
            System.out.println(content); 
            byte[] contentBytes = content.getBytes("gb2312"); 
 
            BufferedImage bufImg = new BufferedImage(140, 140, 
                    BufferedImage.TYPE_INT_RGB); 
 
            Graphics2D gs = bufImg.createGraphics(); 
 
            gs.setBackground(Color.WHITE); 
            gs.clearRect(0, 0, 140, 140); 
 
            // 设定图像颜色> BLACK 
            gs.setColor(Color.BLACK); 
 
            // 设置偏移量 不设置可能导致解析出错 
            int pixoff = 2; 
            // 输出内容> 二维码 
            if (contentBytes.length > 0 && contentBytes.length < 120) { 
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes); 
                for (int i = 0; i < codeOut.length; i++) { 
                    for (int j = 0; j < codeOut.length; j++) { 
                        if (codeOut[j][i]) { 
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3); 
                        } 
                    } 
                } 
            } else { 
                System.err.println("QRCode content bytes length = " 
                        + contentBytes.length + " not in [ 0,120 ]. "); 
            } 
 
            gs.dispose(); 
            bufImg.flush(); 
 
//            File imgFile = new File(imgPath); 
 
            // 生成二维码QRCode图片 
            ImageIO.write(bufImg, "png", output); 
 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    }

	public static void encodeQRCodeToFile(String content, String imgPath) { 
        try { 
            Qrcode qrcodeHandler = new Qrcode(); 
            qrcodeHandler.setQrcodeErrorCorrect('M'); 
            qrcodeHandler.setQrcodeEncodeMode('B'); 
            qrcodeHandler.setQrcodeVersion(7); 
 
            System.out.println(content); 
            byte[] contentBytes = content.getBytes("gb2312"); 
 
            BufferedImage bufImg = new BufferedImage(140, 140, 
                    BufferedImage.TYPE_INT_RGB); 
 
            Graphics2D gs = bufImg.createGraphics(); 
 
            gs.setBackground(Color.WHITE); 
            gs.clearRect(0, 0, 140, 140); 
 
            // 设定图像颜色> BLACK 
            gs.setColor(Color.BLACK); 
 
            // 设置偏移量 不设置可能导致解析出错 
            int pixoff = 2; 
            // 输出内容> 二维码 
            if (contentBytes.length > 0 && contentBytes.length < 120) { 
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes); 
                for (int i = 0; i < codeOut.length; i++) { 
                    for (int j = 0; j < codeOut.length; j++) { 
                        if (codeOut[j][i]) { 
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3); 
                        } 
                    } 
                } 
            } else { 
                System.err.println("QRCode content bytes length = " 
                        + contentBytes.length + " not in [ 0,120 ]. "); 
            } 
 
            gs.dispose(); 
            bufImg.flush(); 
            //
            File imgFile = new File(imgPath);   
            // 生成二维码QRCode图片 
            ImageIO.write(bufImg, "png", imgFile); 
 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
    }

	public static BufferedImage encodeImgLogo(File twodimensioncodeImg, File logoImg){  
        BufferedImage twodimensioncode = null;  
        try{  
            if(!twodimensioncodeImg.isFile() || !logoImg.isFile()){  
                System.out.println("输入非图片");  
                return null;  
            }  
            //读取二维码图片  
            twodimensioncode = ImageIO.read(twodimensioncodeImg);  
            //获取画笔  
            Graphics2D g = twodimensioncode.createGraphics();  
            //读取logo图片  
            BufferedImage logo = ImageIO.read(logoImg);  
            //设置二维码大小，太大，会覆盖二维码，此处20%  
            int logoWidth = logo.getWidth(null) > twodimensioncode.getWidth()*1 /3 ? (twodimensioncode.getWidth() * 1 /3) : logo.getWidth(null);  
            int logoHeight = logo.getHeight(null) > twodimensioncode.getHeight()*1 /3 ? (twodimensioncode.getHeight()*1 /3) : logo.getHeight(null);  
            //设置logo图片放置位置  
            //中心  
            int x = (twodimensioncode.getWidth() - logoWidth) / 2;  
            int y = (twodimensioncode.getHeight() - logoHeight) / 2;  
            //右下角，15为调整值  
//          int x = twodimensioncode.getWidth()  - logoWidth-15;  
//          int y = twodimensioncode.getHeight() - logoHeight-15;  
            //开始合并绘制图片  
            g.drawImage(logo, x, y, logoWidth, logoHeight, null);  
            g.drawRoundRect(x, y, logoWidth, logoHeight, 15 ,15);  
            //logo边框大小  
            g.setStroke(new BasicStroke(2));  
            //logo边框颜色  
            g.setColor(Color.WHITE);  
            g.drawRect(x, y, logoWidth, logoHeight);  
            g.dispose();  
            logo.flush();  
            twodimensioncode.flush();  
        }catch(Exception e){  
            System.out.println("二维码绘制logo失败");  
        }  
        return twodimensioncode;  
    }  
}
