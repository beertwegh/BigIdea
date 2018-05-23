package DatabaseServer.REST;

import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/database")
public class DatabaseServer {

    @GET
    @Path("/{login}")
    public Response getMsg(@PathParam("login") String message) throws Exception {
        LoginResponse response = new LoginResponse();


        Gson json = new Gson();
        return Response.status(200).entity(null).build();
    }

}
