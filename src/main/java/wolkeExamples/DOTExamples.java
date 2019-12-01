package wolkeExamples;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.RankDir;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;
import java.io.File;
import java.io.IOException;

import static guru.nidi.graphviz.model.Factory.*;
import static guru.nidi.graphviz.model.Link.to;

public class DOTExamples {
    /*
    first simple demo
    creates pictures in directory example
     */
    public void generateDemoGraph(){
        Graph g = graph("example1").directed()
                .graphAttr().with(RankDir.LEFT_TO_RIGHT)
                .with(
                        node("a").with(Color.RED).link(node("b")),
                        node("b").link(to(node("c")).with(Style.DASHED))
                );
        try {
            Graphviz.fromGraph(g).height(100).render(Format.PNG).toFile(new File("example/ex1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /*
    1. read dot File and create a mutable graph
    2. change the graph and manipulate its' view
     */
    public void generateDemoGraphFromFile() {
        MutableGraph mg = null;
        try {
            mg = Parser.read(DOTExamples.class.getResourceAsStream("input/color.dot"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Graphviz.fromGraph(mg).width(700).render(Format.PNG).toFile(new File("example/ex3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //change the graph
        mg.graphAttrs()
                .add(Color.WHITE.gradient(Color.rgb("888888")).background().angle(90))
                .nodeAttrs().add(Color.WHITE.fill())
                .nodes().forEach(node ->
                node.add(
                        Color.named(node.name().toString()),
                        Style.lineWidth(4).and(Style.FILLED)));
        try {
            Graphviz.fromGraph(mg).width(700).render(Format.PNG).toFile(new File("example/ex3-2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*
        mutable graph can be changed after creation
        this version is using lambdas
     */
    public void generateMuableDemoGraph() {
        MutableGraph g = mutGraph("example1").setDirected(true).use((gr, ctx) -> {
            mutNode("b");
            nodeAttrs().add(Color.RED);
            mutNode("a").addLink(mutNode("b"));
        });
        try {
            Graphviz.fromGraph(g).width(200).render(Format.PNG).toFile(new File("example/ex2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //TODO Add more nodes to a single one
    //TODO Use Mutable Graph
    //see commented code
    public void generateDemoBinaryTree(){
        Graph g = graph("example1").directed()
                .graphAttr().with(RankDir.TOP_TO_BOTTOM)
                .with(
                        node("1").with(Color.BLACK, Label.of("Eins")).link(node("2").with(Color.RED)),
                        node("1").link(node("3")),
                        node("2").link(node("4")),
                        /*node("2").link(node("5")),*/
                        node("3").link(node("6"))
                        /*node("b").link(to(node("c")).with(Style.DASHED))*/
                );
        try {
            Graphviz.fromGraph(g).height(300).render(Format.PNG).toFile(new File("example/BinTree.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
