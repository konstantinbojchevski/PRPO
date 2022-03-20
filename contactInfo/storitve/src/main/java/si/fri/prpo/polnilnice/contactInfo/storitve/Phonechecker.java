package si.fri.prpo.polnilnice.contactInfo.storitve;
import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

@ApplicationScoped
public class Phonechecker {

    public boolean f(String phone) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://veriphone.p.rapidapi.com/verify?phone=" + phone))
                .header("x-rapidapi-host", "veriphone.p.rapidapi.com")
                .header("x-rapidapi-key", "32007fa99bmshfe85ccb32ab5705p166c3ajsn4cec93768e2d")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        int starting = response.body().indexOf('v')+8;
        String rez = response.body().substring(starting,starting+4);
        System.out.println(rez);
        if (rez.equals("true")) {
            return true;
        }
        else return false;
    }
}
