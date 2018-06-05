/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbeiro.dorminhoco;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonas
 */
public class Semaforo {
    private int count = 0;
    
    public Semaforo(int valorIni){
        count = valorIni;
    }
    
    public synchronized void P(){//operação down
        while(count <= 0){//espera para poder entrar na zona crítica
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Semaforo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        count--;//processo pode entrar na zona crítica
    }
    
    public synchronized void V(){//operação up
        count++;
        notify();//acorda o processo
    }
    
}
