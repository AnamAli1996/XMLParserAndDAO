<%@ page import="Controller.UserController" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>


<body>

<%
    if(session.getAttribute("email") == null){
        response.sendRedirect("/login.jsp");
    }
    %>

Welcome ${email}

<br>
Edit Tracks <br>
<form action="findEdit.html" method="get">
    <input type="Submit" value="Edit Tracks">
</form>

<br>
View Track <br>
<form action="api/track/allTracks" method="get">
    <input type="Submit" value="View Tracks">
</form>
<br>
Delete Track <br>
<form action="findDelete.html" method="post">
    <input type="Submit" value="Delete Tracks">
</form>

<br>
Edit Playlist Name <br>
<form action="findPlaylist.html" method="post">
    <input type="Submit" value="Edit Playlist">
</form>

<br>


Add Tracks to Playlist <br>
<form action="AddTrackToPlaylist.html" method="post">
    <input type="Submit" value="Add Tracks">
</form>

<br>


Delete Tracks from Playlist <br>
<form action="DeleteTrackFromPlaylist.html" method="post">
    <input type="Submit" value="delete Tracks">
</form>

<br>

View Playlists <br>
<form action="api/playlist/allPlaylists" method="get">
    <input type="Submit" value="View Playlists">
</form>
<br>

Delete Playlists <br>
<form action="deletePlaylist.html" method="post">
    <input type="Submit" value="Delete Playlists">
</form>
<br>

Logout <br>
<form action="api/user/logout" method="get">
    <input type="Submit" value="Logout">
</form>
<br>


Choose file to upload<br>
<form action="api/files/upload" method="post" enctype="multipart/form-data">
    <input name="myFile" id="filename" type="file" /><br><br>
    <button name="submit" type="submit">Upload</button>
</form>






</body>
</html>