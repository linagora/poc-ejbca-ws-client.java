package com.linagora.pocejbcawsclient.ssl;

import static com.linagora.pocejbcawsclient.conf.Configuration.keyPassword;
import static com.linagora.pocejbcawsclient.conf.Configuration.keyPath;
import static com.linagora.pocejbcawsclient.conf.Configuration.keyType;
import static com.linagora.pocejbcawsclient.conf.Configuration.trustPassword;
import static com.linagora.pocejbcawsclient.conf.Configuration.trustPath;
import static com.linagora.pocejbcawsclient.conf.Configuration.trustType;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import com.google.common.base.Strings;

public class KeyStoreSetter {

	protected static final String KEY_STORE_PASSWORD = "javax.net.ssl.keyStorePassword";
	protected static final String KEY_STORE_PATH = "javax.net.ssl.keyStore";
	protected static final String KEY_STORE_TYPE = "javax.net.ssl.keyStoreType";
	protected static final String TRUST_STORE_PASSWORD = "javax.net.ssl.trustStorePassword";
	protected static final String TRUST_STORE_PATH = "javax.net.ssl.trustStore";
	protected static final String TRUST_STORE_TYPE = "javax.net.ssl.trustStoreType";
//  protected static final String WEB_CERT_PATH = "com.linagora.com.ejbca.webCertPath";
	
	
	
	public static final KeyStore setTrustStore(final String type, final String path, final String password) {
//		 setIfEmpty("javax.net.debug", "ssl,handshake,verbose,keymanager,trustmanager,failure");

		setIfEmpty(TRUST_STORE_TYPE, type);
		setIfEmpty(TRUST_STORE_PATH, path); 
		setIfEmpty(TRUST_STORE_PASSWORD, password);
//		setIfEmpty(KEY_STORE_PASSWORD, webCertPath);
		
		try {
			KeyStore keyStore = buildKeystore(type, path, password);
//			keyStore.setCertificateEntry("ejbca-web-cert", buildCertificate(webCertPath));
			return keyStore;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static final KeyStore setKeyStore(final String type, final String path, final String password) {
		setIfEmpty(KEY_STORE_TYPE, type);
		setIfEmpty(KEY_STORE_PATH, path); 
		setIfEmpty(KEY_STORE_PASSWORD, password);
		
		try {
			return buildKeystore(type, path, password);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}


	private static KeyStore buildKeystore(final String type, final String path, final String password) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, FileNotFoundException, IOException {
		KeyStore keyStore = KeyStore.getInstance(type);
		keyStore.load(new FileInputStream(path), password.toCharArray());
		return keyStore;
	}
	
	
	public static Certificate buildCertificate(final String certPath) throws IOException, CertificateException {
		DataInputStream dataInputStream = new DataInputStream(new FileInputStream(certPath));
		byte[] bytes = new byte[dataInputStream.available()];
		dataInputStream.readFully(bytes);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
		dataInputStream.close();
		return CertificateFactory.getInstance("X.509").generateCertificate(inputStream);
	}
	
	public static final KeyStore setKeyStore() {
		return setKeyStore(keyType(), keyPath(), keyPassword());
	}
	
	public static final KeyStore setTrustStore() {
		return setTrustStore(trustType(), trustPath(), trustPassword());
	}
	
	private static final void setIfEmpty(final String propertyName, final String value) {
		if(Strings.isNullOrEmpty(System.getProperty(propertyName))) {
			System.setProperty(propertyName, value);
		}
	}
	
	
	public static final String getStorePath() {
		return System.getProperty(KEY_STORE_PATH); 
	}
	
	public static final String getPassword() {
		return System.getProperty(KEY_STORE_PASSWORD); 
	}
		
	public static final String getType() {
		return System.getProperty(KEY_STORE_TYPE); 
	}
}
