package com.comcast.lcs.service;

import com.comcast.lcs.request.LCSRequest;
import com.comcast.lcs.response.LCSResponse;


public interface LCSService {
	public LCSResponse findLCS(LCSRequest lcsRequest);
	public LCSResponse dynamicLCS(LCSRequest lcsRequest);
}