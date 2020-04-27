/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entree.*;

/**
 *
 * @author leon
 */
public class TestSociete {
    final static Societe societe = new Societe(0,"societe1");
    
    public static void main(String[] args){
        
        System.out.println(societe.getRaisonSociale());
        
        societe.setRaisonSociale("societe2");
        
        System.out.println(societe.toString(Presentation.ABREGE,Sens.NOM_PRENOMS));
        
        
        
    }   
    
}

