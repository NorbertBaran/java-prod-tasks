package uj.jwzp.hellodi.logic.savers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uj.jwzp.hellodi.model.FileName;
import uj.jwzp.hellodi.model.Movie;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component("jsonMovieSaver")
public class JsonMovieSaver implements MovieSaver {

    //@Inject
    //@Named("XmlFileName")

    @Autowired
    FileName fileName;

    public JsonMovieSaver(){}

    public JsonMovieSaver(FileName fileName){
        this.fileName=fileName;
    }

    @Override
    public void save(List<Movie> movies) throws IOException {

        String fileName=this.fileName.toString();

        Writer writer = new FileWriter(fileName);
        writer.append("[\n");
        for (Movie movie: movies) {
            writer.append(movieEntry(movie));
            if(!movie.toString().equals(movies.get(movies.size()-1).toString()))
                writer.append(",");
            writer.append("\n");
        }
        writer.append("]\n");
        writer.close();
    }

    private String movieEntry(Movie movie) {
        StringBuilder result = new StringBuilder("  {\n");
        result
                .append("    \"title\": \"" + movie.getTitle() + "\",\n")
                .append("    \"director\": \"" + movie.getDirector() + "\",\n")
                .append("    \"year\": \"" + movie.getYear() + "\"\n")
                .append("  }");
        return result.toString();
    }
}
