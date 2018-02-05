package com.housejoy;

import software.amazon.awssdk.services.ec2.EC2Client;
import software.amazon.awssdk.services.ec2.model.CreateImageRequest;
import software.amazon.awssdk.services.ec2.model.CreateImageResponse;




public class CreateAmi {
	
	
	public static void main(String[] args) {
		String instanceId = "i-04326d30c6a0c321c";

		if (instanceId == null || "".equals(instanceId)) {
			System.out.println("instanceId:"+instanceId);
            System.exit(1);
		}
		
		EC2Client ec2 = EC2Client.create();
		
		CreateImageRequest createImageRequest =  CreateImageRequest.builder().instanceId(instanceId).name("testimng testing")
				.description("testing AMI").noReboot(true).build();
		
		
		 CreateImageResponse	 response = ec2.createImage(createImageRequest);

		//CreateImageResult image = ec2_mumbai.createImage(createImageRequest);
			System.out.println(instanceId + "i-04326d30c6a0c321c --- " + response.imageId()  + "ami-ffa6f090");
		
	}
	
	

}


