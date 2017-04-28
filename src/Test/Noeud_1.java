/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import com.sun.org.apache.xerces.internal.impl.dv.xs.IntegerDV;
import java.util.ArrayList;

public class Noeud_1 {
    String id;
    ArrayList<Integer> Voisin;

    public Noeud_1(String id) {
        this.id = id;
        Voisin = new ArrayList<>();
    }
    
   public void addVoisin(Integer v)
    {
        Voisin.add(v);
    }
    
    public int size()
    {
        return Voisin.size();
    }

    @Override
    public String toString() {
        System.out.println("Feuilles de \n "+id);
        for(Integer n : Voisin)
            System.out.print(" "+n);
        return "";
    }
    
    
}
