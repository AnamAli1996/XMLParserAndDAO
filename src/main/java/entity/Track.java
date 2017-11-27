package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries( {
        @NamedQuery(name = "Track.findAllTracks", query = "from Track t"),
        @NamedQuery(name = "Track.findById", query = "from Track t where t.trackId = :trackId"),
        @NamedQuery(name = "Track.findByName", query = "from Track t where t.name = :name"),
})

@Entity
@XmlRootElement
public class Track {
    @Id
    int trackId;
    String name;
    String artist;
    String album;
    String size;

    public Track(){

    }



    public Track(int Id, String name, String album, String artist, String size){
        this.trackId = Id;
        this.name = name;
        this.album = album;
        this.artist = artist;
        this.size = size;
    }

    @XmlElement
    public int getTrackId() {
        return trackId;
    }
    @XmlElement
    public String getName() {
        return name;
    }
    @XmlElement
    public String getAlbum() {
        return album;
    }
    @XmlElement
    public String getArtist() {
        return artist;
    }
    @XmlElement
    public String getSize(){return size;}

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(String size){this.size = size;}
}
