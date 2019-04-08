package net.guides.springboot2.springboot2jpacrudexample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.gamestore.ualison.springboot2.Application;
import com.gamestore.ualison.springboot2.model.Game;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllEmployees() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/employees",
				HttpMethod.GET, entity, String.class);
		
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetEmployeeById() {
		Game game = restTemplate.getForObject(getRootUrl() + "/employees/1", Game.class);
		System.out.println(game.getName());
		assertNotNull(game);
	}

	@Test
	public void testCreateEmployee() {
		Game game = new Game();
		game.setPrice("45");
		game.setName("Call Of Duty");
		game.setPublisher("Actvision");

		ResponseEntity<Game> postResponse = restTemplate.postForEntity(getRootUrl() + "/employees", game, Game.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateEmployee() {
		int id = 1;
		Game game = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Game.class);
		game.setName("admin1");
		game.setPrice("admin2");

		restTemplate.put(getRootUrl() + "/employees/" + id, game);

		Game updatedEmployee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Game.class);
		assertNotNull(updatedEmployee);
	}

	@Test
	public void testDeleteEmployee() {
		int id = 2;
		Game game = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Game.class);
		assertNotNull(game);

		restTemplate.delete(getRootUrl() + "/employees/" + id);

		try {
			game = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Game.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}
