package be.isservers.audiosyncserver.convert;

import be.isservers.audiosyncserver.parameter.Settings;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.Serializable;

public class Music implements Comparable<Music>, Serializable {
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("name")
    @Expose
    private String name;

    public static String PathToMusic = Settings.getWorkDirectory();

    public Music() {}

    /**
     *
     * @param name nom du fichier
     * @param hash hash md5 sur fichier
     */
    public Music(String hash, String name){
        super();
        this.hash = hash;
        this.name = name;
    }
    public Music(String name) {
        this.setName(name);
        this.setHash(MD5OfFile.calculateMD5(new File(PathToMusic + "/" + name)));
    }


    private void setName(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setHash(String MD5) { this.hash = MD5; }
    public String getHash() { return hash; }

    public Music withSerializedString(String buffer){
        int pos = buffer.lastIndexOf("-");
        this.setName(buffer.substring(0,pos));
        this.setHash(buffer.substring(pos+1,buffer.length()));
        return this;
    }

    @Override
    public String toString() {
        return name.replace(".mp3","") + '-' + hash;
    }

    @Override
    public int compareTo(Music o) {
        return this.getName().compareTo(o.getName());
    }
}
