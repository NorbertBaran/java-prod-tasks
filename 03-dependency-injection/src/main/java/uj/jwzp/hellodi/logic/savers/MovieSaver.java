package uj.jwzp.hellodi.logic.savers;

import uj.jwzp.hellodi.model.Movie;

import java.io.IOException;
import java.util.List;

public interface MovieSaver {

    void save(List<Movie> movies) throws IOException;
}
