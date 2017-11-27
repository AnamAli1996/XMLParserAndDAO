package entity;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NamedQueries( {
        @NamedQuery(name = "Library.findAll", query = "select o from Library o"),
        @NamedQuery(name = "Library.findById", query = "select o from Library o where o.library=:library"),
})

@Entity
public class Library {

    //every entity requires an id, and we can make it auto generated
    @Id
    private String library;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Track> tracks;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Playlist> playlists;


    public Library() {

    }


    public Library(String library) {
       this.library = library;
    }


    public String getLibrary() {
        return library;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setLibrary(String library) {
        this.library = library;
    }


}