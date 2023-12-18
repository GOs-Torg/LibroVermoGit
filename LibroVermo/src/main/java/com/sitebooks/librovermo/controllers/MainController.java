package com.sitebooks.librovermo.controllers;

import com.alibaba.fastjson.JSONObject;
import com.sitebooks.librovermo.dao.Crypt;
import com.sitebooks.librovermo.dao.DAOResponse;
import com.sitebooks.librovermo.dao.DBDAO;
import com.sitebooks.librovermo.dao.SendObject;
import com.sitebooks.librovermo.models.plugs.*;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

@RestController("/")
public class MainController {

    DBDAO db = new DBDAO();

    @GetMapping("/testGet")
    private SendObject sendObjectTest(){
        Map<String, String> map = new HashMap<>();
        map.put("name", "id = '22'");
        SendObject so = new SendObject(new Author(1l, "Говард","Лавкрафт","Филипс","Lovecraft","10.10.1900","Крутой цел"),"Language", map);
        return so;
    }
    @GetMapping("/CryptoTest")
    private Map<String, String> CryptoTest(){
        Map<String, String> map = new HashMap<>();
        try {
            map.put("cryptLog",Crypt.encrypt("SysAdm2000"));
            map.put("cryptPass",Crypt.encrypt("Qwert123!"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return map;
    }
    @PostMapping("/getFromDB")
    private List<Object> getAny(@RequestBody SendObject sendObj){
        List<Object> resultList = new ArrayList<>();
        try {
            Map<String,String> map = sendObj.getConditionMap() != null ? (Map<String, String>) sendObj.getConditionMap(): null;
            Logger.getLogger("MAPA").info((sendObj.getConditionMap() == null)+"");
            String className = DBDAO.rootClassPathName + sendObj.getSendClass();
                    ResultSet set = map == null?
                    db.selectFromTable(sendObj.getTableName(), Class.forName(className)) :
                    db.selectFromTable(sendObj.getTableName(), Class.forName(className),map);
            if(set != null)
                while (set.next()){
                    resultList.add(ReorganizeClass(Class.forName(className), set));
                }
        } catch (ClassNotFoundException | IllegalAccessException | SQLException | InstantiationException e){
            Logger.getLogger("ERRORS").warning(e.getMessage());
        }
        return resultList;
    }
    @PostMapping("/addToDB")
    private Long addAny(@RequestBody SendObject sendObject){
        Long id = 1l;
        try {
            Class<?> reClass = Class.forName(DBDAO.rootClassPathName +sendObject.getSendClass());
            Logger.getLogger("AddMapping").info(sendObject.getSendObject().getClass().getName());

            id = db.addToTable(sendObject.getTableName(),ReorganizeClass(reClass,sendObject.getSendObject()));
        } catch (Exception e){ Logger.getLogger("Error").info(e.getMessage()); return -1l;}
        return id;
    }
    @PostMapping("/updateDB")
    private DAOResponse updateAny(@RequestBody SendObject sendObject){
        DAOResponse response = new DAOResponse();
        try {
            Class<?> reClass = Class.forName(DBDAO.rootClassPathName +sendObject.getSendClass());
            Logger.getLogger("AddMapping").info(sendObject.getSendObject().getClass().getName());
            db.updateTable(sendObject.getTableName(),ReorganizeClass(reClass,sendObject.getSendObject()));
        } catch (Exception e){ Logger.getLogger("Error").info(e.getMessage());}
        return response;
    }
    @PostMapping("/deleteDB")
    private DAOResponse deleteAny(@RequestBody SendObject sendObject){
        DAOResponse response = new DAOResponse();
        try {
            Class<?> reClass = Class.forName(DBDAO.rootClassPathName +sendObject.getSendClass());
            Logger.getLogger("AddMapping").info(sendObject.getSendObject().getClass().getName());
            db.deleteTable(sendObject.getTableName(),ReorganizeClass(reClass,sendObject.getSendObject()));
        } catch (Exception e){ Logger.getLogger("Error").info(e.getMessage());}
        return response;
    }
    @GetMapping("/logIn")
    public String tryLogin(@RequestParam("login") String login, @RequestParam("password") String password){
        String responce = "";
        Logger.getAnonymousLogger().info(login+password);
        try {
            List<Client> cls = (List<Client>)(Object)getAny(new SendObject(new Client(),"Client",null));
            List<Employee> employees = (List<Employee>) (Object)getAny(new SendObject(new Employee(),"Employee",null));
            Logger logger = Logger.getLogger("lol");
            Client cl = cls.stream().filter(client ->
                    client.getClient_Login().equals(login) && Crypt.decrypt(client.getClient_Password()).equals(password)
            ).findFirst().orElse(null);
            if(cl != null){
                responce = "C"+cl.getId_Client();
                return responce;
            }
            Employee emp = employees.stream().filter(employee ->
                     employee.getEmployee_Login().equals(login) && Crypt.decrypt(employee.getEmployee_Password()).equals(password)
            ).findFirst().orElse(null);
            if(emp != null){
                Map<String, String> map = new HashMap<>();
                map.put("1", " id_position = "+emp.getId_Employee());
                Position pos = (Position)getAny(new SendObject(new Position(), "Position", map)).get(0);
                if(pos.getPositionName().equals("Библиотекарь")) responce="B"+emp.getId_Employee();
                if(pos.getPositionName().equals("Рецензист")) responce="R"+emp.getId_Employee();
            }
        } catch (Exception e){
            Logger.getLogger("err").info(e.getMessage());
        }
        Logger.getAnonymousLogger().info(responce);
        return responce;
    }
    public <T> T ReorganizeClass(Class<T> organizeClass, ResultSet set) throws InstantiationException, IllegalAccessException, SQLException {
        T newItem = organizeClass.newInstance();
        Field[] itemFields = organizeClass.getDeclaredFields();
        int i = 1;
        for (Field field : itemFields) {
            field.setAccessible(true);
            if(i == 1) {
                field.set(newItem, set.getLong(i));
            }
            else
                field.set(newItem, set.getString(i));
            i++;
        }
        return newItem;
    }
    public <T> T ReorganizeClass(Class<T> organizeClass, Object rootObject) {
        HashMap<String, Object> map = (HashMap<String, Object>) rootObject;
        return JSONObject.parseObject(JSONObject.toJSONString(map),organizeClass);
    }

}
