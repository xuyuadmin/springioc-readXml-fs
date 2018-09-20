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
 * �Զ���spring ������� xml����
 * 
 * @author Administrator
 *
 */
public class ClassPathXmlApplicationContext {

	// xml��ȡ·����ַ
	private String xmlPath;

	public ClassPathXmlApplicationContext(String xmlPath) {
		this.xmlPath = xmlPath;
	}

	public Object getBean(String beanId) throws Exception {
		// �ж�beanid�Ƿ�Ϊ��
		if (StringUtils.isEmpty(beanId)) {
			throw new Exception("beanid����Ϊ��");
		}
		// 1.����xml�ļ�
		List<Element> readXML = readXML();
		if (readXML == null || readXML.isEmpty()) {
			throw new Exception("�����ļ��У�û������bean��Ϣ");
		}
		// 2.ʹ�÷�������beanid���������ļ���bean�ڵ�id�Ƿ��Ӧ
		String className = findByElement(readXML, beanId);
		//3.�ж�className�Ƿ�Ϊ��
		if(StringUtils.isEmpty(className)) {
			throw new Exception("��bean����û�л�ȡ��class��ַ");
		}
		//4.�����ʼ������
		return newInstance(className);

	}

	// ʹ�÷�������beanid���������ļ�bean�ڵ��id��Ϣ�Ƿ�һ�� ����Class��ַ
	public String findByElement(List<Element> readXML, String beanId) throws Exception {
		for (Element element : readXML) {
			// ��ȡ������Ϣ
			String xmlBeanId = element.attributeValue("id");
			if (StringUtils.isEmpty(xmlBeanId)) {
				// ����ѭ��
				continue;
			}
			if (xmlBeanId.equals(beanId)) {
				String xmlClassName = element.attributeValue("class");
				// 3.��ȡclass��Ϣ��ַ��ʹ�÷�����Ƴ�ʼ��
				String xmlClass = (String) newInstance(xmlClassName);
				return xmlClass;
			}
		}
		return null;
	}

	// ����xml
	public List<Element> readXML() throws DocumentException {

		// 1.����xml�ļ���Ϣ
		SAXReader saxReader = new SAXReader();
		// ��ȡxml�ļ�
		Document document = saxReader.read(getResourceAsStream(xmlPath));
		// 2.��ȡ���ڵ�
		Element rootElement = document.getRootElement();
		// 3.��ȡ���ڵ��µ������ӽڵ�
		List<Element> elements = rootElement.elements();
		return elements;
	}

	/**
	 * ��ʼ������
	 */
	public Object newInstance(String className) throws Exception {
		Class<?> classInfo = Class.forName(className);
		return classInfo.newInstance();
	}

	public InputStream getResourceAsStream(String path) {
		// ��ȡ��ǰ��Ŀ·��
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
		return inputStream;
	}
}
