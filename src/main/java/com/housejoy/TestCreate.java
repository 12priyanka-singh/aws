package com.housejoy;
import software.amazon.awssdk.services.ec2.EC2Client;
import software.amazon.awssdk.services.ec2.model.InstanceType;
import software.amazon.awssdk.services.ec2.model.RunInstancesRequest;
import software.amazon.awssdk.services.ec2.model.RunInstancesResponse;
import software.amazon.awssdk.services.ec2.model.Tag;
import software.amazon.awssdk.services.ec2.model.CreateTagsRequest;
import software.amazon.awssdk.services.ec2.model.EC2Exception;

/**
 * Creates an EC2 instance
 */
public class TestCreate
{
    private static RunInstancesRequest runInstancesRequest;

	public static void main(String[] args)
    {
        final String USAGE =
            "To run this example, supply an instance name and AMI image id\n" +
            "Ex: CreateInstance <instance-name> <ami-image-id>\n";

        if (args.length != 2) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String name = args[0];
        String ami_id = args[1];

        EC2Client ec2 = EC2Client.create();

        RunInstancesRequest run_request = RunInstancesRequest.builder()
            .imageId(ami_id)
            .instanceType(InstanceType.T2Micro)
            .maxCount(1)
            .minCount(1)
            .securityGroupIds("sg-c82f30a0")
			.keyName("sudheer")
			
			.build();
			
			
	
           

        RunInstancesResponse response = ec2.runInstances(runInstancesRequest);

        String instance_id = response.reservation().reservationId();

        Tag tag = Tag.builder()
            .key("Name")
            .value(name)
            .build();

        CreateTagsRequest tag_request = CreateTagsRequest.builder()
            .tags(tag)
            .build();

        try {
        	ec2.createTags(tag_request);

            System.out.printf(
                "Successfully started EC2 instance %s based on AMI %s",
                instance_id, ami_id);
        }
        catch (EC2Exception e) {
        	System.err.println(e.getErrorMessage());
        	System.exit(1);
        }
        System.out.println("Done!");
        
    }
}

				
		