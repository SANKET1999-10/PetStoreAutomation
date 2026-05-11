package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import api.endpoints.userEndpointsMethodImplementation;
import api.endpoints.userEndpointsMethodImplementation2;
import api.payload.User;
import io.restassured.response.Response;
import net.datafaker.Faker;

public class UserTests2 {
	
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeTest
	public void SetupDate () {
		
		faker = new Faker ();
		userPayload = new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		// logs
		
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser () {
		
		logger.info("*******Creating User********");
		Response response= userEndpointsMethodImplementation2.Createuser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("Username: " + this.userPayload.getUsername());
		
		logger.info("*******User is Created********");
	}
	
	@Test(priority=2)
	public void getTestuserByName () {
		
		logger.info("*******Reading User info********");
		Response response = userEndpointsMethodImplementation2.Readeuser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("*******User info is displayed********");
	}
	
	@Test(priority=3)
	public void testUpdateuserByName () {
		
		logger.info("*******Updating User********");
		
		// update data using payload
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = userEndpointsMethodImplementation2.Updateeuser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		//  response.then().log().body().statusCode(200);   Restassured assertion this also we can use
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*******User is updated********");
		
		// Checking data after update
		Response responseAfterupdate = userEndpointsMethodImplementation2.Readeuser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterupdate.getStatusCode(), 200);
		
	}
	
	@Test(priority=4)
	public void testDeleteUserByName () {
		
		logger.info("*******Deleting User********");
		
		Response response = userEndpointsMethodImplementation2.Deleteeuser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*******User is deleted********");
	}

}
