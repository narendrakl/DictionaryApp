package com.naren.register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {

	PreparedStatement ps1 = null, ps2=null, ps3=null;
	ResultSet rs = null, rs1=null;	
	public String register(RegBean rb)
		{
			if(rb.validate().contains(Constants.SUCCESS))
			{
				System.out.println("User input validations have succeeded");
				
				Connection c = JDBCHelper.getConnection();
				if(c==null)
				{
					System.out.println("SomeThing bad happened database is not working!!!");
					return "Boss your Hanebaraha is not good!!!Database is not working";
				}
				else
				{
					
					
					try
					{
						String ssql = "select * from registration where email = ?";
						ps2 = c.prepareStatement(ssql);
						ps2.setString(1, rb.getEmail());
						ps2.execute();
						rs = ps2.getResultSet();
						
						if(rs.next())
						{
							System.out.println("U are a duplicate user Your email id already exists");
							return "U are duplicate user";
						}
						else
						{
							String sql = "insert into registration(name,email,pass) values(?,?,?)";
							ps1 = c.prepareStatement(sql);
							if(rb.getEmail().contains("@"))
							{
								String a[] = rb.getEmail().split("@");
								ps1.setString(1, a[0]);
							}
							ps1.setString(2, rb.getEmail());
							ps1.setString(3, rb.getPwd());
							ps1.execute();							
							String msg ="U have Successfully Registered";
							return Constants.SUCCESS;
						}
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}
					finally
					{
						JDBCHelper.close(rs);
						JDBCHelper.close(ps2);
						JDBCHelper.close(ps1);
						JDBCHelper.close(c);
					}
															
				}
			
			}
			else
			{
				return "User Input validations have failed";
			}
			return "";
		}
	public String authenticate(LogInBean lb){
		 		
		String result = lb.validate(), msg = "";
		
		if(result.contains(Constants.SUCCESS))
		{
			System.out.println("User input validations have succeeded");
			
			Connection c = JDBCHelper.getConnection();
			if(c==null)
			{
				System.out.println("SomeThing bad happened database is not working!!!");
				return "Boss your Hanebaraha is not good!!!Database is not working";
			}
			else
			{
				
				String sql = "Select * from registration where email = ? and pass = ? ";
				int slno = 0;
				try 
				{
					ps1 = c.prepareStatement(sql);
					ps1.setString(1,lb.getEmail());
					ps1.setString(2, lb.getPwd());
					ps1.execute();
					c.commit();
					rs = ps1.getResultSet();
					if(rs.next())
					{	
						slno = rs.getInt(1);
						sql = "select * from users where email = ?";
						ps1 = c.prepareStatement(sql);
						ps1.setString(1, lb.getEmail());
						ps1.execute();
						c.commit();
						rs=ps1.getResultSet();
						if(rs.next())
						{
							System.out.println("I find the user i can authenticate!!");
							return msg = Constants.SUCCESS;
						}
						else
						{
							sql="insert into users(email,reg_slno) values(?,?)";
							ps1 = c.prepareStatement(sql);
							ps1.setString(1, lb.getEmail());
							ps1.setInt(2, slno);
							ps1.execute();
							c.commit();
							System.out.println("I inserted to user table");
							return Constants.SUCCESS;
						}
					}
					else
					{
						System.out.println("Email and password dint match");
						msg = "Email and password dint match";
						return msg;
					}
				} 
				catch (SQLException e) 
				{
					
					e.printStackTrace();
				}
				finally
				{
					JDBCHelper.close(rs);
					JDBCHelper.close(ps2);
					JDBCHelper.close(ps1);
					JDBCHelper.close(c);
				}
			}
		}
		else
		{
			System.out.println("Email or pwd is blank");
			msg = "Email or your Password is blank";
		}
		return msg;
	}
	
	public String createDictionary(String email, DictNameBean db) 
	{
			Connection con = null;
			PreparedStatement ps=null;
			ResultSet rs = null;
			String sql=null;
			String msg=null;
			int slno = 0, uslno = 0;
			if(db.validate().contains(Constants.SUCCESS))
			{
				con = JDBCHelper.getConnection();
				String dictname = db.getDname();
				System.out.println("inside of if block of createDictionary method and email is"+email);
				try 
				{
					sql = "select * from users where email = ?";
					ps = con.prepareStatement(sql);
					ps.setString(1, email);
					ps.execute();
					con.commit();
					rs = ps.getResultSet();
					if(rs.next())
					{
						slno =rs.getInt(3);
						uslno = rs.getInt(1);
						System.out.println(slno);
						sql = "select * from dict_name where dname = ? and dic_user_slno = ?";
						ps = con.prepareStatement(sql);
						ps.setString(1, dictname);
						ps.setInt(2, uslno);
						ps.execute();
						con.commit();
						rs = ps.getResultSet();
						if(rs.next())
						{
								System.out.println("Dictionary name already exists" );
								return Constants.SUCCESS;
							
						}
						else
						{
							System.out.println("dictionary name is unique"+" "+dictname);
							//con.setAutoCommit(false);
							sql = "insert into dict_name(dname,dic_user_slno) values(?,?)";
							ps=con.prepareStatement(sql);
							ps.setString(1, dictname);
							ps.setInt(2, uslno);
							ps.execute();
							return Constants.SUCCESS;
						}
					}
				}
				catch (SQLException e) 
				{
					try 
					{
						con.rollback();
					} catch (SQLException e1) 
					{
						
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
				finally
				{
					JDBCHelper.close(rs);
					JDBCHelper.close(ps2);
					JDBCHelper.close(ps1);
					JDBCHelper.close(con);
				}
			}
			else
				msg="Ree Dictionary Name enter maadi";
			return msg;
	}
	public String addword(AddWordBean ab, String dname) 
	{
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		String sql=null;
		String msg=null;
		int slno=0, wslno=0, pslno=0;
		System.out.println("i am inside of addword method");
		if(ab.validate().contains(Constants.SUCCESS))
		{
			System.out.println("User input validations have succeeded i am inside of if block");
			con = JDBCHelper.getConnection();
			try
			{
				sql = "select * from dict_name where dname = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, dname);
				ps.execute();
				con.commit();
				rs=ps.getResultSet();
				if(rs.next())
				{
					slno=rs.getInt(1);
					System.out.println("dictslno"+slno);
					sql="select * from words, pos_master where words.text=? and words.dict_slno=? and pos_master.pos=?";
					ps=con.prepareStatement(sql);
					ps.setString(1, ab.getWord());
					ps.setInt(2, slno);
					ps.setString(3, ab.getPos());
					ps.execute();
					rs=ps.getResultSet();
					if(rs.next())
					{
						wslno=rs.getInt(1);
						pslno=rs.getInt(4);
						System.out.println(wslno+" "+pslno);
						sql="select * from words_details where word_slno=? and pos_slno=?";
						ps=con.prepareStatement(sql);
						ps.setInt(1, wslno);
						ps.setInt(2, pslno);
						ps.execute();
						System.out.println("After executing sql");
						con.commit();
						rs=ps.getResultSet();
						if(rs.next())
						{
							System.out.println("Duplicate word u did not enter it");
							return msg="Duplicate word u did not enter it";
						}
						else
						{
							System.out.println("inserting into words_details");
							sql="insert into words_details(meaning,example,word_slno,pos_slno) values(?,?,?,?)";
							ps=con.prepareStatement(sql);
							ps.setString(1, ab.getMeaning());
							ps.setString(2, ab.getExample());
							ps.setInt(3, wslno);
							ps.setInt(4, pslno);
							ps.execute();
							con.commit();
							return Constants.SUCCESS;
						}
					}
					else
					{
						sql="insert into words(text,dict_slno) values(?,?)";
						ps=con.prepareStatement(sql);
						ps.setString(1, ab.getWord());
						ps.setInt(2, slno);
						ps.execute();
						con.commit();
						System.out.println("inserted words successfully");
						
						sql="select * from words, pos_master where words.text=? and pos_master.pos=?";
						ps=con.prepareStatement(sql);
						ps.setString(1, ab.getWord());
						ps.setString(2, ab.getPos());
						ps.execute();
						rs=ps.getResultSet();
						if(rs.next())
						{
							wslno=rs.getInt(1);
							pslno=rs.getInt(4);
							System.out.println("inserting into words_details");
							sql="insert into words_details(meaning, example, word_slno, pos_slno) values(?,?,?,?)";
							ps=con.prepareStatement(sql);
							ps.setString(1, ab.getMeaning());
							ps.setString(2, ab.getExample());
							ps.setInt(3, wslno);
							ps.setInt(4, pslno);
							ps.execute();
							con.commit();
							return Constants.SUCCESS;
						}
					}
					
				}
					
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				JDBCHelper.close(rs);
				JDBCHelper.close(ps2);
				JDBCHelper.close(ps1);
				JDBCHelper.close(con);
			}
		}
		else	
			msg= (String)ab.validate();
		return msg;
	}
	public String searchword(String dname, AddWordBean ab) 
	{
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		String sql=null;
		String msg="";
		int slno=0;	
		String word = ab.getWord();
		if(word!=null)
		{
			con = JDBCHelper.getConnection();
			try 
			{
				System.out.println("inside of ab.getword()"+" "+dname+" "+ word);
				sql = "select * from dict_name where dname=?";
				ps=con.prepareStatement(sql);
				ps.setString(1, dname);
				ps.execute();
				con.commit();
				rs=ps.getResultSet();
				if(rs.next())
				{
					System.out.println("inside of dict_name result set"+" "+rs.getInt(1));
					slno=rs.getInt(1);
					sql = "select * from words, pos_master, words_details where words.text=? and words.dict_slno=? and pos_master.slno=words_details.pos_slno and words.slno=words_details.word_slno";
					ps=con.prepareStatement(sql);
					ps.setString(1, word);
					ps.setInt(2, slno);
					ps.execute();
					con.commit();
					rs=ps.getResultSet();
						while(rs.next())
						{
							//rs.getRowId("column label"); If u are using mysql present hsqldb jdbc is not supported to this//
							//System.out.println("Meaning="+rs.getString(2)+" "+"example"+" "+rs.getString(3)+" "+"Part of sp"+" "+rs.getInt(5));
							 msg = msg+"Word="+rs.getString(2)+"@"+"Meaning="+rs.getString(7)+"@"+"Example="+rs.getString(8)+"@"+"Part of Speech="+rs.getString(5)+"@";
						}
						return msg+"0";
				}
										
					else
					{
						System.out.println("No word find");
						return msg = "No word found";
					}
				
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			finally
			{
				JDBCHelper.close(rs);
				JDBCHelper.close(ps2);
				JDBCHelper.close(ps1);
				JDBCHelper.close(con);
			}
		}
		else
			msg= "Word is empty";
		return msg;
	}
	public String editword(String dname, AddWordBean ab)
	{
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		String sql=null, sql1=null;
		String msg=null;
		int slno=0;	
		String word = ab.getWord(), pos=ab.getPos();
		if(word!=null)
		{
			con = JDBCHelper.getConnection();
			try 
			{
				System.out.println("inside of ab.getword()"+" "+dname+" "+ word);
				sql = "select * from dict_name where dname=?";
				ps=con.prepareStatement(sql);
				ps.setString(1, dname);
				ps.execute();
				con.commit();
				rs=ps.getResultSet();
				if(rs.next())
				{
					System.out.println("inside of dict_name result set"+" "+rs.getInt(1));
					slno=rs.getInt(1);
				
					sql="select * from words, pos_master, words_details where words.text=? and words.dict_slno=? and pos_master.pos=? and pos_master.slno=words_details.pos_slno and words.slno=words_details.word_slno";
					ps=con.prepareStatement(sql);
					ps.setString(1, word);
					ps.setInt(2, slno);
					ps.setString(3, pos);
					ps.execute();
					con.commit();
					rs=ps.getResultSet();
					if(rs.next())
					{
						int wslno=rs.getInt(1);
						int pslno=rs.getInt(4);
						System.out.println(wslno+" "+pslno);
						sql="delete from words_details where words_details.word_slno=? and words_details.pos_slno=?";
						ps=con.prepareStatement(sql);
						ps.setInt(1, wslno);
						ps.setInt(2, pslno);
						ps.execute();
						con.commit();
						return Constants.SUCCESS;
					}
					else
					{
						System.out.println("Word dint find may be there is no present word in your dictionary");
						return msg="Word dint find";
					}
					
				}
			} 
			catch (SQLException e) 
			{
					e.printStackTrace();
			}
			finally
			{
					JDBCHelper.close(rs);
					JDBCHelper.close(ps2);
					JDBCHelper.close(ps1);
					JDBCHelper.close(con);
			}	
		}
		else
			msg= "Word is empty";
		return msg;
		
	}
	public String deleteword(String dname, AddWordBean ab) 
	{
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		String sql=null, sql1=null;
		String msg=null;
		int slno=0;	
		String word = ab.getWord(), pos=ab.getPos();
		if(word!=null)
		{
			con = JDBCHelper.getConnection();
			try 
			{
				System.out.println("inside of ab.getword()"+" "+dname+" "+ word);
				sql = "select * from dict_name where dname=?";
				ps=con.prepareStatement(sql);
				ps.setString(1, dname);
				ps.execute();
				con.commit();
				rs=ps.getResultSet();
				if(rs.next())
				{
					System.out.println("inside of dict_name result set"+" "+rs.getInt(1));
					slno=rs.getInt(1);
				
					sql="select * from words, pos_master, words_details where words.text=? and words.dict_slno=? and pos_master.pos=? and pos_master.slno=words_details.pos_slno and words.slno=words_details.word_slno";
					ps=con.prepareStatement(sql);
					ps.setString(1, word);
					ps.setInt(2, slno);
					ps.setString(3, pos);
					ps.execute();
					con.commit();
					rs=ps.getResultSet();
					if(rs.next())
					{
						int wslno=rs.getInt(1);
						int pslno=rs.getInt(4);
						System.out.println(wslno+" "+pslno);
						sql="delete from words_details where words_details.word_slno=? and words_details.pos_slno=?";
						ps=con.prepareStatement(sql);
						ps.setInt(1, wslno);
						ps.setInt(2, pslno);
						ps.execute();
						con.commit();
						return Constants.SUCCESS;
					}
					else
					{
						/*sql="delete from words where words.text=? and dict_slno=?";
						ps=con.prepareStatement(sql);
						ps.setString(1, word);
						ps.setInt(2, slno);
						ps.execute();
						con.commit();*/
						return msg = "no word";
					}
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				JDBCHelper.close(rs);
				JDBCHelper.close(ps2);
				JDBCHelper.close(ps1);
				JDBCHelper.close(con);
			}
		}
		else
		{
			msg="field is empty";
		}
		return msg;
	}
	public String listword(String dname) 
	{
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		String sql=null;
		String msg="";
		int slno=0;	
		con = JDBCHelper.getConnection();
		try
		{
			sql = "select * from dict_name where dname=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, dname);
			ps.execute();
			con.commit();
			rs=ps.getResultSet();
			if(rs.next())
			{
				slno=rs.getInt(1);
				System.out.println(slno);
				sql="select * from words, words_details, pos_master where words.dict_slno=? and words.slno=words_details.word_slno and words_details.pos_slno=pos_master.slno";
				ps=con.prepareStatement(sql);
				ps.setInt(1, slno);
				ps.execute();
				con.commit();
				rs=ps.getResultSet();
				while(rs.next())
				{
					System.out.println("Word="+rs.getString(2)+" Meaning="+rs.getString(5)+" Example="+rs.getString(6)+" Part of Speech="+rs.getString(10));
					msg=msg+"Word="+rs.getString(2)+","+" Meaning="+rs.getString(5)+","+" Example="+rs.getString(6)+","+" Part of Speech="+rs.getString(10)+"@";
				}
				
					return msg+"0";
				
			}
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			JDBCHelper.close(rs);
			JDBCHelper.close(ps2);
			JDBCHelper.close(ps1);
			JDBCHelper.close(con);
		}
		return "";
	}
	public String loadDict()
	{
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		String sql=null;
		String msg="";
		con = JDBCHelper.getConnection();
		try
		{
			sql="select dict_name.dname from dict_name ";
			ps=con.prepareStatement(sql);
			ps.execute();
			rs=ps.getResultSet();
			while(rs.next())
			{
				msg=msg+rs.getString(1)+"@";
			}
			return msg+"0";
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			JDBCHelper.close(rs);
			JDBCHelper.close(ps2);
			JDBCHelper.close(ps1);
			JDBCHelper.close(con);
		}
		return "";
	}
	public String loadcorrect(String dname) 
	{
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		String sql=null;
		String msg="";
		con = JDBCHelper.getConnection();
		try
		{
			sql="select dict_name.dname from dict_name where dname=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, dname);
			ps.execute();
			rs=ps.getResultSet();
			if(rs.next())
			{
				return Constants.SUCCESS;
			}
			else
			return msg = "That dictionary does not exist";
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			JDBCHelper.close(rs);
			JDBCHelper.close(ps2);
			JDBCHelper.close(ps1);
			JDBCHelper.close(con);
		}
		return " ";
	}
}
