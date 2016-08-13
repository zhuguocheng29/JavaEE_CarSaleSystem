/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment.repository;
import fit5192.assignment.repository.entities.Car;
import fit5192.assignment.repository.entities.Sale;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 *
 * @author Think
 */
@Stateless
public class CarRepositoryBean implements CarRepository {
    
    private static final String PERSISTENCE_UNIT = "FIT5192-ejbPU";
    
   //   private final List<Car> cars;
    @PersistenceContext
    private EntityManager entityManager;
   // private EntityManagerFactory entityManagerFactory;

    public CarRepositoryBean() {
       // this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
       // this.entityManager = this.entityManagerFactory.createEntityManager();
    } 
    
//    public List<String> searchByModelNameNoPara() throws Exception{
//        Query query = this.entityManager.createQuery("select c.ModelName from Car c");
//        List<String> modelName = query.getResultList();
//        for(String modelname : modelName)
//        {
//            System.out.println("No:" + modelName);
//        }
//        return modelName;
//    }
    

    @Override
    public List<Car> searchByType(String type) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.type = :type and c.cstate = 'Available'");
        query.setParameter("type", type);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
           return cars; 
        }
        else
        {
            return null;
        }
    }
    
    
        @Override
    public List<Car> s_searchByType(String type) throws Exception {
        System.out.println("type=====" + type);
        Query query = this.entityManager.createQuery("select c from Car c where c.type = :type");
        query.setParameter("type", type);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
           return cars; 
        }
        else
        {
            return null;
        }
    }
    
    

    @Override
    public List<Car> searchByModelNo(String id) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.ModelNo = :id and c.cstate = 'Available'");
        query.setParameter("id", id);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {

             return cars;
        }
        else
        {
            return null;
        }
       
    }
    
        @Override
    public List<Car> s_searchByModelNo(String id) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.ModelNo = :id");
        query.setParameter("id", id);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {

             return cars;
        }
        else
        {
            return null;
        }
       
    }
    
    

    @Override
    public List<Car> searchByModelName(String name) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.ModelName = :name and c.cstate = 'Available'");
        query.setParameter("name", name);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
             return cars;
        }
        else
        {
            return null;
        }
    }
    
        @Override
    public List<Car> s_searchByModelName(String name) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.ModelName = :name");
        query.setParameter("name", name);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
             return cars;
        }
        else
        {
            return null;
        }
    }

    @Override
    public List<Car> searchByMake(String make) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.make = :make and c.cstate = 'Available'");
        query.setParameter("make", make);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
             return cars;
        }
        else
        {
            return null;
        }
    }
    
        @Override
    public List<Car> s_searchByMake(String make) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.make = :make");
        query.setParameter("make", make);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
             return cars;
        }
        else
        {
            return null;
        }
    }
    
    
    
    @Override
    public List<Car> searchFourPara(String make, String modelName, String modelNo, String type) throws Exception{
        Query query = this.entityManager.createQuery("select c from Car c where c.make = :make and c.ModelName = :mname and c.ModelNo =:mid and c.type = :mtype and c.cstate = 'Available'");
        query.setParameter("make", make);
        query.setParameter("mname", modelName);
        query.setParameter("mid", modelNo);
        query.setParameter("mtype", type);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
             return cars;
        }
        else
        {
            return null;
        }
    }
    
        @Override
    public List<Car> s_searchFourPara(String make, String modelName, String modelNo, String type) throws Exception{
        Query query = this.entityManager.createQuery("select c from Car c where c.make = :make and c.ModelName = :mname and c.ModelNo =:mid and c.type = :mtype");
        query.setParameter("make", make);
        query.setParameter("mname", modelName);
        query.setParameter("mid", modelNo);
        query.setParameter("mtype", type);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
             return cars;
        }
        else
        {
            return null;
        }
    }
    
    

    @Override
    public void close() {
         if(this.entityManager.isOpen() )//|| this.entityManagerFactory.isOpen()
        {
            this.entityManager.close();
            //this.entityManagerFactory.close();
        }
    }

    @Override
    public List<Car> searchMakeAName(String make, String modelName) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.make = :make and c.ModelName = :modelName and c.cstate = 'Available'");
        query.setParameter("make", make);
        query.setParameter("modelName", modelName);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
             return cars;
        }
        else
        {
            return null;
        }
    }

    
        @Override
    public List<Car> s_searchMakeAName(String make, String modelName) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.make = :make and c.ModelName = :modelName");
        query.setParameter("make", make);
        query.setParameter("modelName", modelName);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
             return cars;
        }
        else
        {
            return null;
        }
    }
    
    
    @Override
    public List<Car> searchMakeANo(String make, String no) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.make = :make and c.ModelNo = :modelNo and c.cstate = 'Available'");
        query.setParameter("make", make);
        query.setParameter("modelNo", no);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
             return cars;
        }
        else
        {
            return null;
        }
    }
    
    
        @Override
    public List<Car> s_searchMakeANo(String make, String no) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.make = :make and c.ModelNo = :modelNo");
        query.setParameter("make", make);
        query.setParameter("modelNo", no);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
             return cars;
        }
        else
        {
            return null;
        }
    }
    

    @Override
    public List<Car> searchMakeAType(String make, String type) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.make = :make and c.type = :type and c.cstate = 'Available'");
        query.setParameter("make", make);
        query.setParameter("type", type);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
             return cars;
        }
        else
        {
            return null;
        }
    }
    
    
        @Override
    public List<Car> s_searchMakeAType(String make, String type) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.make = :make and c.type = :type");
        query.setParameter("make", make);
        query.setParameter("type", type);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
             return cars;
        }
        else
        {
            return null;
        }
    }
    

    @Override
    public List<Car> searchModelNameANo(String name, String id) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.ModelName = :name and c.ModelNo = :id and c.cstate = 'Available'");
        query.setParameter("name", name);
        query.setParameter("id", id);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
             return cars;
        }
        else
        {
            return null;
        }
    }
    
    
        @Override
    public List<Car> s_searchModelNameANo(String name, String id) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.ModelName = :name and c.ModelNo = :id");
        query.setParameter("name", name);
        query.setParameter("id", id);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
             return cars;
        }
        else
        {
            return null;
        }
    }
    

    @Override
    public List<Car> searchModelNameAType(String name, String type) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.ModelName = :name and c.type = :type and c.cstate = 'Available'");
        query.setParameter("name", name);
        query.setParameter("type", type);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
             return cars;
        }
        else
        {
            return null;
        }
    }
    
    
        @Override
    public List<Car> s_searchModelNameAType(String name, String type) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.ModelName = :name and c.type = :type");
        query.setParameter("name", name);
        query.setParameter("type", type);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
             return cars;
        }
        else
        {
            return null;
        }
    }
    

    @Override
    public List<Car> searchModelNoAType(String id, String type) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.ModelNo = :modelNo and c.type = :type and c.cstate = 'Available'");
        query.setParameter("modelNo", id);
        query.setParameter("type", type);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
             return cars;
        }
        else
        {
            return null;
        }
    }
    
    
       @Override
    public List<Car> s_searchModelNoAType(String id, String type) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.ModelNo = :modelNo and c.type = :type");
        query.setParameter("modelNo", id);
        query.setParameter("type", type);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
             return cars;
        }
        else
        {
            return null;
        }
    }
    
    

    @Override
    public List<Car> searchModelNameAModelNoAType(String name, String id, String type) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.ModelName = :modelName and c.ModelNo = :modelNo and c.type = :type and c.cstate = 'Available'");
        query.setParameter("modelName", name);
        query.setParameter("modelNo", id);
        query.setParameter("type", type);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
             return cars;
        }
        else
        {
            return null;
        }
    }
    
    
        @Override
    public List<Car> s_searchModelNameAModelNoAType(String name, String id, String type) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.ModelName = :modelName and c.ModelNo = :modelNo and c.type = :type");
        query.setParameter("modelName", name);
        query.setParameter("modelNo", id);
        query.setParameter("type", type);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
             return cars;
        }
        else
        {
            return null;
        }
    }
    

    @Override
    public List<Car> searchMakeAModelNameAModelNo(String make, String name, String id) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.make = :make and c.ModelName = :modelName and c.ModelNo = :modelNo and c.cstate = 'Available'");
        query.setParameter("make", make);
        query.setParameter("modelName", name);
        query.setParameter("modelNo", id);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
             return cars;
        }
        else
        {
            return null;
        }
    }

    
        @Override
    public List<Car> s_searchMakeAModelNameAModelNo(String make, String name, String id) throws Exception {
        Query query = this.entityManager.createQuery("select c from Car c where c.make = :make and c.ModelName = :modelName and c.ModelNo = :modelNo");
        query.setParameter("make", make);
        query.setParameter("modelName", name);
        query.setParameter("modelNo", id);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
             return cars;
        }
        else
        {
            return null;
        }
    }
    
    
    @Override
    public Car searchVIN(String vin){
        Query query = this.entityManager.createQuery("select c from Car c where c.vin = :vin and c.cstate = 'Available'");
        query.setParameter("vin", vin);
        List<Car> cars = query.getResultList();
        Car car = cars.get(0);
        System.out.println("car No" + car.getModelNo());
        return car;
    }
    
    
        @Override
    public Car s_searchVIN(String vin){
        System.out.println("output vin============" + vin );
        Query query = this.entityManager.createQuery("select c from Car c where c.vin = :vin");
        query.setParameter("vin", vin);
        List<Car> cars = query.getResultList();
        if(cars.size()!=0)
        {
            Car car = cars.get(0);
            System.out.println("car No" + car.getModelNo());
            return car; 
        }
        else
        {
            return null;
        }

    }
    

    @Override
    public List<Car> searchAllCar() {
    
        System.out.println("searchAllCar");
        Query query = this.entityManager.createQuery("select c from Car c");
        List<Car> cars = query.getResultList();
        return cars;
        
    }
    
    @Override
    public List<Car> searchAllCarVaild() {
    
        System.out.println("searchAllCarValid");
        Query query = this.entityManager.createQuery("select c from Car c where c.cstate = 'Available'");
        List<Car> cars = query.getResultList();
        return cars;
        
    }
    
    

    @Override
    public void deleteCar(Car car) {
        System.out.println("database=====" + car.getVin());
        String vin = car.getVin();
        Query query = this.entityManager.createQuery("select s from Sale s where s.vin = :vin");
        query.setParameter("vin", car);
        List<Sale> sale = query.getResultList();
        if(sale.size()!= 0)
        {
            System.out.println("delete cascade ======");
            Sale sale_new = sale.get(0);
            System.out.println("sale========" + sale_new);
            ///
            System.out.println("00000000000");
            entityManager.remove(sale_new);
            System.out.println("11111111111");
            car = entityManager.merge(car);
            System.out.println("22222222222222");
            entityManager.remove(car);
        }
        else
        {
            System.out.println("delete directly ======");
            car = entityManager.merge(car);
            entityManager.remove(car);
        }
    }

    @Override
    public void updateCar(Car car) {
        System.out.println("update car =====" +car.getColor());
        entityManager.merge(car);
    }

    @Override
    public void addCar(Car car) {
        System.out.println("database add car =====" +car.getVin());
        entityManager.persist(car);
    }

    @Override
    public void updateCarStateToNot(Car car) {
        System.out.println("update car =====" +car.getCstate());
        entityManager.merge(car);
    }

    @Override
    public void updateCarStateToNormal(Car car) {
        System.out.println("update car =====" +car.getCstate());
        entityManager.merge(car);
    }


    
}
