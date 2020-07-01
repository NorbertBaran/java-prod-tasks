package uj.jwzp.hellodi.launchers;

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

public class ManualMain {

    public static void main(String[] args) throws IOException {
        MovieFinder finder = new CSVMovieFinder();
        MovieLister lister = new MovieLister(finder);
        FileName fileName=new FileName(args.length>0 ? args[0] : "saved.xml");
        String formatName=args.length>1 ? args[1] : "xml";
        //MovieSaver saver = new XmlMovieSaver(fileName);
        //MovieSaver saver = FormatChooser.setFormat(fileName, args.length>1 ? args[1] : "xml");
        MovieSaver saver;
        if(formatName.equals("json"))
            saver= new JsonMovieSaver(fileName);
        else if(formatName.equals("yml"))
            saver= new YmlMovieSaver(fileName);
        else
            saver= new XmlMovieSaver(fileName);

        List<Movie> movies = lister.moviesDirectedBy("Lucas").stream()
                .peek(m -> System.out.println(m.toString()))
                .collect(Collectors.toList());
        saver.save(movies);
    }
}
