package com.xuyu.xml.spring;

import java.io.InputStream;
import java.net.NetworkInterface;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 自定义spring 容器框架 xml解析
 * 
 * @author Administrator
 *
 */
public class ClassPathXmlApplicationContext {

	// xml读取路径地址
	private String xmlPath;

	public ClassPathXmlApplicationContext(String xmlPath) {
		this.xmlPath = xmlPath;
	}

	public Object getBean(String beanId) throws Exception {
		// 判断beanid是否为空
		if (StringUtils.isEmpty(beanId)) {
			throw new Exception("beanid不能为空");
		}
		// 1.解析xml文件
		List<Element> readXML = readXML();
		if (readXML == null || readXML.isEmpty()) {
			throw new Exception("配置文件中，没有配置bean信息");
		}
		// 2.使用方法参数beanid查找配置文件中bean节点id是否对应
		String className = findByElement(readXML, beanId);
		//3.判读className是否为空
		if(StringUtils.isEmpty(className)) {
			throw new Exception("该bean对象没有获取到class地址");
		}
		//4.反射初始化对象
		return newInstance(className);

	}

	// 使用方法参数beanid查找配置文件bean节点的id信息是否一致 返回Class地址
	public String findByElement(List<Element> readXML, String beanId) throws Exception {
		for (Element element : readXML) {
			// 获取属性信息
			String xmlBeanId = element.attributeValue("id");
			if (StringUtils.isEmpty(xmlBeanId)) {
				// 结束循环
				continue;
			}
			if (xmlBeanId.equals(beanId)) {
				String xmlClassName = element.attributeValue("class");
				// 3.获取class信息地址，使用反射机制初始化
				String xmlClass = (String) newInstance(xmlClassName);
				return xmlClass;
			}
		}
		return null;
	}

	// 解析xml
	public List<Element> readXML() throws DocumentException {

		// 1.解析xml文件信息
		SAXReader saxReader = new SAXReader();
		// 读取xml文件
		Document document = saxReader.read(getResourceAsStream(xmlPath));
		// 2.读取根节点
		Element rootElement = document.getRootElement();
		// 3.获取根节点下的所有子节点
		List<Element> elements = rootElement.elements();
		return elements;
	}

	/**
	 * 初始化对象
	 */
	public Object newInstance(String className) throws Exception {
		Class<?> classInfo = Class.forName(className);
		return classInfo.newInstance();
	}

	public InputStream getResourceAsStream(String path) {
		// 获取当前项目路径
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
		return inputStream;
	}
}
