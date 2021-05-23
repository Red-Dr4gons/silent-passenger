package si.red.dragons.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/routes")
public class RouteResource {
    @GET
    public Response getPath(@QueryParam("start_lat") Float start_lat,
                            @QueryParam("start_lon") Float start_lon,
                            @QueryParam("finish_lat") Float finish_lat,
                            @QueryParam("finish_lon") Float finish_lon) throws JsonProcessingException {
        if(start_lat == null || start_lon == null || finish_lat == null || finish_lon == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }


        Map<String, Object> params=new HashMap<String, Object>();
        params.put("start_lat", start_lat);
        params.put("start_lon", start_lon);
        params.put("finish_lat", finish_lat);
        params.put("finish_lon", finish_lon);

        // Create client and target
        Client client = ClientBuilder.newClient();
        WebTarget resource = client.target("ttp://router.project-osrm.org/route/v1/car/")
                .path("{start_lat},{start_lon};{finish_lat},{finish_lon}").resolveTemplates(params).queryParam("overview", "full")
                .queryParam("geometries", "polyline").queryParam("steps", "true");

        // Invoke request
        String response = resource.request(MediaType.TEXT_PLAIN).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode neoJsonNode = mapper.readTree(response);

        ArrayList<ArrayList<Float>> points = new ArrayList<ArrayList<Float>>();
        JsonNode steps = neoJsonNode.get("routes").get(0).get("legs").get(0).get("steps");
        for(JsonNode step : steps){
            JsonNode intersections = step.get("intersections");
            for(JsonNode intersection : intersections){
                ArrayList<Float> coords = new ArrayList<Float>();
                coords.add((float)intersection.get("location").get(0).asDouble());
                coords.add((float)intersection.get("location").get(1).asDouble());
                points.add(coords);
            }
        }

        client.close();
        return null;
    }
}

