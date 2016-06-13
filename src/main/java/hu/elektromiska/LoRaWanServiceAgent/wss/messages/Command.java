/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elektromiska.LoRaWanServiceAgent.wss.messages;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.util.Arrays;

/**
 *
 * @author misi
 */
public class Command {

    @SerializedName("cmd")
    private final String cmd;

    @SerializedName("EUI")
    private final String eUI;

    @SerializedName("port")
    private final Integer port;

    @SerializedName("data")
    private final String data;

    public Command(String eUI, Integer port, String data) {
        this.cmd = "tx";
        this.eUI = eUI;
        this.port = port;
        this.data = data;
    }

    public Command(String eUI, Integer port, byte[] data) {
        this.cmd = "tx";
        this.eUI = eUI;
        this.port = port;
        this.data = Arrays.toString(data);
    }

    public String geteUI() {
        return eUI;
    }

    public Integer getPort() {
        return port;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
