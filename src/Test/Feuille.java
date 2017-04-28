package Test;

import java.util.ArrayList;
import java.util.Comparator;

public class Feuille {
    int id;
    int idNoeud;
    ArrayList<ArrayList<Integer>> valeurs;
    ArrayList<ArrayList<Parametre>> sortedValeurs;
    ArrayList<Integer> skyf;
    
    public Feuille(int id,int idNoeud, ArrayList<ArrayList<Integer>> valeur) {
        this.id = id;
        this.valeurs = valeur;
        this.idNoeud = idNoeud;
    }

    public Feuille(int id,int idNoeud) {
        this.id = id;
        valeurs = new ArrayList<>();
        this.idNoeud = idNoeud;
    }
    
    public void addValeur() {
        valeurs.add(new ArrayList<>());
    }
    
    public void addValeur(int i,int val) {
        valeurs.get(i).add(val);
    }
    
    public int getId() {
        return id;
    }
    
    public ArrayList<ArrayList<Integer>> getValeur() {
        return valeurs;
    }
    
    @Override
    public String toString() {
        System.out.println("Feuille "+id);
        for(ArrayList<Integer> val : valeurs)
        {
            for(Integer n : val)
                System.out.print(" "+n);
            System.out.println("----------------");
        }
        return "\n";
    }
    
    public String afficheSortedVal() {
        System.out.println("Feuille "+id);
        for(ArrayList<Parametre> val : sortedValeurs)
        {
            for(Parametre n : val)
                System.out.print(" "+n.val);
            System.out.println("----------------");
        }
        return "\n";
    }
    public void calculMin()
    {
        skyf = new ArrayList<>();
        transfer();
        for(int i = 0 ; i < sortedValeurs.size() ; i++)
        {
            int size = sortedValeurs.get(i).size();
            int indice = sortedValeurs.get(i).get(size - 1).getIndVal();
            if(sortedValeurs.get(i).get(size - 1).getVal() == sortedValeurs.get(i).get(size - 2).getVal())
                dominanceMin(sortedValeurs.get(i));
            
            if(! skyf.contains(new Integer(indice)))
                skyf.add(new Integer(indice));
        }
    }

    public ArrayList<ArrayList<Integer>> getValeurs() {
        return valeurs;
    }

    public ArrayList<Integer> getSkyf() {
        return skyf;
    }
    Parametre min(int ligne) {
        int min=0;
        for(int i = 0 ; i < valeurs.size() ; i++)
            if(sortedValeurs.get(min).get(ligne).getVal() > sortedValeurs.get(i).get(ligne).getVal())
                min = i;
        
        return sortedValeurs.get(min).get(ligne);
    }

    private void transfer() {
        sortedValeurs = new ArrayList<>();
        Comparator sorter = new Comparator<Parametre>() {
            @Override
            public int compare(Parametre t1, Parametre t2) {
                return t2.getVal() - t1.getVal();
            }
        };
        for(int j = 0 ; j < valeurs.size(); j++)
        {
            ArrayList<Integer> liste = valeurs.get(j);
            ArrayList<Parametre> sortedList = new ArrayList<>();
            for(int i = 0 ; i < liste.size() ; i++)
                sortedList.add(new Parametre(id,idNoeud, i, liste.get(i)));
            
            sortedList.sort(sorter);
            sortedValeurs.add(sortedList);
        }
    }

    private void dominanceMin(ArrayList<Parametre> liste) {
        int size = liste.size();
        int i = 0;
        int etat = 0;
        while( i < valeurs.size() && etat != 3)
        {
            if(valeurs.get(i).get(liste.get(size-1).getIndVal()) < valeurs.get(i).get(liste.get(size-2).getIndVal()))
                if(etat == 0)
                    etat  = 1 ;
                else if (etat != 1 )
                    etat = 3;
            if(valeurs.get(i).get(liste.get(size-1).getIndVal()) > valeurs.get(i).get(liste.get(size-2).getIndVal()))
                if(etat == 0)
                    etat  = 2 ;
                else if (etat != 2 )
                    etat = 3;
            i++;
        }
        switch(etat)
        {
            case 1 : 
                if(!skyf.contains(new Integer(liste.get(size-1).getIndVal())))
                    skyf.add(new Integer(liste.get(size-1).getIndVal()));
                break;
            case 2 : 
                if(!skyf.contains(new Integer(liste.get(size-2).getIndVal())))
                    skyf.add(new Integer(liste.get(size-2).getIndVal()));
                break;
                default:
                    if(!skyf.contains(new Integer(liste.get(size-1).getIndVal())))
                        skyf.add(new Integer(liste.get(size-1).getIndVal()));
                    if(!skyf.contains(new Integer(liste.get(size-2).getIndVal())))
                        skyf.add(new Integer(liste.get(size-2).getIndVal()));
        }
    }
    public void affiche(){
        for(Integer p : skyf)
            System.out.println(p);
    }
    ArrayList<Parametre> getLigne(int indValeur) {
        ArrayList<Parametre> p = new ArrayList<>();
        for(int i=0;i < valeurs.size();i++)
            p.add(new Parametre(id, idNoeud, indValeur, valeurs.get(i).get(indValeur)));
        return p;
    }
    public ArrayList<Integer> getL(int i) {
        ArrayList<Integer> ligne = new ArrayList<>();
        for(int j=0;j<valeurs.get(0).size();j++)
            if(i != j)
                switch(domine(i,j))
                {
                    case 1:if(!ligne.contains(i))
                        ligne.add(i);
                    break;
                    case 3:if(!ligne.contains(i))
                        ligne.add(i);
                    if(!ligne.contains(j))
                        ligne.add(j);
                    break;
                }
        return ligne;
    }
    private int domine(int i, int j) {
        int etat = 0;
        int k=0;
        while( k < valeurs.size() && etat != 3)
        {
            if(valeurs.get(k).get(i) > valeurs.get(k).get(j))
                if(etat == 0)
                    etat  = 1 ;
                else if (etat != 1 )
                    etat = 3 ;
            if(valeurs.get(k).get(i) < valeurs.get(k).get(j))
                if(etat == 0)
                    etat  = 2 ;
                else if (etat != 2 )
                    etat = 3 ;
            k++;
        }
        return etat;
    }
    
    void suppLigne(int i)
    {
        for(ArrayList<Integer> l : valeurs)
            l.remove(i);
    }
}
