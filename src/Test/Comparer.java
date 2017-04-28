package Test;

public class Comparer {
    String idNoeud;
    int idFeuille,valeur;

    public String getIdNoeud() {
        return idNoeud;
    }

    public int getIdFeuille() {
        return idFeuille;
    }

    public int getValeur() {
        return valeur;
    }
    
    public Comparer(String idNoeud, int idFeuille, int valeur) {
        this.idNoeud = idNoeud;
        this.idFeuille = idFeuille;
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return "{" + "idNoeud=" + idNoeud + ", idFeuille=" + idFeuille + ", valeur=" + valeur + "}\n";
    }
    
    
}
