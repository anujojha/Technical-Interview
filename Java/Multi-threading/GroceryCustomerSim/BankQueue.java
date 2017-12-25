package GroceryCustomerSim;

import java.util.*;

public class BankQueue implements Queue {
    
    private ArrayList<Server> mServers;
    private LinkedList<Customer> mCustomers;
    private boolean mClosed;

    public BankQueue() {
        mServers = new ArrayList<Server>();
        mCustomers = new LinkedList<Customer>();
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
        mServers.add(server);
    }

    /**
     * Called by customer threads immediately before they
     * wait for service.
     */
    public synchronized void enterQueue(Customer customer) {
        mCustomers.add(customer);
        this.notifyAll();
    }

    /**
     * Called by servers to request another customer to serve.
     * If no customer is available for the specified server,
     * the calling thread should wait until one becomes available
     * or until the Queue is closed. If the queue has been closed,
     * this function returns null.
     */
    public synchronized Customer nextCustomer(Server server) throws InterruptedException {

        Customer ret = mCustomers.poll();

        while( !mClosed && ret == null) {

            this.wait();
            ret = mCustomers.poll();
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
