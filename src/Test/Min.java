package Test;

public class Min {
    String idNoeud;
    int idFeuille;
    Parametre valeur;

    public String getIdNoeud() {
        return idNoeud;
    }

    public int getIdFeuille() {
        return idFeuille;
    }

    public Parametre getValeur() {
        return valeur;
    }
    
    public Min(String idNoeud, int idFeuille, Parametre valeur) {
        this.idNoeud = idNoeud;
        this.idFeuille = idFeuille;
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return "Min{" + "idNoeud=" + idNoeud + ", idFeuille=" + idFeuille + ", valeur=" + valeur + '}';
    }
    
}
