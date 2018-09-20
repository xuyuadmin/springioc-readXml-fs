package com.xuyu;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class TestXML {

	public static void main(String[] args) throws Exception {
		 new TestXML().testXML();
	}
	public void testXML() throws Exception {
		SAXReader saxReader = new SAXReader();
		//读取xml文件
		Document document = saxReader.read(getResourceAsStream("student.xml"));
		//读取根节点
		Element rootElement = document.getRootElement();
		getNodes(rootElement);;
		
	}
	public void getNodes(Element rootElement) {
		System.out.println("获取当前节点名称："+rootElement.getName());
		//获取属性名称和文本
		List<Attribute> attributes = rootElement.attributes();
		for (Attribute attribute : attributes) {
			System.out.println(attribute.getName()+"---"+attribute.getText());
		}
		//获取属性value
		String textTrim = rootElement.getTextTrim();
		if(StringUtils.isEmpty(textTrim)) {
			System.out.println("textTrim:"+textTrim);
		}
		//使用迭代器获取子节点信息
		Iterator<Element> elementIterator = rootElement.elementIterator();
		while (elementIterator.hasNext()) {
			Element nextElement = elementIterator.next();
			getNodes(nextElement);
		}
	}
	
	public InputStream getResourceAsStream(String path) {
		//获取当前项目路径
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
		return inputStream; 
	}
}
