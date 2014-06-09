package com.linagora.pocejbcawsclient.ssl;

import static com.linagora.pocejbcawsclient.ssl.KeyStoreSetter.KEY_STORE_PASSWORD;
import static com.linagora.pocejbcawsclient.ssl.KeyStoreSetter.KEY_STORE_PATH;
import static com.linagora.pocejbcawsclient.ssl.KeyStoreSetter.KEY_STORE_TYPE;
import static com.linagora.pocejbcawsclient.ssl.KeyStoreSetter.TRUST_STORE_PASSWORD;
import static com.linagora.pocejbcawsclient.ssl.KeyStoreSetter.TRUST_STORE_PATH;
import static com.linagora.pocejbcawsclient.ssl.KeyStoreSetter.TRUST_STORE_TYPE;
import static com.linagora.pocejbcawsclient.ssl.KeyStoreSetter.setKeyStore;
import static com.linagora.pocejbcawsclient.ssl.KeyStoreSetter.setTrustStore;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import com.linagora.pocejbcawsclient.conf.Configuration;

public class KeyStoreSetterTest {
	
	@Test
	public void testDefaultStoreSetter() {		
		setKeyStore();
		setTrustStore();
		
		assertThat(System.getProperty(TRUST_STORE_TYPE))
			.isEqualTo(Configuration.trustType());
		assertThat(System.getProperty(KEY_STORE_TYPE))
			.isEqualTo(Configuration.keyType());

		assertThat(System.getProperty(TRUST_STORE_PATH))
			.isEqualTo(Configuration.trustPath());
		
		assertThat(System.getProperty(KEY_STORE_PATH))
			.isEqualTo(Configuration.keyPath());
		
		assertThat(System.getProperty(TRUST_STORE_PASSWORD))
			.isEqualTo(Configuration.trustPassword());
		assertThat(System.getProperty(KEY_STORE_PASSWORD))
			.isEqualTo(Configuration.keyPassword());
	}
}
