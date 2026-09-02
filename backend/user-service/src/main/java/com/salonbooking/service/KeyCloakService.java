package com.salonbooking.service;

import com.salonbooking.payload.dto.Credential;
import com.salonbooking.payload.dto.SignupDTO;
import com.salonbooking.payload.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class KeyCloakService {

    private static final String KEYCLOAK_BASE_URL="http://localhost:8080";
    private static final String KEYCLOAK_ADMIN_API=KEYCLOAK_BASE_URL+"/admin/realms/master/users";
    private static final String TOKEN_URL=KEYCLOAK_BASE_URL+"/realms/master/protocol/openid-connect/token";

    private static final String CLIENT_ID="salon-booking-client";
    private static final String CLIENT_SECRET="M3phI22wpoWO8PRUnnlzWIoRgsrUUJaqBUNxf4PsmVwg9uY0yS3n5etOtEBjDalMravOP6KHGlw1pWQ6i4OZkY";
    private static final String GRANT_TYPE="password";
    private static final String scope = "openid profile email";
    private static final String username = "golu";
    private static final String password = "admin12";
    private static final String clientId = "bdb35321-b390-4505-8dea-63b9be4df827";


    private final RestTemplate restTemplate;

    public void createUser(SignupDTO signupDTO) throws Exception{

        String ACCESS_TOKEN="";

        Credential credential=new Credential();
        credential.setTemporary(false);
        credential.setType("password");
        credential.setValue(signupDTO.getPassword());

        UserRequest userRequest=new UserRequest();
        userRequest.setUsername(signupDTO.getUsername());
        userRequest.setEmail(signupDTO.getEmail());
        userRequest.setEnabled(true);
        userRequest.setLastName(signupDTO.getLastName());
        userRequest.setFirstName(signupDTO.getFirstName());

        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(ACCESS_TOKEN);

        HttpEntity<UserRequest> requestEntity=new HttpEntity<>(userRequest,headers);

        ResponseEntity<String> response = restTemplate.exchange(
                KEYCLOAK_ADMIN_API,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if(response.getStatusCode()==HttpStatus.CREATED){
            System.out.println("User created successfully");
        }

    }

}
