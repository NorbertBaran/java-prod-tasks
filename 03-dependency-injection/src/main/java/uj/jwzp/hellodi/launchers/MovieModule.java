package uj.jwzp.hellodi.launchers;

import dagger.Module;
import dagger.Provides;
import uj.jwzp.hellodi.logic.savers.JsonMovieSaver;
import uj.jwzp.hellodi.logic.savers.MovieSaver;
import uj.jwzp.hellodi.logic.savers.XmlMovieSaver;
import uj.jwzp.hellodi.logic.CSVMovieFinder;
import uj.jwzp.hellodi.logic.MovieFinder;
import uj.jwzp.hellodi.logic.MovieLister;
import uj.jwzp.hellodi.logic.savers.YmlMovieSaver;
import uj.jwzp.hellodi.model.FileName;

import javax.inject.Singleton;

@Module
public class MovieModule {

    FileName fileName;
    String formatName;

    public MovieModule(FileName fileName, String formatName){
        this.fileName=fileName;
        this.formatName=formatName;
    }

    @Provides
    @Singleton
    public MovieFinder provideMovieFinder() {
        return new CSVMovieFinder();
    }

    @Provides
    @Singleton
    public MovieLister provideMovieLister(MovieFinder movieFinder) {
        return new MovieLister(movieFinder);
    }

    @Provides
    @Singleton
    public MovieSaver provideMovieSaver() {
        if(formatName.equals("json"))
            return new JsonMovieSaver(fileName);
        else if(formatName.equals("yml"))
            return new YmlMovieSaver(fileName);
        else
            return new XmlMovieSaver(fileName);
    }

}
