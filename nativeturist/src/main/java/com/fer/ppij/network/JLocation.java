package com.fer.ppij.network;

import com.fer.ppij.beans.Location;
import com.fer.ppij.db.DbLocationAdapter;
import com.fer.ppij.nativeturist.NativeTuristApplication;

/**
 * Created by user00 on 5/5/14.
 */
class JLocation extends Location {

    private String serverAudio = "";
    private String serverPicture = "";

    public JLocation(int id,String name, double latitude, double longitude, String pictureLocation, String audioLocation, String textInfo, int idPlace) {
        super(id,name, latitude, longitude, pictureLocation, audioLocation, textInfo, idPlace);
    }

    public String getServerAudio() {
        return serverAudio;
    }

    public String getServerPicture() {
        return serverPicture;
    }

    public void setAudioLocation(String savedAudio) {
        this.audioLocation = savedAudio;
    }

    public void setPictureLocation(String savedPicture) { this.pictureLocation = savedPicture;}

    long save(){
        DbLocationAdapter dbLocationAdapter = DbLocationAdapter.getInstance();
        return dbLocationAdapter.save(this.id,this.name,this.latitude,
               this.longitude,this.pictureLocation,this.audioLocation,this.textInfo,this.idPlace);

    }
}
