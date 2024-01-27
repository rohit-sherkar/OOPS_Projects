package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.cj.xdevapi.Result;

public class Patients {
	private Connection con;
	private Scanner scanner;
	
	public Patients(Connection connection,Scanner scanner) {
		this.con=connection;
		this.scanner=scanner;
	}
	
	
	public void addPatients() {
		System.out.println("Enter Patient Name: ");
		String name=scanner.next();
		System.out.println();
		
		System.out.println("Enter Patient Age: ");
		int age =scanner.nextInt();
		System.out.println("Enter Patient Gender: ");
		
		String gender=scanner.next();
		
		try {
			String sql="insert into patients(name,age,gender)values(?,?,?)";
			PreparedStatement pre=con.prepareStatement(sql);
			pre.setString(1, name);
			pre.setInt(2, age);
			pre.setString(3, gender);
			int result=pre.executeUpdate();
			if(result>0) {
				System.out.println("Patients Added Successfully!!");
			
			}
			else {
				System.out.println("Failed to add Patients");
			}
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void viewPatients() {
		String sql="select * from patients";
		try {
			PreparedStatement pre=con.prepareStatement(sql);
			ResultSet re=pre.executeQuery();
			System.out.println("Patients: ");
			System.out.println("+-------------+-----------------------+---------+----------------+");
			System.out.println("| Patients Id | Name                  | Age     | Gender         |");
			System.out.println("+-------------+-----------------------+---------+----------------+");
			while(re.next()) {
				int id=re.getInt("id");
				String name=re.getString("name");
				int age=re.getInt("age");     //database columns name();
				String gender=re.getString("gender");
				
			    System.out.printf("|%-13s|%-24s|%-9s|%-17s|\n",id,name,age,gender);
			    System.out.println("+-------------+-----------------------+---------+----------------+");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
				
	}
	public boolean getPatientById(int id) {
		String sql="select * from patients where id=?";

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
