package Parser;

import Controller.UserController;
import entity.User;
import main.LibraryDAO;
import main.PlaylistDAO;
import main.TrackDAO;
import entity.Library;
import main.UserDAO;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class parser {
    private static  boolean Trackfound = false;
    private static boolean Albumfound = false;
    private static boolean Artistfound = false;
    private static boolean Namefound = false;
    private static boolean Sizefound = false;
    private static String name = null;
    private static String artist = null;
    private static String album = null;
    private static String size = null;
    private static String TrackID = null;
    private static boolean tracksfound = true;
    private static boolean playlistFound = false;
    private static boolean playlistNameFound = false;
    private static boolean playlistIdFound = false;
    private static boolean playlistTracksfound = false;
    private static String PlaylistId;
    private static String playlistname;
    private static String track;
    private static String library;
    private static boolean libraryfound = false;
    private static Library libraryObject;


    public static void parseDoument(String fileName){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        String value;
        try {

            builder = factory.newDocumentBuilder();
            Document bookdoc = builder.parse(fileName);
            bookdoc.getDocumentElement().normalize();
            String rootName = bookdoc.getDocumentElement().getNodeName();
            System.out.println("root= " + rootName);
            Node root = bookdoc.getDocumentElement();
            NodeList booksLength = root.getChildNodes();
            for (int i = 0; i < booksLength.getLength(); i++) {

                Node node = booksLength.item(i);
                listNodes(booksLength.item(i));
                System.out.println("---------------------------");


            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void listNodes(Node node) {
        if(!node.getTextContent().equals("")) {

            if(libraryfound){
                library = node.getTextContent().trim();
                System.out.println("library id: " + library);
                LibraryDAO.createLibrary(library);
                UserDAO.addLibrary(UserController.UserId, library);
                libraryfound = false;
            }else if (tracksfound == true) {

                if (Trackfound == true) {
                    //System.out.println("track Id igor= " + node.getNextSibling().getChildNodes().item(0).getNodeValue());
                    TrackID = node.getTextContent().trim();
                   // System.out.println("track Id= " + TrackID);
                    Trackfound = false;
                } else if (Namefound) {
                   // System.out.println("Name = " + node.getTextContent().trim());
                    name = node.getTextContent().trim();
                    Namefound = false;
                } else if (Albumfound) {
                    //System.out.println("Album = " + node.getTextContent().trim());
                    album = node.getTextContent().trim();
                    Albumfound = false;
                } else if (Artistfound) {
                   // System.out.println("Artist = " + node.getTextContent().trim());
                    artist = node.getTextContent().trim();
                    Artistfound = false;
                } else if (Sizefound) {
                   //  System.out.println("Size = " + node.getTextContent().trim());
                    size = node.getTextContent().trim();
                    TrackDAO.createTrack((Integer.parseInt(TrackID)), name, artist, album, size);
                    LibraryDAO.addTracks(library, Integer.parseInt(TrackID));
                    Sizefound = false;

                }
            } else if (playlistFound) {
                if (Namefound) {
                    playlistname = node.getTextContent().trim();
                    //System.out.println(("playlist name" + playlistname));
                    Namefound = false;
                } else if (playlistIdFound) {
                    PlaylistId = node.getTextContent().trim();
                  //  System.out.println(("playlist id" + PlaylistId));
                    playlistIdFound = false;
                    PlaylistDAO.createPlaylist(Integer.valueOf(PlaylistId), playlistname);
                    LibraryDAO.addPlaylist(library, Integer.parseInt(PlaylistId));
                } else if (playlistTracksfound && Trackfound) {
                    track = node.getTextContent().trim();
                    PlaylistDAO.addTracks(Integer.valueOf(PlaylistId), Integer.parseInt(track));
                    playlistTracksfound  = false;
                }
            }




        }


            String returnValue = null;

            boolean nodeText = false;
            if (node instanceof Text) {
                nodeText = true;
                String value = node.getNodeValue().trim();
                if (value.equals("")) {
                    return;
                }
            }


            String nodeName = node.getNodeName();
            if(returnValue(node).equalsIgnoreCase("Library Persistent ID")){
                System.out.println("library found");
                libraryfound = true;
                System.out.println(libraryfound);
            }else if(returnValue(node).equalsIgnoreCase("Track ID") && playlistFound != true){
                Trackfound = true;
            }else if(returnValue(node).equalsIgnoreCase(("Name"))){
                Namefound = true;
            }else if(returnValue(node).equalsIgnoreCase("Album")){
                Albumfound = true;
            }else if(returnValue(node).equalsIgnoreCase("Artist")){
                Artistfound = true;
            }else if(returnValue(node).equalsIgnoreCase("size")){
                Sizefound = true;
            }else if(returnValue(node).equalsIgnoreCase("playlists")){
                tracksfound = false;
                playlistFound = true;
            }else if(returnValue(node).equalsIgnoreCase("Name")){
                Namefound = true;
            }else if(returnValue(node).equalsIgnoreCase("Playlist ID")){
                playlistIdFound = true;
            }else if(returnValue(node).equalsIgnoreCase("Track ID")){
                playlistTracksfound = true;
                Trackfound = true;
            }



            if(!nodeName.toString().equals("#text"))
                //System.out.println("Element Name: " + nodeName.toString());
                if(!returnValue(node).equals("")) {
                    //System.out.println("Value: " + returnValue(node));
                    returnValue = returnValue(node);
                }







            NodeList list = node.getChildNodes();

            if (list.getLength() > 0) {
                for (int i = 0; i < list.getLength(); i++) {
                    listNodes(list.item(i));
                }
            }
        }



    static String returnValue(Node node){
        if (node instanceof Text) {
            String value = node.getNodeValue().trim();
            if (!value.equals(""))
                return value;
        }
        return "";

    }

}