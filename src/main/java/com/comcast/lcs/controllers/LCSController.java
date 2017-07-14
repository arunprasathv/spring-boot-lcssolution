package com.comcast.lcs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.comcast.lcs.request.LCSRequest;
import com.comcast.lcs.response.LCSResponse;
import com.comcast.lcs.service.LCSService;


@RestController
@RequestMapping(value = "/lcs")
public class LCSController {

	@Autowired
	private LCSService lcsService;
	@RequestMapping(method = RequestMethod.POST, value = "/findLCS", headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<LCSResponse> findLCS(
			@RequestBody LCSRequest lcsRequest) {
		return new ResponseEntity<LCSResponse>(lcsService.findLCS(lcsRequest),HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/dynamicLCS", headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseEntity<LCSResponse> dynamicLCS(
			@RequestBody LCSRequest lcsRequest) {
		return new ResponseEntity<LCSResponse>(lcsService.dynamicLCS(lcsRequest),HttpStatus.OK);
	}
}
