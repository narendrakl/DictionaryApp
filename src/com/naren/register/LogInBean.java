package com.naren.register;

public class LogInBean {

	String email = null;
	String pwd = null;
	
	public String getEmail() {
		System.out.println("Inside of getEmail of login");
		return email;
	}
	public void setEmail(String email) {
		System.out.println("Inside of setEmail of login");
		this.email = email;
	}
	public String getPwd() {
		System.out.println("Inside of getpwd of login");
		return pwd;
	}
	public void setPwd(String pwd) {
		System.out.println("Inside of setpwd of login");
		this.pwd = pwd;
	}
	
	public String validate()
	{
		String msg = "";
		if(email == null || email.trim().equals(""))
		{
			return msg = "Email field is blank!!";
		}
		if(email.contains("@"))
		{
			String a[] = email.split("@");
			if(!a[1].contains("."))
			{
				return msg = "Enter email correctly";
			}
		}
		if(pwd==null || pwd.trim().equals(""))
		{
			return msg = msg + "Password is blank";
		}
		if(msg.equals(""))
		{
			return Constants.SUCCESS;
		}
		else
		return "Input validations have failed";
	}
}
