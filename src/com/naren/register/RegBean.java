package com.naren.register;

public class RegBean {

	String email=null, pwd=null, rpwd=null;

	public String getEmail() {
		System.out.println("Inside of getemail");
		return email;
	}

	public void setEmail(String email) {
		System.out.println("Inside of setemail");
		this.email = email;
	}

	public String getPwd() {
		System.out.println("Inside of getpwd");
		return pwd;
	}

	public void setPwd(String pwd) {
		System.out.println("Inside of setpwd");
		this.pwd = pwd;
	}

	public String getRpwd() {
		System.out.println("Inside of getrpwd");
		return rpwd;
	}

	public void setRpwd(String rpwd) {
		System.out.println("Inside of setrpwd");
		this.rpwd = rpwd;
	}
	
	public String validate(){
		String msg="";
		if(email==null || email.trim().equals("") )
		{
			return msg="You dint entered email go back and enter it";
		}
		else if(email.contains("@"))
		{
			String a[]=email.split("@");
			if(!a[1].contains("."))
			{
				return msg="U dint enter email properly";
			}
		}
		else
		{
			return msg="U dint entered email correctly";
		}
		if(pwd==null || pwd.trim().equals(""))
		{
			return msg="Your password is blank";
		}
		if(rpwd==null ||rpwd.trim().equals("")|| !rpwd.equals(pwd))
		{
			return msg=msg+"Password dint match";
		}
		if(msg.equals(""))
		{
			return Constants.SUCCESS;
		}
		else
		{
			return msg;
		}
	}
	
}
