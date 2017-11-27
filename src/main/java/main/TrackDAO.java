
package main;
import entity.Playlist;
import entity.Track;
import entity.User;
import persistence.PersistenceUtil;

import java.util.List;


public class TrackDAO {

    public static void main(String[] args){
        TrackDAO config = new TrackDAO();
    }

    public TrackDAO(){

    }


    public void viewTrack(){
        List<Track> tracks = PersistenceUtil.findAllTracks();
        for(Track s:tracks){
            System.out.println("Track "+s.getTrackId()+ " exists.");
        }
    }

    public static void createTrack(int Id, String name, String album, String artist, String size){
        Track track = new Track(Id, name, album, artist, size);
        System.out.println(track.getTrackId());
        PersistenceUtil.persist(track);
        System.out.println("Track registered");
    }

    public static Track TrackById(int id){
        Track track = PersistenceUtil.findTrackByid(id);
            return track;
        }

    public static Track updateTrack(Track track){
        List<Track> trackList = PersistenceUtil.findAllTracks();
        Track updateTrack = PersistenceUtil.findTrackByid(track.getTrackId());
        updateTrack.setTrackId(track.getTrackId());
        updateTrack.setName(track.getName());
        updateTrack.setAlbum(track.getAlbum());
        updateTrack.setArtist(track.getArtist());
        updateTrack.setSize(track.getSize());
        PersistenceUtil.merge(updateTrack);
        return updateTrack;
    }

    public static void deleteTrack(Track track) {
        PersistenceUtil.remove(track);
        System.out.println("Playlist deleted");
    }

    /* public User updateStudent(User user){
       // List<User> studentList = PersistenceUtil.findAllStudents();
        User updateUser = PersistenceUtil.findStudentById(user.getId());
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setLanguageLevel(user.getLanguageLevel());
        updateUser.setDateOfBirth(user.getDateOfBirth());
                PersistenceUtil.merge(updateUser);
                System.out.println("Updated");
        return updateUser;

        }
*/






    }



