/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import view.FrmServerChat;


public class Messenger {
    
    public static ConnectorServer serverWin, client;
    
    public static void main(String[] args){
        
        FrmServerChat server= new FrmServerChat();
        server.main();
        
        
    }
    
    public static void initServer(){
        serverWin = new ConnectorServer("hilos");
        serverWin.start();
    }
         
}
