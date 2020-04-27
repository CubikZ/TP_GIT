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

//Pour trier
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;



public class Carnet {
    private ArrayList<Entree> listEntree; // type Entree = just utile pour le toString 
    private ArrayList<Entree> listSelectionnees ; // Pour les afficher après la selection
    private ArrayList<Personne> listPersonne; //Utile pour les manipulations
    private ArrayList<Societe> listSociete;
    
    //Constructeur
    public Carnet(Entree entree, Entree selectionnees){
        listEntree=null;
        listSelectionnees=null;
    }
    public Carnet()
    {
        listEntree  = new ArrayList<>();
        listSelectionnees = new ArrayList<>();
        listPersonne = new ArrayList<>();
        listSociete = new ArrayList<>();
    }
    
    //Methode Lecture de Fichier
    public void lectureFichier(String lienFichier) throws IOException{
       
        //Fichier
        File file = new File(lienFichier); 
        BufferedReader in = null ;
        
        //Separateur
        String SEPARATEUR = ";";
        String line;
        
        int ID = 0; //ID de la ligne
        int ID_conjoint = 0; //ID du conjoint
        
        //variable pour SOCIETE
        String raisonSociale;
        int ID_societe;
        ArrayList<Integer> idSAVEs = new ArrayList<>(); //Sauvegarde les id des societe que les personne ont
        
        //variable pour PERSONNE
        String nom=null;
        ArrayList<String> listprenom = new ArrayList<>(); //liste des prenoms
        Genre genre=null;
        Personne conjoint = null;
        Societe societe = null;
        String fonction=null;
        
        //variable conjoint
        ArrayList<Integer> idSAVEc=new ArrayList<>(); //Sauvegarde les id des conjoints que les personne ont
        
        System.out.println("Début du scan du fichier...");
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
            
            
            //affichage des ligne lue
            //System.out.println(line);
            
            //Separation de la ligne en champ separer par ";"
            String mots[] = line.split(SEPARATEUR);
                
            if(mots.length >=3 && !line.equals("")){
                    
                
                switch(mots[1]){
                        
                    case "SOCIETE" :
                            ID = Integer.parseInt(mots[0]); //ID de la ligne
                            raisonSociale = mots[2];
                            listSociete.add(new Societe(ID,raisonSociale));
                            
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
                        
                        //creation d' objet dans listPersonne
                        listPersonne.add(new Personne(ID,nom, listprenom,genre, conjoint,societe,fonction));
                     
                        
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
                for(int j=0;j<listPersonne.size();j++)  //On parcourt la liste a la recherche du meme id
                {
                    if(listPersonne.get(j).getID()== idSAVEc.get(i))
                    {
                        listPersonne.get(i).setConjoint(listPersonne.get(j));   //modification du conjoint 
                    }
                }
            }
        }
        //Pour les id société
       for(int i=0;i<idSAVEs.size();i++)
        {
            if (idSAVEs.get(i)!=null)
            {
                for(int j=0;j<listSociete.size();j++)  //On parcourt la liste a la recherche du meme id
                {
                    if(listSociete.get(j).getID()== idSAVEs.get(i))
                    {
                        listPersonne.get(i).setSociete(listSociete.get(j)); //modification de la societe 
                    }
                }
            }
        }
       System.out.println("Scan du fichier terminé");
       ajoutEntree(listPersonne,listSociete);
        
        
        
        
        
    }
    
    private void ajoutEntree(ArrayList<Personne> listPersonne,ArrayList<Societe> listSociete)
    {
        System.out.println("Création de la listeEntree et ajout des entitées");
        //Ajout des société a listEntree
        for(int i=0;i<listSociete.size();i++)
            listEntree.add(listSociete.get(i));
        //Affichage de la liste des personnes 
        for(int i=0;i<listPersonne.size();i++)
            listEntree.add(listPersonne.get(i));
        System.out.println("Création et ajout réussie");
        
        
    }
    
    public void selection(String nom) //avec le nom
    {
        boolean resultat=false;
        for(int i=0;i<listPersonne.size();i++)
        {
            if(listPersonne.get(i).getNom().equals(nom) || listPersonne.get(i).getPrenoms().contains(nom)) //On regarde si il contient le string
            {
                listSelectionnees.add(listPersonne.get(i));
                resultat=true;
                System.out.println(listPersonne.get(i).getNom()+" ajoutee a la selection");
            }
        }
        if(!resultat)
            System.out.println("Aucun nom / prenom trouver contenant "+nom+". Selection non reussie");
        
    }
    
