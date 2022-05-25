package com.example.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;


@Entity
@XmlRootElement

public class WebUser {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String password;

    //autenticacao(que pode ser retirada caso use tokens) e admin variaveis para destinguir user 
    private int admin; 
    private boolean auth; 

    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Event> events;
   
  
    public WebUser() {
    }
    
    public WebUser(String name) {
        this.name = name;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    //@XmlElementWrapper(name = "teamessors")
    //@XmlElement(name = "team")
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String toString() {
        return this.name + "(id = " + this.id + ")";
    }
}