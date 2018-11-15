package com.hz.elearning

import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestRetrofit {

    private val BASE_URL = "http://10.0.3.2:5000/"

     fun buildRetrofit(): RestServiceApi? {

        val interceptor  = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        val spec = ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
//                .supportsTlsExtensions(true)
//                .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0)
//                .cipherSuites(
//                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
//                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
//                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
//                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,
//                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
//                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
//                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
//                        CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA,
//                        CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA,
//                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA,
//                        CipherSuite.TLS_DHE_DSS_WITH_AES_128_CBC_SHA,
//                        CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA)
//                .build()

//        ProviderInstaller.installIfNeeded(getApplicationContext());
//        val sslContext = SSLContext.getInstance("TLSv1.2")
//        sslContext.init(null, null, null)
//        val engine = sslContext.createSSLEngine()
        val httpClient = OkHttpClient().newBuilder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RestServiceApi::class.java)

         return retrofit
    }


}