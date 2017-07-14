package com.comcast.lcs.response;

import java.util.List;

import com.comcast.lcs.request.LCSValue;

public class LCSResponse {
	private List<LCSValue> values;

	public List<LCSValue> getValues() {
		return values;
	}

	public void setValues(List<LCSValue> values) {
		this.values = values;
	}

}