package com.naren.register;

public class DictNameBean {

	String dname = null, email = null, pwd = null;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getDname() 
	{
		System.out.println("Inside of DictNameBean of getDname method");
		return dname;
	}

	public void setDname(String dname) 
	{
		System.out.println("Inside of DictNameBean of setDname method");
		this.dname = dname;
	}
	
	public String validate()
	{
		if(dname == null || dname.trim().equals(""))
		{
			String msg = "";
			System.out.println("Dictionary name is empty");
			msg = "Dictionary name is empty";
			return msg;
		}
		else
			return Constants.SUCCESS;
	}
	
	
}
