
package main;
import entity.Playlist;
import entity.Track;
import persistence.PersistenceUtil;

import java.util.Iterator;
import java.util.List;


public class PlaylistDAO {

    public static void main(String[] args){
        PlaylistDAO config = new PlaylistDAO();
    }

    public PlaylistDAO(){

    }


    public void viewPlaylist(){
        List<Playlist> playlists = PersistenceUtil.findAllPlaylists();
        for(Playlist playlist: playlists){
            System.out.println("Playlist "+playlist.getPlaylistId()+ " exists.");
        }
    }

    public static void createPlaylist(int Id, String name){
       Playlist playlist = new Playlist(Id, name);
      //System.out.println(playlist.getPlaylistId());
        //System.out.println("poopp");
        PersistenceUtil.persist(playlist);
        System.out.println("Playlist registered");
    }

    public static Playlist mergePlaylist(Playlist p){
        PersistenceUtil.merge(p);
        return p;
    }

    public static void addTracks(int PlaylistID, int TrackID){
        Playlist p = PersistenceUtil.findPlaylistById(PlaylistID);
        Track track = PersistenceUtil.findTrackByid(TrackID);
        p.getTracks().add(track);
        PersistenceUtil.merge(p);
    }

    public static void deleteTracks(int PlaylistID, int TrackID){
        Playlist p = PersistenceUtil.findPlaylistById(PlaylistID);
        Track track = PersistenceUtil.findTrackByid(TrackID);

        for (Iterator<Track> iter = p.getTracks().listIterator(); iter.hasNext(); ) {
            Track a = iter.next();
            if (a.getTrackId()==track.getTrackId()) {
                iter.remove();
                System.out.println("Removed from playlist");
            }
        }





        PersistenceUtil.merge(p);
    }



    public static Playlist updatePlaylist(Playlist playlist){
        List<Playlist> playlists = PersistenceUtil.findAllPlaylists();
        Playlist updatePlaylist = PersistenceUtil.findPlaylistById(playlist.getPlaylistId());
        updatePlaylist.setName(playlist.getName());
        updatePlaylist.setPlaylistId(playlist.getPlaylistId());
        updatePlaylist.setTracks(playlist.getTracks());
        PersistenceUtil.merge(updatePlaylist);
        return updatePlaylist;
    }

    public static void deletePlaylist(Playlist p) {
        PersistenceUtil.remove(p);
        System.out.println("playlist deleted");
    }



}
