package service;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageUtil {
	
	/**
	 * 截取图片
	 * @param srcPath
	 * @param subPath
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @throws IOException 
	 */
	public static void imgCut(String srcPath, String subPath, int x, int y, int width, int height) throws IOException{
		FileInputStream is = null;
		ImageInputStream iis = null;
		try {
			//读取图片文件
			is = new FileInputStream(srcPath);
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("jpg");
			ImageReader reader = it.next();
			//获取图片流
			iis = ImageIO.createImageInputStream(is);
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			// 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象的左上顶点的坐标(x，y)、宽度和高度可以定义这个区域。 
			Rectangle rect = new Rectangle(x, y, width, height);
			//提供一个BufferedImage, 将其用作解码像素数据的目标
			param.setSourceRegion(rect);
			BufferedImage bi = reader.read(0, param);
			//保存新图片
			ImageIO.write(bi, "jpg", new File(subPath+"\\"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if(is != null)
				is.close();
			if(iis != null)
				iis.close();
		}
	}
	
	/**
	 * 截取图片
	 * @param srcImageFile
	 * @param x
	 * @param y
	 * @param desWidth
	 * @param desHeight
	 */
	public static void imgCut(String srcImageFile,int id, int x, int y, int desWidth, int desHeight, String subPath){
		try {
			Image img;
			ImageFilter cropFilter;
			File srcFile = new File(srcImageFile);// 源文件
			BufferedImage bi = ImageIO.read(srcFile); 
			File targetFile = new File(subPath + "/"+ id + "_src.jpg"); //源文件重命名
			ImageIO.write(bi, "JPEG", targetFile); //输出新的源文件
			srcFile.delete(); // 删除旧的源文件
			int srcWidth = bi.getWidth();
			int srcHeight = bi.getHeight();
			if(srcWidth >= desWidth && srcHeight >= desHeight){
				Image image = bi.getScaledInstance(srcWidth, srcHeight, Image.SCALE_DEFAULT);
				cropFilter = new CropImageFilter(x, y, desWidth, desHeight);
				img = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
				BufferedImage tag = new BufferedImage(desWidth, desHeight, BufferedImage.TYPE_INT_RGB);
				Graphics g = tag.getGraphics();
				g.drawImage(img, 0, 0, null);
				g.dispose();
				//输出文件 
				ImageIO.write(tag, "JPEG", new File(subPath + "/"+ id + "_cut.jpg"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
