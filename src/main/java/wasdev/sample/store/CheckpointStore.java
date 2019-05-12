package wasdev.sample.store;
import java.util.Collection;

import wasdev.sample.Checkpoint;

public interface CheckpointStore {
	 /**
     * Get the target db object.
     * 
     * @return Database.
     * @throws Exception 
     */
    public Object getDB();

  
    /**
     * Gets all Checkpoints from the store.
     * 
     * @return All Checkpoints.
     * @throws Exception 
     */
    public Collection<Checkpoint> getAll();

    /**
     * Gets all Checkpoints with tag from the store.
     * 
     * @return All Checkpoints with tag.
     * @throws Exception 
     */
    public Collection<Checkpoint> getAllByTesterID(String testerID);

    /**
     * Gets all checkpoints by testerid from the store.
     * @param testerID of Checkpoint.
     * @return all Checkpoints with tag.
     */
    public Collection<Checkpoint> getAllByNumber(String number);

    /**
     * Gets all checkpoints by number from the store.
     * @param testerID of Checkpoint.
     * @return all Checkpoints with tag.
     */
    public Checkpoint get(String id);

    /**
     * Persists a Checkpoint to the store.
     * @param td The ToDo to persist.
     * @return The persisted ToDo.  The ToDo will not have a unique ID..
     */
    public Checkpoint persist(Checkpoint vi);

    /**
     * Updates a ToDo in the store.
     * @param id The ID of the Checkpoint to update.
     * @param td The Checkpoint with updated information.
     * @return The updated Checkpoint.
     */
    public Checkpoint update(String id, Checkpoint vi);

    /**
     * Deletes a ToDo from the store.
     * @param id The ID of the Checkpoint to delete.
     */
    public void delete(String id);
  
    /**
     * Counts the number of Checkpoints
     * @return The total number of Checkpoints.
     * @throws Exception 
     */
    public int count() throws Exception;
}
