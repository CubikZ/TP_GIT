/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carnet;
//Liste
import java.util.ArrayList;
//Lire le fichier
import java.io.FileReader;
//Erreur
import java.io.BufferedReader;
import java.io.IOException;

import entree.*;
import java.io.File;
import java.io.FileNotFoundException;



public class Carnet {
    ArrayList<Entree> listEntree = new ArrayList<Entree>(); // Create an ArrayList object
    ArrayList<Entree> listselectionnees = new ArrayList<Entree>(); // Create an ArrayList object
    
    //Constructeur
    Carnet(Entree entree, Entree selectionnees){
        listEntree.add(entree);
    }
    
    //Methode Lecture de Fichier
    public static void lectureFichier(String lienFichier) throws IOException{
        
        
        ArrayList<Personne> listpersonne = new ArrayList<>(); //liste des personnes
        ArrayList<Societe> listsociete = new ArrayList<>(); //liste des societe
        
        
        //Fichier
        File file = new File(lienFichier); 
        BufferedReader in = null ;
        
        //Separateur
        final String SEPARATEUR = ";";
        String line;
        
        int ID = 0; //ID de la ligne
        int ID_conjoint = 0; //ID du conjoint
        
        //variable pour SOCIETE
        String raisonSociale;
        int ID_societe;
        ArrayList<Integer> idSAVEs = new ArrayList<>();
        
        //variable pour PERSONNE
        String nom=null;
        ArrayList<String> listprenom = new ArrayList<>(); //liste des prenoms
        Genre genre=null;
        Personne conjoint = null;
        Societe societe = null;
        String fonction=null;
        
        //variable conjoint
        ArrayList<Integer> idSAVEc=new ArrayList<>();
        
        try //Essai d'ouvrir le fichier
        {
            in = new BufferedReader(new FileReader(file));
        }
        catch (FileNotFoundException exc)   //Sinon
        {
            System.out.println("Erreur d'ouverture de fichier");
        }
            
        while((line = in.readLine()) != null)   //Boucle tant qu'il n'y a plus de ligne
        {
            
            
            //affichage des ligne 
            System.out.println(line);
            
            //Separation de la ligne en champ separer par ";"
            String mots[] = line.split(SEPARATEUR);
                
            if(mots.length >=3 && !line.equals("")){
                    
                
                switch(mots[1]){
                        
                    case "SOCIETE" :
                            ID = Integer.parseInt(mots[0]); //ID de la ligne
                            raisonSociale = mots[2];
                            listsociete.add(new Societe(ID,raisonSociale));
                            System.out.println("OK SOCIETE");
                            break;
                        
                    case "PERSONNE" :
                        
                        ID = Integer.parseInt(mots[0]); //ID de la ligne
                        //Nom
                        nom = mots[3];
                        //list prenom
                        listprenom.clear(); //creation d'une liste pour prenom
                        
                        String prenom[] = mots[2].split(","); //Separer le champ des prenoms 
                        for (int i = 0;i < prenom.length; i++){
                            listprenom.add(prenom[i]);  //Ajout des prenom dans la liste 
                        }
                        
                        //Genre
                        switch(mots[4]){
                            case "H" :
                                genre = Genre.HOMME;
                                break;
                            case "F" :
                                genre = Genre.FEMME;
                                break;
                            default :
                                genre = null;
                                
                        }
                        
                        //Conjoint
                        conjoint=null;
                        if (mots[5].equals(""))    //On vérifie si le champ est vide ?
                            idSAVEc.add(null);
                        else
                        {
                            ID_conjoint = Integer.parseInt(mots[5]);    //On crée un tableau pour ensuite modifier les id_conjoint par la suite
                            idSAVEc.add(ID_conjoint);
                        }
                        
                        //Societe
                        societe=null;
                        if (mots[6].equals(""))    //On vérifie si le champ est vide ?
                            idSAVEs.add(null);
                        else
                        {
                            ID_societe = Integer.parseInt(mots[6]);    //On crée un tableau pour ensuite modifier les id_conjoint par la suite
                            idSAVEs.add(ID_societe);
                        }
                        
                        
                        //fonction
                        fonction = mots[7];
                        
                        //creation d' objet dans listpersonne
                        listpersonne.add(new Personne(ID,nom, listprenom,genre, conjoint,societe,fonction));
                     
                        System.out.println("OK Personne");
                        break;
                        
                    default:
                        System.out.println("Erreur");
                        
                }
            }
            
        }   
        in.close();
        //On met a jour les id conjoints / societe des personnes qui en ont
        
        //Pour les conjoints
        for(int i=0;i<idSAVEc.size();i++)
        {
            if (idSAVEc.get(i)!=null)
            {
                for(int j=0;j<listpersonne.size();j++)  //On parcourt la liste a la recherche du meme id
                {
                    if(listpersonne.get(j).getID()== idSAVEc.get(i))
                    {
                        listpersonne.get(j).setConjoint(listpersonne.get(i));   //modification du conjoint 
                    }
                }
            }
        }
        //Pour les id société
       for(int i=0;i<idSAVEs.size();i++)
        {
            if (idSAVEs.get(i)!=null)
            {
                for(int j=0;j<listsociete.size();j++)  //On parcourt la liste a la recherche du meme id
                {
                    if(listsociete.get(j).getID()== idSAVEs.get(i))
                    {
                        listpersonne.get(i).setSociete(listsociete.get(j)); //modification de la societe 
                    }
                }
            }
        }
        
        //Affichage de la liste des personnes 
        for(int i=0;i<listpersonne.size();i++)
            System.out.println(listpersonne.get(i).toString(Presentation.COMPLET, Sens.NOM_PRENOMS));
        
        
    }
        
}
