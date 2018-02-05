package com.housejoy;
import software.amazon.awssdk.services.ec2.EC2Client;

import software.amazon.awssdk.services.ec2.model.CreateSnapshotRequest;
import software.amazon.awssdk.services.ec2.model.CreateSnapshotResponse;

public class CreateSnapsort {
	
	public static void main(String[] args) {
		String  volumeId= "vol-0649b6bc672a68a76";
		if (volumeId == null || "".equals(volumeId)) {
			System.out.println("volumeId:"+volumeId);
            System.exit(1);
		}
		
		
		
		EC2Client ec2 = EC2Client.create();
		
		CreateSnapshotRequest createSnapsortRequest =  CreateSnapshotRequest.builder().volumeId("vol-0649b6bc672a68a76")				
				.description("testing snapsort").build();
		
		CreateSnapshotResponse response = ec2.createSnapshot(createSnapsortRequest);
     System.out.println(  response.snapshot() + "vol-0649b6bc672a68a76");
}
	
}		
		
	

