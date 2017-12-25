package GroceryCustomerSim;

import java.util.*;


public abstract class GroceryQueue implements Queue {

    protected HashMap<Server, LinkedList<Customer>> mQueues;
    private boolean mClosed;

    public GroceryQueue() {        
        mQueues = new HashMap<Server, LinkedList<Customer>>();
        mClosed = false;
    }

    /**
     * This method is called by the simulation immediately
     * before a server thread is started, so the queue can
     * keep track of available servers.
     * Note: You can assume that all servers are added by
     * the simulation before any server or customer thread
     * is started.
     */
    public synchronized void addServer(Server server) {
        mQueues.put(server, new LinkedList<Customer>());
    }

    /**
     * Called by customer threads immediately before they
     * wait for service.
     */
    public synchronized void enterQueue(Customer customer) {
        this.laneChoice().add(customer);
        this.notifyAll();
    }

    /**
     * Chooses the lane to add the next customer to.
     */
    protected abstract LinkedList<Customer> laneChoice();

    /**
     * Called by servers to request another customer to serve.
     * If no customer is available for the specified server,
     * the calling thread should wait until one becomes available
     * or until the Queue is closed. If the queue has been closed,
     * this function returns null.
     */
    public synchronized Customer nextCustomer(Server server) throws InterruptedException {
        LinkedList<Customer> customers = mQueues.get(server);
        Customer ret = customers.poll();
        while( !mClosed && ret == null) {
            this.wait();
            ret = customers.poll();
        }
        return ret;
    }

    /**
     * Closes the queue. After being called, nextCustomer should
     * return null, and any servers still waiting for customers
     * should be notified.
     */
    public synchronized void close() {
        this.mClosed = true;
        this.notifyAll();
    }
}
