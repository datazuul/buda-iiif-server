package io.bdrc.iiif.resolver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;

public class IdentifierInfo {

  public String work;
  public String asset;
  public String access;

  @SuppressWarnings("unchecked")
  public IdentifierInfo(String volumeImageAsset) {
    HttpClient httpClient = HttpClientBuilder.create().build();
    HttpPost request = new HttpPost("http://purl.bdrc.io/query/IdentifierInfo");
    JSONObject object = new JSONObject();

    try {
      object.put("R_RES", volumeImageAsset);
      String message = object.toString();
      request.setEntity(new StringEntity(message, "UTF8"));
      request.setHeader("Content-type", "application/json");
      HttpResponse response = httpClient.execute(request);
      ObjectMapper mapper = new ObjectMapper();
      JsonNode node = mapper.readTree(response.getEntity().getContent());
      this.work = node.findValue("work").findValue("value").toString().replaceAll("\"", "");
      this.asset = node.findValue("asset").findValue("value").toString().replaceAll("\"", "");
      this.access = node.findValue("access").findValue("value").toString().replaceAll("\"", "");

    } catch (Exception ex) {
      System.out.println("Error: " + ex.getMessage());
    }
  }

  public String getWork() {
    return work;
  }

  public void setWork(String work) {
    this.work = work;
  }

  public String getAsset() {
    return asset;
  }

  public void setAsset(String asset) {
    this.asset = asset;
  }

  public String getAccess() {
    return access;
  }

  public void setAccess(String access) {
    this.access = access;
  }

  @Override
  public String toString() {
    return "IdentifierInfo [work=" + work + ", asset=" + asset + ", access=" + access + "]";
  }

  public static void main(String[] args) throws Exception {
    System.out.println(new IdentifierInfo("bdr:V29329_I1KG15042"));
    /*String url="http://localhost:8080/query/IdentifierInfo?R_RES=bdr:V29329_I1KG15042&jsonOut";
        ObjectMapper mapper = new ObjectMapper();
        Object obj = mapper.readValue(new URL(url),Object.class);
        JsonNode node=mapper.readTree(obj.toString());
        System.out.println(node.toString());*/
  }
}
