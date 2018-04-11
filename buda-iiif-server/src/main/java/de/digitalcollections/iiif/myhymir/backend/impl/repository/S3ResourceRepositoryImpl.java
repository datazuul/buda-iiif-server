package de.digitalcollections.iiif.myhymir.backend.impl.repository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import de.digitalcollections.core.backend.api.resource.ResourceRepository;
import de.digitalcollections.core.backend.impl.file.repository.resource.util.S3ResourcePersistenceTypeHandler;
import de.digitalcollections.core.model.api.MimeType;
import de.digitalcollections.core.model.api.resource.Resource;
import de.digitalcollections.core.model.api.resource.enums.ResourcePersistenceType;
import de.digitalcollections.core.model.api.resource.exceptions.ResourceIOException;
import de.digitalcollections.core.model.impl.resource.S3Resource;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * A ResourceRepository implementation to use with Amazon S3 services
 *
 * @author @marcagate - Buddhist Digital Resource Center
 *
 */
@Primary
@Repository
public class S3ResourceRepositoryImpl implements ResourceRepository<Resource> {

  private static final Logger LOGGER = LoggerFactory.getLogger(S3ResourceRepositoryImpl.class);

  @Autowired
  private AmazonS3 amazonS3;

  @Value("buda.S3_BUCKET")
  private String s3Bucket;

  @Override
  public S3Resource create(String key, ResourcePersistenceType resourcePersistenceType, MimeType mimeType) throws ResourceIOException {
    S3Resource resource = new S3Resource();
    if (mimeType != null) {
      if (mimeType.getExtensions() != null && !mimeType.getExtensions().isEmpty()) {
        resource.setFilenameExtension(mimeType.getExtensions().get(0));
      }
      resource.setMimeType(mimeType);
    }
    if (ResourcePersistenceType.REFERENCED.equals(resourcePersistenceType)) {
      resource.setReadonly(true);
    }
    if (ResourcePersistenceType.MANAGED.equals(resourcePersistenceType)) {
      resource.setUuid(UUID.fromString(key));
    }
    S3ResourcePersistenceTypeHandler spt = new S3ResourcePersistenceTypeHandler();
    resource.setIdentifier(spt.getIdentifier(key));
    return resource;
  }

  @Override
  public void delete(Resource r) throws ResourceIOException {
    throw new UnsupportedOperationException("Not supported yet.");
    //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public S3Resource find(String key, ResourcePersistenceType resourcePersistenceType, MimeType mimeType) throws ResourceIOException {
    S3Resource resource = create(key, resourcePersistenceType, mimeType);
    return resource;
  }

  @Override
  public byte[] getBytes(Resource r) throws ResourceIOException {
    throw new UnsupportedOperationException("Not supported yet.");
    //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public InputStream getInputStream(URI uri) throws ResourceIOException {
    throw new UnsupportedOperationException("Not supported yet.");
    //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public InputStream getInputStream(Resource r) throws ResourceIOException {
    return getInputStream((S3Resource) r);
  }

  public InputStream getInputStream(S3Resource r) throws ResourceIOException {
    LOGGER.info("Getting input stream for resource >> " + r.toString());
    S3Object obj = null;
    try {
      GetObjectRequest request = new GetObjectRequest(s3Bucket, r.getIdentifier());
      obj = amazonS3.getObject(request);
      LOGGER.info("Obj from s3 >> " + obj);
    } catch (AmazonS3Exception e) {
      throw new ResourceIOException(e.getMessage());
    }
    InputStream stream = obj.getObjectContent();
    LOGGER.info("Obj from s3 >> " + stream);
    return stream;
  }

  @Override
  public Reader getReader(Resource r) throws ResourceIOException {
    throw new UnsupportedOperationException("Not supported yet.");
    //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void write(Resource rsrc, String string) throws ResourceIOException {
    throw new UnsupportedOperationException("Not supported yet.");
    //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void write(Resource rsrc, InputStream in) throws ResourceIOException {
    throw new UnsupportedOperationException("Not supported yet.");
    //To change body of generated methods, choose Tools | Templates.
  }
}
