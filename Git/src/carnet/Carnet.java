/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carnet;

import entree.Entree;

public class Carnet {
    Entree[] entree;
    Entree[] selectionnees;
    
    Carnet(Entree[] entree, Entree[] selectionnees){
        this.entree = entree;
        this.selectionnees = selectionnees;
    }
}
