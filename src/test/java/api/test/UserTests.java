package api.test;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.userEndpointsMethodImplementation;
import api.payload.User;
import io.restassured.response.Response;
import net.datafaker.Faker;

public class UserTests {
	
	Faker faker;
	User userPayload;
	public Logger logger;
	
	@BeforeClass(alwaysRun = true)
	public void SetupDate() {

	    logger = LogManager.getLogger(UserTests.class);

	    faker = new Faker();

	    userPayload = new User();

	    userPayload.setId(faker.idNumber().hashCode());
	    userPayload.setUsername(faker.name().username());
	    userPayload.setFirstname(faker.name().firstName());
	    userPayload.setLastname(faker.name().lastName());
	    userPayload.setEmail(faker.internet().safeEmailAddress());
	    userPayload.setPassword(faker.internet().password(5,10));
	    userPayload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test(priority=1,groups={"smoke","create"})
	public void testPostUser () {
		
		logger.info("*******Creating User********");
		Response response= userEndpointsMethodImplementation.Createuser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		System.out.println("Username: " + this.userPayload.getUsername());
		System.out.println("Webhook Trigger Testing testing");
		
		logger.info("*******User is Created********");
	}
	
	@Test(priority=2,groups={"smoke","read"})
	public void getTestuserByName () {
		
		logger.info("*******Reading User info********");
		Response response = userEndpointsMethodImplementation.Readeuser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("*******User info is displayed********");
	}
	
	@Test(priority=3,groups={"regression","update"})
	public void testUpdateuserByName () {
		
		logger.info("*******Updating User********");
		
		// update data using payload
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = userEndpointsMethodImplementation.Updateeuser(this.userPayload.getUsername(), userPayload);
		response.then().log().all();
		//  response.then().log().body().statusCode(200);   Restassured assertion this also we can use
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*******User is updated********");
		
		// Checking data after update
		Response responseAfterupdate = userEndpointsMethodImplementation.Readeuser(this.userPayload.getUsername());
		Assert.assertEquals(responseAfterupdate.getStatusCode(), 200);
		
	}
	
	@Test(priority=4, groups={"regression","delete"})
	public void testDeleteUserByName () {
		
		logger.info("*******Deleting User********");
		
		Response response = userEndpointsMethodImplementation.Deleteeuser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("*******User is deleted********");
	}

}
