package org.cc.drive.school.haidian.util.orc.tesseract;
		import java.awt.image.BufferedImage;
		import java.io.File;
		import java.io.FileInputStream;
		import java.io.FileOutputStream;

public class ImageRead {

	public static String read(BufferedImage bi,int ii){
		ImageFilter imgFliter = new ImageFilter(bi);
		BufferedImage ss = imgFliter.changeGrey();

		imgFliter = new ImageFilter(ss);
		ss = imgFliter.median();

		imgFliter = new ImageFilter(ss);
		ss = imgFliter.grayFilter();

		File xx = ImageIOHelper.createImage(ss);

		try{
			FileInputStream input = new FileInputStream(xx);
			FileOutputStream output = new FileOutputStream("D:\\CODE\\Demot\\img\\yzm"+ii+".tiff");// 把扩展名添加到原来文件的后面
			int in = input.read();
			while (in != -1) {
				output.write(in);
				in = input.read();
			}
			input.close();
			output.close();
			OCR ocr = new OCR();
			String rlt = ocr.recognizeText(xx, "tiff").trim();
			System.out.println(  "rlt:====" +  rlt);

			return rlt;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

