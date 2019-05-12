package wasdev.sample.store;
import java.util.Collection;

import wasdev.sample.Question;

public interface QuestionStore {
	 /**
     * Get the target db object.
     * 
     * @return Database.
     * @throws Exception 
     */
    public Object getDB();

  
    /**
     * Gets all Questions from the store.
     * 
     * @return All Questions.
     * @throws Exception 
     */
    public Collection<Question> getAll();

    /**
     * Gets all Questions with tag from the store.
     * @param tagged the tag of a question
     * @return All Questions with tag.
     * @throws Exception 
     */
    public Collection<Question> getAllByTag(String tagged);

    /**
     * Gets an individual Question from the store.
     * @param id of question.
     * @return  question with id.
     */
    public Question get(String id);

    /**
     * Persists a Question to the store.
     * @param ques The Question to persist.
     * @return The persisted Question.  The Question will not have a unique ID..
     */
    public Question persist(Question ques);

    /**
     * Updates a Question in the store.
     * @param id The ID of the Question to update.
     * @param ques The Question with updated information.
     * @return The updated Question.
     */
    public Question update(String id, Question ques);

    /**
     * Deletes a Question from the store.
     * @param id The ID of the Question to delete.
     */
    public void delete(String id);
  
    /**
     * Counts the number of Questions
     * @return The total number of Questions.
     * @throws Exception 
     */
    public int count() throws Exception;
}
