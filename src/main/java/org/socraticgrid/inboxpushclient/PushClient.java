/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.socraticgrid.inboxpushclient;

/**
 *
 * @author tnguyen
 */
import java.io.IOException;
import java.net.URI;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
 
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;


@ClientEndpoint 
public class PushClient {
    
    Session userSession = null;
    private MessageHandler messageHandler;
    
    public PushClient(URI endpointURI) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, endpointURI);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @OnOpen
    public void onOpen(Session session) throws IOException {
        System.out.println("Connected to endpoint: " + session.getBasicRemote());
        this.userSession = session;
    }
    
    /**
     * Callback hook for Connection close events.
     *
     * @param userSession
     *            the userSession which is getting closed.
     * @param reason
     *            the reason for connection close
     */
    @OnClose
    public void onClose(Session session, CloseReason reason) {
        this.userSession = null;
    }
 
    @OnMessage
    public void onMessage(String message) {
        System.out.println("PushClient.onMessage: "+message);
        
        if (this.messageHandler != null) {
            this.messageHandler.handleMessage(message);
        }
    }
 
    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }
    
    
    /**
     * register message handler
     *
     * @param message
     */
    public void addMessageHandler(MessageHandler msgHandler) {
        this.messageHandler = msgHandler;
    }
 
    /**
     * Send a message.
     *
     * @param user
     * @param message
     */
    public void sendMessage(String message) {
        this.userSession.getAsyncRemote().sendText(message);
    }
    
    /**
     * Message handler.
     *
     * @author Jiji_Sasidharan
     */
    public static interface MessageHandler {
        public void handleMessage(String message);
    }
    
}