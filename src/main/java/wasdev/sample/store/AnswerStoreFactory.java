
package wasdev.sample.store;

public class AnswerStoreFactory {
	private static AnswerStore instance;
    static {
        // Only use MongoDB if credentials are available.
       
            CloudantAnswerStore cvif = new CloudantAnswerStore();
            if (cvif.getDB() != null) {
                System.out.println("new CloudantAnswerStore created");
                instance = cvif;
            }else{
                System.out.println("new CloudantAnswerStore not created");
            }
        
        
    }

    public static AnswerStore getInstance() {
        return instance;
    }
}

