import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretManagerServiceSettings;
import org.junit.jupiter.api.Test;

class TestApp {
    @Test
	void simpleTest() throws Exception {
	    // can use a real one if wired up, or just fallback to a mock implementation
	    //var creds = GoogleCredentials.getApplicationDefault();
		var creds = new GoogleCredentials() {};
		var settings = SecretManagerServiceSettings.newBuilder()
							.setCredentialsProvider(FixedCredentialsProvider.create(creds))
							.build();

        try (var client = SecretManagerServiceClient.create(settings)) {
		    System.err.println("hello");
		}
	}
}
