package Test;

import java.io.FileNotFoundException;

public class timer {
static long time;
    public static void main(String[] args) throws FileNotFoundException {
        //Centralisé.main(args);
        Distribué.main(args);
        System.out.println("temps: "+time);
    }

}

    

