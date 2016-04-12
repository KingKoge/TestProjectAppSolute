package com.ssru.mrsmile.testprojectappsolute.presenter;


import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

/**
 * Created by Mr.Smile on 11/4/2559.
 */

@SuppressWarnings("unused")
public class ClientBuilder {

    private static ClientBuilder clientBuilder;

    private static OkHttpClient client;

    private ClientBuilder(){

    }

    public static ClientBuilder getIntance(){
        if(clientBuilder == null){
            clientBuilder = new ClientBuilder();
        }
        return clientBuilder;
    }

    @SuppressWarnings("null")
    private OkHttpClient configureClient(OkHttpClient client) {

        TrustManager[] trustManagers = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};

        SSLContext sslContext = null;

        try {

            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagers, new SecureRandom());

        } catch (final java.security.GeneralSecurityException ex) {
            ex.printStackTrace();
        }

        try {
            final HostnameVerifier hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(final String hostname,
                                      final SSLSession session) {
                    return true;
                }
            };

            client.newBuilder()
                    .hostnameVerifier(hostnameVerifier)
                    .sslSocketFactory(sslContext.getSocketFactory());
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return client;
    }

    public OkHttpClient getClient() {
        if (client == null){
            client = new OkHttpClient();
            return configureClient(client);
        }  else {
            return client;
        }
    }
}
