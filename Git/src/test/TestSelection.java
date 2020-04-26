/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import carnet.*;
import entree.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author loann
 */
public class TestSelection {
    public static void main(String[] args) throws IOException{
        ArrayList<Personne> listPersonne = new ArrayList();
        ArrayList<Societe> listSociete = new ArrayList();
        ArrayList<Entree> listEntree = new ArrayList();
        
        Carnet carnet=new Carnet();
        
        carnet.lectureFichier("fichier.txt"); //Li le fichier et instancie les Personnes et Société
        
        listPersonne=carnet.getListPersonne();
        listSociete=carnet.getListSociete();
        listEntree=carnet.getListEntree();
        
        listEntree.remove(4);//On enleve Dumbledore
        listEntree.remove(4);// On enleve Potter
        listEntree.remove(4); // On enleve Weasley
        //Il reste que des societe dans la liste Entree
        
        //
        carnet.selection("Dumbledore");
        carnet.selection("Harry");
        
        carnet.selection(listEntree.get(3)); //On recupere l'ile des bonbons
        
        listEntree.remove(3);//On enleve l'ile des bonbons
        carnet.selection(listEntree);
        
        carnet.afficherSelection(Ordre.CROISSANT,Presentation.ABREGE, Sens.NOM_PRENOMS);//On affiche la selection
        
        //carnet.selection();
    }
    
}
