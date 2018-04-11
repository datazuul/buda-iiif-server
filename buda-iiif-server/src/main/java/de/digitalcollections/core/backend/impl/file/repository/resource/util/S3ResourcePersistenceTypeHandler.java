package de.digitalcollections.core.backend.impl.file.repository.resource.util;

import de.digitalcollections.core.backend.impl.file.repository.resource.resolver.S3Resolver;
import de.digitalcollections.core.model.api.MimeType;
import de.digitalcollections.core.model.api.resource.enums.ResourcePersistenceType;
import de.digitalcollections.core.model.api.resource.exceptions.ResourceIOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class S3ResourcePersistenceTypeHandler implements ResourcePersistenceTypeHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(S3ResourcePersistenceTypeHandler.class);

  @Value("buda.S3_RESOLVER")
  private String s3Resolver;

  public S3ResourcePersistenceTypeHandler() {

  }

  @Override
  public ResourcePersistenceType getResourcePersistenceType() {
    // to Adjust I added S3 to de.digitalcollections.core.model.api.resource.enums.ResourcePersistenceType
    return ResourcePersistenceType.S3;
  }

  @Override
  public List<URI> getUris(String resolvingKey, MimeType mimeType) throws ResourceIOException {
    List<URI> list = null;
    URI[] uri = new URI[1];
    try {
      S3Resolver resolver = (S3Resolver) Class.forName(s3Resolver).newInstance();
      uri[0] = new URI(resolver.getS3Identifier(resolvingKey));
      list = Arrays.asList(uri);
    } catch (Exception ex) {
      String msg = "";
      throw new ResourceIOException(msg + " " + ex.getMessage());
    }
    return list;
  }

  public String getIdentifier(String resolvingKey) throws ResourceIOException {
    try {
      S3Resolver resolver = (S3Resolver) Class.forName(s3Resolver).newInstance();
      return resolver.getS3Identifier(resolvingKey);
    } catch (Exception ex) {
      String msg = "";
      throw new ResourceIOException(msg + " " + ex.getMessage());
    }
  }

}
