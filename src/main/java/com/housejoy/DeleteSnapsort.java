package com.housejoy;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import software.amazon.awssdk.services.ec2.EC2Client;
import software.amazon.awssdk.services.ec2.model.DeleteSnapshotRequest;

import software.amazon.awssdk.services.ec2.model.DescribeSnapshotsRequest;
import software.amazon.awssdk.services.ec2.model.DescribeSnapshotsResponse;

import software.amazon.awssdk.services.ec2.model.Snapshot;


public class DeleteSnapsort {
	

		public static void main(String[] args)
	    {
	    
			Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
			calendar.add(Calendar.DATE, -2);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			Date date1 = calendar.getTime();
			
			Date date2 = null;
			String tempDate = null;
			
			System.out.println(date1);
			
	     
	        EC2Client ec2 = EC2Client.create();
	        
	        DeleteSnapshotRequest deleteSnapshotRequest = null;
	        DescribeSnapshotsRequest describeSnapshotsRequest = DescribeSnapshotsRequest.builder().ownerIds("486863379463").build();
	        
	        DescribeSnapshotsResponse describeSnapshotsResponse  =  ec2.describeSnapshots(describeSnapshotsRequest);
	        
	        
	        
	        for( Snapshot snapshot : describeSnapshotsResponse.snapshots()) {
	        	try {
					System.out.println(snapshot.description() +"::::"+snapshot.snapshotId()+":::::"+snapshot.startTime());
					
					tempDate = snapshot.startTime().toString();
					
										
					date2 = sdf.parse(tempDate);
					
				if(date1.compareTo(date2)>=0) {
						System.out.println("Enter and delete image");
						
						 deleteSnapshotRequest = DeleteSnapshotRequest.builder().snapshotId(snapshot.snapshotId()).build();
						
						ec2.deleteSnapshot(deleteSnapshotRequest);
						System.out.println("Successfully Deleted>>>>>>>>>>>>>>>>>>>>>");
						
					}
				else {
						System.out.println("Not enter....................");
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
	        	
	        }

	    }
}
