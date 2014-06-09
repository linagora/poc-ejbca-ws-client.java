package com.linagora.pocejbcawsclient.conf;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public final class Configuration {

	private static final String TMPDIR = System.getProperty("java.io.tmpdir");
	private static final String DEFAULT_DN = "CN=ManagementCA,C=SE";
	private static final String DEFAULT_ENTITY_PROFILE = "EP_USER-AUTH";
	private static final String DEFAULT_CERTIFICATE_PROFILE = "CP_USER-AUTH";
	private static final String DEFAULT_CA_NAME = "ManagementCA";
	private static final String DEFAULT_CONFIGURATION_FILE_PATH = TMPDIR + "/ejbca-ws-client.properties";
	private static final String DEFAULT_WSDL_URL = "https://ejbca:8443/ejbca/ejbcaws/ejbcaws?wsdl";
	private static final String DEFAULT_KEY_TYPE = "PKCS12";
	private static final String DEFAULT_KEY_PATH = TMPDIR + "/ws-login.p12";
	private static final String DEFAULT_KEY_PASSWORD = "ejbca";
	private static final String DEFAULT_TRUST_TYPE = "JKS";
	private static final String DEFAULT_TRUST_PATH = TMPDIR + "/ejbca-truststore.jks";
	private static final String DEFAULT_TRUST_PASSWORD = "password";
//	private static final String DEFAULT_WEB_CERT_PATH = "/tmp/ws-login.cert";
	
	
	private static final Properties PROPERTIES;

	static {
		PROPERTIES = new Properties();
		File configFile = new File(configFilePath());
		if (configFile.isFile()) {
			try {
				PROPERTIES.load(new FileInputStream(configFile));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	}

	private Configuration() { }
	
	public static String configFilePath() {
		return getConfigurationEntry("configuration.file", DEFAULT_CONFIGURATION_FILE_PATH);
	}

	public static String wsdlURL() {
		return getConfigurationEntry("ejbca.wsdl.url", DEFAULT_WSDL_URL);
	}
	
	public static String keyType() {
		return getConfigurationEntry("keyType", DEFAULT_KEY_TYPE);
	}
	
	public static String keyPath() {
		return getConfigurationEntry("keyPath", DEFAULT_KEY_PATH);
	}
	
	public static String keyPassword() {
		return getConfigurationEntry("keyPassword", DEFAULT_KEY_PASSWORD);
	}
	
	public static String trustType() {
		return getConfigurationEntry("trustType", DEFAULT_TRUST_TYPE);
	}
	
	public static String trustPath() {
		return getConfigurationEntry("trustPath", DEFAULT_TRUST_PATH);
	}
	
	public static String trustPassword() {
		return getConfigurationEntry("trustPassword", DEFAULT_TRUST_PASSWORD);
	}	

	public static String caName() {
		return getConfigurationEntry("caName", DEFAULT_CA_NAME);
	}

	public static String certificateProfile() {
		return getConfigurationEntry("certificateProfile", DEFAULT_CERTIFICATE_PROFILE);
	}

	public static String entityProfile() {
		return getConfigurationEntry("entitiyProfile", DEFAULT_ENTITY_PROFILE);
	}

	public static String subjectDN() {
		return getConfigurationEntry("DN", DEFAULT_DN);
	}

	private static String getConfigurationEntry(String configurationEntry,
			String defaultValue) {
		String configurationFilePath = System.getProperty(configurationEntry);
		if (isNullOrEmpty(configurationFilePath)) {
			configurationFilePath = PROPERTIES.getProperty(configurationEntry); 
			if (isNullOrEmpty(configurationFilePath)) {
				return defaultValue;
			}
		}
		return configurationFilePath;
	}
}
