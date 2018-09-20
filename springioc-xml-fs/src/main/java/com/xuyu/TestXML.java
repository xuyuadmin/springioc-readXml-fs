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
		//��ȡxml�ļ�
		Document document = saxReader.read(getResourceAsStream("student.xml"));
		//��ȡ���ڵ�
		Element rootElement = document.getRootElement();
		getNodes(rootElement);;
		
	}
	public void getNodes(Element rootElement) {
		System.out.println("��ȡ��ǰ�ڵ����ƣ�"+rootElement.getName());
		//��ȡ�������ƺ��ı�
		List<Attribute> attributes = rootElement.attributes();
		for (Attribute attribute : attributes) {
			System.out.println(attribute.getName()+"---"+attribute.getText());
		}
		//��ȡ����value
		String textTrim = rootElement.getTextTrim();
		if(StringUtils.isEmpty(textTrim)) {
			System.out.println("textTrim:"+textTrim);
		}
		//ʹ�õ�������ȡ�ӽڵ���Ϣ
		Iterator<Element> elementIterator = rootElement.elementIterator();
		while (elementIterator.hasNext()) {
			Element nextElement = elementIterator.next();
			getNodes(nextElement);
		}
	}
	
	public InputStream getResourceAsStream(String path) {
		//��ȡ��ǰ��Ŀ·��
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
		return inputStream; 
	}
}
