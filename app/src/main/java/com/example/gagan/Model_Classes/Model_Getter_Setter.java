package com.example.gagan.Model_Classes;

import java.io.Serializable;
import java.util.Locale;

/**
 * Created by GaGan on 3/30/2017.
 */

public class Model_Getter_Setter implements Serializable {

    String History;
    String Habitate;
    String Diet;
    String Scientific;

    public Model_Getter_Setter( String lngLat,String name,String Category) {
        LngLat = lngLat;
        Name = name;
        this.Category=Category;
    }

    public String getLngLat() {
        return LngLat;
    }

    String LngLat;
    public String getTechName() {
        return TechName;
    }

    public Model_Getter_Setter(String Name,String TechName,String LngLat,String Image)
    {
        this.Name=Name;
        this.TechName=TechName;
        this.LngLat=LngLat;
        this.Image = Image;
    }
    String TechName;
    String Cat;

    String Name;

   public Model_Getter_Setter(String History,String Habitate,String Diet,String Scientific,String Cat,String Name,String Image)
    {
        this.History = History;
        this.Habitate = Habitate;
        this.Diet = Diet;
        this.Scientific = Scientific;
        this.Cat = Cat;
        this.Name = Name;
        this.Image = Image;

    }

    public String getHistory() {
        return History;
    }

    public String getHabitate() {
        return Habitate;
    }

    public String getDiet() {
        return Diet;
    }

    public String getScientific() {
        return Scientific;
    }

    public String getCat() {
        return Cat;
    }

    public String getName() {
        return Name;
    }


    public String getCategory() {
        return Category;
    }

    public String getImage() {
        return Image;
    }

    String Category,Image;



}
