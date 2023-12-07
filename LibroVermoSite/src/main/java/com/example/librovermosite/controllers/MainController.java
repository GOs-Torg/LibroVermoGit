package com.example.librovermosite.controllers;

import com.example.librovermosite.models.plugs.*;
import com.example.librovermosite.utilites.ApiInterface;
import com.example.librovermosite.utilites.DAOResponse;
import com.example.librovermosite.utilites.RequestBuilder;
import com.example.librovermosite.utilites.SendObject;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import retrofit2.Call;
import retrofit2.http.Query;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@Controller("/")
public class MainController {
    ApiInterface apiInterface = RequestBuilder.buildRequest().create(ApiInterface.class);

    @GetMapping("/test")
    @ResponseBody
    public Object test(){
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "id_Client = "+1);
        List<Object> curClient = new ArrayList<>();
        try {
            curClient = apiInterface.getAny(new SendObject(new Client(),"Client", map)).execute().body();
        } catch (IOException e) {
            Logger.getAnonymousLogger().info(e.getMessage());
        }
        //Client client = (Client) curClient.get(0);
        //model.addAttribute("clientName", client.getClient_Name()+" "+client.getClient_Surname());
        return curClient.get(0);
    }

    ///
    /// Регистрация и авторизация
    ///

    @GetMapping("/Register")
    public String Register(@RequestParam(name = "Data", required = false) Client data,
                           Model model)
    {
        if(data != null) model.addAttribute("data",data);
        return "Register";
    }
    @PostMapping("/Register")
    public RedirectView RegisterUser(Model model,
                                     HttpServletResponse respHttp,
                                     @RequestParam("Fio") String fio,
                                     @RequestParam("client_Description") String client_Description,
                                     @RequestParam("client_Login") String client_Login,
                                     @RequestParam("client_Password") String client_Password,
                                     @RequestParam("client_Phone_Number") String client_Phone_Number,
                                     @RequestParam("client_Email") String client_Email,
                                     @RequestParam("client_Nickname") String client_Nickname) throws IOException {
        String[] FIO = fio.split(" ");
        Client client =new Client(0l,FIO[0], FIO[1], client_Description, client_Login, client_Password, client_Phone_Number, client_Email, client_Nickname);
        Call<DAOResponse> register = apiInterface.addAny(new SendObject(client, "Client", null));
        if(register.execute().isSuccessful()){
            return (SendLogin(client_Login, client_Password, null,respHttp));
        }
        return new RedirectView(Register(client, model));
    }
    @GetMapping("/Login")
    public String Login(){
        return "Login_page";
    }
    @GetMapping ("/LoginAcc")
    public RedirectView SendLogin(@RequestParam("login") String login,
                                  @RequestParam("password") String password,
                                  @CookieValue(name = "SavedUser", required = false) String savedUser,
                                  HttpServletResponse respHttp){
        Call<String> logIn = apiInterface.logIn(login, password);
        try {
            String responce = logIn.execute().body();
            Logger.getLogger("LOGIN").info(responce);
            if(!responce.isEmpty()){
                Cookie cookie = new Cookie("SavedUser", responce);
                cookie.setMaxAge(2 * 24 * 60 * 60);
                respHttp.addCookie(cookie);
                if(responce.charAt(0) == 'B')
                    return new RedirectView("/Biblio_Main");
                if(responce.charAt(0) == 'R')
                    return new RedirectView("/Recen_Main");
                if(responce.charAt(0) == 'C')
                    return new RedirectView("/LK");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new RedirectView("/Login_page");
    }
    ///
    /// Переходы на основные страницы
    ///

    @GetMapping("/")
    public String MainPage(Model model){
        return "main_page";
    }
    @GetMapping("Leave")
    public String Leave(Model model,HttpServletResponse respHttp){
        Cookie cookie = new Cookie("SavedUser", "");
        cookie.setMaxAge(0);
        respHttp.addCookie(cookie);
        return "redirect:/";
    }
    @GetMapping("/catalog")
    public String Catalog(){
        return "catalog";
    }
    @GetMapping("/LK")
    public String LK(@CookieValue(name = "SavedUser", required = false) String savedUser,
                     Model model){
        if(savedUser == null) return "redirect:Login";
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "id_Client = "+savedUser.substring(1));
        List<Object> curClient = new ArrayList<>();
        try {
            Call<List<Object>> call = apiInterface.getAny(new SendObject(new Client(),"Client", null));
            curClient = call.execute().body();
            model.addAttribute("clientName", curClient.get(0));
        } catch (Exception e) {
            Logger.getAnonymousLogger().info(e.getMessage());
        }
        return "LK";
    }
    ///
    ///Библиотекарь
    ///
    @GetMapping("/Biblio_Main")
    public String Biblio_Main(@CookieValue(name = "SavedUser", required = false) String savedUser,
                     Model model){
        if(savedUser == null) return "redirect:Login";
        if(savedUser.charAt(0) != 'B') return "redirect:Login";

        return "Biblio_Main";
    }
    @GetMapping("/NewBook")
    public String NewBookGet(@CookieValue(name = "SavedUser", required = false) String savedUser,
                             Model model){
        if(savedUser == null) return "redirect:Login";
        if(savedUser.charAt(0) != 'B') return "redirect:Login";
        try {
            List<Author> authors = (List<Author>)(Object)apiInterface.getAny(new SendObject(new Author(),"Author",null)).execute().body();
            List<Language> langs = (List<Language>)(Object)apiInterface.getAny(new SendObject(new Language(),"Language",null)).execute().body();
            List<Country> countries = (List<Country>)(Object)apiInterface.getAny(new SendObject(new Country(),"Country",null)).execute().body();
            model.addAttribute("authors",authors);
            model.addAttribute("langs",langs);
            model.addAttribute("countries",countries);
        } catch (IOException e) {
            Logger.getAnonymousLogger().info(e.getMessage());
        }
        return "NewBook";
    }
    @PostMapping ("/NewBookAdd")
    public String NewBookGet(@CookieValue(name = "SavedUser", required = false) String savedUser,
                             @RequestParam(name = "product_Ven_Code", required = false) String product_Ven_Code,
                             @RequestParam("product_Name") String product_Name,
                             @RequestParam("product_Description") String product_Description,
                             @RequestParam("product_File_Ref") File product_File_Ref,
                             @RequestParam("product_Logo_Ref") File product_Logo_Ref,
                             @RequestParam("product_Promo_Ref") File product_Promo_Ref,
                             @RequestParam("product_Date") String product_Date,
                             @RequestParam("product_Keywords") String product_Keywords,
                             @RequestParam("product_Price") String product_Price,
                             @RequestParam("id_Author") String id_Author,
                             @RequestParam("id_Language") String id_Language,
                             @RequestParam("id_Country") String id_Country){
        if(savedUser == null) return "redirect:Login";
        if(savedUser.charAt(0) != 'B') return "redirect:Login";

        return "NewBook";
    }




    @GetMapping("/GetBooksFeature")
    @ResponseBody
    public Product GetBooksFeature(){
        ApiInterface apiInterface = RequestBuilder.buildRequest().create(ApiInterface.class);
        Call<List<Object>> callLang = apiInterface.getAny(new SendObject(new Product(), "Product", null));
        try {
            return (Product) callLang.execute().body().get(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/GetBooks")
    @ResponseBody
    public List<Fullproduct> GetBooks(@RequestParam("condition") String condition,@RequestParam("lim") int lim) throws IOException {
        Logger.getAnonymousLogger().info(condition);
        HashMap<String,String> map = new HashMap<>();
        map.put("condition", condition);
        map.put("lim", lim+"");
        ApiInterface apiInterface = RequestBuilder.buildRequest().create(ApiInterface.class);
        Call<List<Object>> callLang = apiInterface.getAny(new SendObject(new Fullproduct(), "Fullproduct", map));
        List<Fullproduct> prods = (List<Fullproduct>)(Object)callLang.execute().body();
        return prods;
    }
    @GetMapping("/GetBooksBase")
    @ResponseBody
    public List<Fullproduct> GetBooksBase() throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("lim", 0+"");
        Call<List<Object>> callLang = apiInterface.getAny(new SendObject(new Fullproduct(), "Fullproduct", map));
        List<Fullproduct> prods = (List<Fullproduct>)(Object)callLang.execute().body();
        return prods;
    }
}
