package my.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import android.util.Xml;

import domain.Person;

public class PersonService {
	//����person.xmnl
	public static  List<Person> getPersons(InputStream xml) throws Exception{
		//��ȡpull������ ����
		List<Person> persons=null;
		Person person=null;
		XmlPullParser pullParser=Xml.newPullParser();
		pullParser.setInput(xml, "UTF-8");//����.xml�ĵ��ĵ�һ�У�<?xml version="1.0" encoding="UTF-8"?>
		int event=pullParser.getEventType();//����start_document�¼�
		//ѭ��
		while(event!=XmlPullParser.END_DOCUMENT){
			switch(event){
			case XmlPullParser.START_DOCUMENT:
				persons=new ArrayList<Person>();
				break;
			
			case XmlPullParser.START_TAG: //��ʼ���
				if("person".equals(pullParser.getName())){
					int id=new Integer(pullParser.getAttributeValue(0));
					
					person=new Person();
					person.setId(id);
				}
				if("name".equals(pullParser.getName())){
					String name=pullParser.nextText();
					
					person.setName(name);
				}
				if("age".equals(pullParser.getName())){
					int age=new Integer(pullParser.nextText());
					
					person.setAge(age);
				}
				break;
			case XmlPullParser.END_TAG: //�������
				if("person".equals(pullParser.getName())){
					persons.add(person);
					person=null;
				}
				break;
			}
			event=pullParser.next();
		}
		return persons;
	}
	
	
	/**
	 * �������ݵ�XML�ļ���
	 * @param person
	 * @throw Exception
	 */
	public static void save(List<Person> persons,OutputStream out) throws Exception{
	
		XmlSerializer serializer = Xml.newSerializer();
		serializer.setOutput(out, "UTF-8");
		serializer.startDocument("UTF-8", true);
		serializer.startTag(null, "persons");
		for(Person person : persons){
			serializer.startTag(null, "person");
			serializer.attribute(null, "id", person.getId().toString());
			
			serializer.startTag(null, "name");
			serializer.text(person.getName());
			serializer.endTag(null, "name");
			
			serializer.startTag(null, "age");
			serializer.text(person.getAge().toString());
			serializer.endTag(null, "age");
			
			serializer.endTag(null, "person");
		}
		serializer.endTag(null, "persons");
		serializer.endDocument();
		out.flush();
		out.close();
	}
}



