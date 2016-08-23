package com.aq.web.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date>{

	private String[] datePattern;
	
	public void setDatePattern(String[] datePattern) {
		this.datePattern = datePattern;
	}

	@Override
	public Date convert(String value) {
		// TODO Auto-generated method stub
		if(value == null || value.isEmpty()){
			return null;
		}else {
			for (String string : datePattern) {
				SimpleDateFormat sdf = new SimpleDateFormat(string);
				try {
					return sdf.parse(value);
				} catch (Exception e) {
					// TODO: handle exception
					continue;
				}
			}
			throw new RuntimeException("日期格式错误");
		}
		
	}

}
