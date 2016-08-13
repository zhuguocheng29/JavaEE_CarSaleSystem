/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5192.assignment.Bean;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Think
 */
public class test {
    public static void main(String[] args) throws UnsupportedEncodingException
    {
        URL url;
        try
        {
            //String path = "https://api.edmunds.com/api/vehicle/v2/vins/"+local_vin+"?&fmt=json&api_key=m84maxzxqyjbgdb23pm8spkt";
            String path = "https://api.edmunds.com/api/vehicle/v2/vins/4T1BK1EB6DU056165?&fmt=json&api_key=m84maxzxqyjbgdb23pm8spkt";
            url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            StringBuilder sb = new StringBuilder();
            String output;
            while((output = reader.readLine()) != null)
            {
                sb.append(output);
            }
            conn.disconnect();
            
            String required = sb.toString();
            
            JSONObject jsonObject = new JSONObject(required);
            
//            String vin = jsonObject.getString("vin");
//            System.out.println("vin=====" + vin);
            System.out.println("sb.append=======" + sb);
            
            JSONObject jsonObject_modelName = jsonObject.getJSONObject("make");
            String Modelname = jsonObject_modelName.getString("name");
            System.out.println("modelName:===" + Modelname);
            
            JSONObject jsonObject_modelNo = jsonObject.getJSONObject("model");
            String ModelNo = jsonObject_modelNo.getString("name");
            System.out.println("modelNo===" + ModelNo);
            
            JSONObject jsonObject_make = jsonObject.getJSONObject("make");
            String make = jsonObject_modelName.getString("name");
            System.out.println("modelName:===" + make);
            
            String type = jsonObject.getString("drivenWheels");
            System.out.println("type====" + type);
            
//            JSONObject jsonObject_price = jsonObject.getJSONObject("price");
//            System.out.println("jsonObject_price" + jsonObject_price);
//            float price = Float.parseFloat(jsonObject_price.getString("baseMSRP"));
//            System.out.println("price====" + price);
//            
            JSONArray jsonArray_color1 = jsonObject.getJSONArray("options");
            JSONObject jsonObject_color2 = jsonArray_color1.getJSONObject(0);
            System.out.println("color2" + jsonObject_color2);
            JSONArray jjsonArray_color3 = jsonObject_color2.getJSONArray("options");
            System.out.println("color3" + jjsonArray_color3);
            JSONObject jsonObject_color4 = jjsonArray_color3.getJSONObject(0);
            String description = jsonObject_color4.getString("description");
            System.out.println("description====" + description);
                   
        }catch(Exception e)
        {
            
        }
        invokeSearch();
        invokeImage();
    }
    public static void invokeSearch()
    {
        //String path1 = "https://api.datamarket.azure.com/Bing/Search/Web?Query=%27" + partofitems_bing + "%27&$top=3&$skip=1&$format=JSON&Market=%27en-GB%27"
        String path1 = "https://api.datamarket.azure.com/Bing/Search/Web?Query=%27" + "Toyota" + "%27&$top=1&$skip=1&$format=JSON&Market=%27en-GB%27";
        URL url;
        HttpURLConnection conn = null;
        String username = "65196cf1-49f4-44d6-ae1f-3e3bbe518319";
        String password = "crfO+EBYiFbGV6EJUQIiG957gXMq8BzmuU7jnSngllE=";
        String userpass = username+":"+password;
        //update
        String basicAuth ="Basic "+new String(Base64.encode(userpass.getBytes()));
        try {
            url = new URL(path1);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization",basicAuth);
            String resmsg = "";
            System.out.println("1111  " + conn.getResponseCode());
            InputStream instream = new BufferedInputStream(conn.getInputStream());
            BufferedReader buffer = new BufferedReader(new InputStreamReader(instream));
            StringBuilder sb = new StringBuilder();
            while ((resmsg = buffer.readLine()) != null) {
                sb.append(resmsg);
            }
            buffer.close();
            //System.out.println("output===" + sb.toString());
            String required = sb.toString();
            JSONObject jsonObject = new JSONObject(required);
            JSONObject jsonObject1 = jsonObject.getJSONObject("d");
            JSONArray jsonArray2 = jsonObject1.getJSONArray("results");
            JSONObject jsonObject2 = jsonArray2.getJSONObject(0);
            String decription = jsonObject2.getString("Description");
            String preview_url = jsonObject2.getString("Url");
            System.out.println("decription" + decription);
            System.out.println("preview_url" + preview_url);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("88888000000");
        } finally {
            conn.disconnect();
        }

    
    }
    
    
    public static void invokeImage() throws UnsupportedEncodingException
    {
        String modelname = "";
        String url_1 = "";
        url_1 = URLEncoder.encode(modelname,"utf-8");
        url_1 = url_1.replace("+", "%20");
        String modelname_new = url_1;            
        //String path = "https://api.datamarket.azure.com/Bing/Search/Image?Query=%27"+partofitems_bing+"%27&$top=1&$skip=1&$format=JSON";
        String path =  "https://api.datamarket.azure.com/Bing/Search/Image?Query=%27"+ "Toyota" +"%27&$top=1&$skip=1&$format=JSON";

        
        URL url;
        HttpURLConnection conn = null;
        String username = "65196cf1-49f4-44d6-ae1f-3e3bbe518319";
        String password = "crfO+EBYiFbGV6EJUQIiG957gXMq8BzmuU7jnSngllE=";
        String userpass = username+":"+password;
        String basicAuth ="Basic "+new String(Base64.encode(userpass.getBytes()));
        try {
            url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization",basicAuth);
            String resmsg = "";
            InputStream instream = new BufferedInputStream(conn.getInputStream());
            BufferedReader buffer = new BufferedReader(new InputStreamReader(instream));
            StringBuilder sb = new StringBuilder();
            while ((resmsg = buffer.readLine()) != null) {
                sb.append(resmsg);
            }
            buffer.close();
            
            String required = sb.toString();
            JSONObject jsonObject = new JSONObject(required);
            JSONObject jsonObject1 = jsonObject.getJSONObject("d");
            JSONArray jsonArray2 = jsonObject1.getJSONArray("results");
            JSONObject jsonObject2 = jsonArray2.getJSONObject(0);
            String mediaUrl = jsonObject2.getString("MediaUrl");
            
            System.out.println("mediaURL===" + mediaUrl);
            Image(mediaUrl);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("88888000000");
        } finally {
            conn.disconnect();
        }
        
        
        
        
    }
    
    public static void Image(String path)
    {
        URL url;
        HttpURLConnection conn = null;
        //Bitmap bitmap = null;
        try {
            url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(20000);
            conn.setDoInput(true);
//                conn.setRequestMethod("GET");
            //System.out.println("3333   " + conn.getResponseCode());
            conn.connect();
            System.out.println("55555   " + conn.getResponseCode());
            if(conn.getResponseCode()==301 ||conn.getResponseCode()==403 || conn.getResponseCode()==404)
            {
                //return null;
            }
            InputStream is = conn.getInputStream();
            //bitmap = BitmapFactory.decodeStream(is);
             
            byte[] data = readInputStream(is);  
            //new一个文件对象用来保存图片，默认保存当前工程根目录  
            File imageFile = new File("web\\Image\\Toyota.jpg");    
            FileOutputStream outStream = new FileOutputStream(imageFile);    
            outStream.write(data);    
            outStream.close();  
            is.close();
            conn.getResponseCode();
            //Bitmap b = bitmap
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("000000");
        } finally {
            conn.disconnect();
        }
    }
    
    
     public static byte[] readInputStream(InputStream inStream) throws Exception{  
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
