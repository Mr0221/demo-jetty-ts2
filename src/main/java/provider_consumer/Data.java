package provider_consumer;

import java.util.concurrent.ConcurrentHashMap;

public class Data {
	private String id ;
	private String value;
	public Data(String string, String string2) {
		this.id = string;
		this.value = string2;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
