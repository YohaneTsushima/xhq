package com.aq.web.converter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateConverter extends JsonSerializer<Date> implements Converter<String, Date>{

	private String[] datePattern;
	
	public void setDatePattern(String[] datePattern) {
		this.datePattern = datePattern;
	}

	@Override
	public Date convert(String value) {
		// TODO 实现日期转换
		if(value == null || value.isEmpty()){
			return null;
		}else {
			for (String string : datePattern) {
				SimpleDateFormat sdf = new SimpleDateFormat(string);
				try {
					return sdf.parse(value);
				} catch (Exception e) {
					// TODO: 转换异常时候继续下一步
					continue;
				}
			}
			throw new RuntimeException("日期格式错误");
		}
		
	}

	@Override
	public void serialize(Date value, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		generator.writeString(sdf.format(value));
	}

}
