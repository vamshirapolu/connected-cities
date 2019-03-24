package com.cc.routefinder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RouteFinderControllerTest {

	@Autowired
	WebApplicationContext webApplicationContext;

	MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void connectedRoutesTest() throws Exception {
		assertEquals(checkRouteExist("Boston", "New York"), "yes");
		assertEquals(checkRouteExist("Boston", "Philadelphia"), "yes");
		assertEquals(checkRouteExist("Philadelphia", "Albany"), "no");
	}

	private String checkRouteExist(String origin, String destication) throws Exception {
		MvcResult result = this.mockMvc
				.perform(get("/connected?origin={origin}&destination={destination}", origin, destication))
				.andExpect(status().isOk()).andReturn();
		String content = result.getResponse().getContentAsString();
		assertNotNull(content);
		return content;
	}

}
