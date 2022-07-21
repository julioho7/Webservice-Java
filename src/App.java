import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os TOP 250 movies IMDB
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        //System.out.println(body);


        // Extrair/parsear os dados que nos interessam: título, poster, classificação
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        System.out.println(listaDeFilmes.size());
        System.out.println(listaDeFilmes.get(0));

        // Exibir e manipular os dados como queremos      
        var count = 0;
        for (Map<String,String> filme : listaDeFilmes) {
            count++;
            System.out.println(count + ". " + filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println("\u001b[97m \u001b[104m Nota:\u001b[m" + filme.get("imDbRating"));
            System.out.println();
        }
        

    }
}