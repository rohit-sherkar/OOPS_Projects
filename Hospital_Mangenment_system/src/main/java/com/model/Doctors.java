package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctors {
	private Connection con;
	private Scanner scanner;
	
	public Doctors(Connection connection) {
		this.con=connection;
		
	}
	
	

	public void viewDoctors() {
		String sql="select * from doctors";
		try {
			PreparedStatement pre=con.prepareStatement(sql);
			ResultSet re=pre.executeQuery();
			System.out.println("Doctors: ");
			System.out.println("+-------------+-----------------------+---------------------------+");
			System.out.println("| Doctors Id  | Name                  | Specialization            |");
			System.out.println("+-------------+-----------------------+---------------------------+");
			while(re.next()) {
				int id=re.getInt("id");
				String name=re.getString("name");
				    //database columns name();
				String specilization=re.getString("specilization");
				
			    System.out.printf("|%-13s|%-24s|%-27s|\n",id,name,specilization);
			    System.out.println("+-------------+-----------------------+---------------------------+");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
				
	}
	public boolean getDoctorsById(int id) {
		String sql="select * from doctors where id=?";

		try {
			PreparedStatement pre=con.prepareStatement(sql);
			ResultSet re=pre.executeQuery();
			if(re.next()) {
				return true;
			}
			else {
				return false;
		 
			}
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
		
		
		
	}

}



