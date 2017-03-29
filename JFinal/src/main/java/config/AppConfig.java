package config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.render.ViewType;

import controller.HomeController;
import controller.UploadifyController;
import controller.UserController;
import controller.ValidCodeController;
import model.City;
import model.Members;
import model.Usr_info;

public class AppConfig extends JFinalConfig{

	@Override
	public void configConstant(Constants constants) {
		// TODO Auto-generated method stub
		constants.setEncoding("UTF-8");
		constants.setDevMode(true);
		constants.setViewType(ViewType.JSP);
		constants.setBaseUploadPath("D:\\LIZHIHUA\\myFile\\myFile\\wsp\\uploadImage\\heads");
	}

	@Override
	public void configHandler(Handlers arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configInterceptor(Interceptors interceptors) {
		// TODO Auto-generated method stub
		//这里用于配置全局的拦截器，对所有请求进行拦截
		// 添加控制层全局拦截器
		
	}

	@Override
	public void configPlugin(Plugins plugins) {
		// TODO Auto-generated method stub
		//jFinal插件
		PropKit.use("jdbc.properties");
		final String URL =PropKit.get("jdbc.url");
		final String USERNAME = PropKit.get("jdbc.username");
		final String PASSWORD =PropKit.get("jdbc.password");
		final Integer INITIALSIZE = PropKit.getInt("initialSize");
		final Integer MIDIDLE = PropKit.getInt("minIdle");
		final Integer MAXACTIVEE = PropKit.getInt("maxActivee");
		DruidPlugin druidPlugin = new DruidPlugin(URL, USERNAME, PASSWORD);
		druidPlugin.set(INITIALSIZE, MIDIDLE, MAXACTIVEE);
		druidPlugin.setFilters("stat,wall");
		plugins.add(druidPlugin);
		ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
		//添加Model类和数据库表的映射。Members指的是表名，id指的是主键
		activeRecordPlugin.addMapping("Members", "id", Members.class);
		activeRecordPlugin.addMapping("Usr_info", "id", Usr_info.class);
		activeRecordPlugin.addMapping("City", "id", City.class);
		plugins.add(activeRecordPlugin);
		//plugins.add(new MailPlugin(PropKit.use("mail.properties").getProperties()));
	}

	@Override
	public void configRoute(Routes routes) {
		// TODO Auto-generated method stub
		routes.add("/", HomeController.class);
		routes.add("/user", UserController.class);
		routes.add("/user/validCode", ValidCodeController.class);
		routes.add("/uploadify", UploadifyController.class);
	}

	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 80, "/", 5);
	}

}
