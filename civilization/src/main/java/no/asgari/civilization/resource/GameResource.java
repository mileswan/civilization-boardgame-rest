package no.asgari.civilization.resource;

import com.codahale.metrics.annotation.Timed;
import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;
import no.asgari.civilization.representations.PBF;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/games")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class GameResource {

    private JacksonDBCollection<PBF, String> collection;

    public GameResource(JacksonDBCollection<PBF, String> games) {
        this.collection = games;
    }

    @GET
    @Timed
    public List<PBF> getAllGames() {
        DBCursor<PBF> dbCursor = collection.find();
        List<PBF> blogs = new ArrayList<>();
        while (dbCursor.hasNext()) {
            PBF blog = dbCursor.next();
            blogs.add(blog);
        }
        return blogs;

    }

    @POST
    @Timed
    public Response createNewGame(@Valid PBF PBF) {
        collection.insert(PBF);
        return Response.noContent().build();
    }

}