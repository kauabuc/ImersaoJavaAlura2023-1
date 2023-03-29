import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratoraConteudoNasa implements ExtratorConteudo{

    public List<Conteudo> extraiConteudos(String json) {

        // pegar os dados importantes (titulo, poster, classificação)
        var Parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = Parser.parse(json);
        List<Conteudo> conteudos = new ArrayList<>();

        for (Map<String, String> atributos : listaDeAtributos) {
            String titulo = atributos.get("title");
            String urlImagem = atributos.get("url");

            var conteudo = new Conteudo(titulo, urlImagem);

            conteudos.add(conteudo);
        }
        return conteudos;
    }
}
