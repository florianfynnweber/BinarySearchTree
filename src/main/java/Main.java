import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.RankDir;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static guru.nidi.graphviz.model.Factory.*;

public class Main extends Application {

    static BufferedImage gr;

    public static void main(String[] args) throws IOException {
        Graph g = graph("example1").directed()
                .graphAttr().with(RankDir.LEFT_TO_RIGHT)
                .with(
                        node("a").with(Color.RED).link(node("b")),
                        node("b").link(to(node("c")).with(Style.DASHED))
                );
        gr = Graphviz.fromGraph(g).render(Format.SVG).toImage();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane p = new Pane();
        WritableImage i = new WritableImage(gr.getWidth(), gr.getHeight());
        SwingFXUtils.toFXImage(gr, i);
        p.getChildren().add(new ImageView(i));
        stage.setScene(new Scene(p));
        stage.show();
    }
}