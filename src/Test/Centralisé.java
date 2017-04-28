package Test;

import static Test.Distribué.min;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.management.timer.Timer;

public class Centralisé {

  //  public static void main(String[] args) throws FileNotFoundException {
       
        //System.out.println("MinSystem---------------");
       /* ArrayList<Parametre> minSyst = noeud.minUP;
        
        for(int l = 0 ; l < nbrc ; l++)
        {
            System.out.println(minSyst.get(l));
        }*/
        
//}

    /**
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
       
    
        ArrayList<Noeud> liste ;
       Noeud noeud;   
        Scanner sc = new Scanner (new File("a510000.txt"));
        
        String line = sc.nextLine();
        String [] var = line.split(" ");
        //System.out.println(line);
     
     
        int nbrligne = Integer.parseInt(var[0]);
        int nbrc = Integer.parseInt(var[1]);        
        int mc [][]= new int [nbrligne][nbrc];
         timer.time=System.currentTimeMillis();
        for (int i=0;i<nbrligne;i++)
        {
            String [] ligne = sc.nextLine().split(" ");
            for (int j=0;j<nbrc;j++)
                mc[i][j]=Integer.parseInt(ligne[j]);
        }
            noeud = new Noeud(1);
        
        noeud.feuilles.add(new Feuille(0,0));
        for(int i = 0 ; i < nbrc ; i++)
            noeud.feuilles.get(0).addValeur();
        for(int i = 0 ; i  < nbrligne ; i++)
        {
            for(int j = 0 ; j < nbrc ; j++)
                noeud.feuilles.get(0).addValeur(j, mc[i][j]);
        }
       
     timer.time=System.currentTimeMillis()-timer.time;
        System.out.println("");
        noeud.calculMin();
        noeud.afficheSkyFGlobal();
        noeud.afficheMUp();
        noeud.afficheMxUp();
    
    
    }
    
    
}
