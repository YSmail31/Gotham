package Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Distribué {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Noeud> liste ;
        liste = new ArrayList<>();
        Scanner sc = new Scanner (new File("e3100.txt")); 
        String line = sc.nextLine();
        String [] var = line.split(" ");

        int nbrligne = Integer.parseInt(var[0]);
        int nbrc = Integer.parseInt(var[1]);
        int ligneParFeuille = 4;
        int nbrFeuille = 3;
        
        int mc [][]= new int [nbrligne][nbrc];
        timer.time=System.currentTimeMillis();
        for (int i=0;i<nbrligne;i++)
        {
            String [] ligne = sc.nextLine().split(" ");
            for (int j=0;j<nbrc;j++)
                mc[i][j]=Integer.parseInt(ligne[j]);
        }
        int i;
        for(i=0;i<nbrligne/(nbrFeuille*ligneParFeuille);i++)
            liste.add(new Noeud(i));
        
        if(nbrligne%(nbrFeuille*ligneParFeuille) != 0)
            liste.add(new Noeud(i));
        int n,j,k;
        i=0 ;n =0;j = 0;k = 0;
        Feuille feuille = new Feuille(9999,1);
        while(i<nbrligne)
        {
            
            Noeud noeud = liste.get(n);            
            
            if(k+1 == 1)
            {
                liste.get(n).feuilles.add(new Feuille(j,noeud.id));
                feuille = liste.get(n).feuilles.get(j);
                for(int l = 0 ; l < nbrc ; l++)
                    feuille.addValeur();
            }
            
            for(int m = 0; m < nbrc ; m++)
                feuille.addValeur(m, mc[i][m]);

            k = (k+1)%ligneParFeuille;
            
            if(k == 0)
            {
                j = (j+1)%nbrFeuille;
                if(j == 0)
                {
                    for(int m=0; m < nbrFeuille ;m++)
                    {
                        liste.get(n).feuilles.get(m).calculMin();
                    }
                    n++;
                }
            }
            i++;
        }
        
        for(Noeud noeud : liste)
        {
            //System.out.println(noeud);
            noeud.calculMin();
            noeud.afficheMinUp();
            //noeud.afficheMaxUp();
        }
        timer.time=System.currentTimeMillis()-timer.time;
         System.out.println("");
        ArrayList<Parametre> minSyst = new ArrayList<>();
        ArrayList<Parametre> maxSyst = new ArrayList<>();
        ArrayList<ArrayList<Parametre>> ListeMax = new ArrayList<>();
        System.out.println("Min System -------------------------");
        for(int l = 0 ; l < nbrc ; l++)
        {
            minSyst.add(min(l,liste));
            Parametre p = minSyst.get(l);
            ListeMax.add(getLigne(p, liste));
            System.out.println(p);
        }
       
       
        System.out.println("Max System -------------------------");
        maxSyst = calculeMaxSyst(ListeMax);
        for(Parametre p : maxSyst)
        {
            System.out.println(p);
        }
            
        System.out.println("les premiers points skyline ");
        ArrayList<Integer> liste1 ;
        liste1 = new ArrayList<>();
       
        for(Parametre p: minSyst)
        { if(!liste1.contains(p.indNeoud))
        {
            liste1.add(p.indNeoud);
            System.out.println("UP"+p.indNeoud);
        }
        }
        
        System.out.println("NBrMINPI > MAXSYS");
        for(Parametre p : minSyst)
        {
            System.out.println("NOEUD "+p.indNeoud);
            for(Integer l : liste.get(p.indNeoud).feuilles.get(p.indFeuille).getL(p.indVal))
                System.out.print(l+"\t");
            System.out.println("");
            System.out.println("------------"); 
        }
        System.out.println("NBRUP > MAXSYS");
        for(Noeud noeud: liste)
        {
            System.out.print("Noeud "+noeud.id+" : ");
            noeud.compare(maxSyst);
            System.out.println("--------------------");
        }
        System.out.println("UP ECARTE");
        int nb = minSyst.size();
        ArrayList<Integer> upEcart = new ArrayList<>();
        ArrayList<Integer> upNonEcart = new ArrayList<>();
        for(Noeud noeud : liste)
        {
            if(nb == 0)
            {
                upEcart.add(noeud.id);
                System.out.print(noeud.id+"\t");
            }
            else
            {
                boolean trouve = false;
                for(int l = 0 ; l < minSyst.size() ; l++)
                    if(minSyst.get(l).indNeoud == noeud.id)
                    {
                        nb--;
                        trouve = true;
                    }
                
                if(!trouve)
                {
                    upEcart.add(noeud.id);
                    System.out.print(noeud.id+"\t");
                }
                else
                {
                    upNonEcart.add(noeud.id);
                }
                    
            }
        }
        System.out.println("");
        System.out.println("Nbr up ecarte = "+upEcart.size());
        System.out.println("Feuille ECARTE");
        int nbf = 0;
        for(Integer indUP:upEcart)
        {
            System.out.print("UP"+indUP+" :\t");
            for(Feuille f : liste.get(indUP).feuilles)
            {
                nbf++;
                System.out.print(f.id+"\t");
            }
            System.out.println("");
        }
        for(Integer indUP : upNonEcart)
        {
            System.out.print("UP"+indUP+" :\t");
            nbf += liste.get(indUP).getFeuilleEcarte(minSyst);
            System.out.println("");
        }
        System.out.println("Nbr feuille ecarte = "+nbf);
        
        //------------------------------------------------------------------
        for(Parametre p : minSyst)
        {
            liste.get(p.indNeoud).feuilles.get(p.indFeuille).suppLigne(p.indVal);            
        }
        
        for(Noeud noeud : liste)
        {
            //System.out.println(noeud);
            noeud.calculMin();
            noeud.afficheMinUp();
            //System.exit(0);
            //noeud.afficheMaxUp();
        }
        timer.time=System.currentTimeMillis()-timer.time;
         System.out.println("");
        minSyst = new ArrayList<>();
        maxSyst = new ArrayList<>();
        ListeMax = new ArrayList<>();
        System.out.println("Min System -------------------------");
        for(int l = 0 ; l < nbrc ; l++)
        {
            minSyst.add(min(l,liste));
            Parametre p = minSyst.get(l);
            ListeMax.add(getLigne(p, liste));
            System.out.println(p);
        }
       
       
        System.out.println("Max System -------------------------");
        maxSyst = calculeMaxSyst(ListeMax);
        for(Parametre p : maxSyst)
        {
            System.out.println(p);
        }
            
        System.out.println("les premiers points skyline ");
        liste1 = new ArrayList<>();
       
        for(Parametre p: minSyst)
        { if(!liste1.contains(p.indNeoud))
        {
            liste1.add(p.indNeoud);
            System.out.println("UP"+p.indNeoud);
        }
        }
        
        System.out.println("NBrMINPI > MAXSYS");
        for(Parametre p : minSyst)
        {
            System.out.println("NOEUD "+p.indNeoud);
            for(Integer l : liste.get(p.indNeoud).feuilles.get(p.indFeuille).getL(p.indVal))
                System.out.print(l+"\t");
            System.out.println("");
            System.out.println("------------"); 
        }
        System.out.println("NBRUP > MAXSYS");
        for(Noeud noeud: liste)
        {
            System.out.print("Noeud "+noeud.id+" : ");
            noeud.compare(maxSyst);
            System.out.println("--------------------");
        }
        System.out.println("UP ECARTE");
        nb = minSyst.size();
        upEcart.clear();
        for(Noeud noeud : liste)
        {
            if(nb == 0)
            {
                upEcart.add(noeud.id);
                System.out.print(noeud.id+"\t");
            }
            else
            {
                boolean trouve = false;
                for(int l = 0 ; l < minSyst.size() ; l++)
                    if(minSyst.get(l).indNeoud == noeud.id)
                    {
                        nb--;
                        trouve = true;
                    }
                
                if(!trouve)
                {
                    if(!upNonEcart.contains(noeud.id))
                    {
                        upEcart.add(noeud.id);
                        System.out.print(noeud.id+"\t");
                    }
                }
                else
                {
                    if(!upNonEcart.contains(noeud.id))
                        upNonEcart.add(noeud.id);
                }
                    
            }
        }
        System.out.println("");
        System.out.println("UP NON ECARTE");
        for(Integer in : upNonEcart)
            System.out.print(in+"\t");
        System.out.println("");
        System.out.println("Nbr up ecarte = "+upEcart.size());
        System.out.println("Feuille ECARTE");
        nbf = 0;
        for(Integer indUP:upEcart)
        {
            System.out.print("UP"+indUP+" :\t");
            for(Feuille f : liste.get(indUP).feuilles)
            {
                nbf++;
                System.out.print(f.id+"\t");
            }
            System.out.println("");
        }
        for(Integer indUP : upNonEcart)
        {
            System.out.print("UP"+indUP+" :\t");
            nbf += liste.get(indUP).getFeuilleEcarte(minSyst);
            System.out.println("");
        }
        System.out.println("Nbr feuille ecarte = "+nbf);
    }
    static Parametre min(int ligne,ArrayList<Noeud> liste) {
          
        int min=0;
        for(int i = 0 ; i < liste.size() ; i++)
            if(liste.get(min).minUP.get(ligne).getVal() > liste.get(i).minUP.get(ligne).getVal())
                min = i;
         
        return liste.get(min).minUP.get(ligne);
        
        
    }
    static Parametre max(int ligne,ArrayList<Noeud> liste) {
        int max=0;
        for(int i = 0 ; i < liste.size() ; i++)
            if(liste.get(max).maxUP.get(ligne).getVal() > liste.get(i).maxUP.get(ligne).getVal())
                max = i;
        
        return liste.get(max).maxUP.get(ligne);
    }    
    private static ArrayList<Parametre> getLigne(Parametre p, ArrayList<Noeud> liste) {
        int indNoeud = p.getIndNeoud();
        int indFeuille = p.getIndFeuille();
        int indValeur = p.getIndVal();
        
        return liste.get(indNoeud).getLigne(indFeuille,indValeur);
        
       
    }
    private static ArrayList<Parametre> calculeMaxSyst(ArrayList<ArrayList<Parametre>> ListeMax) {
        ArrayList<Parametre> maxSyst = new ArrayList<>();
        int max=0;
        
        for(int i = 0 ; i < ListeMax.get(0).size() ; i++)
        {
            for(int j = 0 ; j < ListeMax.size() ; j++)
            if(ListeMax.get(max).get(i).getVal() < ListeMax.get(j).get(i).getVal())
                max = j;
            maxSyst.add(ListeMax.get(max).get(i));
        }
        
        return maxSyst;
    }    
}
