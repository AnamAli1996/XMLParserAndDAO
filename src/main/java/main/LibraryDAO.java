
package main;
import entity.Library;
import entity.Playlist;
import entity.Track;
import persistence.PersistenceUtil;

import java.util.Iterator;
import java.util.List;


public class LibraryDAO {

    public static void main(String[] args){
        LibraryDAO config = new LibraryDAO();
    }

    public LibraryDAO(){

    }


    public void viewLibrary(){
        List<Library> libraries = PersistenceUtil.findAllLibraries();
        for(Library library: libraries){
            System.out.println("Library "+library.getLibrary()+ " exists.");
        }
    }

    public static void createLibrary(String name){
        Library library = new Library(name);
        PersistenceUtil.persist(library);
        System.out.println("Library registered");
    }


    public static void addTracks(String LibraryId, int TrackID){
        Library library = PersistenceUtil.findLibraryById(LibraryId);
        Track track = PersistenceUtil.findTrackByid(TrackID);
        library.getTracks().add(track);
        PersistenceUtil.merge(library);
    }

    public static void addPlaylist(String LibraryId, int PlaylistID){
        Library library = PersistenceUtil.findLibraryById(LibraryId);
        Playlist playlist = PersistenceUtil.findPlaylistById(PlaylistID);
        library.getPlaylists().add(playlist);
        PersistenceUtil.merge(library);
    }

    public static void deleteTrack(Library l, Track t, Playlist p){

        for (Iterator<Track> iter = l.getTracks().listIterator(); iter.hasNext(); ) {
            Track a = iter.next();
            if (a.getTrackId()==t.getTrackId()) {
                iter.remove();
                System.out.println("Removed from Library");
            }
        }

        for(Iterator<Playlist> iterator = l.getPlaylists().listIterator(); iterator.hasNext();){
            Playlist playlist = iterator.next();
            if(playlist.getPlaylistId() == p.getPlaylistId()){
                for(Iterator<Track> iteratorTracks = playlist.getTracks().listIterator(); iteratorTracks.hasNext();){
                    Track a = iteratorTracks.next();
                    if (a.getTrackId()==t.getTrackId()) {
                        iteratorTracks.remove();
                        System.out.println("Removed from playlist");
                    }
                }
            }

        }

        PersistenceUtil.merge(l);
    }

    public static void deletePlaylist(Library l, Playlist p){
        for (Iterator<Playlist> iter = l.getPlaylists().listIterator(); iter.hasNext(); ) {
            Playlist playlist = iter.next();
            if (playlist.getPlaylistId()==p.getPlaylistId()) {
                iter.remove();
                System.out.println("Removed from Library");
            }
        }
        PersistenceUtil.merge(l);



    }



}
