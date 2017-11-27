package entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@NamedQueries( {
        @NamedQuery(name = "Playlist.findAllPlaylists", query = "from Playlist p"),
        @NamedQuery(name = "Playlist.findById", query = "from Playlist p where p.playlistId = :playlistId"),
        @NamedQuery(name = "Playlist.findByName", query = "from Playlist t where t.name = :name")
})

@Entity
@XmlRootElement
public class Playlist {
    @Id
    int playlistId;
    String name;

    @ManyToMany(targetEntity = Track.class, fetch = FetchType.EAGER,cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
    List<Track> tracks;

    public Playlist(){

    }



    public Playlist(int Id, String name){
        this.playlistId = Id;
        this.name = name;
    }

    @XmlElement
    public int getPlaylistId() {
        return playlistId;
    }
    @XmlElement
    public String getName() {
        return name;
    }

    @XmlElement
    public List<Track> getTracks() {
        return tracks;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
