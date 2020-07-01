package uj.jwzp.hellodi.launchers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import uj.jwzp.hellodi.logic.CSVMovieFinder;
import uj.jwzp.hellodi.logic.MovieFinder;
import uj.jwzp.hellodi.logic.savers.JsonMovieSaver;
import uj.jwzp.hellodi.logic.savers.XmlMovieSaver;
import uj.jwzp.hellodi.logic.savers.YmlMovieSaver;
import uj.jwzp.hellodi.model.FileName;
import uj.jwzp.hellodi.model.Movie;
import uj.jwzp.hellodi.logic.MovieLister;
import uj.jwzp.hellodi.logic.savers.MovieSaver;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SpringMain {

    public static void main(String[] args) throws IOException {
        //final ApplicationContext ctx = new AnnotationConfigApplicationContext(
        //    "uj.jwzp2019.hellodi.logic", "uj.jwzp2019.hellodi.logic.savers", "uj.jwzp.hellodi.model");

        final ApplicationContext ctx = new AnnotationConfigApplicationContext(FileName.class, XmlMovieSaver.class, JsonMovieSaver.class, YmlMovieSaver.class, CSVMovieFinder.class, MovieLister.class);
        ctx.getBean(FileName.class).setFileName(args.length>0 ? args[0] : "saved.xml");
        //MovieSaver saver = ctx.getBean(XmlMovieSaver.class);

        String formatName=args.length>1 ? args[1] : "xml";
        MovieSaver saver;
        if(formatName.equals("json"))
            saver = ctx.getBean(JsonMovieSaver.class);
        else if(formatName.equals("yml"))
            saver = ctx.getBean(YmlMovieSaver.class);
        else
            saver = ctx.getBean(XmlMovieSaver.class);

        MovieLister lister=ctx.getBean(MovieLister.class);
        //MovieLister lister = (MovieLister) ctx.getBean("movieLister");

        List<Movie> movies = lister.moviesDirectedBy("Lucas").stream()
                .peek(m -> System.out.println(m.toString()))
                .collect(Collectors.toList());
        saver.save(movies);
    }
}
