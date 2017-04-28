package Test;

public class Parametre {
    int indVal,val,indFeuille,indNeoud;

    public Parametre(int indFeuille,int indNeoud,int indVal, int val) {
        this.indVal = indVal;
        this.val = val;
        this.indFeuille = indFeuille;
        this.indNeoud = indNeoud;
    }

    
    public Parametre(int ind, int val) {
        this.indVal = ind;
        this.val = val;
    }

    public Parametre(int indFeuille,int indVal, int val) {
        this.indVal = indVal;
        this.val = val;
        this.indFeuille = indFeuille;
    }

    public int getIndFeuille() {
        return indFeuille;
    }
    
    public int getIndVal() {
        return indVal;
    }

    public int getVal() {
        return val;
    }

    public void setIndVal(int indVal) {
        this.indVal = indVal;
    }

    public int getIndNeoud() {
        return indNeoud;
    }

    public void setIndNeoud(int indNeoud) {
        this.indNeoud = indNeoud;
    }

    public void setIndFeuille(int indFeuille) {
        this.indFeuille = indFeuille;
    }
    
    @Override
    public String toString() {
        return "{ Indice Noeud "+indNeoud+" Indice Feuille = " +indFeuille+" , Indice Valeur = "+ indVal + " , Valeur = " + val + '}';
    }
    
}
