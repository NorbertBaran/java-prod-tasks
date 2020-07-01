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
public class PeopleService {

    private final String starWarsApiUrl;
    private final RestTemplate restTemplate;
    private final PlanetService planetService;

    @Autowired
    public PeopleService(@Value("${starwars.api.url}") String starWarsApiUrl, RestTemplate restTemplate, PlanetService planetService) {
        this.starWarsApiUrl = starWarsApiUrl;
        this.restTemplate = restTemplate;
        this.planetService = planetService;
    }

    public Person getPersonById(int id) {
        Person person=restTemplate.getForEntity(starWarsApiUrl+"/people/"+id, Person.class).getBody();
        Planet planet=restTemplate.getForEntity(person.getHomeworld(), Planet.class).getBody();
        person.setPlanet(planet);
        return person;
    }

    public List<Person> getPeopleInRangeWithEyeColor(int fromId, int toId, String color) {
        if(fromId>toId) throw new IllegalArgumentException();
        else{
            List<Person> people=new ArrayList<>();
            for(int id=fromId; id<=toId; id++){
                Person person=getPersonById(id);
                if(person.getEye_color().equals(color))
                    people.add(person);
            }
            return people;
        }
    }
}
