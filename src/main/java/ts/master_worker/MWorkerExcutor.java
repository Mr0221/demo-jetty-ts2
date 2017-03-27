package ts.master_worker;

public class MWorkerExcutor extends MWorker{
    @Override
    public Object handle(final Object task) {
        logger.debug("task running: " + task.toString());
        if(task instanceof Integer){
            final Integer tmp = ((Integer) task);
            return tmp*tmp*tmp;
        }
        return 1;
    }

}
