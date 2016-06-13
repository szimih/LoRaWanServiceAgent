/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elektromiska.LoRaWanServiceAgent.wss;

/**
 *
 * @author misi
 */
public interface MessageHandler {

    public void handleMessage(String msg);
}
