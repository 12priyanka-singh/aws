package com.housejoy;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import software.amazon.awssdk.services.ec2.EC2Client;
import software.amazon.awssdk.services.ec2.model.DeregisterImageRequest;
import software.amazon.awssdk.services.ec2.model.DescribeImagesRequest;
import software.amazon.awssdk.services.ec2.model.DescribeImagesResponse;
import software.amazon.awssdk.services.ec2.model.Image;


public class DeleteAmi {
	

		public static void main(String[] args)
	    {
	    
			Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
			calendar.add(Calendar.DATE, -1);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			Date date1 = calendar.getTime();
			
			Date date2 = null;
			String tempDate = null;
			
			System.out.println(date1);
			
	     
	        EC2Client ec2 = EC2Client.create();
	        
	        DeregisterImageRequest deregisterImageRequest = null;
	        DescribeImagesRequest describeImagesRequest = DescribeImagesRequest.builder().owners("self").build();
	        
	        DescribeImagesResponse describeImagesResponse =  ec2.describeImages(describeImagesRequest);
	        for(Image image : describeImagesResponse.images()) {
	        	try {
					System.out.println(image.imageId() +"::::"+image.name()+":::::"+image.creationDate());
					
					tempDate = image.creationDate().substring(0, 10);
					
					date2 = sdf.parse(tempDate);
					
					System.out.println("tempDate:"+date2);
					
					
					if(date1.compareTo(date2)>=0) {
						System.out.println("Enter and delete image");
						
						 deregisterImageRequest = DeregisterImageRequest.builder().imageId(image.imageId()).build();
						
						ec2.deregisterImage(deregisterImageRequest);
						System.out.println("Successfully Deleted>>>>>>>>>>>>>>>>>>>>>");
						
					}else {
						System.out.println("Not enter....................");
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
	        	
	        }

	    }
}
