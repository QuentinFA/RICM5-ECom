package tools;

import com.amazonaws.AmazonClientException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;

public class AmazonS3ClientInstance{
	
	public static AmazonS3Client instance;
	public static AmazonS3Client getInstance(){
		ClientConfiguration clientConfig;
		BasicAWSCredentials basicCred;
		try {
			basicCred = new BasicAWSCredentials("AKIAIKCAO3X6Y35VXQYA", "jHLw7WRc49tuuJqpbVM/tgnr0p/eZqDu4cCR+ff7");
			clientConfig = new ClientConfiguration();
			clientConfig.setMaxConnections(100);
			clientConfig.setRequestTimeout(1000000);
			clientConfig.setProtocol(Protocol.HTTP);

		} catch (Exception e) {
			throw new AmazonClientException("failed",e);
		}
			instance = new AmazonS3Client(basicCred, clientConfig);
			Region CNNORTH1 = Region.getRegion(Regions.EU_WEST_1);
			instance.setRegion(CNNORTH1);
		long startTime = System.currentTimeMillis();
		if(instance == null){
			new AmazonS3ClientInstance();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("AmazonS3ClientInstance create instance time: " + (endTime - startTime));
		return instance;
	}	

}
