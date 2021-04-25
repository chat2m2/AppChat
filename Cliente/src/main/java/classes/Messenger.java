/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import view.FrmClientChat;





/**
 *
 * @author Usuario
 */
public class Messenger  {
    public static ConnectorClient server, clientChat;
    
    public static void main(String[] args){
        
        FrmClientChat client = new FrmClientChat();
        client.main();
        
        
    }
    
    public static void initClient(String ip){
        clientChat = new ConnectorClient(ip);
        clientChat.start();
    }
    
    
}
