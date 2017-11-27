package Controller;

import entity.Library;
import entity.Playlist;
import entity.Track;
import entity.User;
import main.LibraryDAO;
import main.PlaylistDAO;
import main.TrackDAO;
import persistence.PersistenceUtil;

import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/track")
public class TrackController {
    static int id;


    @GET
    @Path("/allTracks")
        @Produces("application/json")
    public List<Track> getAllTracks(@Context HttpServletResponse servletResponse) {
        User u = PersistenceUtil.findUserById(1);
        Library l = u.getLibrary();
        l.getTracks();
        return l.getTracks();

    }

    @POST
    @Path("/findTrack")
    @Produces("application/json")
    public void FindEditTrack(@FormParam("name") String name,
                              @Context HttpServletResponse servletResponse) throws IOException {
        Track track = PersistenceUtil.findTrackByName(name);
        id = track.getTrackId();
        servletResponse.sendRedirect("/editTrack.html");


    }

    @POST
    @Path("/editTrack")
    @Produces("application/json")
    public Response EditTrack(@FormParam("name") String name,
                              @FormParam("artist") String artist,
                              @FormParam("album") String album,
                              @Context HttpServletResponse servletResponse) throws IOException {
        Track track = PersistenceUtil.findTrackByid(id);
        String size = track.getSize();
        int trackid = track.getTrackId();

        track.setTrackId(trackid);
        track.setName(name);
        track.setAlbum(album);
        track.setArtist(artist);
        track.setSize(size);
        TrackDAO.updateTrack(track);
        return Response.ok(track).build();


    }

    @POST
    @Path("/deleteTrack")
    @Produces("application/json")
    public Response DeleteTrack(@FormParam("name") String name,
                                @Context HttpServletResponse servletResponse) throws IOException {
        int id = 0;

      User u = PersistenceUtil.findUserById( UserController.UserId);
      Playlist playlist = null;

        Track track = PersistenceUtil.findTrackByName(name);
        List<Playlist> playlists = PersistenceUtil.findAllPlaylists();

        for(Playlist p: playlists){
            for(Track t: p.getTracks()){
                if(t.getTrackId() ==track.getTrackId()){
                    id = p.getPlaylistId();
                    playlist = p;
                    LibraryDAO.deleteTrack(u.getLibrary(), track, playlist);
                    PlaylistDAO.deleteTracks(id, track.getTrackId());
                }
            }
        }

        TrackDAO.deleteTrack(track);
        return Response.ok("track deleted successfully").build();

    }



}


