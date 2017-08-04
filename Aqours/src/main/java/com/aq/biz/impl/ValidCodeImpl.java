package com.aq.biz.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aq.biz.ValidCodeBiz;

public class ValidCodeImpl implements ValidCodeBiz{
	
	private int width = 90;// 定义图片的width
	private int height = 20;// 定义图片的height
	private int codeCount = 5;// 定义图片上显示验证码的个数
	private int xx = 15;
	private int fontHeight = 18;
	private int codeY = 16;
	char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	@Override
	public void validCode(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			//定义图像buffer
			BufferedImage image = new BufferedImage(width, fontHeight, BufferedImage.TYPE_INT_RGB);
			Graphics graphics = image.getGraphics();
			//随机数
			Random r = new Random();
			//背景白色
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, width, fontHeight);
			//创建字体
			Font font = new Font("", Font.BOLD, fontHeight);
			//边框
			graphics.setFont(font);
			graphics.drawRect(0, 0, width - 1, height - 1);
			//随机产生40条干扰线
			for (int i = 0; i < 40; i++) {
				int x = r.nextInt(width);
				int y = r.nextInt(height);
				int x1 = r.nextInt(12);
				int y1 = r.nextInt(12);
				graphics.drawLine(x, y, x + x1, y + y1);
			}
			//保存随机产生的验证码
			StringBuilder randomCode = new StringBuilder();
			int red = 0, green = 0, blue = 0;
			//随机验证数字
			for (int i = 0; i < codeCount; i++) {
				//产生随机数字验证码
				String code = String.valueOf(codeSequence[r.nextInt(36)]);
				//产生随机颜色来构造颜色值
				red = r.nextInt(255);
				green = r.nextInt(255);
				blue = r.nextInt(255);
				//用随机产生的颜色绘制到图中
				graphics.setColor(new Color(red, green, blue));
				graphics.drawString(code, (i + 1) * xx - 12, codeY);
				//将产生的四个随机数组合
				randomCode.append(code);
			}
			//保存到session
			HttpSession session = request.getSession();
			System.out.println(randomCode);
			session.setAttribute("code", randomCode.toString());
			//禁止缓存
			response.setHeader("Param", "no-cache");
			response.setHeader("Cache-COntrol", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");
			//将图像输出到servlet流中
			ServletOutputStream sos = response.getOutputStream();
			ImageIO.write(image, "jpeg", sos);
			sos.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
