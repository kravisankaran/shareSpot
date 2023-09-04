package com.kravisankaran.sharespot.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Tracks {
	@Override
	public String toString() {
		return "Tracks [href=" + href + ", total=" + total + "]";
	}
	@JsonProperty("href")
	String href;
	@JsonProperty("total")
	int total;
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
