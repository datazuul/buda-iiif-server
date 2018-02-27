package de.digitalcollections.iiif.myhymir.backend.impl.repository;

import de.digitalcollections.core.backend.api.resource.ResourceRepository;
import de.digitalcollections.core.model.api.MimeType;
import de.digitalcollections.core.model.api.resource.Resource;
import de.digitalcollections.core.model.api.resource.enums.ResourcePersistenceType;
import de.digitalcollections.core.model.api.resource.exceptions.ResourceIOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URI;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class S3ResourceRepositoryImpl implements ResourceRepository<Resource> {

  @Override
  public Resource create(String string, ResourcePersistenceType rpt, MimeType mt) throws ResourceIOException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void delete(Resource r) throws ResourceIOException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Resource find(String string, ResourcePersistenceType rpt, MimeType mt) throws ResourceIOException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public byte[] getBytes(Resource r) throws ResourceIOException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public InputStream getInputStream(URI uri) throws ResourceIOException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public InputStream getInputStream(Resource r) throws ResourceIOException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Reader getReader(Resource r) throws ResourceIOException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void write(Resource rsrc, String string) throws ResourceIOException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void write(Resource rsrc, InputStream in) throws ResourceIOException {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}