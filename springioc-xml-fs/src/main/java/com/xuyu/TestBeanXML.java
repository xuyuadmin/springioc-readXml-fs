package com.xuyu;

import com.xuyu.service.UserService;
import com.xuyu.xml.spring.ClassPathXmlApplicationContext;

/**
 * ������дSpringIOC����
 * @author Administrator
 *
 */
public class TestBeanXML {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("spring.xml");
		UserService bean =(UserService) app.getBean("userServiceImpl");
		bean.add();
	}
}
