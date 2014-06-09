package com.linagora.pocejbcawsclient;

import com.google.common.base.Stopwatch;
import com.linagora.pocejbcawsclient.ssl.SSLRelaxer;

public class Poc {		
	public static void main(String[] args) {
		SSLRelaxer.relaxSSLVerification();
		
		Client client = new Client();
				
		System.out.println("Creation de 500 Certificats");
		final Stopwatch stopwatch = Stopwatch.createStarted();
		create500Certs(client);
		stopwatch.stop();
		
		System.out.println("Temps pour la creation de 500 certificats: " + stopwatch);
		stopwatch.reset();
		
		System.out.println("delestage de 500 certificats");
		stopwatch.start();
		delete500Certs(client);
		stopwatch.stop();
		
		System.out.println("Temps pour le delestage de 500 certificats: " + stopwatch);
		stopwatch.reset();
		
	}
	
	public static void create500Certs(Client client) {
		for (int i = 1500; i > 1000; i--) {
			client.createUser(i);
			client.generateUserCert(i);
		}
	}
	public static void delete500Certs(Client client) {
		for (int i = 1500; i > 1000; i--) {
			client.revoke(i);
		}
	}
}
