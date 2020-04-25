package entree;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author loann
 */
public class Societe implements Entree{
    private String raisonSociale;
    private int id;
    
    public Societe(){
        raisonSociale = null;
    }
    
    public Societe(int id2,String raisonSociale){
        id=id2;
        this.raisonSociale = raisonSociale;
        
    }
    
    //getters
    public String getRaisonSociale(){
        return raisonSociale;
    }
    public int getID()
    {
        return id;
    }
    
    //setter
    public void setRaisonSociale(String raisonSociale){
        this.raisonSociale = raisonSociale;
    }
    
    //ToString
    @Override
    public String toString(Presentation presentation, Sens sens){
        return ("raison Sociale : " + this.raisonSociale);
    }
    
    //Recherche
    @Override
    public boolean recherche(String recherche){
        return false;
    };
    
}
