import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os dados
        String imdbkey = System.getenv("IMBD_API_KEY");
        System.out.println(imdbkey);
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";
        URI endereco  =URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response =client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // pegar os dados importantes (titulo, poster, classificação)
        var Parser = new JsonParser();
        List<Map<String, String>> filmes = Parser.parse(body);

        // exibir e mainupular os dados do jeito que queremos
        for (Map<String,String> filme : filmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            System.out.println();
        }
        for (Map<String,String> filme : filmes) {
            String urlImagem = filme.get("image");
            String Titulo = filme.get("title");
            InputStream inputStream = new URL(urlImagem).openStream();

            String nomeArquivo = Titulo + ".png";

            System.out.println(filme.get("title"));
            var geradora = new GeradoraDeFigurinha();
            geradora.cria(inputStream, nomeArquivo);

        }
    }
}
