class SharedResource {
    int item;
    boolean available = false;

    // TODO: synchronize void put(int item)
    synchronized void put(int item){
        while(available){
           try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.item=item;
        available=true;
        System.out.println("Produced: "+item);
        notify();
    }
    
    synchronized void get(){
        while(!available){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Consumed: "+item);
        available=false;
        notify();
    }
}

class Producer extends Thread {

    SharedResource resource;
    Producer(SharedResource obj) {
        this.resource = obj;
    }
    // TODO: Constructor to init resource
    
    public void run(){
        for (int i =1; i <=5; i++) {
            resource.put(i);
        }
    }

}

class Consumer extends Thread {

    SharedResource resource;
    Consumer(SharedResource obj) {
        this.resource = obj;
    }
    // TODO: Constructor to init resource
    public void run(){
        for (int i =1; i <=5; i++) {
            resource.get();
        }
    }

}

public class ProducerConsumer {
    public static void main(String[] args) {
        // TODO: Create SharedResource object
        SharedResource obj=new SharedResource();
        Producer t1=new Producer(obj);
        Consumer t2=new Consumer(obj);
        t1.start();
        t2.start();
    }
}
