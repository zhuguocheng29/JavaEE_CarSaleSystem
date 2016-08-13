/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment.Bean;

import fit5192.assignment.repository.CarRepository;
import fit5192.assignment.repository.entities.Car;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Think
 */
@Named(value = "salesPersonManagedBean")
@SessionScoped
public class SalesPersonManagedBean implements Serializable {
    
    @EJB
    private CarRepository carRepository;
    
    private String modelName;
    private String modelNo;
    private String make;
    private String type;
    private Car car;

    public SalesPersonManagedBean() {
    }
    


    public CarRepository getCarRepository() {
        return carRepository;
    }

    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    
    
    public String SalesPersondeleteCar()
    {
        System.out.println("Car VIN=====" + this.car.getVin());
        this.carRepository.deleteCar(car);
        return "/SalesPerson/SalesPerson";
              
    }
    
    public String SalesPersonResearchdeleteCar()
    {
        System.out.println("Car VIN SalesPersonResearch=====" + this.car.getVin());
        this.carRepository.deleteCar(car);
        return "/SalesPerson/SalesPersonResearch";
              

    }
    
    public String SalesPersonUpdate()
    {
        System.out.println("Sale update car ----------" + this.car.getColor());
        this.carRepository.updateCar(car);
        return "/SalesPerson/SalesPerson";
    }
    
    
    
    
    public List<Car> searchall()
    {
        List<Car> cars = this.carRepository.searchAllCar();
        return cars;
    }
    
    
     public List<Car> searchPara() throws Exception
    {
        //System.out.println("modelName=============" + this.modelName);
        System.out.println("SalesPerson========" + this.modelNo + "222222");
       // List<Car> cars = carRepository.searchByModelName(modelName);
       // System.out.println(cars);  
       
        String make = this.make;
        String carName = this.modelName;
        String carNo = this.modelNo;
        String type = this.type;
        
        System.out.println("make is :" + make);
        System.out.println("CarName is :" + carName);
        System.out.println("CarNo is :" + carNo);
        System.out.println("type is :" + type);
        System.out.println("Boolean :" + type.equals("Please choose types of car:"));
 
        
        if((make != null) && (!carName.equals("")) && (!carNo.equals(""))&& (!type.equals("Please choose types of car:")))// 
        {
            List<Car> cars = this.carRepository.s_searchFourPara(make, carName, carNo, type);
            System.out.println("222222");
            return cars;
        }
        if(make != null  && (carName.equals("")) && (carNo.equals("")) && (type.equals("Please choose types of car:")))//
        {
            System.out.println("11111");
            List<Car> cars = this.carRepository.s_searchByMake(make);
           // System.out.println(cars);
            return cars;
        }
        else if((make.equals(""))  && (carName != null) && (carNo.equals("")) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.s_searchByModelName(carName);
            return cars;
        }
        else if((make.equals(""))  && (carName.equals("")) && (carNo != null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.s_searchByModelNo(carNo);
            return cars;
        }
        else if((make.equals(""))  && (carName.equals("")) && (carNo.equals("")) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.s_searchByType(type);
            return cars;
        }
        else if((make.equals(""))  && (carName.equals("")) && (carNo.equals("")) && (type.equals("Please choose types of car:")))
        {
            //Car car = new Car();
            return null;
        }
        else if((make != null)  && (carName != null) && (carNo.equals("")) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.s_searchMakeAName(make,carName);
            return cars;
        }
        else if((make != null)  && (carName.equals("")) && (carNo != null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.s_searchMakeANo(make,carNo);
           return cars;
        }
        else if((make != null)  && (carName.equals("")) && (carNo.equals("")) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.s_searchMakeAType(make,type);
            return cars;
        }
        else if((make.equals(""))  && (carName != null) && (carNo != null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.s_searchModelNameANo(carName,carNo);
            return cars;
        }
        else if((make.equals(""))  && (carName != null) && (carNo.equals("")) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.s_searchModelNameAType(carName,type);
            return cars;
        }
         else if((make.equals(""))  && (carName.equals("")) && (carNo != null) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.s_searchModelNoAType(carNo,type);
            return cars;
        }
        else if((make.equals(""))  && (carName != null) && (carNo != null) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.s_searchModelNameAModelNoAType(carName,carNo,type);
            return cars;
        }
        else if((make != null)  && (carName != null) && (carNo != null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.s_searchMakeAModelNameAModelNo(make,carName,carNo);
            return cars;
        }
        else
        {
            return null;
        }
    }
    
    
    
    
}
