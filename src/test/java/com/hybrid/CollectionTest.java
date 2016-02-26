package com.hybrid;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionTest {
	
	static Log log = LogFactory.getLog(CollectionTest.class);
	
	List<String> color;
	Map<String, String> language;
	Set<String> locale;
	
	public void setColor (List<String> color) {
		this.color = color;
	}
	
	public void setLanguage (Map<String, String> language) {
		this.language = language;
	}
	
	public void setLocale (Set<String> locale) {
		this.locale = locale;
	}
	
	public void printColor () {
		for (String c : color) {
			log.info("color = " + c);
		}
	}
	
	public void printLanuage () {
		for (Entry<String, String> e : language.entrySet()) {
			log.info(e.getKey() + " = " + e.getValue());
		}
	}
	
	public void printLocale () {
		for (String l : locale) {
			log.info(l);
		}
	}
	
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("spring/beans1.xml");
		
		CollectionTest ct = ctx.getBean(CollectionTest.class);
		ct.printColor();
		ct.printLanuage();
		ct.printLocale();
	}
	
}
