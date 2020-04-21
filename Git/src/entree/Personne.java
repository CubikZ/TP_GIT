package entree;


/**
 *
 * @author loann
 */

import java.util.ArrayList;

public class Personne {
    String nom;
    ArrayList<String> prenoms = new ArrayList<>();
    Genre genre;
    Personne conjoint=null;
    Societe societe=null;
    String fonction;
    
    public Personne (String nom2, ArrayList<String> prenoms2,Genre genre2, Personne conjoint2, Societe societe2, String fonction2)
    {
        nom=nom2;
        prenoms.addAll(prenoms2);
        genre=genre2;
        conjoint=conjoint2;
        
        societe=societe2;
        fonction=fonction2;
    }
    //Accesseurs
    public String getNom()
    { return nom ; }
    
    public String getPrenoms()
    { 
        String resultat="";
        for (int i=0;i<prenoms.size();i++)
        {
            resultat=resultat+prenoms.get(i)+", ";
        }
        if (resultat=="")
        {
            return "Aucun prenom";
        }
        else
            return resultat;
    }
    public String getPrenomsINIT()
    {
        String resultat="";
        String prenom;
        for (int i=0;i<prenoms.size();i++)
        {
            prenom=prenoms.get(i);
            resultat=resultat+prenom.substring(0,1)+". ";
        }
        if (resultat=="")
        {
            return "Aucun prenom";
        }
        else
            return resultat;     
    }
    
    public Genre getGenre()
    { 
        return genre ; 
    }
    public String getGenreINIT()
    {
        if (genre.equals(Genre.FEMME))
            return "Mme.";
        if (genre.equals(Genre.HOMME))
            return "M.";
        else return "erreur";
        
    }
    public Personne getConjoint()
    { return conjoint ; }
    public Societe getSociete()
    { return societe ; }
    public String getFonction()
    { return fonction ; }
    //Mutateurs
    public void setNom(String nom2)
    {
        nom=nom2;
    }
    public void setPrenoms(ArrayList<String> prenoms2)
    {
        prenoms.clear();
        prenoms.addAll(prenoms2);
    }
    public void setGenre(Genre genre2)
    {
        genre=genre2;
    }
    public void setConjoint(Personne conjoint2)
    {
        conjoint=conjoint2;
    }
    public void setSociete(Societe societe2)
    {
        societe=societe2;
    }
    public void setFonction(String fonction2)
    {
        fonction=fonction2;
    }
    
    public String toString(Presentation presentation, Sens sens)
    {
        switch (presentation) {
        //Uniquement Initials prenom + nom
            case ABREGE:
                
                if (sens.equals(Sens.NOM_PRENOMS))
                {
                    return getNom()+" "+getPrenomsINIT();
                }
                else if (sens.equals(Sens.PRENOMS_NOM))
                {
                    return getPrenomsINIT()+" "+getNom();
                }   
                else return "Erreur de sens";
                
        // titre abrégé, 1st prenom, initials des autres prenoms + nom + (nom Societe si existe)
            case SIMPLE:
                if (sens.equals(Sens.NOM_PRENOMS))
                {
                    if (societe==null)
                    {
                        return getGenreINIT()+" "+getNom()+" "+getPrenomsINIT();
                    }
                    else
                    {
                        return getGenreINIT()+" "+getNom()+" "+getPrenomsINIT()+" "+getSociete();
                    
                    }
                }
                
                if (sens.equals(Sens.PRENOMS_NOM))
                {
                    if (societe==null)
                    {
                        return getGenreINIT()+" "+getPrenomsINIT()+" "+getNom();
                    }
                    else
                    {
                        return getGenreINIT()+" "+getPrenomsINIT()+" "+getNom()+" "+getSociete();
                    }
                    
                }
                else return "Erreur de sens";
        //Affiche tout
            case COMPLET:
                if (sens.equals(Sens.NOM_PRENOMS))
                {
                    return  getGenreINIT()+" "+getNom()+" "+getPrenoms()+"\n"
                            +"   -Societe  : "+getSociete()+"\n"
                            +"   -Fonction : "+getFonction()+"\n"
                            +"   -Conjoint : "+getConjoint();
                }
                
                if (sens.equals(Sens.PRENOMS_NOM))
                {
                    return getGenreINIT()+" "+getPrenoms()+" "+getNom()+"\n"
                           +"   -Societe  : "+getSociete()+"\n"
                           +"   -Fonction : "+getFonction()+"\n"
                           +"   -Conjoint : "+getConjoint();               
                }
                else return "Erreur de sens";
            default:
                return "Erreur presentation";
        }
        
    }
    
    
}
