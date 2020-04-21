/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author loann
 */
public class Societe {
    private String raisonSociale;
    
    Societe(){
        raisonSociale = null;
    }
    
    Societe(String raisonSociale){
        this.raisonSociale = raisonSociale;
    }
    
    //getters
    public String getRaisonSociale(){
        return raisonSociale;
    }
    
    //setter
    public void setRaisonSociale(String raisonSociale){
        this.raisonSociale = raisonSociale;
    }
    
    @Override
    public String toString(){
        return ("raison Sociale : " + this.raisonSociale);
    }
    
    
    
}
