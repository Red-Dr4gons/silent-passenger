package si.red.dragons.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetPoints {
    public GetPoints(){}

    @ConfigProperty(name="api.key")
    String APIKey;
    @ConfigProperty(name="api.apiHost")
    String APIHost;

    public List callAPI(String cityStart, String streetStart, String postStart, String cityEnd, String streetEnd, String postEnd) throws IOException {
        String baseGeolocationURL = "https://forward-reverse-geocoding.p.rapidapi.com/v1/forward";
        String basePointsURL = "ttp://router.project-osrm.org/route/v1/car/";

        // Start coords
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilderStart = HttpUrl.parse(baseGeolocationURL).newBuilder();
        String  urlStart = urlBuilderStart.addQueryParameter("format", "json").addQueryParameter("city", cityStart)
                .addQueryParameter("street", streetStart).addQueryParameter("postalcode", postStart).build().toString();

        Request requestStart = new Request.Builder()
                .url(urlStart)
                .get()
                .addHeader("x-rapidapi-key", APIKey)
                .addHeader("x-rapidapi-host", APIHost)
                .build();

        Response responseStart = client.newCall(requestStart).execute();
        String bodyStart = responseStart.body().string();

        ObjectMapper mapperStart = new ObjectMapper();
        JsonNode jsonStart = mapperStart.readTree(bodyStart);
        float start_lat = (float)jsonStart.get(0).get("lat").asDouble();
        float start_lon = (float)jsonStart.get(0).get("lon").asDouble();

        // End coords
        HttpUrl.Builder urlBuilderEnd = HttpUrl.parse(baseGeolocationURL).newBuilder();
        String  urlEnd = urlBuilderEnd.addQueryParameter("format", "json").addQueryParameter("city", cityEnd)
                .addQueryParameter("street", streetEnd).addQueryParameter("postalcode", postEnd).build().toString();

        Request requestEnd = new Request.Builder()
                .url(urlEnd)
                .get()
                .addHeader("x-rapidapi-key", APIKey)
                .addHeader("x-rapidapi-host", APIHost)
                .build();

        Response responseEnd = client.newCall(requestEnd).execute();
        String bodyEnd = responseEnd.body().string();

        ObjectMapper mapperEnd = new ObjectMapper();
        JsonNode jsonEnd = mapperEnd.readTree(bodyEnd);
        float end_lat = (float)jsonEnd.get(0).get("lat").asDouble();
        float end_lon = (float)jsonEnd.get(0).get("lon").asDouble();

        // Points
        String newPointsURL = basePointsURL + start_lat + "," + start_lon + ";" +  end_lat + "," + end_lon;
        HttpUrl.Builder urlBuilderPoints = HttpUrl.parse(newPointsURL).newBuilder();
        String urlPoints = urlBuilderPoints.addQueryParameter("overview", "full").addQueryParameter("geometries", "polyline")
                .addQueryParameter("steps", "true").build().toString();

        Request requestPoints = new Request.Builder()
                .url(urlPoints)
                .get()
                .build();

        Response responsePoints = client.newCall(requestStart).execute();
        String bodyPoints = responseStart.body().string();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode neoJsonNode = mapper.readTree(bodyPoints);

        ArrayList<List<Float>> points = new ArrayList<>();
        JsonNode steps = neoJsonNode.get("routes").get(0).get("legs").get(0).get("steps");
        for(JsonNode step : steps){
            JsonNode intersections = step.get("intersections");
            for(JsonNode intersection : intersections){
                ArrayList<Float> coords = new ArrayList<>();
                coords.add((float)intersection.get("location").get(0).asDouble());
                coords.add((float)intersection.get("location").get(1).asDouble());
                points.add(coords);
            }
        }

        return points;
    }
}
