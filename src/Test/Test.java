package Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
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
        System.out.println(liste.get(0).feuilles.get(0));
        liste.get(0).feuilles.get(0).suppLigne(0);
        System.out.println("-----------");
        System.out.println(liste.get(0).feuilles.get(0));
    }
}
