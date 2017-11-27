package Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import Parser.parser;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
@Path("/files")
public class UploadController {
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("myFile") InputStream uploadedInputStream,
            @FormDataParam("myFile") FormDataContentDisposition fileDetail
    ) {
        String fileLocation = "/Users/anamali/Downloads/apache-tomcat-8.5.20/bin/" + fileDetail.getFileName();

       System.out.println(fileDetail.getFileName());//saving file
        try {
            FileOutputStream out = new FileOutputStream(new File(fileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];
            out = new FileOutputStream(new File(fileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {e.printStackTrace();}
        String output = "File successfully uploaded to : " + fileLocation;

        parser.parseDoument(fileDetail.getFileName());
       // return Response.status(200).entity(output).build();
        return Response.ok(output).build();
    }


}