/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment.Bean;

import fit5192.assignment.repository.CarRepository;
import fit5192.assignment.repository.entities.Car;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author Think
 */
@Named(value = "IndexManagedBean")
//@RequestScoped
@SessionScoped
public class IndexManagedBean implements Serializable{

    /**
     * Creates a new instance of IndexManagedBean
     */
    @EJB
    private CarRepository carRepository;
    private String modelName;
    private String modelNo;
    private String make;
    private String type;
    
        
    public IndexManagedBean() {
        //this.getCars();
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
    
    
    
    public List<Car> searchPara() throws Exception
    {
        //System.out.println("modelName=============" + this.modelName);
        System.out.println("modelNo========" + this.modelNo + "11111");
       // List<Car> cars = carRepository.searchByModelName(modelName);
       // System.out.println(cars);  
       
        String make = this.getMake();
        String carName = this.getModelName();
        String carNo = this.getModelNo();
        String type = this.getType();
        
        System.out.println("make is :" + make);
        System.out.println("CarName is :" + carName);
        System.out.println("CarNo is :" + carNo);
        System.out.println("type is :" + type);
        System.out.println("Boolean :" + type.equals("Please choose types of car:"));
 
        
        if((make != null) && (!carName.equals("")) && (!carNo.equals(""))&& (!type.equals("Please choose types of car:")))// 
        {
            List<Car> cars = this.carRepository.searchFourPara(make, carName, carNo, type);
            System.out.println("222222");
            return cars;
        }
        if(make != null  && (carName.equals("")) && (carNo.equals("")) && (type.equals("Please choose types of car:")))//
        {
            System.out.println("11111");
            List<Car> cars = this.carRepository.searchByMake(make);
           // System.out.println(cars);
            return cars;
        }
        else if((make.equals(""))  && (carName != null) && (carNo.equals("")) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchByModelName(carName);
            return cars;
        }
        else if((make.equals(""))  && (carName.equals("")) && (carNo != null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchByModelNo(carNo);
            return cars;
        }
        else if((make.equals(""))  && (carName.equals("")) && (carNo.equals("")) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchByType(type);
            return cars;
        }
        else if((make.equals(""))  && (carName.equals("")) && (carNo.equals("")) && (type.equals("Please choose types of car:")))
        {
            //Car car = new Car();
            return null;
        }
        else if((make != null)  && (carName != null) && (carNo.equals("")) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchMakeAName(make,carName);
            return cars;
        }
        else if((make != null)  && (carName.equals("")) && (carNo != null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchMakeANo(make,carNo);
           return cars;
        }
        else if((make != null)  && (carName.equals("")) && (carNo.equals("")) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchMakeAType(make,type);
            return cars;
        }
        else if((make.equals(""))  && (carName != null) && (carNo != null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchModelNameANo(carName,carNo);
            return cars;
        }
        else if((make.equals(""))  && (carName != null) && (carNo.equals("")) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchModelNameAType(carName,type);
            return cars;
        }
         else if((make.equals(""))  && (carName.equals("")) && (carNo != null) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchModelNoAType(carNo,type);
            return cars;
        }
        else if((make.equals(""))  && (carName != null) && (carNo != null) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchModelNameAModelNoAType(carName,carNo,type);
            return cars;
        }
        else if((make != null)  && (carName != null) && (carNo != null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchMakeAModelNameAModelNo(make,carName,carNo);
            return cars;
        }
        else
        {
            return null;
        }
    }
    
}
