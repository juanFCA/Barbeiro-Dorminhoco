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
public class Barbeiro extends Thread{
    static final int CADEIRAS = 5;//cadeiras de espera disponíveis
    static Semaforo cliente = new Semaforo(0);
    static Semaforo barbeiro = new Semaforo(0);
    static Semaforo semaforo = new Semaforo(1);
    static int esperando = 0;//clientes em espera
    static boolean cortando = false;
    static boolean dormindo = false;
    //declaração estática para acessar sem precisar instanciar a classe
    
    public void cortarCabelo(){
        System.out.println("O barbeiro está cortando!");
        cortando = true;//cortando o cabelo
        try {
            Thread.sleep(sleepTime());
            System.out.println("O barbeiro terminou o corte!");
            cortando = false;
            Thread.sleep(1000);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Barbeiro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void barbeiro(){
        if(esperando <= 0){
            System.out.println("O barbeiro dormiu!");
            dormindo = true;
            cliente.P();
        } else {
            semaforo.P();
            esperando--;
            barbeiro.V();
            semaforo.V();
            dormindo = false;
            cortarCabelo();
        }
    }
    
    /**
     *
     * @return
     */
    public static int sleepTime(){//gera um valor aleatorio para dar o tempo da thread dormir
        return (int) Math.floor(Math.random() * 6000);
    }
    
    @Override
    public void run(){
        while(true){
            barbeiro();
        }
    }
    
}
