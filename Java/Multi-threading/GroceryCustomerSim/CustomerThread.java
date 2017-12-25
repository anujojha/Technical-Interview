package GroceryCustomerSim;

public class CustomerThread extends Thread implements Customer {

    private boolean mServed; //has been served
    private Queue mQueue; //major queue this is a member of
    private long mWaitTime; //time entered queue
    private long mDoneTime; //time served

    public CustomerThread(Queue queue) {

        mQueue = queue;
        mServed = false;
        mWaitTime = mDoneTime = 0;
    }

    @Override
    public void run() {
        //record time
        mWaitTime = mDoneTime = System.currentTimeMillis();

        //enter queue and wait
        mQueue.enterQueue(this);
        this.waitForService();
    }

    /**
     * After entering the Queue, the customer
     * should call this method, which should
     * wait until service has been provided, or
     * immediately return if service has already
     * been provided.
     * (This should be a synchronized method.)
     */
    public synchronized long waitForService() {
        try {
            this.wait();
        } catch( InterruptedException e) { return -1;}
        return 0;
    }

    /**
     * After a server removes the customer from
     * the queue, it will call this method which
     * will set the state of the customer to
     * serviced and notify the waiting customer
     * thread.
     * (This should be a synchronized method.)
     */
    public synchronized void provideService() {

        //record service time
        mDoneTime = System.currentTimeMillis();
        mServed = true;

        //notify waiting customer that he/she is currently being served
        this.notify();
    }

    /**
     * After a customer is finished waiting for
     * service, it will record the amount of
     * time it spent waiting, which can be
     * accessed with this method.
     */
    public long getWaitTime() {
        return mDoneTime - mWaitTime;
    }
}
