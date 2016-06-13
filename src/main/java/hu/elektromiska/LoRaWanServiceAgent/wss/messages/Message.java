/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elektromiska.LoRaWanServiceAgent.wss.messages;

import com.google.gson.annotations.SerializedName;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author misi
 */
public class Message {

    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @SerializedName("cmd")
    private String cmd;

    @SerializedName("EUI")
    private String eUI;

    @SerializedName("ts")
    private Long ts;

    @SerializedName("fcnt")
    private Integer fcnt;

    @SerializedName("port")
    private Integer port;

    @SerializedName("freq")
    private Long freq;

    @SerializedName("rssi")
    private Integer rssi;

    @SerializedName("snr")
    private Double snr;

    @SerializedName("dr")
    private String dr;

    @SerializedName("data")
    private String data;

    @SerializedName("gws")
    private List<GatewayInfo> gws = new ArrayList<>();

    @SerializedName("seqdn")
    private Integer seqdn;

    @SerializedName("seqq")
    private Integer seqq;

    public Message() {
    }

    public String getCmd() {
        return cmd;
    }

    public String geteUI() {
        return eUI;
    }

    public long getTs() {
        return ts;
    }

    public Date getTimeStamp() {
        return new Date(ts);
    }

    public String getTimeStampString() {
        return dateFormat.format(new Date(ts));
    }

    public Integer getFcnt() {
        return fcnt;
    }

    public Integer getPort() {
        return port;
    }

    public long getFreq() {
        return freq;
    }

    public Integer getRssi() {
        return rssi;
    }

    public Double getSnr() {
        return snr;
    }

    public String getDr() {
        return dr;
    }

    public String getData() {
        return data;
    }

    public byte[] getByteData() {
        return new BigInteger(data, 16).toByteArray();
    }

    public List<GatewayInfo> getGws() {
        return gws;
    }

    public Integer getSeqdn() {
        return seqdn;
    }

    public Integer getSeqq() {
        return seqq;
    }

    public boolean isCmdRX() {
        return cmd.equals("rx");
    }

    public boolean isCmdGW() {
        return cmd.equals("gw");
    }

    public boolean isCmdTXD() {
        return cmd.equals("txd");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("EUI: ").append(eUI).append("\n");
        sb.append("cmd: ").append(cmd).append("\n");
        sb.append("ts: ").append(getTimeStampString()).append("\n");

        if (isCmdTXD()) {
            sb.append("seqdn: ").append(seqdn).append("\n");
            sb.append("seqq: ").append(seqq).append("\n");
        }

        if (isCmdRX() || isCmdGW()) {
            sb.append("fcnt: ").append(fcnt).append("\n");
            sb.append("port: ").append(port).append("\n");
            sb.append("freq: ").append(freq).append("\n");
            sb.append("dr: ").append(dr).append("\n");
            sb.append("data: ").append(data).append("\n");
        }

        if (isCmdRX()) {
            sb.append("rssi: ").append(rssi).append("\n");
            sb.append("snr: ").append(snr).append("\n");
        }
        sb.append("-------------------------------\n");
        return sb.toString();
    }

}
