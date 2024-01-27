package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalSystem {
	private List<Car>cars;
	private List<Customer>customers;
	private List<Rental>rentals;
	
	public CarRentalSystem() {
		cars=new ArrayList<>();
		customers=new ArrayList<>();
		rentals=new ArrayList<>();
	}
	
	public void addCar(Car car) {
		cars.add(car);
	}
	public void addCustomer(Customer customer) {
	 customers.add(customer);
	}
	public void rentCar(Car car,Customer customer,int days) {
		if(car.isAvailable()) {
			car.rent();
			rentals.add(new Rental(car,customer,days));
		}
		else {
			System.out.println("Car is not available for rent");
		}
	}
	public void returnCar(Car car) {
		car.returnCar();
		Rental rentalToRemove=null;
		for(Rental rental:rentals) {
			if(rental.getCar()==car) {
				rentalToRemove=rental;
				break;
			}
		}
		if(rentalToRemove !=null) {
			rentals.remove(rentalToRemove);
		}
		else {
			System.out.println("car was not rented");
		}
	}
	public void menu() {
		Scanner scanner=new Scanner(System.in);
		while(true) {
			System.out.println("**** Car Rental System ****");
			System.out.println("1. Rent a Car");
		    System.out.println("2. Return a Car");
		    System.out.println("3. Exit");
		    System.out.println("Enter your choice");
		    
		    int choice=scanner.nextInt();
		    scanner.nextLine();//consume new line;
		    
		    if(choice ==1) {
		    	System.out.println("\n== Rent a Car ==\n");
		    	System.out.println("Enter your name");
		    	String customerName=scanner.nextLine();
		    	
		    	System.out.println("\nAvailable Cars:");
		    	for(Car car:cars) {
		    		if(car.isAvailable()) {
		    		   System.out.println(car.getcarId()+" - "+car.getBrand()+" "+car.getModel());
		    		}
		    	}
		    	System.out.println("\nEnter the car ID you want to rent: ");
		    	String carId=scanner.nextLine();
		    	
		    	System.out.println("Enter the number of days for rental: ");
		    	int rentalDays=scanner.nextInt();
		    	scanner.nextLine();
		    	
		    	Customer newCustomer=new Customer("customerId:" + "CUS" + (customers.size()+1),customerName);
		    	addCustomer(newCustomer);
		    	
		    	Car selectedCar=null;
		    	for(Car car:cars) {
		    		if(car.getcarId().equals(carId)&& car.isAvailable()) {
		    			selectedCar=car;
		    			break;
		    		}
		    	}
		    	if(selectedCar !=null) {
		    		double totalPrice=selectedCar.calculatePrice(rentalDays);
		    		System.out.println("\n== Rental Information ==\n");
		    		System.out.println("Customer ID: " + newCustomer.getCustomerId());
		    		System.out.println("Customer Name: " + newCustomer.getName());
		    		System.out.println("car: " + selectedCar.getBrand()+ " " + selectedCar.getModel());
		    		System.out.println("Rental Days: " + rentalDays);
		    		System.out.printf("Total Price: $%.2f%n",totalPrice);
		    		
		    		System.out.println("\nConfirm rental (Y/N): ");
		    		String confirm =scanner.nextLine();
		    		
		    		if(confirm.equalsIgnoreCase("Y")) {
		    			rentCar(selectedCar,newCustomer,rentalDays);
		    			System.out.println("\nCar rented successfully");
		    		}else {
		    			System.out.println("\nRental canceled.");
		    		}
		    		
		    	}
		    	else {
		    		System.out.println("\nInvaild car selection or car not available for rent.");
		    	}
		    	
		    	
		    }
		    else if(choice==2){
		    	System.out.println("\n** Return a car **\n");
		    	System.out.println("Enter the car id you wnat to return: ");
		    	String carId=scanner.nextLine();
		    	
		    	Car carToRetun=null;
		    	for(Car car:cars) {
		    		if(car.getcarId()==(carId)&& car.isAvailable()) {
		    			 carToRetun=car;
		    		}
		    	}
		    	if(carToRetun !=null) {
		    		Customer customer=null;
		    		for(Rental rental:rentals) {
		    			if(rental.getCar()==carToRetun) {
		    				customer=rental.getCustomer();
		    				break;
		    			}
		    		}
		    		if(customer !=null) {
		    			returnCar(carToRetun);
		    			System.out.println("Car returned successfully by "+ customer.getName());
		    		}
		    		else {
		    			System.out.println("Car was not rented or rental information missing.");
		    		}
		    	}
		    	else {
		    		System.out.println("Invalid car ID or car is not rented.");
		    	}
		    }
		    else if(choice ==3) {
		    	break;
		    }
		    else {
		    	System.out.println("Invalid choice,please enter a valid option:");
		    }
		}
		System.out.println("\nThank you for using the Car Rental System:");
	}
	
	
	
	
	

	
}
