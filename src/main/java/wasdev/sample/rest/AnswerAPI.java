package wasdev.sample.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

import wasdev.sample.Answer;
import wasdev.sample.store.AnswerStore;
import wasdev.sample.store.AnswerStoreFactory;

@ApplicationPath("api")
@Path("/answers")
public class AnswerAPI extends Application {
	
    //Our database store
    AnswerStore store = AnswerStoreFactory.getInstance();

    @GET
    @Path("/")
    @Produces({"application/json"})
    public String getAnswers() {

        if (store == null) {
            return "[]";
        }

        List<HashMap<String,String>> answers = new ArrayList<HashMap<String,String>>();
        for (Answer doc : store.getAll()){
           
            HashMap<String, String>hmap = new HashMap<String,String>();
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
            
            
            if (answers != null){
               
                answers.add(hmap);
                
            }
            
        }
        String jsonArr = new Gson().toJson(answers);
        System.out.println(jsonArr);
        return new Gson().toJson(answers);
    }

     @GET
    @Path("/{testerID}")
    @Produces({"application/json"})
    public String getAnswersByTesterID(@PathParam("testerID") String testerID) {

        if (store == null) {
            return "[]";
        }

        List<HashMap<String,String>> answers = new ArrayList<HashMap<String,String>>();
        for (Answer doc : store.getAllByTesterID(testerID)){
           
            HashMap<String, String>hmap = new HashMap<String,String>();
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
            
            
            
            if (answers != null){
               
                answers.add(hmap);
                
            }
            
        }
        String jsonArr = new Gson().toJson(answers);
        System.out.println(jsonArr);
        return new Gson().toJson(answers);
    }
    
    @GET
    @Path("/{type}")
    @Produces({"application/json"})
    public String getAnswersByType(@PathParam("type") String type) {

        if (store == null) {
            return "[]";
        }

        List<HashMap<String,String>> answers = new ArrayList<HashMap<String,String>>();
        for (Answer doc : store.getAllByType(type)){
           
            HashMap<String, String>hmap = new HashMap<String,String>();
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
            
            
            
            if (answers != null){
               
           answers.add(hmap);
                
            }
            
        }
        String jsonArr = new Gson().toJson(answers);
        System.out.println(jsonArr);
        return new Gson().toJson(answers);
    }
    
   @POST
    @Path("/")
    @Produces("application/text")
    @Consumes("application/json")
    public String newToDo(Answer answer) {
        if(store == null) {
            return String.format("Answer number %s!", answer.getType());
        }
        System.out.println(answer.getTimestamp() + " " + answer.getType());
        store.persist(answer);
        return String.format("Answer %s has been added to the database.", answer.getType());
    }

}


