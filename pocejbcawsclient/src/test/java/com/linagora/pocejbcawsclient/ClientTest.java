package com.linagora.pocejbcawsclient;

import static com.linagora.pocejbcawsclient.Client.STATUS_NEW;
import static com.linagora.pocejbcawsclient.Client.createUserData;
import static com.linagora.pocejbcawsclient.Client.createUserMatch;
import static com.linagora.pocejbcawsclient.Client.userName;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.linagora.pocejbcawsclient.gen.KeyStore;
import com.linagora.pocejbcawsclient.gen.UserDataVOWS;
import com.linagora.pocejbcawsclient.gen.UserMatch;
import com.linagora.pocejbcawsclient.ssl.SSLRelaxer;

public class ClientTest {
	private Client client;
	
	@Before
	public void setUp() {
		SSLRelaxer.relaxSSLVerification();
		client = new Client();
	}

	@Test
	public void testUserName() {
		assertThat(userName(0)).isEqualTo("test0000");
	}

	@Test
	public void testCreateUserMatch() {
		final UserMatch actual = createUserMatch(0);
		assertThat(actual.getMatchtype()).isEqualTo(0);
		assertThat(actual.getMatchvalue()).isEqualTo("test0000");
		assertThat(actual.getMatchwith()).isEqualTo(0);
	}
	
	@Test
	public void testCreateUserData() {
		final UserDataVOWS userData = createUserData(0);
		assertThat(userData.getUsername()).isEqualTo("test0000");
		assertThat(userData.getEmail()).isEqualTo("test0000@laposte.fr");
		assertThat(userData.getPassword()).isEqualTo("password");
		assertThat(userData.getTokenType()).isEqualTo("P12");
		assertThat(userData.getStatus()).isEqualTo(STATUS_NEW);
	}

	public void testCreateUser(int userNumber) {
		client.createUser(userNumber);
		final UserDataVOWS userData = client.findUser(userNumber);
		assertThat(userData.getUsername()).isEqualTo(userName(userNumber));
		assertThat(userData.getEmail()).isEqualTo(userName(userNumber) + "@laposte.fr");
		assertThat(userData.getTokenType()).isEqualTo("P12");
		assertThat(userData.getStatus()).isEqualTo(STATUS_NEW);
	}

	@Test
	public void testCreateUser() {
		testCreateUser(0);
	}

	
	@Test
	public void testGenerateUserCert() {
		testCreateUser(1);
		KeyStore userCert = client.generateUserCert(1);
		assertThat(userCert).isNotNull();
	}

	@Test
	public void testRenew() {
		testCreateUser(2);
		client.renew(2);
		UserDataVOWS userData = client.findUser(2);
		assertThat(userData.getStatus()).isEqualTo(STATUS_NEW);
	}

	@Test
	public void testRevoke() {
		testCreateUser(3);
		client.revoke(3);
		UserDataVOWS userData = client.findUser(3);
		assertThat(userData.getStatus()).isNotEqualTo(STATUS_NEW);
	}

}
