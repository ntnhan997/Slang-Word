/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


/**
 *
 * @author nguye
 */
public class Main {
    public static void DocFile(HashMap<String, ArrayList<String>> hashmap) throws IOException{
       String line = "";
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("slang.txt"));
            while ((line = csvReader.readLine()) != null) {
                
               String[] data = line.split("`");
               if(data.length == 2){
                   ArrayList<String> word = new ArrayList<String>();
//                    SlangWord SW = new SlangWord(data[0], data[1]);
//                    Collections.addAll(List, SW);
                       String[] words = data[1].split("\\| ");
                       for(String W :words ){
                        word.add(W);
                       }
                    hashmap.put(data[0], word);
//                    System.out.println(List.size() + " sadasjdas  " + map.size());
               }
                   

        }
        } catch (Exception e) {
            
        }
    }
    
    public static void TimKiemSlangWord(HashMap<String, ArrayList<String>> hashmap) throws IOException{
        System.out.println("Nhap tu can tim: ");
        Scanner input = new Scanner(System.in);
        String Acronym = input.nextLine();
        if(hashmap.get(Acronym) == null){
            System.out.println("******Khong Co Tu Ban Can Tim.");
            return;
        }
        for(int i = 0 ; i < hashmap.get(Acronym).size(); i++){
            System.out.println("Nghia Thu "+ (i + 1) +":");
            System.out.println(hashmap.get(Acronym).get(i));
        }
    }
    
    
    public static void NhapMenu(HashMap<String, ArrayList<String>> hashmap) throws IOException{
        int chon;
        while(true){
            System.out.println("========================Menu=======================.");
            System.out.println("1. Tim Kiem Theo Slang Word.");
            System.out.println("2. Tim Kiem Theo Definition.");
            System.out.println("3. Hien Thi History.");
            System.out.println("4. Them Moi 1 Slang Word.");
            System.out.println("5. Chinh Sua 1 Slang Word.");
            System.out.println("6. Xoa 1 Slang Word.");
            System.out.println("7. Reset Danh Sach Slang Word goc.");
            System.out.println("8. Random 1 Slang Word.");
            System.out.println("9. Quizz 1(Slang Word).");
            System.out.println("10. Quizz 2(Definition).");
            System.out.println("11. Thoat Chuong Trinh.");
            System.out.println("========> Chon chuc nang:");
            Scanner input = new Scanner(System.in);
            chon = input.nextInt();
            switch(chon){
                case 1:
                    TimKiemSlangWord(hashmap);
                    break;
                case 2:
                    System.out.println("hello 2");
                    break;
                case 3:
                    System.out.println("hello 3");
                    break;
                case 4:
                    System.out.println("hello 4");
                    break;
                case 5:
                    System.out.println("hello 5");
                    break;
                case 6:
                    System.out.println("hello 6");
                    break;
                case 7:
                    System.out.println("hello 7");
                    break;
                case 8:
                    System.out.println("hello 8");
                    break;
                case 9:
                    System.out.println("hello 9");
                    break;
                case 10:
                    System.out.println("hello 10");
                    break;
                case 11:
                    return;
                default:
                    System.out.println("Chon Sai Xin Moi Chon Lai.");
                    break;
                
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
            HashMap<String, ArrayList<String>> hashmap = new HashMap<String, ArrayList<String>>(); 
            DocFile(hashmap);
            NhapMenu(hashmap);
//        System.out.println("Nhap tu can tim: ");
//        Scanner input = new Scanner(System.in);
//        String Acronym = input.nextLine();
//        System.out.println(hashmap.get(Acronym));
    }
}
