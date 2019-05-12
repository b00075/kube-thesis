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

import wasdev.sample.Question;
import wasdev.sample.store.QuestionStore;
import wasdev.sample.store.QuestionStoreFactory;

@ApplicationPath("api")
@Path("/questions")
public class QuestionAPI extends Application {
	
    //Our database store
    QuestionStore store = QuestionStoreFactory.getInstance();

    @GET
    @Path("/")
    @Produces({"application/json"})
    public String getQuestions() {

        if (store == null) {
            return "[]";
        }

        List<HashMap<String,String>> questions = new ArrayList<HashMap<String,String>>();
        for (Question doc : store.getAll()){
          
            HashMap<String, String>hmap = new HashMap<String,String>();
                hmap.put("ques", doc.getQues());
                hmap.put("opt1", doc.getOpt1());
                hmap.put("opt2", doc.getOpt2());
                hmap.put("opt3", doc.getOpt3());
                hmap.put("opt4", doc.getOpt4());
                hmap.put("correctAns", doc.getCorrectAns());
                hmap.put("qnum", doc.getQnum());
            
            
            if (questions != null){
                questions.add(hmap);
                
            }
            
        }
        String jsonArr = new Gson().toJson(questions);
        System.out.println(jsonArr);
        return new Gson().toJson(questions);
    }

     @GET
    @Path("/{tag}")
    @Produces({"application/json"})
    public String getQuestionsByTag(@PathParam("tag") String tag) {

        if (store == null) {
            return "[]";
        }

        List<HashMap<String,String>> questions = new ArrayList<HashMap<String,String>>();
        for (Question doc : store.getAllByTag(tag)){
           
            HashMap<String, String>hmap = new HashMap<String,String>();
                hmap.put("ques", doc.getQues());
                hmap.put("opt1", doc.getOpt1());
                hmap.put("opt2", doc.getOpt2());
                hmap.put("opt3", doc.getOpt3());
                hmap.put("opt4", doc.getOpt4());
                hmap.put("correctAns", doc.getCorrectAns());
                hmap.put("qnum", doc.getQnum());
            
            
            
            if (questions != null){
               
                questions.add(hmap);
                
            }
            
        }
        String jsonArr = new Gson().toJson(questions);
        System.out.println(jsonArr);
        return new Gson().toJson(questions);
    }
    
   
    @POST
    @Produces("application/text")
    @Consumes("application/json")
    public String newToDo(Question ques) {
        
        store.persist(ques);
        return String.format("The following Question: %s! has been added to the database.", ques.getQues());
    }

}


