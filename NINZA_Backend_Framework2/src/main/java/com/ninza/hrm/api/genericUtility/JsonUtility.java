package com.ninza.hrm.api.genericUtility;



import java.util.List;

import com.jayway.jsonpath.JsonPath;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

/**
 * @author= Meghana T D
 */

public class JsonUtility {
	Fileutility fLib= new Fileutility();
	
	/**
	 * get JSon data using JSon complex xpath
	 * @param resp
	 * @param jsonPath
	 * @return jsonData
	 */
	public String getDataBasedOnJsonPath(Response resp, String jsonXpath)
	{
		List<Object> list = JsonPath.read(resp.asString(), jsonXpath);
		return list.get(0).toString();
	}
	
	/**
	 * get xml data using xml complex xpath
	 * @param resp
	 * @param xmlXpath
	 * @return xmlData
	 */
	public String getDataBasedOnXmlPath(Response resp, String xmlXpath)
	{
		return resp.xmlPath().get(xmlXpath);
	}
	
	/**
	 * verify the data in Response body based on JSonpath
	 * @param resp
	 * @param jsonXpath
	 * @param expectedData
	 * @return boolean_value
	 */
	public boolean verifyDataOnJsonPath(Response resp, String jsonXpath, String expectedData)
	{
		List<String> list= JsonPath.read(resp.asString(), jsonXpath);
		boolean flag=false;
		for(String str: list)
		{
			if(str.equals(expectedData)) {
				flag=true;
				System.out.println(expectedData +"is available");
			}
		}
		if(flag==false) {
			System.out.println(expectedData +"is not available");
		}
		return flag;
	}
	
	/**
	 * this method will return the access token present in response body for Oauth2 protocol
	 * @return access_token
	 * @throws Exception 
	 */
	public String getAccessToken() throws Exception {
		Response resp= given()
				.formParam("client_id", fLib.getDataFromPropertiesFile("ClientID"))			//these things will be given by developer
				.formParam("client_secret", fLib.getDataFromPropertiesFile("ClientSecret"))
				.formParam("grant_type","client_credentials")

				.when().post("http://49.249.28.218:8180/auth/realms/ninza/protocol/openid-connect/token");
				resp.then().log().all();
				
				//capture data from response
				String token= resp.jsonPath().get("access_token");
				return token;
	}

}
