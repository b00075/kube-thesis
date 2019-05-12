package wasdev.sample.store;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.FindByIndexOptions;
import com.google.gson.JsonObject;

import wasdev.sample.Checkpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
/**
 * @author john
 *
 */
public class CloudantCheckpointStore implements CheckpointStore {

     private Database db = null;
        private static final String databaseName = "mycheckpointdb";
        
        public CloudantCheckpointStore(){
            CloudantClient cloudant = createClient();
            if(cloudant!=null){
             db = cloudant.database(databaseName, true);
             System.out.println("Cloudant is not null!");
            }else{
                System.out.println("Cloudant is null!");
            }
        }
        
        public Database getDB(){
            return db;
        }

        private static CloudantClient createClient() {
            
            String url;

            if (System.getenv("VCAP_SERVICES") != null) {
                // When running in IBM Cloud, the VCAP_SERVICES env var will have the credentials for all bound/connected services
                // Parse the VCAP JSON structure looking for cloudant.
                JsonObject cloudantCredentials = VCAPHelper.getCloudCredentials("cloudant");
                if(cloudantCredentials == null){
                    System.out.println("No cloudant database service bound to this application");
                    return null;
                }
                url = cloudantCredentials.get("url").getAsString();
            } else if (System.getenv("CLOUDANT_URL") != null) {
                url = System.getenv("CLOUDANT_URL");
            } else {
                System.out.println("Running locally. Looking for credentials in cloudant.properties");
                url = VCAPHelper.getLocalProperties("cloudant.properties").getProperty("cloudant_url");
                if(url == null || url.length()==0){
                    System.out.println("To use a database, set the Cloudant url in src/main/resources/cloudant.properties");
                    return null;
                }
            }

            try {
                System.out.println("Connecting to Cloudant");
                CloudantClient client = ClientBuilder.url(new URL(url)).build();
                System.out.println("Connected to Cloudant");
                return client;
            } catch (Exception e) {
                System.out.println("Unable to connect to database");
                //e.printStackTrace();
                return null;
            }
        }
        
        @Override
        public Collection<Checkpoint> getAll(){
           List<Checkpoint> docs;
            try {
                docs = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Checkpoint.class);
            } catch (IOException e) {
                return null;
            }
            for(Checkpoint doc:docs){
                HashMap<String, String>hmap= new HashMap<String,String>();
                hmap.put("testerID", doc.getTesterID());
                hmap.put("number", doc.getNumber());
                hmap.put("ans1", doc.getAns1());
                hmap.put("ans2", doc.getAns2());
                hmap.put("ans3", doc.getAns3());
                hmap.put("ans4", doc.getAns4());
                hmap.put("score", doc.getScore());
                hmap.put("timestamp", doc.getTimestamp());

                Set set = hmap.entrySet();
                Iterator iterator = set.iterator();
                while(iterator.hasNext()){
                    Map.Entry mentry = (Map.Entry)iterator.next();
                    System.out.print("key is "+mentry.getKey() + " & Value is: ");
                    System.out.println(mentry.getValue());
                }
            }
                
            return docs;
        }

        @Override
        public Collection<Checkpoint> getAllByTesterID(String testerID){
           List<Checkpoint> docs;

           // try {
               // docs = db.getAllDocsRequestBuilder().keys(tag).includeDocs(true).build().getResponse().getDocsAs(Checkpoint.class);
                docs=db.findByIndex("\"selector\":{\"testerID\":{\"$eq\":"+testerID+"}}",Checkpoint.class, new FindByIndexOptions().fields("testerID").fields("number").fields("ans1").fields("ans2").fields("ans3").fields("ans4").fields("score").fields("timestamp"));
           // } catch (IOException e) {
              //  return null;
           // }
            for(Checkpoint doc:docs){

                HashMap<String, String>hmap= new HashMap<String,String>();
                
                hmap.put("testerID", doc.getTesterID());
                hmap.put("number", doc.getNumber());
                hmap.put("ans1", doc.getAns1());
                hmap.put("ans2", doc.getAns2());
                hmap.put("ans3", doc.getAns3());
                hmap.put("ans4", doc.getAns4());
                hmap.put("score", doc.getScore());
                hmap.put("timestamp", doc.getTimestamp());
            
                Set set = hmap.entrySet();
                Iterator iterator = set.iterator();
                while(iterator.hasNext()){
                    Map.Entry mentry = (Map.Entry)iterator.next();
                    System.out.print("key is "+mentry.getKey() + " & Value is: ");
                    System.out.println(mentry.getValue());
                }
            }
                
            return docs;
        }

        @Override
        public Collection<Checkpoint> getAllByNumber(String number){
           List<Checkpoint> docs;

           // try {
               // docs = db.getAllDocsRequestBuilder().keys(tag).includeDocs(true).build().getResponse().getDocsAs(Checkpoint.class);
                docs=db.findByIndex("\"selector\":{\"number\":{\"$eq\":"+number+"}}",Checkpoint.class, new FindByIndexOptions().fields("testerID").fields("number").fields("ans1").fields("ans2").fields("ans3").fields("ans4").fields("score").fields("timestamp"));
           // } catch (IOException e) {
              //  return null;
           // }
            for(Checkpoint doc:docs){

                HashMap<String, String>hmap= new HashMap<String,String>();
                
                hmap.put("testerID", doc.getTesterID());
                hmap.put("number", doc.getNumber());
                hmap.put("ans1", doc.getAns1());
                hmap.put("ans2", doc.getAns2());
                hmap.put("ans3", doc.getAns3());
                hmap.put("ans4", doc.getAns4());
                hmap.put("score", doc.getScore());
                hmap.put("timestamp", doc.getTimestamp());
            
                Set set = hmap.entrySet();
                Iterator iterator = set.iterator();
                while(iterator.hasNext()){
                    Map.Entry mentry = (Map.Entry)iterator.next();
                    System.out.print("key is "+mentry.getKey() + " & Value is: ");
                    System.out.println(mentry.getValue());
                }
            }
                
            return docs;
        }

        @Override
        public Checkpoint get(String id) {
            return db.find(Checkpoint.class, id);
        }

        @Override
        public Checkpoint persist(Checkpoint td) {
            String id = db.save(td).getId();
            return db.find(Checkpoint.class, id);
        }

        @Override
        public Checkpoint update(String id, Checkpoint newCheckpoint) {
            Checkpoint checkpoint = db.find(Checkpoint.class, id);
            checkpoint.setNumber(newCheckpoint.getNumber());
            db.update(checkpoint);
            return db.find(Checkpoint.class, id);

        }

        @Override
        public void delete(String id) {
            Checkpoint checkpoint = db.find(Checkpoint.class, id);
            db.remove(id, checkpoint.get_rev());

        }

        @Override
        public int count() throws Exception {
            return getAll().size();
        }

}
