package com.demo;

import com.model.Car;
import com.model.CarRentalSystem;

public class App 
{
    public static void main( String[] args )
    {
      CarRentalSystem rentalSystem=new CarRentalSystem();
      Car car1=new Car("C001","Toyoto","eitos",40);
      Car car2=new Car("C002","Maruti","alto",30);
      Car car3=new Car("C003","Honda","Honda City",35);
      
      rentalSystem.addCar(car1);
      rentalSystem.addCar(car2);
      rentalSystem.addCar(car3); 
      
      rentalSystem.menu();
    }
}
