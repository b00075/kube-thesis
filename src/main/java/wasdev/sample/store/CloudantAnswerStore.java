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

import wasdev.sample.Answer;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
/**
 * @author john
 *
 */
public class CloudantAnswerStore implements AnswerStore {

     private Database db = null;
        private static final String databaseName = "myanswerdb";
        
        public CloudantAnswerStore(){
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
        public Collection<Answer> getAll(){
           List<Answer> docs;
            try {
                docs = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Answer.class);
            } catch (IOException e) {
                return null;
            }
            for(Answer doc:docs){
                HashMap<String, String>hmap= new HashMap<String,String>();
                hmap.put("testerID", doc.getTesterID());
                hmap.put("type", doc.getType());
                hmap.put("question1", doc.getQuestion1());
                hmap.put("question2", doc.getQuestion2());
                hmap.put("question3", doc.getQuestion3());
                hmap.put("question4", doc.getQuestion4());
                hmap.put("question5", doc.getQuestion5());
                hmap.put("question6", doc.getQuestion6());
                hmap.put("question7", doc.getQuestion7());
                hmap.put("question8", doc.getQuestion8());
                hmap.put("question9", doc.getQuestion9());
                hmap.put("question10", doc.getQuestion10());
                hmap.put("question11", doc.getQuestion11());
                hmap.put("question12", doc.getQuestion12());
                hmap.put("question13", doc.getQuestion13());
                hmap.put("question14", doc.getQuestion14());
                hmap.put("question15", doc.getQuestion15());
                hmap.put("question16", doc.getQuestion16());
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
        public Collection<Answer> getAllByTesterID(String testerID){
           List<Answer> docs;

           // try {
               // docs = db.getAllDocsRequestBuilder().keys(tag).includeDocs(true).build().getResponse().getDocsAs(Answer.class);
                docs=db.findByIndex("\"selector\":{\"testerID\":{\"$eq\":"+testerID+"}}",Answer.class, new FindByIndexOptions().fields("testerID").fields("type").fields("ans1").fields("ans2").fields("ans3").fields("ans4").fields("ans5").fields("ans6").fields("ans7").fields("ans8").fields("ans9").fields("ans10").fields("ans11").fields("ans12").fields("ans13").fields("ans14").fields("ans15").fields("ans16").fields("score").fields("timestamp"));
           // } catch (IOException e) {
              //  return null;
           // }
            for(Answer doc:docs){

                HashMap<String, String>hmap= new HashMap<String,String>();
                
                hmap.put("testerID", doc.getTesterID());
                hmap.put("type", doc.getType());
                hmap.put("question1", doc.getQuestion1());
                hmap.put("question2", doc.getQuestion2());
                hmap.put("question3", doc.getQuestion3());
                hmap.put("question4", doc.getQuestion4());
                hmap.put("question5", doc.getQuestion5());
                hmap.put("question6", doc.getQuestion6());
                hmap.put("question7", doc.getQuestion7());
                hmap.put("question8", doc.getQuestion8());
                hmap.put("question9", doc.getQuestion9());
                hmap.put("question10", doc.getQuestion10());
                hmap.put("question11", doc.getQuestion11());
                hmap.put("question12", doc.getQuestion12());
                hmap.put("question13", doc.getQuestion13());
                hmap.put("question14", doc.getQuestion14());
                hmap.put("question15", doc.getQuestion15());
                hmap.put("question16", doc.getQuestion16());
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
        public Collection<Answer> getAllByType(String type){
           List<Answer> docs;

           // try {
               // docs = db.getAllDocsRequestBuilder().keys(tag).includeDocs(true).build().getResponse().getDocsAs(Answer.class);
                docs=db.findByIndex("\"selector\":{\"type\":{\"$eq\":"+type+"}}",Answer.class, new FindByIndexOptions().fields("testerID").fields("type").fields("ans1").fields("ans2").fields("ans3").fields("ans4").fields("ans5").fields("ans6").fields("ans7").fields("ans8").fields("ans9").fields("ans10").fields("ans11").fields("ans12").fields("ans13").fields("ans14").fields("ans15").fields("ans16").fields("score").fields("timestamp"));
           // } catch (IOException e) {
              //  return null;
           // }
            for(Answer doc:docs){

                HashMap<String, String>hmap= new HashMap<String,String>();
                
                hmap.put("testerID", doc.getTesterID());
                hmap.put("type", doc.getType());
                hmap.put("question1", doc.getQuestion1());
                hmap.put("question2", doc.getQuestion2());
                hmap.put("question3", doc.getQuestion3());
                hmap.put("question4", doc.getQuestion4());
                hmap.put("question5", doc.getQuestion5());
                hmap.put("question6", doc.getQuestion6());
                hmap.put("question7", doc.getQuestion7());
                hmap.put("question8", doc.getQuestion8());
                hmap.put("question9", doc.getQuestion9());
                hmap.put("question10", doc.getQuestion10());
                hmap.put("question11", doc.getQuestion11());
                hmap.put("question12", doc.getQuestion12());
                hmap.put("question13", doc.getQuestion13());
                hmap.put("question14", doc.getQuestion14());
                hmap.put("question15", doc.getQuestion15());
                hmap.put("question16", doc.getQuestion16());
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
        public Answer get(String id) {
            return db.find(Answer.class, id);
        }

        @Override
        public Answer persist(Answer td) {
            String id = db.save(td).getId();
            return db.find(Answer.class, id);
        }

        @Override
        public Answer update(String id, Answer newAnswer) {
            Answer answer = db.find(Answer.class, id);
            answer.setType(newAnswer.getType());
            db.update(answer);
            return db.find(Answer.class, id);

        }

        @Override
        public void delete(String id) {
            Answer answer = db.find(Answer.class, id);
            db.remove(id, answer.get_rev());

        }

        @Override
        public int count() throws Exception {
            return getAll().size();
        }

}
