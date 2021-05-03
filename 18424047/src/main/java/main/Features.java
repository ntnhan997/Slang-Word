/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author nguye
 */
public class Features {
    public static List<SlangWord> list = new ArrayList<>();
    public static HashMap<String, ArrayList<String>> hashmap = new HashMap<String, ArrayList<String>>();
    public static ArrayList<String> history = new ArrayList<>();
     public static void DocFile(List<SlangWord> list, HashMap<String, ArrayList<String>> hashmap) throws IOException{
       String line = "";
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("slang.txt"));
            while ((line = csvReader.readLine()) != null) {
                
               String[] data = line.split("`");
               if(data.length == 2){
                   
                    SlangWord SW = new SlangWord(data[0], data[1]);
                    Collections.addAll(list, SW);
               }
                   

        }
        } catch (Exception e) {
            
        }
        for(int i = 0; i < list.size(); i++){
            ArrayList<String> word = new ArrayList<String>();
            String[] words = list.get(i).getWord().split("\\| ");
            for(String W :words ){
                word.add(W);
            }
            hashmap.put(list.get(i).getAcronym(), word);
        }
        
        String row = "";
          try {
            BufferedReader csvReader = new BufferedReader(new FileReader("history.txt"));
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split("/n");
                history.add(data[0]);
        }
        } catch (Exception e) {
            
        }
    }
    
    public static void TimKiemSlangWord(List<SlangWord> list, HashMap<String, ArrayList<String>> hashmap) throws IOException{
        System.out.println("Nhap tu can tim (Slang Word): ");
        Scanner input = new Scanner(System.in);
        String Acronym = input.nextLine();
        history.add(Acronym);
        if(hashmap.get(Acronym) == null){
            System.out.println("******Khong Co Tu Ban Can Tim.");
            return;
        }
        for(int i = 0 ; i < hashmap.get(Acronym).size(); i++){
            System.out.println("Nghia Thu "+ (i + 1) +":");
            System.out.println(hashmap.get(Acronym).get(i));
        }
        
        FileWriter writer = new FileWriter("history.txt");  
        BufferedWriter buffer = new BufferedWriter(writer);  
        for(int i = 0; i < history.size(); i++){
            buffer.write(history.get(i));  
        }
        
    }
   
    
      public static void TimKiemDefinition(List<SlangWord> list, HashMap<String, ArrayList<String>> hashmap) throws IOException{
        System.out.println("Nhap tu can tim (definition): ");
        Scanner input = new Scanner(System.in);
        String Word = input.nextLine();
        history.add(Word);
        int dem = 1;
        for (String i:hashmap.keySet()){
            for(int j = 0; j < hashmap.get(i).size(); j++){
        if(hashmap.get(i).get(j).contains(Word)){
                    System.out.println("Tu viet tat thu " + dem);
                    System.out.println(i + "       " + hashmap.get(i).get(j));
                    dem++;
                }
                        
            }
        }
        if(dem == 1){
            System.out.println("khong co tu ban can tim!");
        }
        FileWriter writer = new FileWriter("history.txt");  
        BufferedWriter buffer = new BufferedWriter(writer);  
        for(int i = 0; i < history.size(); i++){
            buffer.write(history.get(i));  
        }
    }
      
      public static void history (ArrayList<String> history) throws IOException{
          for(int i = 0; i < history.size(); i ++){
              System.out.println(history.get(i));
          }
      }
      
       public static void ThemMoi (List<SlangWord> list, HashMap<String, ArrayList<String>> hashmap) throws IOException{
        System.out.println("Moi ban nhap Slang Word");
        Scanner input = new Scanner(System.in);
        String Word = "";
        String Acronym = input.nextLine();
        if(hashmap.get(Acronym) != null){
            System.out.println("Slang Word da trung");
            System.out.println("    1. Ghi de");
            System.out.println("    2. Ban sao");
            input = new Scanner(System.in);
            int chon = input.nextInt();
            System.out.println("Moi ban nhap nghia cua tu do");
            input = new Scanner(System.in);
            Word = input.nextLine();       
            if(chon == 1){   
                File tempFile = new File("temp.txt");
                String dong;
                RandomAccessFile raf= new RandomAccessFile(new File("Slang.txt"), "rw");
                RandomAccessFile tempraf = new RandomAccessFile(tempFile, "rw");
                raf.seek(0);
                while (raf.getFilePointer() < raf.length()) {
                    dong = raf.readLine();
                    String[] data = dong.split("`");
                    if(data.length == 2){
                        if (data[0].equals(Acronym)) {
                            dong = Acronym + "`"+ data[1] + "| " + Word;
                        }
                        tempraf.writeBytes(dong + System.lineSeparator());
                    }
                    
                }
                KhoiPhucFile(new File("temp.txt"), new File("Slang.txt"));
                DocFile(list, hashmap);
                raf.setLength(tempraf.length());
                tempraf.close();
                raf.close();
                tempFile.delete();
 
                System.out.println("Them moi thanh cong. ");
            }
            if(chon == 2){
                SlangWord SW = new SlangWord(Acronym, Word);
                Collections.addAll(list, SW);
                ArrayList<String> word = new ArrayList<String>();
                word.add(Word);
                hashmap.put(Acronym, word);
                Path path = Paths.get("slang.txt");
                String text = Acronym + "`"+Word;
                try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND,StandardOpenOption.CREATE)) {
                writer.write(text);
                } catch (IOException e) {
                }
            }
        }
        if(hashmap.get(Acronym) == null){
            System.out.println("Moi ban nhap nghia cua tu do");
            input = new Scanner(System.in);
            Word = input.nextLine();     
            SlangWord SW = new SlangWord(Acronym, Word);
            Collections.addAll(list, SW);
            ArrayList<String> word = new ArrayList<String>();
            word.add(Word);
            hashmap.put(Acronym, word);
            Path path = Paths.get("slang.txt");
            String text = Acronym + "`"+Word;
            try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND,StandardOpenOption.CREATE)) {
            writer.write(text);
            } catch (IOException e) {
            }
        }
        
      }
       
       public static void Xoa (List<SlangWord> list, HashMap<String, ArrayList<String>> hashmap) throws IOException{
           System.out.println("Moi ban nhap Slang Word");
           Scanner input = new Scanner(System.in);
           String Acronym = input.nextLine();
            if(hashmap.get(Acronym) == null){
                System.out.println("******Khong Co Tu Ban Can Xoa.");
                return;
            }
                File tempFile = new File("temp.txt");
                String dong;
                RandomAccessFile raf= new RandomAccessFile(new File("Slang.txt"), "rw");
                RandomAccessFile tempraf = new RandomAccessFile(tempFile, "rw");
                raf.seek(0);
                while (raf.getFilePointer() < raf.length()) {
                    dong = raf.readLine();
                    String[] data = dong.split("`");
                    if(data.length == 2){
                        if (data[0].equals(Acronym)) {
                            hashmap.remove(Acronym);
                            list.remove(Acronym);
                            continue;
                        }
                        tempraf.writeBytes(dong + System.lineSeparator());
                    }
                    
                }
                KhoiPhucFile(new File("temp.txt"), new File("Slang.txt"));
                raf.setLength(tempraf.length());
                tempraf.close();
                raf.close();
                tempFile.delete();
 
                System.out.println("Xoa thanh cong. ");
       }
       
       public static void CapNhat (List<SlangWord> list, HashMap<String, ArrayList<String>> hashmap) throws IOException{
           System.out.println("Moi ban nhap Slang Word");
           Scanner input = new Scanner(System.in);
           String Acronym = input.nextLine();
            if(hashmap.get(Acronym) == null){
                System.out.println("******Khong Co Tu Ban Can Cap Nhat.");
                return;
            }
           System.out.println("Moi ban nhap nghia cua tu");
           input = new Scanner(System.in);
           String Word = input.nextLine();
           File tempFile = new File("temp.txt");
           String dong;
           RandomAccessFile raf= new RandomAccessFile(new File("Slang.txt"), "rw");
            RandomAccessFile tempraf = new RandomAccessFile(tempFile, "rw");
                raf.seek(0);
                while (raf.getFilePointer() < raf.length()) {
                    dong = raf.readLine();
                    String[] data = dong.split("`");
                    if(data.length == 2){
                        if (data[0].equals(Acronym)) {
                            dong = Acronym + "`"+ Word;    
                        }
                        tempraf.writeBytes(dong + System.lineSeparator());
                    }
                    
                }
                KhoiPhucFile(new File("temp.txt"), new File("Slang.txt"));
                DocFile(list, hashmap);
                raf.setLength(tempraf.length());
                tempraf.close();
                raf.close();
                tempFile.delete();
 
                System.out.println("Cap nhat thanh cong. ");
                
                
                
                
                
                
//                File tempFile = new File("temp.txt");
//                String dong;
//                RandomAccessFile raf= new RandomAccessFile(new File("Slang.txt"), "rw");
//                RandomAccessFile tempraf = new RandomAccessFile(tempFile, "rw");
//                raf.seek(0);
//                while (raf.getFilePointer() < raf.length()) {
//                    dong = raf.readLine();
//                    String[] data = dong.split("`");
//                    if(data.length == 2){
//                        if (data[0].equals(Acronym)) {
////                            hashmap.remove(Acronym);
////                            list.remove(Acronym);
//                              dong = data[0] + "`";
//                              String[] nghia = data[1].split("\\| ");
//                              System.out.println("Tu " + Acronym + " co " + nghia.length + " nghia, moi ban chon nghia muon xoa!");
//                              for(int i = 0; i < nghia.length; i++){
//                                  System.out.println("Nghia thu " +(i + 1) +  ": " + nghia[i]);
//                              }
//                              input = new Scanner(System.in);
//                              int chon = input.nextInt();
//                              for(int i = 0; i < nghia.length; i++){
////                                  if(i == chon - 1){ // nghia == chon - bo qua
////                                      continue;
////                                  }
////                                  if(chon != nghia.length ){ //chon khac cuoi
////                                     if(i == nghia.length){
////                                         dong += nghia[i];
////                                     }else{
////                                         dong += nghia[i] +"| ";
////                                     }
////                                  }else{// chon cuoi
////                                     if(i == nghia.length - 1){ 
////                                        dong += nghia[i];
////                                     }else{
////                                         dong += nghia[i] +"| ";
////                                     }
////                                  }
//                                    dong += nghia[i] + "| ";
//                                         
//                              }
//                              dong.substring(2, nghia.length);  
//                        }
//                        tempraf.writeBytes(dong + System.lineSeparator());
//                    }
//                    
//                }
       }
       
       
       
       
     public static void SlangWordNgauNhien() throws IOException{
         Random rand = new Random();
         int range = hashmap.size();
         int songaunhien = rand.nextInt(range);
         System.out.println(list.get(songaunhien).toString());
     }
     
     public static void Quizz1(){
         Random rand = new Random();
         int range = hashmap.size();
         int songaunhien = rand.nextInt(range);
         System.out.println("Nghia cua tu "+ list.get(songaunhien).getAcronym() + " la gi?");
         ArrayList<String> answer = new ArrayList<>();
         String a = hashmap.get(list.get(songaunhien).getAcronym()).get(0);
         answer.add(a);
         for(int i = 0; i < 3; i ++){
             int so = rand.nextInt(range);
             String b = hashmap.get(list.get(so).getAcronym()).get(0);
             answer.add(b);
         }
         Collections.sort(answer);
         System.out.println("1. "+ answer.get(0) +"           2. "+ answer.get(1));
         System.out.println("3. "+ answer.get(2) +"           4. "+ answer.get(3));
         System.out.println("Moi ban nhap dap an: ");
         Scanner input = new Scanner(System.in);
         int chon = input.nextInt();
         if(answer.get(chon-1).equals(a)){
             System.out.println("Chuc mung ban chon dung!");
             return;
         }
         System.out.println("Ban da chon sai, dap an la: "+ a);
     }
     
     public static void Quizz2(){
         Random rand = new Random();
         int range = hashmap.size();
         int songaunhien = rand.nextInt(range);
         String[] nghia = list.get(songaunhien).getWord().split("\\| ");
         String kq = list.get(songaunhien).getAcronym();
         int nghiangaunhien = rand.nextInt(nghia.length);
         System.out.println(nghiangaunhien);
         System.out.println("Tu Viet Tat cua tu "+ nghia[nghiangaunhien] + " la gi?");
         ArrayList<String> answer = new ArrayList<>();
         answer.add(kq);
         for(int i = 0; i < 3; i ++){
             int so = rand.nextInt(range);
             String b = list.get(so).getAcronym();
             answer.add(b);
         }
         Collections.sort(answer);
         System.out.println("1. "+ answer.get(0) +"           2. "+ answer.get(1));
         System.out.println("3. "+ answer.get(2) +"           4. "+ answer.get(3));
         System.out.println("Moi ban nhap dap an: ");
         Scanner input = new Scanner(System.in);
         int chon = input.nextInt();
         if(answer.get(chon-1).equals(kq)){
             System.out.println("Chuc mung ban chon dung!");
             return;
         }
         System.out.println("Ban da chon sai, dap an la: "+ kq);
     }
    
     public static void KhoiPhucFile(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
    try {
        is = new FileInputStream(source);
        os = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
    } finally {
        is.close();
        os.close();
    }
}
    
    public static void NhapMenu(List<SlangWord> list, HashMap<String, ArrayList<String>> hashmap) throws IOException{
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
                    TimKiemSlangWord(list, hashmap);
                    break;
                case 2:
                    TimKiemDefinition(list,hashmap);
                    break;
                case 3:
                    history(history);
                    break;
                case 4:
                    ThemMoi(list, hashmap);
                    break;
                case 5:
                    CapNhat(list, hashmap);
                    break;
                case 6:
                    Xoa(list,hashmap);
                    break;
                case 7:
                    KhoiPhucFile(new File("slangroot.txt"),new File("slang.txt"));
                    DocFile(list, hashmap);
                    System.out.println("Khoi phuc file goc thanh cong");
                    break;
                case 8:
                    SlangWordNgauNhien();
                    break;
                case 9:
                    Quizz1();
                    break;
                case 10:
                    Quizz2();
                    break;
                case 11:
                    return;
                default:
                    System.out.println("Chon Sai Xin Moi Chon Lai.");
                    break;
                
            }
        }
    }
}
