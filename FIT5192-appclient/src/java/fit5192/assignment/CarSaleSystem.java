/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment;

import fit5192.assignment.gui.CarSaleSystemGUI;
import fit5192.assignment.gui.TableGUIImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.ejb.EJB;
import fit5192.assignment.repository.CarRepository;
import fit5192.assignment.repository.entities.Car;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Think
 */
public class CarSaleSystem implements ActionListener, ListSelectionListener{
   
    @EJB
    private static CarRepository carRepository;
    
    private String name;

    private CarSaleSystemGUI gui;
     


    public CarSaleSystem(String name) {
        this.name = name;
    }
    
    public void initView()
    {
        this.gui = new TableGUIImpl(this,this);
    }
    
    
        public static void main(String[] args) {
        try {
            final CarSaleSystem agency = new CarSaleSystem("CarSaleSystem");
            //JDK 1.7
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    agency.initView();
                }
            });       
//            //JDK 1.8
//            SwingUtilities.invokeLater(()-> {
//                agency.initView();
//            });
        } catch (Exception ex) {
            System.out.println("Failed to run application: " + ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == gui.getResultButton())
        {
            try {
                displaySimpleResult();
            } catch (Exception ex) {
                Logger.getLogger(CarSaleSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        else if(e.getSource() == gui.getDetailButton())
        {
            try {
                displayDetailsResult();
            } catch (Exception ex) {
                Logger.getLogger(CarSaleSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void displaySimpleResult() throws Exception
    {
        String make = gui.getMake();
        String carName = gui.getModelName();
        String carNo = gui.getModelNo();
        String type = gui.getModelType();
        
        
        System.out.println("make is :" + make);
        System.out.println("CarName is :" + carName);
        System.out.println("CarNo is :" + carNo);
        System.out.println("type is :" + type);
        System.out.println("Boolean :" + !type.equals("Please choose types of car:"));
        
        if((make != null) && (carName != null) && (carNo != null) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchFourPara(make, carName, carNo, type);
            if(cars == null)
            {
                System.out.println("No record 1");
                this.gui.displaySimpleNull();
                this.gui.displayDescriptionNull();
                this.gui.showPicNull();
            }
            else
            {
                Car car = cars.get(0);
                this.gui.displaySimpleTable(car);
                this.gui.displayDescriptionNull();
                this.gui.showPicNull();
            }
        }
        else if(make != null  && (carName == null) && (carNo == null) && (type.equals("Please choose types of car:")))
        {
            System.out.println("11111");
            List<Car> cars = this.carRepository.searchByMake(make);
            this.gui.showPicNull();
            if(cars == null)
            {
                System.out.println("No record 2");
                this.gui.displaySimpleNull();
                this.gui.displayDescriptionNull();
            }
            else
            {
                this.gui.displaySimpleTable(cars);
                this.gui.displayDescriptionNull();
            }
        }
        else if((make == null)  && (carName != null) && (carNo == null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchByModelName(carName);
            this.gui.showPicNull();
            if(cars == null)
            {
                System.out.println("No record 3");
                this.gui.displaySimpleNull();
                this.gui.displayDescriptionNull();
            }
            else
            {
                this.gui.displaySimpleTable(cars);
                this.gui.displayDescriptionNull();
            }
        }
        else if((make == null)  && (carName == null) && (carNo != null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchByModelNo(carNo);
            this.gui.showPicNull();
            if(cars == null)
            {
                System.out.println("No record 4");
                this.gui.displaySimpleNull();
                this.gui.displayDescriptionNull();
            }
            else
            {
                this.gui.displaySimpleTable(cars);
                this.gui.displayDescriptionNull();
            }
        }
        else if((make == null)  && (carName == null) && (carNo == null) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchByType(type);
            this.gui.showPicNull();
            if(cars == null)
            {
                System.out.println("No record 5");
                this.gui.displaySimpleNull();
                this.gui.displayDescriptionNull();
            }
            else
            {
                this.gui.displaySimpleTable(cars);
                this.gui.displayDescriptionNull();
            }
        }
        else if((make == null)  && (carName == null) && (carNo == null) && (type.equals("Please choose types of car:")))
        {
            Car car = new Car();
            this.gui.showPicNull();
            if(car == null)
            {
                System.out.println("No record 6");
                this.gui.displaySimpleNull();
                this.gui.displayDescriptionNull();
            }
            else
            {
                this.gui.displaySimpleNull();
                this.gui.displayDescriptionNull();
                //this.gui.displaySimpleTable(car);
            }
        }
        else if((make != null)  && (carName != null) && (carNo == null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchMakeAName(make,carName);
            this.gui.showPicNull();
            if(cars == null)
            {
                System.out.println("No record 7");
                this.gui.displaySimpleNull();
                this.gui.displayDescriptionNull();
            }
            else
            {
                this.gui.displaySimpleTable(cars);
                this.gui.displayDescriptionNull();
            }
        }
        else if((make != null)  && (carName == null) && (carNo != null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchMakeANo(make,carNo);
            this.gui.showPicNull();
            if(cars == null)
            {
                System.out.println("No record 8");
                this.gui.displaySimpleNull();
                this.gui.displayDescriptionNull();
            }
            else
            {
                this.gui.displaySimpleTable(cars);
                this.gui.displayDescriptionNull();
            }
        }
        else if((make != null)  && (carName == null) && (carNo == null) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchMakeAType(make,type);
            this.gui.showPicNull();
            if(cars == null)
            {
                System.out.println("No record 9");
                this.gui.displaySimpleNull();
                this.gui.displayDescriptionNull();
            }
            else
            {
                this.gui.displaySimpleTable(cars);
                this.gui.displayDescriptionNull();
            }
        }
        else if((make == null)  && (carName != null) && (carNo != null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchModelNameANo(carName,carNo);
            this.gui.showPicNull();
            if(cars == null)
            {
                System.out.println("No record 10");
                this.gui.displaySimpleNull();
                this.gui.displayDescriptionNull();
            }
            else
            {
                this.gui.displaySimpleTable(cars);
                this.gui.displayDescriptionNull();
            }
        }
        else if((make == null)  && (carName != null) && (carNo == null) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchModelNameAType(carName,type);
            this.gui.showPicNull();
            if(cars == null)
            {
                System.out.println("No record 11");
                this.gui.displaySimpleNull();
                this.gui.displayDescriptionNull();
            }
            else
            {
                this.gui.displaySimpleTable(cars);
                this.gui.displayDescriptionNull();
            }
        }
         else if((make == null)  && (carName == null) && (carNo != null) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchModelNoAType(carNo,type);
            this.gui.showPicNull();
            if(cars == null)
            {
                System.out.println("No record 12");
                this.gui.displaySimpleNull();
                this.gui.displayDescriptionNull();
            }
            else
            {
                this.gui.displaySimpleTable(cars);
                this.gui.displayDescriptionNull();
            }
        }
        else if((make == null)  && (carName != null) && (carNo != null) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchModelNameAModelNoAType(carName,carNo,type);
            this.gui.showPicNull();
            if(cars == null)
            {
                System.out.println("No record 13");
                this.gui.displaySimpleNull();
                this.gui.displayDescriptionNull();
            }
            else
            {
                this.gui.displaySimpleTable(cars);
                this.gui.displayDescriptionNull();
            }
        }
        else if((make != null)  && (carName != null) && (carNo != null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchMakeAModelNameAModelNo(make,carName,carNo);
            this.gui.showPicNull();
            if(cars == null)
            {
                System.out.println("No record 14");
                this.gui.displaySimpleNull();
                this.gui.displayDescriptionNull();
            }
            else
            {
                this.gui.displaySimpleTable(cars);
                this.gui.displayDescriptionNull();
            }
        }
        this.carRepository.close();
    }
    

    public void displayDetailsResult() throws Exception
    {
        String make = gui.getMake();
        String carName = gui.getModelName();
        String carNo = gui.getModelNo();
        String type = gui.getModelType();
        
        
        System.out.println("make is :" + make);
        System.out.println("CarName is :" + carName);
        System.out.println("CarNo is :" + carNo);
        System.out.println("type is :" + type);
        System.out.println("Boolean :" + !type.equals("Please choose types of car:"));
        
        if((make != null) && (carName != null) && (carNo != null) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchFourPara(make, carName, carNo, type);
            if(cars == null)
            {
                System.out.println("No record 1");
                this.gui.displaySimpleNull();
                this.gui.showPicNull();
            }
            else
            {
                
                this.gui.displayComplexTable(cars);
                // add description
                Car car = cars.get(0);
                this.gui.displayDescription(car);
                
                String name = car.getThumbnail();
                this.gui.showPic(getImageFromNetByUrl("http://172.16.120.179:8080/FIT5192-web/Image/"+name));
            }
        }
        else if(make != null  && (carName == null) && (carNo == null) && (type.equals("Please choose types of car:")))
        {
            
            List<Car> cars = this.carRepository.searchByMake(make);
            if(cars == null)
            {
                System.out.println("No record 2");
                this.gui.displaySimpleNull();
                this.gui.showPicNull();
            }
            else
            {
                this.gui.displayComplexTable(cars);
                 // add description
                Car car = cars.get(0);
  //              System.out.println("car123" + car.getDescription());
                this.gui.displayDescription(car);
                
                String name = car.getThumbnail();
                this.gui.showPic(getImageFromNetByUrl("http://172.16.120.179:8080/FIT5192-web/Image/"+name));
            }
        }
        else if((make == null)  && (carName != null) && (carNo == null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchByModelName(carName);
            if(cars == null)
            {
                System.out.println("No record 3");
                this.gui.displaySimpleNull();
                this.gui.showPicNull();
            }
            else
            {
                this.gui.displayComplexTable(cars);
                // add description
                Car car = cars.get(0);
                this.gui.displayDescription(car);
                
                 String name = car.getThumbnail();
                            this.gui.showPic(getImageFromNetByUrl("http://172.16.120.179:8080/FIT5192-web/Image/"+name));
            }
        }
        else if((make == null)  && (carName == null) && (carNo != null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchByModelNo(carNo);
            if(cars == null)
            {
                System.out.println("No record 4");
                this.gui.displaySimpleNull();
                this.gui.showPicNull();
            }
            else
            {
                this.gui.displayComplexTable(cars);
                                // add description
                Car car = cars.get(0);
                this.gui.displayDescription(car);
                
                 String name = car.getThumbnail();
                            this.gui.showPic(getImageFromNetByUrl("http://172.16.120.179:8080/FIT5192-web/Image/"+name));
            }
        }
        else if((make == null)  && (carName == null) && (carNo == null) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchByType(type);
            if(cars == null)
            {
                System.out.println("No record 5");
                this.gui.displaySimpleNull();
                this.gui.showPicNull();
            }
            else
            {
                this.gui.displayComplexTable(cars);
                                // add description
                Car car = cars.get(0);
                this.gui.displayDescription(car);
                
                 String name = car.getThumbnail();
                            this.gui.showPic(getImageFromNetByUrl("http://172.16.120.179:8080/FIT5192-web/Image/"+name));
            }
        }
        else if((make == null)  && (carName == null) && (carNo == null) && (type.equals("Please choose types of car:")))
        {
            Car car = new Car();
            if(car == null)
            {
                System.out.println("No record 6");
                this.gui.displaySimpleNull();
                this.gui.showPicNull();
            }
            else
            {
                this.gui.displaySimpleNull();
                this.gui.displayDescriptionNull();
                this.gui.showPicNull();
                //this.gui.displaySimpleTable(car);
            }
        }
        else if((make != null)  && (carName != null) && (carNo == null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchMakeAName(make,carName);
            if(cars == null)
            {
                System.out.println("No record 7");
                this.gui.displaySimpleNull();
                this.gui.showPicNull();
            }
            else
            {
                this.gui.displayComplexTable(cars);
                                // add description
                Car car = cars.get(0);
                this.gui.displayDescription(car);
                
                 String name = car.getThumbnail();
                            this.gui.showPic(getImageFromNetByUrl("http://172.16.120.179:8080/FIT5192-web/Image/"+name));
            }
        }
        else if((make != null)  && (carName == null) && (carNo != null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchMakeANo(make,carNo);
            if(cars == null)
            {
                System.out.println("No record 8");
                this.gui.displaySimpleNull();
                this.gui.showPicNull();
            }
            else
            {
                this.gui.displayComplexTable(cars);
                                // add description
                Car car = cars.get(0);
                this.gui.displayDescription(car);
                
                 String name = car.getThumbnail();
                            this.gui.showPic(getImageFromNetByUrl("http://172.16.120.179:8080/FIT5192-web/Image/"+name));
            }
        }
        else if((make != null)  && (carName == null) && (carNo == null) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchMakeAType(make,type);
            if(cars == null)
            {
                System.out.println("No record 9");
                this.gui.displaySimpleNull();
                this.gui.showPicNull();
            }
            else
            {
                this.gui.displayComplexTable(cars);
                                // add description
                Car car = cars.get(0);
                this.gui.displayDescription(car);
                
                 String name = car.getThumbnail();
                            this.gui.showPic(getImageFromNetByUrl("http://172.16.120.179:8080/FIT5192-web/Image/"+name));
            }
        }
        else if((make == null)  && (carName != null) && (carNo != null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchModelNameANo(carName,carNo);
            if(cars == null)
            {
                System.out.println("No record 10");
                this.gui.displaySimpleNull();
                this.gui.showPicNull();
            }
            else
            {
                this.gui.displayComplexTable(cars);
                                // add description
                Car car = cars.get(0);
                this.gui.displayDescription(car);
                
                 String name = car.getThumbnail();
                            this.gui.showPic(getImageFromNetByUrl("http://172.16.120.179:8080/FIT5192-web/Image/"+name));
            }
        }
        else if((make == null)  && (carName != null) && (carNo == null) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchModelNameAType(carName,type);
            if(cars == null)
            {
                System.out.println("No record 11");
                this.gui.displaySimpleNull();
                this.gui.showPicNull();
            }
            else
            {
                this.gui.displayComplexTable(cars);
                                // add description
                Car car = cars.get(0);
                this.gui.displayDescription(car);
                
                 String name = car.getThumbnail();
                            this.gui.showPic(getImageFromNetByUrl("http://172.16.120.179:8080/FIT5192-web/Image/"+name));
            }
        }
         else if((make == null)  && (carName == null) && (carNo != null) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchModelNoAType(carNo,type);
            if(cars == null)
            {
                System.out.println("No record 12");
                this.gui.displaySimpleNull();
                this.gui.showPicNull();
            }
            else
            {
                this.gui.displayComplexTable(cars);
                                // add description
                Car car = cars.get(0);
                this.gui.displayDescription(car);
                
                 String name = car.getThumbnail();
                            this.gui.showPic(getImageFromNetByUrl("http://172.16.120.179:8080/FIT5192-web/Image/"+name));
            }
        }
        else if((make == null)  && (carName != null) && (carNo != null) && (!type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchModelNameAModelNoAType(carName,carNo,type);
            if(cars == null)
            {
                System.out.println("No record 13");
                this.gui.displaySimpleNull();
                this.gui.showPicNull();
            }
            else
            {
                this.gui.displayComplexTable(cars);
                                // add description
                Car car = cars.get(0);
                this.gui.displayDescription(car);
                
                 String name = car.getThumbnail();
                            this.gui.showPic(getImageFromNetByUrl("http://172.16.120.179:8080/FIT5192-web/Image/"+name));
            }
        }
          else if((make != null)  && (carName != null) && (carNo != null) && (type.equals("Please choose types of car:")))
        {
            List<Car> cars = this.carRepository.searchMakeAModelNameAModelNo(make,carName,carNo);
            if(cars == null)
            {
                System.out.println("No record 14");
                this.gui.displaySimpleNull();
                this.gui.showPicNull();
            }
            else
            {
                this.gui.displayComplexTable(cars);
                                // add description
                Car car = cars.get(0);
                this.gui.displayDescription(car);
                
                 String name = car.getThumbnail();
                            this.gui.showPic(getImageFromNetByUrl("http://172.16.120.179:8080/FIT5192-web/Image/"+name));
            }
        }
        
        this.carRepository.close();
    }
    
    @Override
    public void valueChanged(ListSelectionEvent event) {
         if ((event.getSource() == this.gui.getCarTable().getSelectionModel())&& (! event.getValueIsAdjusting()))
        {
            try
            {
                if (this.gui.isCarSelected()) {
                    if(this.gui.getSelectedCarVIN().equals("1"))
                    {
                        System.out.println("wrong");
                    }
                    else
                    {
                        System.out.println("666666");
                        String vin = this.gui.getSelectedCarVIN();
                        System.out.println("CarVIN=======3" + vin +"6666");
                        Car car = this.carRepository.searchVIN(vin);
                        System.out.println("car......." + car.getModelName());
                        this.gui.displayDescription(car);
                        System.out.println("22222");
                        //add picture from url
//                        HttpURLConnection conn = null;
//                        BufferedImage image = null;
                        try{
                           System.out.println("url...." + car.getThumbnail());
//                         //   URL url = new URL(car.getThumbnail());                      
//                            URL url = new URL("http://172.16.120.179:8080/FIT5192-web/Image/1.jpg");
//                            conn = (HttpURLConnection) url.openConnection();                                  
//                            conn.setReadTimeout(15000);
//                            conn.setConnectTimeout(15000);
//                            conn.setDoInput(true);
//                            conn.setRequestMethod("GET");
//                            conn.connect();
//                            InputStream is = conn.getInputStream();
//
//                         //   InputStream is = url.openConnection().getInputStream();
//                            image = ImageIO.read(is);
//
//                            System.out.println("image is:"+image);
//                            is.close();
                            String name = car.getThumbnail();
                            this.gui.showPic(getImageFromNetByUrl("http://172.16.120.179:8080/FIT5192-web/Image/"+name));
                        
                        }catch(Exception e)
                        {
                            e.printStackTrace();
                            System.out.println("000000");
                        }finally{
 //                           conn.disconnect();
                        }
                    }
                    
                    
                }               
            }
            catch (Exception e)
            {
                gui.displayMessageInDialog(e.getMessage());
            }
        }
    }
    
    public  byte[] getImageFromNetByUrl(String strUrl){  
        try {  
            URL url = new URL(strUrl);  
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            InputStream inStream = conn.getInputStream(); 
            byte[] btImg = readInputStream(inStream);  
            //InputStream buffin = new ByteArrayInputStream(btImg,0,btImg.length); 
            //BufferedImage img = ImageIO.read(buffin); 
            System.out.println("btImg: " + btImg);
            conn.disconnect();
            return btImg;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  

    public  byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        while( (len=inStream.read(buffer)) != -1 ){  
            outStream.write(buffer, 0, len);  
        }  
        inStream.close();  
        return outStream.toByteArray();  
    }  

    
}
