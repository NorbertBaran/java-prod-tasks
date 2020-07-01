package uj.jwzp.hellodi.launchers;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import uj.jwzp.hellodi.logic.savers.JsonMovieSaver;
import uj.jwzp.hellodi.logic.savers.MovieSaver;
import uj.jwzp.hellodi.logic.savers.XmlMovieSaver;
import uj.jwzp.hellodi.logic.savers.YmlMovieSaver;
import uj.jwzp.hellodi.model.FileName;
import uj.jwzp.hellodi.model.Movie;
import uj.jwzp.hellodi.logic.CSVMovieFinder;
import uj.jwzp.hellodi.logic.MovieFinder;
import uj.jwzp.hellodi.logic.MovieLister;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GuiceMain {

    public static void main(String[] args) throws IOException {

        GuiceMovieModule.fileName=new FileName(args.length>0 ? args[0] : "saved.xml");
        GuiceMovieModule.formatName=args.length>1 ? args[1] : "xml";

        Injector injector = Guice.createInjector(new GuiceMovieModule());
        MovieLister lister = injector.getInstance(MovieLister.class);
        MovieSaver saver = injector.getInstance(MovieSaver.class);

        List<Movie> movies = lister.moviesDirectedBy("Lucas").stream()
                .peek(m -> System.out.println(m.toString()))
                .collect(Collectors.toList());
        saver.save(movies);
    }
}

class GuiceMovieModule extends AbstractModule {
    static FileName fileName;
    static String formatName;

    @Override
    protected void configure() {
        bind(MovieFinder.class).to(CSVMovieFinder.class);
        //bind(MovieSaver.class).to(XmlMovieSaver.class);
        //bind(MovieSaver.class).toInstance(new XmlMovieSaver(fileName));
        if(formatName.equals("json"))
            bind(MovieSaver.class).toInstance(new JsonMovieSaver(fileName));
        else if(formatName.equals("yml"))
            bind(MovieSaver.class).toInstance(new YmlMovieSaver(fileName));
        else
            bind(MovieSaver.class).toInstance(new XmlMovieSaver(fileName));
    }
}