    public void selection(Entree selection)
    {
        if(listSelectionnees.add(selection))
            System.out.println("Ajout reussie de l'objet a la selection");
        else
            System.out.println("Erreur, selection non reussie");
        
    }
    public void selection(ArrayList<Entree> selection)
    {
        if(listSelectionnees.addAll(selection))
            System.out.println("Ajout reussie de la liste a la selection");
        else
            System.out.println("Erreur, selection non reussie");
    }
    public void deselection()
    {
        listSelectionnees.clear();
    }
    public ArrayList<Entree> recherche(String recherche) //Recherche Personne
    {
        ArrayList<Entree> listRecherche = new ArrayList<>();
        //System.out.println("la(Les) personne contenant le caractère "+lettre+" sont :");
        for(int i=0;i<listPersonne.size();i++) //On regarde pour les personne
        {
            
            if(listPersonne.get(i).recherche(recherche)) //Pour pas qu'il traite les société
            {
                listRecherche.add(listPersonne.get(i));
            }
            
        }
        for (int i=0;i<listSociete.size();i++)  //On regarde pour les societe
        {
            if(listSociete.get(i).recherche(recherche))
            {
                listRecherche.add(listSociete.get(i));
            }
        }
        return listRecherche;
        
        
    }

    public void afficher(Ordre ordre,Presentation presentation, Sens sens)
    {
        switch(ordre)
        {
            case CROISSANT:
                //Trier par ordre croissant ...
                System.out.println("\n Trie par ordre Croissant : ");
        
                // ... la liste personne par noms
                System.out.println("\nListe des Personnes : ");
                Collections.sort(listPersonne, ComparatorNom);
                //Affichage de la Liste Personne
                for (int i = 0; i < listPersonne.size(); i++){
                    System.out.println(listPersonne.get(i).toString(presentation, sens)); 
                }
                
                //... la liste de Societe par Raison Social
                System.out.println("\nListe des Societes : ");
                Collections.sort(listSociete, ComparatorRaisonSocial);
                //Affichage de la Liste Societe
                for (int i = 0; i < listSociete.size(); i++){
                    System.out.println(listSociete.get(i).toString(presentation, sens)); 
                }
                
                break;
                
            case DECROISSANT:
                //Trier par ordre decroissant ...
                System.out.println("\nTrie par ordre Decroissant : ");
                //... la liste des personnes par nom
                System.out.println("\nListe des Personnes : ");
                Collections.sort(listPersonne, ComparatorNom); //trie par ordre croissant
                Collections.reverse(listPersonne); //inverse la liste
                //Affichage de la Liste Personne
                for (int i = 0; i < listPersonne.size(); i++){
                    System.out.println(listPersonne.get(i).toString(presentation, sens)); 
                }
        
                //... la liste de Societe par Raison Social
                System.out.println("\nListe des Societes : ");
                Collections.sort(listSociete, ComparatorRaisonSocial); //Trie par ordre croissant
                Collections.reverse(listSociete);   //Inverse la liste
                //Affichage de la Liste Societe
                for (int i = 0; i < listSociete.size(); i++){
                    System.out.println(listSociete.get(i).toString(presentation, sens)); 
                }
                
                break;
        }     
        
    }
    
    
    public void afficherSelection(Ordre ordre,Presentation presentation, Sens sens)
    {
        //Collections.sort(listEntree);
        //ArrayList<Entree> listePersonne2= new ArrayList<>();
        //ArrayList<Entree> listeSociete2= new ArrayList<>();
        //Impossible de convertir un type Entree en  type Personne ou Societe. Comment faire pour trier ?
        for(int i=0;i<listSelectionnees.size();i++)
        {
            System.out.println(listSelectionnees.get(i).toString(presentation, sens));
        }
    }
    
    /*
     * Comparator pour le tri des personne par nom 
     */
    public static Comparator<Personne> ComparatorNom = new Comparator<Personne>() {
      
        @Override
        public int compare(Personne p1, Personne p2) {
            return p1.getNom().compareTo(p2.getNom());
        }
    };
    
    /*
     * Comparator pour le tri des societe par raison Sociale
     */
    
    public static Comparator<Societe> ComparatorRaisonSocial = new Comparator<Societe>() {
        @Override
        public int compare(Societe s1, Societe s2){
            return s1.getRaisonSociale().compareTo(s2.getRaisonSociale());
        }
    };
    public ArrayList<Personne> getListPersonne()
    {
        return listPersonne;
    }
    public ArrayList<Societe> getListSociete()
    {
        return listSociete;
    }
    public ArrayList<Entree> getListEntree()
    {
        return listEntree;
    }
    
    
    //public static Comparator<Entree> ComparatorNomOuRaisonSociale = new Comparator<Entree>() {
    //    public int compare(Personne, Societe s2){
     //       return s1.getRaisonSociale().compareTo(s2.getRaisonSociale());
    //    }
}
