// static block initialization
public class Singleton {
    private static Singleton instance;
    
    private Singleton(){};
    
    //static block initialization for exception handling
    // about static block: http://www.geeksforgeeks.org/g-fact-79/
    static {
        try {
            instance = new Singleton;
        } catch (Exception e) {
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }
    
    public static Single getInstance {
        return instance;
    }
}


// Lazy initialization
public class Singleton {
    private static Singleton instance;
    
    private Singleton(){};
    
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
