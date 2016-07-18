package com.jayqqaa12.mobilesafe.xml;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

import com.jayqqaa12.mobilesafe.domain.VersionInfo;

public class VersionInfoParse
{
	/*
	 * parser xml version
	 */
	public static VersionInfo parseVersion(InputStream is) throws XmlPullParserException, IOException
	{
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "utf-8");
		VersionInfo info = new VersionInfo();
		int type = parser.getEventType();

		while (type != XmlPullParser.END_DOCUMENT)
		{
			switch (type)
			{
			case XmlPullParser.START_TAG:

				if (info.XML_TITLE_VERSION.equals(parser.getName()))
				{
					info.setNumber(parser.nextText());
				}
				else if (info.XML_TITLE_DESCIPTION.equals(parser.getName()))
				{
					info.setDesciber(parser.nextText());
				}
				else if (info.XML_TITLE_APKURL.equals(parser.getName()))
				{
					info.setApkurl(parser.nextText());
				}
				break;
			}
			type = parser.next();

		}

		return info;
	}
}