package main;


import java.io.Serializable;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nguye
 */
public class SlangWord implements Serializable{
    String Acronym;
    String Word;
    
     public SlangWord(String Acronym, String Word) {
        this.Acronym = Acronym;
        this.Word = Word;
    }
    
     
     public String getAcronym(){
        return Acronym;
    }

    public void setAcronym(String Acronym){
        this.Acronym = Acronym;
    } 
     
     public String getWord(){
        return Word;
    }

    public void setWord(String Word){
        this.Word = Word;
    } 
    @Override
    public String toString(){
        return "Tu Viet Tat: " + Acronym + "\n Nghia : " + Word;
    }

    public void display(){
        System.out.println(toString());
    }
    
    
}
