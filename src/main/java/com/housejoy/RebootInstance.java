package com.housejoy;
import software.amazon.awssdk.services.ec2.EC2Client;
import software.amazon.awssdk.services.ec2.model.RebootInstancesRequest;
import software.amazon.awssdk.services.ec2.model.RebootInstancesResponse;

/**
 * Reboots and EC2 instance
 */
public class RebootInstance
{
    public static void main(String[] args)
    {
        final String USAGE =
            "To run this example, supply an instance id\n" +
            "Ex: RebootInstnace <instance_id>\n";

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String instance_id = args[0];

        EC2Client ec2 = EC2Client.create();

        RebootInstancesRequest request = RebootInstancesRequest.builder()
            .instanceIds(instance_id).build();

        RebootInstancesResponse response = ec2.rebootInstances(request);

        System.out.printf(
            "Successfully rebooted instance %s", instance_id);
    }
}
