package com.swaggy7.licenseweb.bean;

public class LicenseTransmit {

	private String name;
	private int port;

	public LicenseTransmit(String name, int port) {
		super();
		this.name = name;
		this.port = port;
	}

	@Override
	public String toString() {
		return "LicenseTransmit{" + "name='" + name + '\'' + ", port=" + port + '}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
