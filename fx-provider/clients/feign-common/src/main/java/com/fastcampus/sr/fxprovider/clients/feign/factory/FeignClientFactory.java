package com.fastcampus.sr.fxprovider.clients.feign.factory;

import com.fastcampus.sr.fxprovider.clients.feign.common.config.FeignConfigurationProperties;
import feign.Client;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.okhttp.OkHttpClient;
import feign.optionals.OptionalDecoder;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient.Builder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignFormatterRegistrar;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class FeignClientFactory {
    private FeignClientFactory() {
        throw new IllegalStateException("Factory class.");
    }

    public static Feign.Builder createBuilder(FeignConfigurationProperties feignConfigurationProperties,
                                              ObjectFactory<HttpMessageConverters> messageConverters,
                                              ObjectProvider<HttpMessageConverterCustomizer> customizers) {
        return Feign.builder()
                .client(createClient(feignConfigurationProperties))
                .options(createOptions(feignConfigurationProperties))
                .retryer(Retryer.NEVER_RETRY)
                .encoder(new SpringEncoder(messageConverters))
                .decoder(new OptionalDecoder(new ResponseEntityDecoder(new SpringDecoder(messageConverters, customizers))))
                .contract(SpringMvcContractFactory.createSpringContract(Arrays.asList(defaultTimeFormatterRegistrar())));

    }

    private static Client createClient(FeignConfigurationProperties feignConfigurationProperties) {
        var dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(feignConfigurationProperties.getMaxRequests());
        dispatcher.setMaxRequestsPerHost(feignConfigurationProperties.getMaxRequestsPerHost());

        return new OkHttpClient(
                new Builder()
                        .connectionPool(
                                new ConnectionPool(
                                        feignConfigurationProperties.getMaxIdleConnections(),
                                        feignConfigurationProperties.getKeepAliveDuration().getSeconds(),
                                        TimeUnit.SECONDS

                                )
                        )
                        .connectTimeout(feignConfigurationProperties.getConnectTimeout())
                        .readTimeout(feignConfigurationProperties.getReadTimeout())
                        .followRedirects(false)
                        .dispatcher(dispatcher)
                        .build())
                ;
    }

    private static Request.Options createOptions(FeignConfigurationProperties feignConfigurationProperties) {
        return new Request.Options(
                feignConfigurationProperties.getConnectTimeout().getSeconds(),
                TimeUnit.SECONDS,
                feignConfigurationProperties.getReadTimeout().getSeconds(),
                TimeUnit.SECONDS,
                false
        );
    }

    private static FeignFormatterRegistrar defaultTimeFormatterRegistrar() {
        var registrar = new DateTimeFormatterRegistrar();
        registrar.setUseIsoFormat(false);
        registrar.setTimeFormatter(DateTimeFormatter.ofPattern("HH:mm:ss"));
        registrar.setDateFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        registrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return registry -> registrar.registerFormatters(registry);
    }
}
