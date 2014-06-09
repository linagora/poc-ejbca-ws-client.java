package com.linagora.pocejbcawsclient.conf;

import static com.linagora.pocejbcawsclient.conf.Configuration.configFilePath;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class ConfigurationTest {

	@Test
	public void testhasDefaults() {
		assertThat(configFilePath()).isNotEmpty();
	}
	
	@Test
	public void testFetchesSysProperties() {
		System.setProperty("configuration.file", "/tmp/config.properties");
		assertThat(configFilePath()).isEqualTo("/tmp/config.properties");
	}

}
