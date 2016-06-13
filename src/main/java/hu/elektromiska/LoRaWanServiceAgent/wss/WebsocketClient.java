/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elektromiska.LoRaWanServiceAgent.wss;

import hu.elektromiska.LoRaWanServiceAgent.wss.messages.Message;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.SendHandler;
import javax.websocket.SendResult;
import javax.websocket.Session;

/**
 *
 * @author misi
 */
@ClientEndpoint
public class WebsocketClient {

    // init logger
    private static final Logger logger = Logger.getLogger(WebsocketClient.class.getName());

    private Session session; // session
    private MessageHandler messageHandler; // message handler

    public WebsocketClient(String url) throws DeploymentException, IOException {
        ContainerProvider.getWebSocketContainer().connectToServer(this, URI.create(url)); // init websocket
    }

    @OnOpen
    public void onOpen(Session userSession) {
        this.session = userSession;
        logger.info("onOpen");
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        this.session = null;
        logger.log(Level.INFO, "onClose: {0}", reason.getReasonPhrase());
    }

    @OnError
    public void onError(Throwable exception, Session session) {
        logger.log(Level.INFO, "onError: {0}", exception.toString());
    }

    @OnMessage
    public void onMessage(String message) {
        if (messageHandler != null) {
            messageHandler.handleMessage(message);
        }
    }

    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    public void sendAsyncMessage(Message message) {        
        session.getAsyncRemote().sendText(message.toString());
    }
}
