package fit5192.assignment.repository;

import fit5192.assignment.repository.entities.Car;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.ejb.Remote;

/**
 * @autor Think
 */
@Remote
public interface CarRepository {

    public List<Car> searchByType(String type) throws Exception;
    
    public List<Car> searchByModelNo(String id) throws Exception;
    
    public List<Car> searchByModelName(String name) throws Exception;
    
    public List<Car> searchByMake(String make) throws Exception;
    
    public List<Car> searchFourPara(String make, String modelName, String modelNo, String type) throws Exception;
    
    public List<Car> searchMakeAName(String make, String modelName) throws Exception;
    
    public List<Car> searchMakeANo(String make, String no) throws Exception;
    
    public List<Car> searchMakeAType(String make, String type) throws Exception;
    
    public List<Car> searchModelNameANo(String name, String id) throws Exception;
    
    public List<Car> searchModelNameAType(String name, String type) throws Exception;
    
    public List<Car> searchModelNoAType(String id, String type) throws Exception;
    
    public List<Car> searchModelNameAModelNoAType(String name, String id, String type) throws Exception;

    public List<Car> searchMakeAModelNameAModelNo(String make, String name, String id) throws Exception;
    
    public List<Car> searchAllCar();
    
    public List<Car> searchAllCarVaild();
    
    public Car searchVIN(String vin); 
    
    public void deleteCar(Car car);
    
    public void updateCar(Car car);
    
    public void addCar(Car car);
    
   // public List<String> searchByModelNameNoPara() throws Exception;
    
    
    public List<Car> s_searchByType(String type) throws Exception;
    
    public List<Car> s_searchByModelNo(String id) throws Exception;
    
    public List<Car> s_searchByModelName(String name) throws Exception;
    
    public List<Car> s_searchByMake(String make) throws Exception;
    
    public List<Car> s_searchFourPara(String make, String modelName, String modelNo, String type) throws Exception;
    
    public List<Car> s_searchMakeAName(String make, String modelName) throws Exception;
    
    public List<Car> s_searchMakeANo(String make, String no) throws Exception;
    
    public List<Car> s_searchMakeAType(String make, String type) throws Exception;
    
    public List<Car> s_searchModelNameANo(String name, String id) throws Exception;
    
    public List<Car> s_searchModelNameAType(String name, String type) throws Exception;
    
    public List<Car> s_searchModelNoAType(String id, String type) throws Exception;
    
    public List<Car> s_searchModelNameAModelNoAType(String name, String id, String type) throws Exception;

    public List<Car> s_searchMakeAModelNameAModelNo(String make, String name, String id) throws Exception;
    
    public Car s_searchVIN(String vin);
    
    public void updateCarStateToNot(Car car);
    
    public void updateCarStateToNormal(Car car);
    

    
    public void close();
    
}
