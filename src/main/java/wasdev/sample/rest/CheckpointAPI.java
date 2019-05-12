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

import wasdev.sample.Checkpoint;
import wasdev.sample.store.CheckpointStore;
import wasdev.sample.store.CheckpointStoreFactory;

@ApplicationPath("api")
@Path("/checkpoints")
public class CheckpointAPI extends Application {
	
    //Our database store
    CheckpointStore store = CheckpointStoreFactory.getInstance();
    @GET
    @Path("/")
    @Produces({"application/json"})
    public String getCheckpoints() {

        if (store == null) {
            return "[]";
        }

        List<HashMap<String,String>> checkpoints = new ArrayList<HashMap<String,String>>();
        for (Checkpoint doc : store.getAll()){
           
            HashMap<String, String>hmap = new HashMap<String,String>();
                hmap.put("testerID", doc.getTesterID());
                hmap.put("number", doc.getNumber());
                hmap.put("ans1", doc.getAns1());
                hmap.put("ans2", doc.getAns2());
                hmap.put("ans3", doc.getAns3());
                hmap.put("ans4", doc.getAns4());
                hmap.put("score", doc.getScore());
                hmap.put("timestamp", doc.getTimestamp());
            
            
            if (checkpoints != null){
               
                checkpoints.add(hmap);
                
            }
            
        }
        String jsonArr = new Gson().toJson(checkpoints);
        System.out.println(jsonArr);
        return new Gson().toJson(checkpoints);
    }

     @GET
    @Path("/{testerID}")
    @Produces({"application/json"})
    public String getCheckpointsByTesterID(@PathParam("testerID") String testerID) {

        if (store == null) {
            return "[]";
        }

        List<HashMap<String,String>> checkpoints = new ArrayList<HashMap<String,String>>();
        for (Checkpoint doc : store.getAllByTesterID(testerID)){
           
            HashMap<String, String>hmap = new HashMap<String,String>();
                hmap.put("testerID", doc.getTesterID());
                hmap.put("number", doc.getNumber());
                hmap.put("ans1", doc.getAns1());
                hmap.put("ans2", doc.getAns2());
                hmap.put("ans3", doc.getAns3());
                hmap.put("ans4", doc.getAns4());
                hmap.put("score", doc.getScore());
                hmap.put("timestamp", doc.getTimestamp());
            
            
            
            if (checkpoints != null){
               
                checkpoints.add(hmap);
                
            }
            
        }
        String jsonArr = new Gson().toJson(checkpoints);
        System.out.println(jsonArr);
        return new Gson().toJson(checkpoints);
    }
    
    @GET
    @Path("/{number}")
    @Produces({"application/json"})
    public String getCheckpointsByNumber(@PathParam("number") String number) {

        if (store == null) {
            return "[]";
        }

        List<HashMap<String,String>> checkpoints = new ArrayList<HashMap<String,String>>();
        for (Checkpoint doc : store.getAllByNumber(number)){
           
            HashMap<String, String>hmap = new HashMap<String,String>();
                hmap.put("testerID", doc.getTesterID());
                hmap.put("number", doc.getNumber());
                hmap.put("ans1", doc.getAns1());
                hmap.put("ans2", doc.getAns2());
                hmap.put("ans3", doc.getAns3());
                hmap.put("ans4", doc.getAns4());
                hmap.put("score", doc.getScore());
                hmap.put("timestamp", doc.getTimestamp());
            
            
            
            if (checkpoints != null){
               
           checkpoints.add(hmap);
                
            }
            
        }
        String jsonArr = new Gson().toJson(checkpoints);
        System.out.println(jsonArr);
        return new Gson().toJson(checkpoints);
    }
    
    @POST
    @Path("/")
    @Produces("application/text")
    @Consumes("application/json")
    public String newToDo(Checkpoint checkpt) {
        if(store == null) {
            return String.format("Checkpoint number %s!", checkpt.getNumber());
        }
        store.persist(checkpt);
        return String.format("Checkpoint %s has been added to the database.", checkpt.getNumber());
    }
}


