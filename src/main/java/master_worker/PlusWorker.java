package master_worker;

public class PlusWorker extends Worker {

    public Object handle(final Object input) {

        final Integer i =(Integer)input;
        return i*i*i;
    }


}
