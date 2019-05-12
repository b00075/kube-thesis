package wasdev.sample.store;
import java.util.Collection;

import wasdev.sample.Answer;

public interface AnswerStore {
	 /**
     * Get the target db object.
     * 
     * @return Database.
     * @throws Exception 
     */
    public Object getDB();

  
    /**
     * Gets all Answers from the store.
     * 
     * @return All Answers.
     * @throws Exception 
     */
    public Collection<Answer> getAll();

    /**
     * Gets all Answers with tag from the store.
     * 
     * @return All Answers with tag.
     * @throws Exception 
     */
    public Collection<Answer> getAllByTesterID(String testerID);

    /**
     * Gets all Answers by testerid from the store.
     * @param testerID of Answer.
     * @return all Answers with tag.
     */
    public Collection<Answer> getAllByType(String type);

    /**
     * Gets all Answers by number from the store.
     * @param testerID of Answer.
     * @return all Answers with tag.
     */
    public Answer get(String id);

    /**
     * Persists a Answer to the store.
     * @param td The ToDo to persist.
     * @return The persisted ToDo.  The ToDo will not have a unique ID..
     */
    public Answer persist(Answer vi);

    /**
     * Updates a ToDo in the store.
     * @param id The ID of the Answer to update.
     * @param td The Answer with updated information.
     * @return The updated Answer.
     */
    public Answer update(String id, Answer vi);

    /**
     * Deletes a ToDo from the store.
     * @param id The ID of the Answer to delete.
     */
    public void delete(String id);
  
    /**
     * Counts the number of Answers
     * @return The total number of Answers.
     * @throws Exception 
     */
    public int count() throws Exception;
}
