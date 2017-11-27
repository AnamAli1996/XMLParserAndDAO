package Controller;

import com.sun.org.apache.regexp.internal.RE;
import entity.Library;
import entity.Playlist;
import entity.Track;
import entity.User;
import main.LibraryDAO;
import main.PlaylistDAO;
import main.TrackDAO;
import persistence.PersistenceUtil;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/playlist")

public class PlaylistController {

    static int id;

    @GET
    @Path("/allPlaylists")
    @Produces("application/json")
    public List<Playlist> getAllPlaylists() {
        User u = PersistenceUtil.findUserById(1);
        Library l = u.getLibrary();
        l.getPlaylists();

        return l.getPlaylists();
    }




    @POST
    @Path("/findPlaylist")
    @Produces("application/json")
    public void FindEditPlaylist(@FormParam("name") String name,
                              @Context HttpServletResponse servletResponse) throws IOException {
        Playlist playlist = PersistenceUtil.findPlaylistByName(name);
        id = playlist.getPlaylistId();
        servletResponse.sendRedirect("/editPlaylist.html");


    }

    @POST
    @Path("/editPlaylist")
    @Produces("application/json")
    public Response EditPlaylist(@FormParam("name") String name,
                                 @Context HttpServletResponse servletResponse) throws IOException {
       Playlist playlist= PersistenceUtil.findPlaylistById(id);
        List<Track> playlistTracks = playlist.getTracks();

        playlist.setPlaylistId(id);
        playlist.setName(name);
        for(Track t: playlistTracks){
            PlaylistDAO.addTracks(playlist.getPlaylistId(), t.getTrackId());
        }
        PlaylistDAO.updatePlaylist(playlist);
        return Response.ok(playlist).build();


    }

    @POST
    @Path("/addTrackToPlaylist")
    @Produces("application/json")
    public Response AddTrackToPlaylist(@FormParam("trackName") String trackName,
                                       @FormParam("playlistName") String playlistName,
                                       @Context HttpServletResponse servletResponse) throws IOException {
        Playlist p = PersistenceUtil.findPlaylistByName(playlistName);
        Track t = PersistenceUtil.findTrackByName(trackName);

      PlaylistDAO.addTracks(p.getPlaylistId(), t.getTrackId());
      Playlist updatedPlaylist = PersistenceUtil.findPlaylistByName(playlistName);

        return Response.ok(updatedPlaylist).build();
    }

    @POST
    @Path("/deleteTrackFromPlaylist")
    public Response DeleteTrackFromPlaylist(@FormParam("trackName") String trackName,
                                            @FormParam("playlistName") String playlistName,
                                            @Context HttpServletResponse servletResponse) throws IOException {
        Playlist p = PersistenceUtil.findPlaylistByName(playlistName);
        Track t = PersistenceUtil.findTrackByName(trackName);
        PlaylistDAO.deleteTracks(p.getPlaylistId(), t.getTrackId());
        Playlist updatedPlaylist = PersistenceUtil.findPlaylistByName(playlistName);
        return Response.ok(updatedPlaylist).build();

    }

    @POST
    @Path("/deleteplaylist")
    public void DeletePlaylis(@FormParam("name") String name,
                                  @Context HttpServletResponse servletResponse) throws IOException{

        User u = PersistenceUtil.findUserById(UserController.UserId);
        Library l = u.getLibrary();
        Playlist p = PersistenceUtil.findPlaylistByName(name);
        LibraryDAO.deletePlaylist(l, p);
        if(!p.getTracks().isEmpty()){
            p.setTracks(null);
        }
        PlaylistDAO.deletePlaylist(p);
        servletResponse.sendRedirect("/api/playlist/allPlaylists");

    }


}
