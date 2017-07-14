package com.comcast.lcs.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.comcast.lcs.LCSApplication;
import com.comcast.lcs.controllers.LCSController;
import com.comcast.lcs.request.LCSRequest;
import com.comcast.lcs.request.LCSValue;
import com.comcast.lcs.response.LCSResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { LCSApplication.class })
@TestPropertySource({ "classpath:application.properties" })
//@Ignore

public class LCSControllerTest {
	
	@Autowired
	private LCSController lcsController;
	
	@Test
	public void testFindLCS() {
		String str1 = "commcast";
		String str2 = "commcaster";
		LCSRequest lcsRequest = new LCSRequest();
		List<LCSValue> lcsList = new ArrayList<>();
		LCSValue lcs = new LCSValue();
		lcs.setValue(str1);
		lcsList.add(lcs);
		lcs = new LCSValue();
		lcs.setValue(str2);
		lcsList.add(lcs);
		ResponseEntity<LCSResponse> lcResponseEntity = lcsController.findLCS(lcsRequest);
		System.out.println("itgListResponse" + lcResponseEntity.getBody().toString());		
	}
	@Test
	public void testDynamicLCS() {
		String str1 = "commcast";
		String str2 = "commcaster";
		LCSRequest lcsRequest = new LCSRequest();
		List<LCSValue> lcsList = new ArrayList<>();
		LCSValue lcs = new LCSValue();
		lcs.setValue(str1);
		lcsList.add(lcs);
		lcs = new LCSValue();
		lcs.setValue(str2);
		lcsList.add(lcs);
		lcsRequest.setValues(lcsList);
		ResponseEntity<LCSResponse> lcResponseEntity = lcsController.dynamicLCS(lcsRequest);
		System.out.println("itgListResponse" + lcResponseEntity.getBody().toString());		
	}
}
