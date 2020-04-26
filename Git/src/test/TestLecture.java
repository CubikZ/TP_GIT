/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import carnet.*;
import entree.Presentation;
import entree.Sens;
import java.io.IOException;
/**
 *
 * @author leon
 */
public class TestLecture {
    
    public static void main(String[] args) throws IOException{
        
        Carnet carnet=new Carnet();
        carnet.lectureFichier("fichier.txt"); //Li le fichier et instancie les Personnes et Société
        System.out.println("\n");
        
        carnet.recherche('c');
        
        carnet.recherche("Ecole");
        carnet.afficher(Ordre.DECROISSANT, Presentation.ABREGE, Sens.NOM_PRENOMS);
        

        
    }   
    
}
