package com.model;

public class Car {
	private String carId;
	private String model;
	private String brand;
	private double basePricePerDay;
	private boolean isAvailable;
//	public String getCarId() {
//		return carId;
//	}
//	public void setCarId(String carId) {
//		this.carId = carId;
//	}
//	public String getModel() {
//		return model;
//	}
//	public void setModel(String model) {
//		this.model = model;
//	}
//	public String getBrand() {
//		return brand;
//	}
//	public void setBrand(String brand) {
//		this.brand = brand;
//	}
//	public double getBasePricePerDay() {
//		return basePricePerDay;
//	}
//	public void setBasePricePerDay(double basePricePerDay) {
//		this.basePricePerDay = basePricePerDay;
//	}
//	public boolean isAvailable() {
//		return isAvailable;
//	}
//	public void setAvailable(boolean isAvailable) {
//		this.isAvailable = isAvailable;
//	}
	
	public Car(String carId,String brand,String model,double basePricePerDay) {
		this.carId=carId;
		this.model=model;
		this.brand=brand;
		this.basePricePerDay=basePricePerDay;
		this.isAvailable=true;
	}
	public String getcarId() {
		return carId;
	}
	public String getBrand() {
		return brand;
	}
	public String getModel() {
		return model;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public double getBasePricePerday() {
		return basePricePerDay;
	}
	public double calculatePrice(int rentPerDay) {
		return basePricePerDay * rentPerDay;
	}
	public void rent() {
		isAvailable=false;
	}
	public void returnCar() {
		isAvailable=true;
	}
	
	
	
	

}
