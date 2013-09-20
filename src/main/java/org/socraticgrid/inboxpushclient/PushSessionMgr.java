package org.socraticgrid.inboxpushclient;

import java.net.URI;
import org.socraticgrid.presentationservices.helpers.PropertyHelper;
 
/**
 * 
 *
 */
public class PushSessionMgr 
{   
    //======================================================================
    //NOTE: This URI reference needs to change if this WebSocket Java client 
    //      is deployed remotely from the WebSocket service itself.
    //======================================================================
    private String EP = "ws://localhost:8080/inboxmsgservice/sendmsg";
    
    public PushSessionMgr() {
    }
    
    
    public void pushMsg(String msg) throws Exception {
        this.pushMsg(this.EP, msg);
    }
    
    public void pushMsg(String pushEndpoint, final String msg) throws Exception {
        
        final PushClient clientEndPoint = new PushClient(new URI(pushEndpoint));
        
        clientEndPoint.addMessageHandler(new PushClient.MessageHandler() {
            
            @Override
            public void handleMessage(String msg) {
                System.out.println("[PushSessionMgr.handleMessage] incoming msg:\n"+ msg);
                
                //PUSH json-ified alerts.
                clientEndPoint.sendMessage(msg);
            }
                    
        });
        
        clientEndPoint.onMessage(msg);
        clientEndPoint.userSession.close();

    }
 
}
