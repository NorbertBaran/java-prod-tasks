package uj.jwzp.vet;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void initTest() throws IOException {
        String clientsCorrect=Files.readString(
                Paths.get(
                        RestControllerTest.class.getResource("RestControllerTest/initTest/output/Clients.json").getPath()
                )
        );
        String AnimalsCorrect=Files.readString(
                Paths.get(
                        RestControllerTest.class.getResource("RestControllerTest/initTest/output/Animals.json").getPath()
                )
        );
        String VisitsCorrect=Files.readString(
                Paths.get(
                        RestControllerTest.class.getResource("RestControllerTest/initTest/output/Visits.json").getPath()
                )
        );

        String clients=this.restTemplate.getForObject("http://localhost:" + port + "/clients/get/all", String.class);
        String animals=this.restTemplate.getForObject("http://localhost:" + port + "/animals/get/all", String.class);
        String visits=this.restTemplate.getForObject("http://localhost:" + port + "/visits/get/all", String.class);

        JSONAssert.assertEquals(clientsCorrect, clients, false);
        JSONAssert.assertEquals(AnimalsCorrect, animals, false);
        JSONAssert.assertEquals(VisitsCorrect, visits, false);
    }

    @Test
    public void addClientTest() throws Exception {
        String inputClient=Files.readString(
                Paths.get(
                        RestControllerTest.class.getResource("RestControllerTest/addClientTest/input/Client.json").getPath()
                )
        );
        String clientsCorrect=Files.readString(
                Paths.get(
                        RestControllerTest.class.getResource("RestControllerTest/addClientTest/output/Clients.json").getPath()
                )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(inputClient, headers);
        this.restTemplate.postForObject("http://localhost:" + port + "/clients/add/client", request, void.class);

        String clients=this.restTemplate.getForObject("http://localhost:" + port + "/clients/get/all", String.class);

        JSONAssert.assertEquals(clientsCorrect, clients, false);
    }

    @Test
    public void addAnimalTest() throws IOException {
        String inputAnimal=Files.readString(
                Paths.get(
                        RestControllerTest.class.getResource("RestControllerTest/addAnimalTest/input/Animal.json").getPath()
                )
        );
        String animalsCorrect=Files.readString(
                Paths.get(
                        RestControllerTest.class.getResource("RestControllerTest/addAnimalTest/output/Animals.json").getPath()
                )
        );
        String clientsCorrect=Files.readString(
                Paths.get(
                        RestControllerTest.class.getResource("RestControllerTest/addAnimalTest/output/Clients.json").getPath()
                )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(inputAnimal, headers);
        this.restTemplate.postForObject("http://localhost:" + port + "/animals/add/animal", request, void.class);

        String animals=this.restTemplate.getForObject("http://localhost:" + port + "/animals/get/all", String.class);
        String clients=this.restTemplate.getForObject("http://localhost:" + port + "/clients/get/all", String.class);

        JSONAssert.assertEquals(animalsCorrect, animals, false);
        JSONAssert.assertEquals(clientsCorrect, clients, false);
    }

    @Test
    public void addVisitTest() throws IOException {
        String inputBeforeTomorrowVisit=Files.readString(
                Paths.get(
                        RestControllerTest.class.getResource("RestControllerTest/addVisitTest/input/beforeTomorrowVisit.json").getPath()
                )
        );
        String inputClosedVetVisit=Files.readString(
                Paths.get(
                        RestControllerTest.class.getResource("RestControllerTest/addVisitTest/input/closedVetVisit.json").getPath()
                )
        );
        String inputCollidedVisit=Files.readString(
                Paths.get(
                        RestControllerTest.class.getResource("RestControllerTest/addVisitTest/input/collidedVisit.json").getPath()
                )
        );
        String inputCorrectVisit=Files.readString(
                Paths.get(
                        RestControllerTest.class.getResource("RestControllerTest/addVisitTest/input/correctVisit.json").getPath()
                )
        );

        String outputVisitsCorrect=Files.readString(
                Paths.get(
                        RestControllerTest.class.getResource("RestControllerTest/addVisitTest/output/visits.json").getPath()
                )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request;

        request = new HttpEntity<>(inputBeforeTomorrowVisit, headers);
        String beforeTomorrowResponse=this.restTemplate.postForObject("http://localhost:" + port + "/visits/add/visit", request, String.class);
        request = new HttpEntity<>(inputClosedVetVisit, headers);
        String closedVetResponse=this.restTemplate.postForObject("http://localhost:" + port + "/visits/add/visit", request, String.class);
        request = new HttpEntity<>(inputCollidedVisit, headers);
        String collidedVisitResponse=this.restTemplate.postForObject("http://localhost:" + port + "/visits/add/visit", request, String.class);

        Assert.assertEquals("Before tomorrow", beforeTomorrowResponse);
        Assert.assertEquals("Closed vet", closedVetResponse);
        Assert.assertEquals("Collided visit", collidedVisitResponse);

        /*request = new HttpEntity<>(inputBeforeTomorrowVisit, headers);
        String beforeTomorrowResponse=this.restTemplate.postForObject("http://localhost:" + port + "/visits/add/visit", request, String.class);
        request = new HttpEntity<>(inputClosedVetVisit, headers);
        ResponseEntity closedVetResponse=this.restTemplate.postForObject("http://localhost:" + port + "/visits/add/visit", request, ResponseEntity.class);
        request = new HttpEntity<>(inputCollidedVisit, headers);
        ResponseEntity collidedVisitResponse=this.restTemplate.postForObject("http://localhost:" + port + "/visits/add/visit", request, ResponseEntity.class);

        request = new HttpEntity<>(inputCorrectVisit, headers);
        this.restTemplate.postForObject("http://localhost:" + port + "/visits/add/visit", request, void.class);
        String visits=this.restTemplate.getForObject("http://localhost:" + port + "/visits/get/all", String.class);

        Assert.assertEquals(HttpStatus.CONFLICT, beforeTomorrowResponse.getStatusCode());
        Assert.assertEquals("Before tomorrow", beforeTomorrowResponse.getBody());

        Assert.assertEquals(HttpStatus.CONFLICT, closedVetResponse.getStatusCode());
        Assert.assertEquals("Closed vet", closedVetResponse.getBody());

        Assert.assertEquals(HttpStatus.CONFLICT, collidedVisitResponse.getStatusCode());
        Assert.assertEquals("CollidedVisit", collidedVisitResponse.getBody());*/

        request = new HttpEntity<>(inputCorrectVisit, headers);
        this.restTemplate.postForObject("http://localhost:" + port + "/visits/add/visit", request, void.class);
        String visits=this.restTemplate.getForObject("http://localhost:" + port + "/visits/get/all", String.class);

        JSONAssert.assertEquals(outputVisitsCorrect, visits, false);
    }

    @Test
    public void changeAnimalOwnerTest() throws IOException {
        String animalsCorrect=Files.readString(
                Paths.get(
                        RestControllerTest.class.getResource("RestControllerTest/changeAnimalOwnerTest/output/Animals.json").getPath()
                )
        );

        String clientsCorrect=Files.readString(
                Paths.get(
                        RestControllerTest.class.getResource("RestControllerTest/changeAnimalOwnerTest/output/Clients.json").getPath()
                )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>("", headers);
        this.restTemplate.postForObject("http://localhost:" + port + "/animals/set/owner?clientId=1&animalId=2", request, void.class);

        String animals=this.restTemplate.getForObject("http://localhost:" + port + "/animals/get/all", String.class);
        String clients=this.restTemplate.getForObject("http://localhost:" + port + "/clients/get/all", String.class);

        JSONAssert.assertEquals(animalsCorrect, animals, false);
        JSONAssert.assertEquals(clientsCorrect, clients, false);

    }

    @Test
    public void deleteAnimalOwnerTest() throws IOException {
        String animalsCorrect=Files.readString(
                Paths.get(
                        RestControllerTest.class.getResource("RestControllerTest/deleteAnimalOwnerTest/output/Animals.json").getPath()
                )
        );

        String clientsCorrect=Files.readString(
                Paths.get(
                        RestControllerTest.class.getResource("RestControllerTest/deleteAnimalOwnerTest/output/Clients.json").getPath()
                )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>("", headers);
        this.restTemplate.postForObject("http://localhost:" + port + "/animals/delete/owner?&animalId=2", request, void.class);

        String animals=this.restTemplate.getForObject("http://localhost:" + port + "/animals/get/all", String.class);
        String clients=this.restTemplate.getForObject("http://localhost:" + port + "/clients/get/all", String.class);

        JSONAssert.assertEquals(animalsCorrect, animals, false);
        JSONAssert.assertEquals(clientsCorrect, clients, false);
    }

    @Test
    public void setVisitResultTest() throws IOException {
        String inputVisit=Files.readString(
                Paths.get(
                        RestControllerTest.class.getResource("RestControllerTest/setVisitResultTest/input/VisitResult.json").getPath()
                )
        );

        String visitsCorrect=Files.readString(
                Paths.get(
                        RestControllerTest.class.getResource("RestControllerTest/setVisitResultTest/output/Visits.json").getPath()
                )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(inputVisit, headers);
        this.restTemplate.postForObject("http://localhost:" + port + "/visits/set/result?&visitId=1", request, void.class);

        String visits=this.restTemplate.getForObject("http://localhost:" + port + "/visits/get/all", String.class);

        JSONAssert.assertEquals(visitsCorrect, visits, false);
    }

    @Test
    public void getAnimalVisitsTest() throws IOException {
        String visitsCorrect=Files.readString(
                Paths.get(
                        RestControllerTest.class.getResource("RestControllerTest/getAnimalVisitsTest/output/Visits.json").getPath()
                )
        );

        String visits=this.restTemplate.getForObject("http://localhost:" + port + "/visits/get/animal?animalId=1", String.class);
        String emptyVisits=this.restTemplate.getForObject("http://localhost:" + port + "/visits/get/animal?animalId=2", String.class);


        JSONAssert.assertEquals(visitsCorrect, visits, false);
        JSONAssert.assertEquals("[]", emptyVisits, false);
    }

    @Test
    public void getClientVisitsTest() throws IOException {
        String visitsCorrect=Files.readString(
                Paths.get(
                        RestControllerTest.class.getResource("RestControllerTest/getClientVisitsTest/output/Visits.json").getPath()
                )
        );

        String visits=this.restTemplate.getForObject("http://localhost:" + port + "/visits/get/client?clientId=1", String.class);
        String emptyVisits=this.restTemplate.getForObject("http://localhost:" + port + "/visits/get/client?clientId=2", String.class);


        JSONAssert.assertEquals(visitsCorrect, visits, false);
        JSONAssert.assertEquals("[]", emptyVisits, false);
    }

}