package uj.jwzp.hellodi.logic.savers;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import uj.jwzp.hellodi.model.FileName;
import uj.jwzp.hellodi.model.Movie;


import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component("xmlMovieSaver")
public class XmlMovieSaver implements MovieSaver {

    //@Inject
    //@Named("XmlFileName")

    @Autowired
    FileName fileName;

    public XmlMovieSaver(){}

    public XmlMovieSaver(FileName fileName){
        this.fileName=fileName;
    }

    @Override
    public void save(List<Movie> movies) throws IOException {

        String fileName=this.fileName.toString();

        Writer writer = new FileWriter(fileName);
        writer.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n").append("<movies>\n");
        for (Movie movie: movies) {
            writer.append(movieEntry(movie));
        }
        writer.append("</movies>\n");
        writer.close();
    }

    private String movieEntry(Movie movie) {
        StringBuilder result = new StringBuilder("  <movie>\n");
        result
                .append("    <title>" + movie.getTitle() + "</title>\n")
                .append("    <director>" + movie.getDirector() + "</director>\n")
                .append("    <year>" + movie.getYear() + "</year>\n")
                .append("  </movie>\n");
        return result.toString();
    }
}
