package com.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.model.Doctors;
import com.model.Patients;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;

public class App 
{
	private static final String url="jdbc:mysql://localhost:3306/hospital";
	private static final String usename="root";
	private static final String password="root";
    public static void main( String[] args )
    {
       try {
    	   Class.forName("com.mysql.cj.jdbc.Driver");
       }catch(ClassNotFoundException e) {
    	   e.printStackTrace();
       }
       Scanner scanner=new Scanner(System.in);
       try {
    	   Connection connection=DriverManager.getConnection(url,usename,password);
    	   Patients patient=new Patients(connection, scanner);
    	   Doctors doctor=new Doctors(connection);
    	   while(true) {
    		   System.out.println("HOSPITAL MANGENMENT SYSTEM");
    		   System.out.println("1. Add Patient");
    		   System.out.println("2. View Patients");
    		   System.out.println("3. View Doctors");
    		   System.out.println("4. Book Appointment");
    		   System.out.println("5. Exit");
    		   System.out.println("Enter your choice: ");
    		   int choice=scanner.nextInt();
    		   
    		   switch(choice) {
    		   case 1:
    			   patient.addPatients();
    			   System.out.println();
    			   break;
    		   case 2:
    			   patient.viewPatients();
    			   System.out.println();
    			   break;
    		   case 3:
    			   doctor.viewDoctors();
    			   System.out.println();
    			   break;
    		   case 4:
    			   bookApointment(patient,doctor,connection,scanner);
    			   System.out.println();
    			   break;
    		   case 5:
    			   return ;
    		   default :
    			   System.out.println("Enter valid choice!!");
    			   break;
    		   }
    		   
    		   
		
    	   }
       }catch(SQLException e) {
    	   e.printStackTrace();
       }
    }
    public static void bookApointment(Patients patient,Doctors doctor,Connection connection,Scanner scanner) {
    	System.out.println("Enter Patient ID: ");
    	int patientId=scanner.nextInt();
    	
    	System.out.println("Enter Doctor ID: ");
    	int doctorId =scanner.nextInt();
    	
    	System.out.println("Enter Appointment data (YYYY-MM-DD): ");
    	String appointmentDate =scanner.next();
    	System.out.println();
    	
    	if(patient.getPatientById(patientId) && doctor.getDoctorsById(doctorId)) {
    		if(checkDoctorAvailability(doctorId,appointmentDate,connection)) {
    			String appointmentQuery="insert into appointments(patient_id,doctor_id,appointment_data)values(?,?,?)";
    			try {
    				PreparedStatement pre=connection.prepareStatement(appointmentQuery);
    				pre.setInt(1, patientId);
    				pre.setInt(2, doctorId);
    				pre.setString(3, appointmentDate);
    				int rowsAffected=pre.executeUpdate();
    				if(rowsAffected>0) {
    					System.out.println("Appointment Booked");
    				}
    				else {
    					System.out.println("Failed to Book Appointment");
    				}
    				
    			}catch(SQLException e) {
    				e.printStackTrace();
    			}
    			
    		}else {
    			System.out.println( "Doctor not availbale this date"  );
    		}
    	}
    	else {
    		System.out.println("Either doctor or patient does not exist!!");
    	}
    	
    	
    }
    public static boolean checkDoctorAvailability(int doctorId,String appointmentDate,Connection connection) {
    	String sql="select count(*)from appointments where doctor_id=? and appointment_date=?";
    	try {
    		PreparedStatement pre=connection.prepareStatement(sql);
    		pre.setInt(1, doctorId);
    		pre.setString(2, appointmentDate);
    		ResultSet re=pre.executeQuery();
    		if(re.next()) {
    			int count=re.getInt(1);
    			if(count==0) {
    				return true;
    			}
    			else {
    				return false;
    			}
    			
    		}
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    
    	
    }
}
