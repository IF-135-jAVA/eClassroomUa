package com.softserve.betterlearningroom.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

//@Profile("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class CriterionControllerTest {
    @Autowired
    private TestRestTemplate template;
    @Autowired
    private MockMvc mvc;
    private String token;
    @LocalServerPort
    private int port;

//    @BeforeEach
//    public void setUp() throws Exception {
//        JacksonJsonParser jsonParser = new JacksonJsonParser();
//        ObjectMapper mapper = new ObjectMapper();
//        AuthRequest request = new AuthRequest();
//        request.setLogin("jurok3x@gmail.com");
//        request.setPassword("yawinpassword");
//
//        String jsonRequestBody = mapper.writeValueAsString(request);
//
//        ResultActions result = mvc
//                .perform(post("/api/auth/login?role=teacher").content(jsonRequestBody).contentType(MediaType.APPLICATION_JSON)
//                        .accept("text/plain;charset=UTF-8"));
//
//         token = "Bearer " + result.andReturn().getResponse().getContentAsString();
//    }
//
//    private HttpHeaders createHttpHeaders()
//    {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add("Authorization", token);
//        return headers;
//    }
//    private String createURLWithPort(String url)
//    {
//
//        return "http://localhost:" + port + url;
//    }
//    @Test
//    void getAll() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add("Authorization", token);
//
//        HttpEntity entity = new HttpEntity<>(null, headers );
//
//
//        ResponseEntity responseEntity = template.exchange(createURLWithPort(
//                "/api/classrooms/0/topics/0/materials/0/criterions"), HttpMethod.GET, entity, String.class);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());;
//    }


    @Test
    void getById() {


    }

    @Test
    void create() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.add("Authorization", token);
//        CriterionDTO criterionDTO = CriterionDTO.builder()
//                .id(12)
//                .materialIdDTO(4)
//                .title("title")
//                .description("desc")
//                .build();
//        HttpEntity entity = new HttpEntity<>(criterionDTO, headers );
//
//
//        ResponseEntity responseEntity = template.exchange(createURLWithPort(
//                "/api/classrooms/0/topics/0/materials/0/criterions"), HttpMethod.GET, entity, String.class);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());;
    }

    @Test
    void update() {
    }
//    private HttpEntity getSecuredHTTPEntity (){
//
//        ObjectNode loginRequest = mapper.createObjectNode();
//        loginRequest.put("username","name");
//        loginRequest.put("password","password");
//        ValueNodes.JsonNode loginResponse = template.postForObject("/authenticate", loginRequest.toString(), ValueNodes.JsonNode.class);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        headers.add("X-Authorization", "Bearer " + loginResponse.get("token").textValue());
//        headers.add("Content-Type", "application/json");
//        return new HttpEntity<>(null, headers);
//    }
}