package com.housejoy;


import software.amazon.awssdk.services.ec2.EC2Client;
import software.amazon.awssdk.services.ec2.model.StartInstancesRequest;
import software.amazon.awssdk.services.ec2.model.StopInstancesRequest;

/**
 * Starts or stops and EC2 instance
 */
public class StartStopInstance
{
    public static void startInstance(String instance_id)
    {
        EC2Client ec2 = EC2Client.create();

        StartInstancesRequest request = StartInstancesRequest.builder()
            .instanceIds(instance_id).build();

        ec2.startInstances(request);

        System.out.printf("Successfully started instance %s", instance_id);
    }

    public static void stopInstance(String instance_id)
    {
        EC2Client ec2 = EC2Client.create();
        
        StopInstancesRequest request = StopInstancesRequest.builder()
            .instanceIds(instance_id).build();

        ec2.stopInstances(request);

        System.out.printf("Successfully stop instance %s", instance_id);
    }

    public static void main(String[] args)
    {
        final String USAGE =
            "To run this example, supply an instance id and start or stop\n" +
            "Ex: StartStopInstance <instance-id> <start|stop>\n";

        if (args.length != 2) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String instance_id = args[0];

        boolean start;

        if(args[1].equals("start")) {
            start = true;
        } else {
            start = false;
        }

        if(start) {
            startInstance(instance_id);
        } else {
            stopInstance(instance_id);
        }
    }
}