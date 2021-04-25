/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.FrmServerChat;
import java.net.*;

public class ConnectorServer extends Thread {
    
    private Socket socket;
    private ServerSocket serverSocket;
    private InputStreamReader entradaSucket;
    private DataOutputStream salida;
    private BufferedReader entrada;
    final int puerto= 4314;
    
    
    public ConnectorServer(String nombre) {
        
        super(nombre);
          
    }
    
    public void sendMssg(String mssg){
        
        try {
            salida.writeUTF(mssg + "\n");
        } catch (IOException ex) {
            Logger.getLogger(ConnectorServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void run(){
        String text;
        try {
        this.serverSocket = new ServerSocket(puerto);
        this.socket = serverSocket.accept();
        
        //Creacion de entrada de datos para lectura de mensajes
            this.entradaSucket = new InputStreamReader(socket.getInputStream());   
            this.entrada = new BufferedReader(entradaSucket);
            
            //Creacion de salida de datos para el envio de mensajes
            this.salida = new DataOutputStream(socket.getOutputStream());
            
            while(true){
                text = this.entrada.readLine();
                System.out.println(text);
                FrmServerChat.txtAreaChat.setText(FrmServerChat.txtAreaChat.getText()+"\n"+ text);
           
            }
            
        } catch (IOException ex) {
            System.out.println("Algun tipo de error ha ocurrido");
        }
        
    }
    
    public String readMssg(){
        
        try {
            return entrada.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ConnectorServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    public void disconnect(){
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectorServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectorServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
