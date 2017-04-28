package Test;

import java.util.ArrayList;

public class Noeud {
    int id;
    ArrayList<Feuille> feuilles;
    ArrayList<ArrayList<Parametre>> skyfGlobal ;
    ArrayList<Parametre> minUP,maxUP;
    public Noeud(int id) {
        this.id = id;
        feuilles = new ArrayList<>();
        skyfGlobal = new ArrayList<>();
    }
    
   public void addFeuille(Feuille f)
    {
        f.calculMin();
        feuilles.add(f);
    }
    
    public int size()
    {
        return feuilles.size();
    }

    @Override
    public String toString() {
        System.out.println("\n Noeud "+id);
        for(Feuille f : feuilles)
            System.out.print(f);
        return "\n---------\n";
    }
    public void calculMin()
    {
        skyfGlobal = new ArrayList<>();
        for(int i = 0 ; i < feuilles.size() ; i++)
        {
            feuilles.get(i).calculMin();
        }
        
        minUP = new ArrayList<>();
        maxUP = new ArrayList<>();
        
        int j=0;
        for(Feuille f : feuilles)
        {
           
           for(Integer ind : f.getSkyf())
            {    
               ArrayList<Parametre> skyf = new ArrayList<>();
               
               for(int i = 0 ; i < f.getValeurs().size() ; i++)
               {
                   int val = f.getValeurs().get(i).get(ind);
                   Parametre p = new Parametre(f.getId(),id, ind, val);
                   skyf.add(p);
               }
               skyfGlobal.add(skyf);
            }
           
       }
        //afficheSkyFGlobal();
        for(int i = 0 ; i < skyfGlobal.get(0).size() ; i++)
        {
            minUP.add(min(i));
            maxUP.add(max(i));
        }
    }
    void afficheMinUp()
    {
        System.out.println("MinUp Noeud "+id+" :---------------------------");

        for(Parametre p : minUP)
            System.out.println(p);
        System.out.println("-----------------------------------");
    }
     void afficheMUp()
    {
        System.out.println("minsyst  :---------------------------");

        for(Parametre p : minUP)
            System.out.println(p);
        System.out.println("-----------------------------------");
    }
    void afficheMaxUp()
    {
        System.out.println("MaxUp Noeud "+id+":---------------------------");

        for(Parametre p : maxUP)
            System.out.println(p);
        System.out.println("-----------------------------------");
    }
    void afficheMxUp()
    {
        System.out.println("maxsyst  :---------------------------");

        for(Parametre p : maxUP)
            System.out.println(p);
        System.out.println("-----------------------------------");
    }
    void afficheSkyFGlobal()
    {
        System.out.println("SkyGlobal Noeud "+id+" :---------------------");
        for(ArrayList<Parametre> param : skyfGlobal)
        {
           for(Parametre p : param)
               System.out.print(p);
           System.out.println("");
        }
    }
    Parametre min(int ligne) {
        int min=0;
        for(int i = 0 ; i < skyfGlobal.size() ; i++)
            if(skyfGlobal.get(min).get(ligne).getVal() > skyfGlobal.get(i).get(ligne).getVal())
                min = i;
        
        return skyfGlobal.get(min).get(ligne);
    }    
    Parametre max(int ligne) {
        int max=0;
        for(int i = 0 ; i < skyfGlobal.size() ; i++)
            if(skyfGlobal.get(max).get(ligne).getVal() < skyfGlobal.get(i).get(ligne).getVal())
                max = i;
        
        return skyfGlobal.get(max).get(ligne);
    }

    ArrayList<Parametre> getLigne(int indFeuille, int indValeur) {
        ArrayList<Parametre> p = feuilles.get(indFeuille).getLigne(indValeur);
        return p;
    }
    
    public void compare(ArrayList<Parametre> p)
    {
        int etat = 0;
        int k=0;
        while( k < p.size() && etat != 3)
        {
            if(minUP.get(k).val < p.get(k).val)
                if(etat == 0)
                    etat  = 1 ;
                else if (etat != 1 )
                    etat = 3 ;
            if(minUP.get(k).val > p.get(k).val)
                if(etat == 0)
                    etat  = 2 ;
                else if (etat != 2 )
                    etat = 3 ;
            k++;
        }
        switch(etat)
        {
            case 1: System.out.println("NON"); break;
            case 2: System.out.println("Contradiction"+(k-1)); break;
            case 3: System.out.println("OUI"); 
                    afficheMinUp();
                    break;
        }
        
    }

    int getFeuilleEcarte(ArrayList<Parametre> minSyst) {
        int nbf = 0;
        for(int i = 0;i < feuilles.size(); i++)
        {
            boolean trouve = false;
            for(int j=0; j < minSyst.size() ; j++)
                if(feuilles.get(i).id == minSyst.get(j).indFeuille && minSyst.get(j).indNeoud == id)
                    trouve = true;
            if(!trouve)
            {
                nbf ++;
                System.out.print(feuilles.get(i).id+"\t");
            }
        }
        return nbf;
    }
}