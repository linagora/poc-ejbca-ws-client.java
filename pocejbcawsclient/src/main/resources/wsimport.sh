#JAVA_OPTIONS="$JAVA_OPTIONS -Djavax.net.ssl.trustStore=romeu.p12 -Djavax.net.ssl.trustStoreType=PKCS12 -Djavax.net.ssl.trustStorePassword=ejbca -Djavax.net.ssl.keyStore=romeu.p12 -Djavax.net.ssl.keyStoreType=PKCS12 -Djavax.net.ssl.keyStorePassword=ejbca" wsimport -p com.linagora.pocejbcawsclient.gen -s src-gen -XadditionalHeaders -Xnocompile -XdisableSSLHostnameVerification https://ejbca:8443/ejbca/ejbcaws/ejbcaws?wsdl

#JAVA_OPTIONS="$JAVA_OPTIONS -Djavax.net.ssl.trustStore=romeu.jks -Djavax.net.ssl.trustStorePassword=pasword -Djavax.net.ssl.keyStore=romeu.p12 -Djavax.net.ssl.keyStoreType=PKCS12 -Djavax.net.ssl.keyStorePassword=ejbca" wsimport -p com.linagora.pocejbcawsclient.gen -s src-gen -XadditionalHeaders -Xnocompile -XdisableSSLHostnameVerification https://ejbca:8443/ejbca/ejbcaws/ejbcaws?wsdl

#JAVA_OPTIONS="$JAVA_OPTIONS -Djavax.net.ssl.trustStore=romeu.jks -Djavax.net.ssl.trustStorePassword=password -Djavax.net.ssl.keyStore=romeu.jks -Djavax.net.ssl.keyStorePassword=password" wsimport -p com.linagora.pocejbcawsclient.gen -s src-gen -XadditionalHeaders -Xnocompile -XdisableSSLHostnameVerification https://ejbca:8443/ejbca/ejbcaws/ejbcaws?wsdl

#java -classpath /usr/lib/jvm/java-8-oracle/lib/tools.jar -Djavax.net.ssl.trustStore=./romeu.jks -Djavax.net.ssl.trustStorePassword="password" -Djavax.net.ssl.keyStore=./romeu.jks -Djavax.net.ssl.keyStorePassword="password" com.sun.tools.internal.ws.WsImport https://ejbca:8443/ejbca/ejbcaws/ejbcaws?wsdl -p com.linagora.pocejbcawsclient.gen -s src-gen -XadditionalHeaders -Xnocompile -XdisableSSLHostnameVerification

wsimport -p com.linagora.pocejbcawsclient.gen -s src-gen \
    -XadditionalHeaders -Xnocompile -XdisableSSLHostnameVerification \
    -wsdllocation https://ejbca:8443/ejbca/ejbcaws/ejbcaws?wsdl \
    http://ejbca:8080/ejbca/ejbcaws/ejbcaws?wsdl
