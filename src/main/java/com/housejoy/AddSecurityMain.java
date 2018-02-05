package com.housejoy;

import software.amazon.awssdk.services.ec2.model.SecurityGroup;

public class AddSecurityMain {
	public static void main(String[] args) {
		String[] argss = {
				"launch-wizard-3","new VPC security group","vpc-e5bba78c"};
		AddSecurity.main(argss);
		
		
		SecurityGroup securityGroup = SecurityGroup.builder().groupId("").build();
		
		
		
	}		

}
