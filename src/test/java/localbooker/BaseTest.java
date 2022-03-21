package localbooker;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import model.User;

public class BaseTest {

    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri("http://localhost:3001/")
            .build();
    public static final User admin = new User("admin", "password123");
}
