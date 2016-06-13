/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elektromiska.LoRaWanServiceAgent.example;

import com.google.gson.Gson;
import hu.elektromiska.LoRaWanServiceAgent.wss.MessageHandler;
import hu.elektromiska.LoRaWanServiceAgent.wss.WebsocketClient;
import hu.elektromiska.LoRaWanServiceAgent.wss.messages.Command;
import hu.elektromiska.LoRaWanServiceAgent.wss.messages.Message;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.DeploymentException;

/**
 *
 * @author misi
 */
public class Example implements MessageHandler {

    private final String WSS = "wss://eu1.loriot.io/app?id=XXXX&token=xxxxx"; //loriot wws url
    private WebsocketClient wc; // WebsocketClient class
    private final Gson gson;    // Google gson decode messages
    private Set<String> allowedEUI = new HashSet<>(); // optional device EUI filter

    public static void main(String[] args) {
        Example mainClass = new Example();
    }

    public Example() {
        try {
            wc = new WebsocketClient(WSS); // init websocket
        } catch (DeploymentException | IOException ex) {
            Logger.getLogger(Example.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
        gson = new Gson(); // init google gson

        allowedEUI.add("0123456789ABCDEF"); // optional init filter (load from csv/db/etc )

        wc.setMessageHandler(this); // set message handler

        for (;;); // do nothing

    }

    @Override
    public void handleMessage(String msg) {
        // decode json message
        Message message = gson.fromJson(msg, Message.class);
        // optional, filter allowed device by EUI
        if (allowedEUI.contains(message.geteUI())) {
            // print message
            System.out.println(message.toString());
        }
    }
}
