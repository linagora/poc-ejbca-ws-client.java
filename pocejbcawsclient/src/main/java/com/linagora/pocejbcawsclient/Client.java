package com.linagora.pocejbcawsclient;

import java.net.URL;

import org.fest.util.VisibleForTesting;

import com.google.common.base.Preconditions;
import com.linagora.pocejbcawsclient.conf.Configuration;
import com.linagora.pocejbcawsclient.gen.EjbcaWS;
import com.linagora.pocejbcawsclient.gen.EjbcaWSService;
import com.linagora.pocejbcawsclient.gen.KeyStore;
import com.linagora.pocejbcawsclient.gen.UserDataVOWS;
import com.linagora.pocejbcawsclient.gen.UserMatch;

public class Client {
	protected static final int STATUS_NEW = 10;

	private final URL wsURL;
	private EjbcaWS ejbcaWS = null;

	
	public Client(String wsURL) {
		Preconditions.checkNotNull(wsURL);
		try {
			this.wsURL = new URL(wsURL);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Client() {
		this(Configuration.wsdlURL());
	}
	
	public static String userName(int userNumber) {
		return "test" + String.format("%04d", userNumber);
	}
	
	public void renew(int userNumber) {
		final UserDataVOWS userFound = findUser(userNumber);
		userFound.setStatus(STATUS_NEW);
		userFound.setPassword("password");
		editUser(userFound);
	}
	
	public void revoke(int userNumber) {
		try {
			ejbaWs().revokeUser(
					userName(userNumber),
					0,
					false);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public KeyStore generateUserCert(int userNumber) {
		try {
			return ejbaWs().pkcs12Req(
					userName(userNumber),
					"password",
					null,
					"2048",
					"RSA");
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void createUser(int userNumber) {
		editUser(createUserData(userNumber));
	}
	
	public void editUser(final UserDataVOWS userData) {
		try {
			ejbaWs().editUser(userData);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public UserDataVOWS findUser(int userNumber) {
		final UserMatch userMatch = createUserMatch(userNumber);
		try {
			return ejbaWs().findUser(userMatch).get(0);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static UserMatch createUserMatch(int userNumber) {
		UserMatch userMatch = new UserMatch();
		userMatch.setMatchtype(0);
		userMatch.setMatchwith(0);
		userMatch.setMatchvalue(userName(userNumber));
		return userMatch;
	}

	public static UserDataVOWS createUserData(int userNumber) {
		final String userName = userName(userNumber);
		final UserDataVOWS userData = new UserDataVOWS();
		userData.setEmail(userName + "@laposte.fr");
		userData.setUsername(userName);
		userData.setPassword("password");

		userData.setCaName(Configuration.caName());
		userData.setCertificateProfileName(Configuration.certificateProfile());
		userData.setEndEntityProfileName(Configuration.entityProfile());
		userData.setSubjectDN(Configuration.DN());

		userData.setTokenType("P12");
		userData.setSubjectAltName("");
		userData.setClearPwd(false);
		userData.setKeyRecoverable(false);
		userData.setSendNotification(false);
		userData.setStatus(STATUS_NEW);
		return userData;
	}

	@VisibleForTesting
	protected EjbcaWS ejbaWs() {
		if(ejbcaWS == null) {
			final EjbcaWSService ejbcaWSService = new EjbcaWSService(wsURL);
			ejbcaWS = ejbcaWSService.getEjbcaWSPort();
		}
		return ejbcaWS; 
	}

}
