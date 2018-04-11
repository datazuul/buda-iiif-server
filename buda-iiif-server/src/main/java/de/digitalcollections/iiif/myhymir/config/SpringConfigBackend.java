package de.digitalcollections.iiif.myhymir.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfigBackend {

  @Value("buda.S3_ACCESS_KEY_ID")
  private String s3AccessKeyId;
  @Value("buda.S3_ENDPOINT")
  private String s3Endpoint;
  @Value("buda.S3_MAX_CONNECTIONS")
  private int s3MaxConnections;
  @Value("buda.S3_REGION")
  private String s3Region;
  @Value("buda.S3_SECRET_KEY")
  private String s3SecretKey;

  @Bean
  public AmazonS3 amazonS3() {
    BasicAWSCredentials bac = new BasicAWSCredentials(s3AccessKeyId, s3SecretKey);
    ClientConfiguration ccfg = new ClientConfiguration();
    ccfg.setMaxConnections(s3MaxConnections);

    return AmazonS3ClientBuilder.standard()
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(s3Endpoint, s3Region))
            .withCredentials(new AWSStaticCredentialsProvider(bac))
            .withClientConfiguration(ccfg)
            .build();
  }
}
