import java.io.InputStream;
import java.net.URL;
import java.util.List;


public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os dados
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";
        //ExtratorConteudo extrator = new ExtratorConteudoImdb();


        //String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        //ExtratorConteudo extrator = new ExtratoraConteudoNasa();


        String url = "http://localhost:8080/linguagens";
        ExtratorConteudo extrator = new ExtratorConteudoImdb();


        var http = new ClienteHttp();
        String json = http.buscaDados(url);



        // exibir e mainupular os dados do jeito que queremos
        List<Conteudo> listaDeConteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinha();

        for (Conteudo conteudo : listaDeConteudos) {
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo().replace(":", "-") + ".png";

            System.out.println(conteudo.getTitulo());
            geradora.cria(inputStream, nomeArquivo);

        }
    }
}
