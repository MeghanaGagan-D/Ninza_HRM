package com.ninza.hrm.api.genericUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


/*
 * @author = Meghana T D
 * class contains Java reusable methods such as getRandomnumber(), getSystemDate() and getRequiredDateYYYYMMDD(int days)
 */


public class JavaUtility {
public int getRandomnumber()
{
	Random random=new Random();
	int randomnumber=random.nextInt(5000);
	return randomnumber;
}

public String getSystemDate() {
	Date datepbj= new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	String date=sdf.format(datepbj);
	return date;
}

public String getRequiredDateYYYYMMDD(int days)
{
	Date datepbj= new Date();
	SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
	String date=sim.format(datepbj);
	Calendar cal=sim.getCalendar();
	
	cal.add(Calendar.DAY_OF_MONTH,days);
	String reqdate=sim.format(cal.getTime());
	return reqdate;
	
}
}
