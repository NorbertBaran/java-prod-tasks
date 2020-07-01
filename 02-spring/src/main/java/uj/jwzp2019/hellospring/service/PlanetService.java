package uj.jwzp2019.hellospring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uj.jwzp2019.hellospring.model.Person;
import uj.jwzp2019.hellospring.model.Planet;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanetService {

    private final String starWarsApiUrl;
    private final RestTemplate restTemplate;

    @Autowired
    public PlanetService(@Value("${starwars.api.url}") String starWarsApiUrl, RestTemplate restTemplate) {
        this.starWarsApiUrl = starWarsApiUrl;
        this.restTemplate = restTemplate;
    }

    public Planet getPlanetById(int id) {
        return restTemplate.getForEntity(starWarsApiUrl+"/planets/"+id, Planet.class).getBody();
    }

    public Planet getPlanetByUrl(String url) {
        return restTemplate.getForEntity(url, Planet.class).getBody();
    }

    public Planet getSmallestPlanetInRange(int fromId, int toId) {
        if(fromId>toId) throw new IllegalArgumentException();
        else{
            Planet minPlanet=getPlanetById(fromId);
            for(int id=fromId+1; id<=toId; id++){
                Planet planet=getPlanetById(id);
                if(planet.getDiameter()<minPlanet.getDiameter())
                    minPlanet=planet;
            }
            return minPlanet;
        }
    }
}
