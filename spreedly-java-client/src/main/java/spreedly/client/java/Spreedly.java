package spreedly.client.java;

public class Spreedly {

    protected final String environmentKey;
    protected final String accessSecret;

    private Spreedly(String environmentKey, String accessSecret) {
        this.environmentKey = environmentKey;
        this.accessSecret = accessSecret;
    }

    public static Spreedly newClient(String environmentKey, String accessSecret) {
        return new Spreedly(environmentKey, accessSecret);
    }

    public void findTransaction(String token) {
        
    }

}
