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
import javax.swing.JOptionPane;
import view.FrmClientChat;

public class ConnectorClient extends Thread{
    
    private Socket socket;
    private ServerSocket clientSocket;
    private InputStreamReader entradaSucket;
    private DataOutputStream salida;
    private BufferedReader entrada;
    final int puerto= 4314;
    

    public ConnectorClient() {
        try {
            //Estableciendo el puerto y la conexion
            clientSocket = new ServerSocket(puerto);
            socket = clientSocket.accept();
            
            //Creacion de entrada de datos para lectura de mensajes
            entradaSucket = new InputStreamReader(socket.getInputStream());   
            entrada = new BufferedReader(entradaSucket);
            
            //Creacion de salida de datos para el envio de mensajes
            salida = new DataOutputStream(socket.getOutputStream());
            
        } catch (IOException ex) {
            Logger.getLogger(ConnectorClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ConnectorClient(String ip) {
        
        try {
            //Estableciendo el puerto y la conexion
            socket = new Socket(ip,this.puerto);
            
            //Creacion de entrada de datos para lectura de mensajes
            entradaSucket = new InputStreamReader(socket.getInputStream());   
            entrada = new BufferedReader(entradaSucket);
            
            //Creacion de salida de datos para el envio de mensajes
            salida = new DataOutputStream(socket.getOutputStream());
            
        } catch (IOException ex) {
            Logger.getLogger(ConnectorClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
     public void run (){
        String texto;
        while(true){
        try{
            texto = entrada.readLine();
            FrmClientChat.txtAreaChat.setText(FrmClientChat.txtAreaChat.getText()+"\n"+ texto);
        }catch(IOException e){
        
            
        };
        }
    }
    
    public void sendMssg(String mssg){
        
        try {
            salida.writeUTF(mssg + "\n");
        } catch (IOException ex) {
            Logger.getLogger(ConnectorClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String readMssg(){
        
        try {
            return this.entrada.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ConnectorClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    public void disconnect(){
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectorClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectorClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
