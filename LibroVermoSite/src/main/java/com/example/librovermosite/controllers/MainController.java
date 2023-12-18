package com.example.librovermosite.controllers;

import com.alibaba.fastjson.JSONObject;
import com.example.librovermosite.models.plugs.*;
import com.example.librovermosite.utilites.ApiInterface;
import com.example.librovermosite.utilites.DAOResponse;
import com.example.librovermosite.utilites.RequestBuilder;
import com.example.librovermosite.utilites.SendObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
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
        Call<Long> register = apiInterface.addAny(new SendObject(client, "Client", null));
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
            Response<String> resp = logIn.execute();
            if(!resp.isSuccessful()) return new RedirectView("/Login");
            String responce = resp.body();
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
            return new RedirectView("/Login");
        }
        return new RedirectView("/Login");
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
            Call<List<Object>> call = apiInterface.getAny(new SendObject(new Client(),"Client", map));
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
    @GetMapping("/AddBookPage")
    public String NewBookGet(@CookieValue(name = "SavedUser", required = false) String savedUser,
                             Model model){
        if(savedUser == null) return "redirect:Login";
        if(savedUser.charAt(0) != 'B') return "redirect:Login";
        try {
            List<Author> authors = (List<Author>)(Object)apiInterface.getAny(new SendObject(new Author(),"Author",null)).execute().body();
            List<Language> langs = (List<Language>)(Object)apiInterface.getAny(new SendObject(new Language(),"Language",null)).execute().body();
            List<Country> countries = (List<Country>)(Object)apiInterface.getAny(new SendObject(new Country(),"Country",null)).execute().body();
            List<Genre> genres = (List<Genre>)(Object)apiInterface.getAny(new SendObject(new Genre(),"Genre",null)).execute().body();
            model.addAttribute("authors",authors);
            model.addAttribute("langs",langs);
            model.addAttribute("countries",countries);
            model.addAttribute("genres",genres);
        } catch (IOException e) {
            Logger.getAnonymousLogger().info(e.getMessage());
        }
        return "AddBookPage";
    }
    Path basePath = Path.of("C:\\Users\\pavel\\OneDrive\\Рабочий стол\\Курсовая\\Git\\LibroVermoGit\\LibroVermoSite\\files\\books\\");
    Path imagePath = Path.of("C:\\Users\\pavel\\OneDrive\\Рабочий стол\\Курсовая\\Git\\LibroVermoGit\\LibroVermoSite\\src\\main\\resources\\static\\images\\logos");
    @PostMapping ("/AddBookPage")
    public String NewBookGet(@CookieValue(name = "SavedUser", required = false) String savedUser,
                             @RequestParam(name = "product_Ven_Code", required = false) String product_Ven_Code,
                             @RequestParam("product_Name") String product_Name,
                             @RequestParam("product_Description") String product_Description,
                             @RequestParam("product_File_Ref") MultipartFile product_File_Ref,
                             @RequestParam("product_Logo_Ref") MultipartFile  product_Logo_Ref,
                             @RequestParam("product_Promo_Ref") MultipartFile  product_Promo_Ref,
                             @RequestParam("product_Date") String product_Date,
                             @RequestParam("product_Keywords") String product_Keywords,
                             @RequestParam("product_Price") String product_Price,
                             @RequestParam("id_Author") String id_Author,
                             @RequestParam("id_Language") String id_Language,
                             @RequestParam("id_Country") String id_Country,
                             @RequestParam("id_Genre") String[] id_Genre){
        if(savedUser == null) return "redirect:Login";
        if(savedUser.charAt(0) != 'B') return "redirect:Login";
        try {
            Random random = new Random();
            String fileRefName = generateUniqueFileName()+"_"+(random.nextInt(1000,9999)*random.nextInt(1000,9999))+".pdf";
            String logoRefName = generateUniqueFileName()+"_"+(random.nextInt(1000,9999)*random.nextInt(1000,9999))+".jpg";
            String promoRefName = generateUniqueFileName()+"_"+(random.nextInt(1000,9999)*random.nextInt(1000,9999))+".jpg";

            Path fileRefPath = basePath.resolve(fileRefName);
            Path logoRefPath = imagePath.resolve(logoRefName);
            Path promoRefPath = imagePath.resolve(promoRefName);

            Files.copy(product_File_Ref.getInputStream(), fileRefPath);
            Files.copy(product_Logo_Ref.getInputStream(), logoRefPath);
            Files.copy(product_Promo_Ref.getInputStream(), promoRefPath);

            Product product =new Product(0,
                    generateRandomCode(random.nextInt(4,11)),
                    product_Name,
                    product_Description,
                    fileRefName,
                    logoRefName,
                    promoRefName,
                    product_Date,
                    product_Keywords,
                    product_Price,
                    id_Author,
                    id_Language,
                    id_Country);
            Logger.getAnonymousLogger().info(product.getProduct_Author());
            Long id = apiInterface.addAny(new SendObject(product, "Product",null)).execute().body();
            Logger.getAnonymousLogger().info(id.toString());
            if(id != -1l){
                for (String genre:
                     id_Genre) {
                    Bookgenre bg = new Bookgenre(0,id.toString(),genre);
                    apiInterface.addAny(new SendObject(bg,"Bookgenre", null)).execute();

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }



        return "redirect:/AddBookPage";
    }
    private String generateUniqueFileName() {
        return "file_" + System.currentTimeMillis();
    }
    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandomCode(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }

    @PostMapping("addGenre")
    public String addGenre(@RequestParam("name") String name,
                           @CookieValue(name = "SavedUser", required = false) String savedUser){
        if(savedUser == null) return "redirect:/LK";
        try{
            Genre genre = new Genre(0, name);
            apiInterface.addAny(new SendObject(genre, "Genre", null)).execute();
        }catch (Exception e){ Logger.getAnonymousLogger().info(e.getMessage());}
        return "redirect:/AddBookPage";
    }
    @PostMapping("addCountry")
    public String addCountry(@CookieValue(name = "SavedUser", required = false) String savedUser,
                             @RequestParam("name") String name){
        if(savedUser == null) return "redirect:/LK";
        try{
            Country country = new Country(0, name);
            apiInterface.addAny(new SendObject(country, "Country", null)).execute();
        }catch (Exception e){ Logger.getAnonymousLogger().info(e.getMessage());}
        return "redirect:/AddBookPage";
    }
    @PostMapping("addLanguage")
    public String addLanguage(@CookieValue(name = "SavedUser", required = false) String savedUser,
                              @RequestParam("name") String name){
        if(savedUser == null) return "redirect:/LK";
        try{
            Language language = new Language(0, name);
            apiInterface.addAny(new SendObject(language, "Language", null)).execute();
        }catch (Exception e){ Logger.getAnonymousLogger().info(e.getMessage());}
        return "redirect:/AddBookPage";
    }
    @PostMapping("addAuthor")
    public String addAuthor(@CookieValue(name = "SavedUser", required = false) String savedUser,
                            @RequestParam("lastName") String lastName,
                            @RequestParam("firstName") String firstName,
                            @RequestParam("middleName") String middleName,
                            @RequestParam("alias") String alias,
                            @RequestParam("birthYear") String birthYear,
                            @RequestParam("authorDescription") String authorDescription){
        if(savedUser == null) return "redirect:/LK";
        try{
            Author author = new Author(0, firstName, lastName, middleName, alias, birthYear, authorDescription);
            apiInterface.addAny(new SendObject(author, "Author", null)).execute();
        }catch (Exception e){ Logger.getAnonymousLogger().info(e.getMessage());}
        return "redirect:/AddBookPage";
    }

    @GetMapping("/MyRecessions")
    public String MyRecessions(Model model,
                               @CookieValue(name = "SavedUser", required = false) String savedUser){
        if(savedUser == null) return "redirect:/LK";
        HashMap<String,String> map = new HashMap<>();
        map.put("1", "id_client = '"+savedUser.substring(1)+"'");
        try {
            List<Purchasedgoods> purchasedgoods = new ArrayList<>();
            for (Object obj:
                    apiInterface.getAny(new SendObject(new Purchasedgoods(), "Purchasedgoods",map)).execute().body()) {
                purchasedgoods.add(ReorganizeClass(Purchasedgoods.class, obj));
            }
            map = new HashMap<>();
            String condition = "";
            if(!purchasedgoods.isEmpty())
                for (Purchasedgoods g:
                     purchasedgoods) {
                    condition += "(id_product = '"+g.getPurchasedgoods_Product()+"' or ";
                }
            if(!condition.isEmpty()) {
                condition = condition.substring(0, condition.length()-4)+")";
                Logger.getAnonymousLogger().info(condition);
                map.put("1", condition);
            }
            else map = null;
            HashMap<String,String> rewMap = new HashMap<>();
            rewMap.put("1", "id_client = "+savedUser.substring(1));
            List<Review> reviews = ReorganizeClass(Review.class, apiInterface.getAny(new SendObject(new Review(), "Review", rewMap)).execute().body());
            String condition2 = "";
            if(!reviews.isEmpty())
                for (Review rev: reviews){
                    Logger.getAnonymousLogger().info("adaadada");
                    condition2 += "(id_product != '"+rev.getId_Product()+"' or ";
                }
            if (!condition2.isEmpty()){
                condition2 = condition2.substring(0, condition2.length()-4)+")";
                Logger.getAnonymousLogger().info(condition2);
                map.put("2", condition2);
            }
            model.addAttribute("revies",reviews);

            List<Fullproduct> prods = ReorganizeClass(Fullproduct.class, apiInterface.getAny(new SendObject(new Fullproduct(), "Fullproduct", map)).execute().body());
            model.addAttribute("prodsReview", prods);
        } catch (IOException e) {
            Logger.getLogger("Error").info(e.getMessage());
        }
        return "MyRecessions";

    }
    @GetMapping("WriteRecen")
    public String WriteRecen(Model model,
                             @CookieValue(name = "SavedUser", required = false) String savedUser,
                             @RequestParam("id") String id,
                             @RequestParam("name") String name){
        if(savedUser == null) return "redirect:/LK";
        try{
            model.addAttribute("IdRecen",id);
            model.addAttribute("name",name);
            return "WriteRecen";
        }catch (Exception e){ Logger.getAnonymousLogger().info(e.getMessage());}
        return "redirect:/MyRecessions";
    }
    @PostMapping("WriteRecen")
    public String WriteRecenPost(Model model,
                             @CookieValue(name = "SavedUser", required = false) String savedUser,
                             @RequestParam("id") String id,
                                 @RequestParam("rate") String rate,
                                 @RequestParam("text") String text,
                                 @RequestParam("title") String title
                                 ){
        if(savedUser == null) return "redirect:/LK";
        try{
            Review review = new Review(0,title,text,rate,"Проверка", LocalDateTime.now().toLocalDate().toString(),savedUser.substring(1),null,id);
            apiInterface.addAny(new SendObject(review, "Review",null)).execute();
            return "redirect:/MyRecessions";
        }catch (Exception e){ Logger.getAnonymousLogger().info(e.getMessage());}
        return "redirect:/WriteRecen";
    }
    @GetMapping("/BookPage")
    public String BookPage(Model model,
                           @RequestParam("id") String id){
        HashMap<String,String> conditions = new HashMap<>();
        conditions.put("con1", "id_product = "+id);
        try {
            Fullproduct pageProduct = ReorganizeClass(Fullproduct.class, apiInterface.getAny(new SendObject(new Fullproduct(), "Fullproduct", conditions)).execute().body().get(0));
            model.addAttribute("prod", pageProduct);
        } catch (IOException e) {
            Logger.getAnonymousLogger().info(e.getMessage());
        }
        return "BookPage";
    }

    @GetMapping("/GetBooks")
    @ResponseBody
    public List<Fullproduct> GetBooks(@RequestParam("condition") String condition,@RequestParam("lim") int lim) throws IOException {
        Logger.getAnonymousLogger().info(condition);
        HashMap<String,String> map = new HashMap<>();
        map.put("condition", condition);
        map.put("lim", lim+"");
        map.put("1", "product_price>0");
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
        map.put("1", "product_price>0");
        Call<List<Object>> callLang = apiInterface.getAny(new SendObject(new Fullproduct(), "Fullproduct", map));
        List<Fullproduct> prods = (List<Fullproduct>)(Object)callLang.execute().body();
        return prods;
    }
    public <T> T ReorganizeClass(Class<T> organizeClass, Object rootObject) {
        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Object> map = objectMapper
                .convertValue(rootObject, new TypeReference<HashMap<String, Object>>() {});
        return JSONObject.parseObject(JSONObject.toJSONString(map),organizeClass);
    }
    public <T> List<T> ReorganizeClass(Class<T> organizeClass, List<Object> rootObject) {
        List<T> list = new ArrayList<>();
        for (Object obj:
             rootObject) {
            ObjectMapper objectMapper = new ObjectMapper();
            HashMap<String, Object> map = objectMapper
                    .convertValue(obj, new TypeReference<HashMap<String, Object>>() {});
            list.add(JSONObject.parseObject(JSONObject.toJSONString(map),organizeClass));
        }
        return list;
    }

    @GetMapping("/GetRecensions")
    @ResponseBody
    public List<authorizereview> GetBooks(@RequestParam("id") String id) throws IOException {
        HashMap<String,String> map = new HashMap<>();
        map.put("condition", "id_product = "+id);
        List<authorizereview> reviews = ReorganizeClass(authorizereview.class, apiInterface.getAny(new SendObject(new authorizereview(), "authorizereview", map)).execute().body());
        return reviews;
    }
}
