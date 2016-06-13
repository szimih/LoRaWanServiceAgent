/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elektromiska.LoRaWanServiceAgent.wss.messages;

import com.google.gson.annotations.SerializedName;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author misi
 */
public class GatewayInfo {

    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @SerializedName("rssi")
    private Integer rssi;

    @SerializedName("snr")
    private Double snr;

    @SerializedName("ts")
    private long ts;

    @SerializedName("gweui")
    private String gweui;

    @SerializedName("lat")
    private Double lat;

    @SerializedName("lon")
    private Double lon;

    public GatewayInfo() {
    }

    public Integer getRssi() {
        return rssi;
    }

    public Double getSnr() {
        return snr;
    }

    public long getTs() {
        return ts;
    }

    public String getGweui() {
        return gweui;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    public String getTimeStampString() {
        return dateFormat.format(new Date(ts));
    }

    public Date getTimeStamp() {
        return new Date(ts);
    }

    @Override
    public String toString() {
        return "gweui: " + gweui
                + "rssi: " + rssi
                + "snr:" + snr
                + "ts: " + dateFormat.format(new Date(ts))
                + "lat:  " + lat
                + "lon: " + lon;
    }
}
