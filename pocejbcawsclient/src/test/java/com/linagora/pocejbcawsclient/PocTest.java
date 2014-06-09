package com.linagora.pocejbcawsclient;

import org.junit.Before;
import org.junit.Test;

import com.linagora.pocejbcawsclient.ssl.SSLRelaxer;


public class PocTest {

	@Before
	public void setUp() {
		SSLRelaxer.relaxSSLVerification();
	}
	
	@Test
	public void testMain() {
		Poc.main(null);
	}
}
