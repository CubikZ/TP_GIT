/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import entree.*;
import carnet.*;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author leon
 */
public class TestTout {
        public static void main(String[] args) throws IOException{
            //Initialisation d'un carnet
            Carnet carnet=new Carnet();
            
            //Va etre utiliser pour la selection
            ArrayList<Personne> listPersonne = new ArrayList();
            ArrayList<Societe> listSociete = new ArrayList();
            ArrayList<Entree> listEntree = new ArrayList();
            
            //Pour la recherche
            ArrayList<Entree> listRecherche = new ArrayList();
            
            //Lecture d'un fichier texte
            carnet.lectureFichier("fichier.txt"); //Li le fichier et instancie les Personnes et Société
            System.out.println("\n");
            listPersonne=carnet.getListPersonne();
            listSociete=carnet.getListSociete();
            listEntree=carnet.getListEntree();
            
            listEntree.remove(4);//On enleve Dumbledore
            listEntree.remove(4);// On enleve Potter
            listEntree.remove(4); // On enleve Weasley
            //Il reste que des societe dans la liste Entree

            
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
            
            //Selection
            carnet.selection("Dumbledore");
            carnet.selection("Harry");
            carnet.selection(listEntree.get(3)); //On recupere l'ile des bonbons
        
            listEntree.remove(3);//On enleve l'ile des bonbons
            carnet.selection(listEntree);
        
            carnet.afficherSelection(Ordre.CROISSANT,Presentation.ABREGE, Sens.NOM_PRENOMS);//On affiche la selection
            
            //Recherche
            listRecherche = carnet.recherche("Dumbledore");
            for(int i=0;i<listRecherche.size();i++)
                System.out.println(listRecherche.get(i).toString(Presentation.ABREGE, Sens.NOM_PRENOMS));
            
            listRecherche = carnet.recherche("Ecole");
            for(int i=0;i<listRecherche.size();i++)
                System.out.println(listRecherche.get(i).toString(Presentation.ABREGE, Sens.NOM_PRENOMS));
            
        
    }   
}
