package com.linagora.pocejbcawsclient.ssl;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public final class SSLRelaxer {
	
	private final static HostnameVerifier trustAllHostsNames = new HostnameVerifier() {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	};
	
	static TrustManager[] trustAllCerts = {new X509TrustManager() {
		public X509Certificate[] getAcceptedIssuers() { return null; }
		public void checkClientTrusted(X509Certificate[] certs, String authType) { return; }
		public void checkServerTrusted(X509Certificate[] certs, String authType) { return; }
	}};

	/**
	 * Stops Verifications of certificate domain, instead of doing this we should have a signed certificate or add our self signed certificate to the machine's jre cacerts
	 */
	public final static void relaxSSLVerification() {
		cheatCertVerification();
		disableHostVerification();
	}

	public final static void disableHostVerification() {		
		// Create all-trusting host name verifier
		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(trustAllHostsNames);
	}
	
	public final static void cheatCertVerification() {
		try
		{	
			String defaultAlgorithm = KeyManagerFactory.getDefaultAlgorithm();
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(defaultAlgorithm);
			kmf.init(KeyStoreSetter.setKeyStore(), KeyStoreSetter.getPassword().toCharArray());
			KeyManager km[] = kmf.getKeyManagers();

			String defaultAlgorithm2 = TrustManagerFactory.getDefaultAlgorithm();
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(defaultAlgorithm2);
			tmf.init(KeyStoreSetter.setTrustStore());
			TrustManager tm[] = tmf.getTrustManagers();

			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(km, tm, new SecureRandom());

			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
		} catch (Exception e) {
			throw new RuntimeException(e.getCause());
		}
	}
	
	

}
