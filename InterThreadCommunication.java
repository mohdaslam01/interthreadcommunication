
package interthreadcommunication;

class MyData{
    int value=0;
    boolean flag=true;
    
    synchronized void set(int value){
        while(flag==false)try{wait();}catch(Exception e){e.printStackTrace();}
        this.value=value;
        flag=false;
        notifyAll();
        if(value==99)System.exit(1);
    }
    synchronized int get(){
        while(flag==true)try{wait();}catch(Exception e){e.printStackTrace();}
        flag=true;
        int x=0;
        x=value;
        notifyAll();
        if(value==99)System.exit(1);
        return x;
    }
}
class Producer extends Thread{
    MyData d;
    Producer(MyData data){d=data;}
    public void run(){
        int i=0;
        while(i<100){
            d.set(i++);
        }
    }
}
class Consumer extends Thread{
    MyData d;
    Consumer(MyData data){d=data;}
    public void run(){
        while(true)
            System.out.println(d.get());
    }
}

public class InterThreadCommunication {
    
    public static void main(String[] args) {
        MyData d=new MyData();
        Producer p=new Producer(d);
        Consumer c=new Consumer(d);
        p.start();
        c.start();
        
        
    }
    
}
