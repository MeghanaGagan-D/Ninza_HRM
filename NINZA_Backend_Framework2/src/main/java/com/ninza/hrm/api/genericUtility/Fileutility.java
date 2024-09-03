package com.ninza.hrm.api.genericUtility;

import java.io.FileInputStream;
import java.util.Properties;

public class Fileutility {
public String getDataFromPropertiesFile(String key) throws Exception
{
	FileInputStream fis=new FileInputStream("./config_env_data/configenv.properties");
	Properties pobj=new Properties();
	pobj.load(fis);
	String data=pobj.getProperty(key);
	return data;	
}
}
