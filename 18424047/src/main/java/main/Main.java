/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;


/**
 *
 * @author nguye
 */
public class Main {
   
    
    public static void main(String[] args) throws IOException{
        Features F = new Features();
        F.DocFile(F.list, F.hashmap);
        F.NhapMenu(F.list, F.hashmap);
//        System.out.println("Nhap tu can tim: ");
//        Scanner input = new Scanner(System.in);
//        String Acronym = input.nextLine();
//        System.out.println(hashmap.get(Acronym));
    }
}
