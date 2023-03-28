import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinha {
    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {
        //leitura da imagem
        // InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs_2.jpg").openStream();
        BufferedImage original = ImageIO.read(inputStream);

        //criar nova imagem com transparência e outro tamanho
        int largura = original.getWidth();
        int altura = original.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar imagem original para nova imagem
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(original, 0, 0, null);

        // fonte 
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.yellow);
        graphics.setFont(fonte);


        // escrever na imagem
        String texto = "TOPZERA";
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
        int larguratexto = (int) retangulo.getWidth();
        int posicaotextoX = (largura - larguratexto) / 2;
        graphics.drawString(texto,posicaotextoX, novaAltura - 100);


        //escrever em um arquivo 
        ImageIO.write(novaImagem, "png", new File("aulao java/alura-stickers/saida", nomeArquivo));
    }

}
