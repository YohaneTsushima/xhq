package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import com.jfinal.core.Controller;

/**
 * @author chika
 * 验证码生成控制器
 */
public class ValidCodeController extends Controller{
	private int width = 90;// 定义图片的width
	private int height = 20;// 定义图片的height
	private int codeCount = 5;// 定义图片上显示验证码的个数
	private int xx = 15;
	private int fontHeight = 18;
	private int codeY = 16;
	char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	
	public void create() throws Exception{
		//定义图像buffer缓存
		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = bufferedImage.getGraphics();
		//创建一个随机数生成器
		Random random = new Random();
		//图像填充白色
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, width, height);
		//创建字体，字体大小根据图片高度
		Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
		//设置字体
		graphics.setFont(font);
		//边框
		graphics.setColor(Color.BLACK);
		graphics.drawRect(0, 0, width - 1, height - 1);
		//随机生成40条干扰线，使图像的验证码不易被探测
		graphics.setColor(Color.BLACK);
		for (int i = 0; i < 40; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			graphics.drawLine(x, y, x + xl, y + yl);
		}
		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer();
		int red = 0, green = 0, blue = 0;
		//随机产生codeCount数字的验证码
		for (int i = 0; i < codeCount; i++) {
			//得到随机产生的数字
			String code = String.valueOf(codeSequence[random.nextInt(36)]);
			//产生随机的颜色分量构造颜色，输出数字颜色都不同
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);
			//随机产生的颜色绘制到图中。
			graphics.setColor(new Color(red, green, blue));
			graphics.drawString(code, (i + 1) * xx - 12, codeY);
			//将产生的四个随机数组合一起
			randomCode.append(code);
		}
		//将数字的验证码保存到Session
		System.out.println(randomCode);
		getSession().setAttribute("code", randomCode.toString());
		//禁止图像缓存
		getResponse().setHeader("Pragma", "no-cache");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setDateHeader("Expires", 0);
		getResponse().setContentType("image/jpeg");
		//图像输出到Servlet输出流中。
		ServletOutputStream sos = getResponse().getOutputStream();
		ImageIO.write(bufferedImage, "jpeg", sos);
		sos.close();
		renderNull();
	}
	
	public void validCheck() throws Exception{
		String sessionValid = (String) getSession().getAttribute("code");
		String inputValid = getPara("validCode");
		boolean flag = false;
		if(sessionValid.equalsIgnoreCase(inputValid)){
			flag = true;
		}
		getResponse().getWriter().println(flag);
		renderNull();
	}
	
}
