
package wasdev.sample.store;

public class CheckpointStoreFactory {
	private static CheckpointStore instance;
    static {
        
       
            CloudantCheckpointStore cvif = new CloudantCheckpointStore();
            if (cvif.getDB() != null) {
                System.out.println("new CloudantCheckpointStore created");
                instance = cvif;
            }else{
                System.out.println("new CloudantCheckpointStore not created");
            }
        
        
    }

    public static CheckpointStore getInstance() {
        return instance;
    }
}

