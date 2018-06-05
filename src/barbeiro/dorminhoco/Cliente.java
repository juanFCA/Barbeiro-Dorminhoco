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
public class Cliente extends Thread{
    
    public void clientes(){
        Barbeiro.semaforo.P();
        
        if(Barbeiro.esperando < Barbeiro.CADEIRAS){
            Barbeiro.esperando++; //cliente se senta na cadeira para esperar sua vez
            Barbeiro.cliente.V();
            Barbeiro.semaforo.V();
        } else {
            Barbeiro.semaforo.V();
            System.out.println("Um cliente foi embora!");
        }
    }
    
    @Override
    public void run(){
        while(true){
            clientes();
            System.out.println("Um cliente chegou!");
            try {
                Thread.sleep(Barbeiro.sleepTime());
            } catch (InterruptedException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
