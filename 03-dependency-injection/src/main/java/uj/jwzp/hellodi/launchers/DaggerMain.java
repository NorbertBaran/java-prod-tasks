package uj.jwzp.hellodi.launchers;

import uj.jwzp.hellodi.logic.savers.MovieSaver;
import uj.jwzp.hellodi.model.FileName;
import uj.jwzp.hellodi.model.Movie;
import uj.jwzp.hellodi.logic.MovieLister;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DaggerMain {

    public static void main(String[] args) throws IOException {
        FileName fileName=new FileName(args.length>0 ? args[0] : "saved.xml");
        String formatName=args.length>1 ? args[1] : "xml";

        DaggerComponent daggerComponent = DaggerDaggerComponent.builder()
                .movieModule(new MovieModule(fileName, formatName))
                .build();

        MovieLister lister = daggerComponent.getMovieLister();
        MovieSaver saver = daggerComponent.getMovieSaver();

        List<Movie> movies = lister.moviesDirectedBy("Lucas").stream()
                .peek(m -> System.out.println(m.toString()))
                .collect(Collectors.toList());
        saver.save(movies);
    }
}
