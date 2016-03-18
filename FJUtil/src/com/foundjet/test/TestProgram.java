package com.foundjet.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.foundjet.util.QrcodeHandlerUtil;

public class TestProgram {
	public static void main(String[] args) {
		testQrcode();
	}
	
	public static void testQrcode() {
		// 创捷官网
		String content = "http://www.foundjet.com"; 
		
		// 创捷官网
//		String content = "http://www.yimalls.com"; 
        
		String rootPath = "E:/code/git/foundjet/OpenSource/V1/FJUtil/resource/img/";
        String fileName = "qrfile.png";
        String logoName = "logo.png";
        String qrPath = rootPath + fileName;
        String logoPath = rootPath + logoName;
        System.out.println("Write content:" + content);
        
        // Generate QRCode image
        // 生成二维码图片文件
        QrcodeHandlerUtil.encodeQRCodeToFile(content, qrPath);
        // Embed logo image at the center of QRCode image.
        // 把logo图片嵌入到二维码图片的正中央
        BufferedImage bufImg = QrcodeHandlerUtil.encodeImgLogo(new File(qrPath), new File(logoPath));
        // Output buffered image to local file
        // 把buffered image 输出到本地文件
        File mixedFile = new File(rootPath + "mixedImg.png");
        try {
			ImageIO.write(bufImg, "png", mixedFile);
		} catch (IOException e) {
			e.printStackTrace();
		} 
        System.out.println("End.");
	}
}
