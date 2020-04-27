/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import entree.*;
import carnet.*;
import java.io.IOException;
/**
 *
 * @author leon
 */
public class TestTout {
        public static void main(String[] args) throws IOException{
            //Initialisation d'un carnet
            Carnet carnet=new Carnet();
            
            //Lecture d'un fichier texte
            carnet.lectureFichier("fichier.txt"); //Li le fichier et instancie les Personnes et Société
            System.out.println("\n");

            
            //Affichage
            carnet.afficher(Ordre.CROISSANT, Presentation.ABREGE, Sens.NOM_PRENOMS);
            carnet.afficher(Ordre.CROISSANT, Presentation.ABREGE, Sens.PRENOMS_NOM);
            carnet.afficher(Ordre.CROISSANT, Presentation.COMPLET, Sens.NOM_PRENOMS);
            carnet.afficher(Ordre.CROISSANT, Presentation.COMPLET, Sens.PRENOMS_NOM);
            carnet.afficher(Ordre.CROISSANT, Presentation.SIMPLE, Sens.NOM_PRENOMS);
            carnet.afficher(Ordre.CROISSANT, Presentation.SIMPLE, Sens.PRENOMS_NOM);
            carnet.afficher(Ordre.DECROISSANT, Presentation.ABREGE, Sens.NOM_PRENOMS);
            carnet.afficher(Ordre.DECROISSANT, Presentation.ABREGE, Sens.PRENOMS_NOM);
            carnet.afficher(Ordre.DECROISSANT, Presentation.COMPLET, Sens.NOM_PRENOMS);
            carnet.afficher(Ordre.DECROISSANT, Presentation.COMPLET, Sens.PRENOMS_NOM);
            carnet.afficher(Ordre.DECROISSANT, Presentation.SIMPLE, Sens.NOM_PRENOMS);
            carnet.afficher(Ordre.DECROISSANT, Presentation.SIMPLE, Sens.PRENOMS_NOM);

        
    }   
}
