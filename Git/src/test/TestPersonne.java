/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entree.*;
import java.util.ArrayList;

/**
 *
 * @author loann
 */
public class TestPersonne {
    
    public static void main(String[] args){
        ArrayList<String> prenoms = new ArrayList<>();
        prenoms.add("Vincent");
        prenoms.add("Didier");
        
        
        Societe societe1 = new Societe(0,"Boulanger");
        Personne humain1 = new Personne(0,"Paul",prenoms,Genre.HOMME,null,societe1,null);
        
        prenoms.clear();
        prenoms.add("Patricia");
        prenoms.add("Alexandra");
        
        Personne humain2 = new Personne(1,"Camille",prenoms,Genre.FEMME,humain1,societe1,null);
       
        System.out.println(humain1.getPrenomsINIT());
        System.out.println(humain1.getNom());
        
        System.out.println(humain1.toString(Presentation.ABREGE, Sens.NOM_PRENOMS));
        
        System.out.println(humain2.toString(Presentation.SIMPLE, Sens.PRENOMS_NOM));
        
        System.out.println(humain1.toString(Presentation.COMPLET, Sens.PRENOMS_NOM));
    }   
}
