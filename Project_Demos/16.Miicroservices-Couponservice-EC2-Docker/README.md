#### Connect to SSH:
		ssh -i "C:/Users/nirbkuma/Downloads/DockerKeyPair.pem" ec2-user@ec2-18-118-169-12.us-east-2.compute.amazonaws.com

		sudo su
		#!/bin/bash
		chown root:root /home
		chmod 755 /home
		chown ec2-user:ec2-user /home/ec2-user -R
		chmod 700 /home/ec2-user /home/ec2-user/.ssh
		chmod 600 /home/ec2-user/.ssh/authorized_keys

#### Copy couponservice and productservice jar:
		scp -i C:/Users/nirbkuma/Downloads/DockerKeyPair.pem C:/Users/nirbkuma/GIT/Microservices/Project_Demos/16.Miicroservices-Couponservice-EC2-Docker/target/couponservice-1.0.jar ec2-user@18.118.169.12:/home/ec2-user

		scp -i C:/Users/nirbkuma/Downloads/DockerKeyPair.pem C:/Users/nirbkuma/GIT/Microservices/Project_Demos/17.Miicroservices-Productservice-EC2-Docker/target/productservice-1.0.jar ec2-user@18.118.169.12:/home/ec2-user

##### Dockerfile - couponservice
		FROM java:8
		ADD couponservice-1.0.jar  couponservice.jar
		ENTRYPOINT ["java","-jar","couponservice.jar"]

		$docker build -f Dockerfile -t coupon_app .

##### Dockerfile - productservice
		FROM java:8
		ADD productservice-1.0.jar  productservice.jar
		ENTRYPOINT ["java","-jar","productservice.jar"]

		$docker build -f Dockerfile -t product_app .

#### Start MySQL container:
		docker run -d -p 6666:3306 --name=docker-mysql --env="MYSQL_ROOT_PASSWORD=password" --env="MYSQL_DATABASE=mydb" mysql

		$docker exec -it docker-mysql bash

		#mysql -uroot -p
		password

		mysql> show databases;
		mysql> use mydb;
		mysql> show tables;

		create table product(
		id int AUTO_INCREMENT PRIMARY KEY,
		name varchar(20),
		description varchar(100),
		price decimal(8,3) 
		);

		create table coupon(
		id int AUTO_INCREMENT PRIMARY KEY,
		code varchar(20) UNIQUE,
		discount decimal(8,3),
		exp_date varchar(100) 
		);

		[root@ip-172-31-35-229 ec2-user]# docker images
		REPOSITORY    TAG       IMAGE ID       CREATED             SIZE
		product_app   latest    2228cfff1f4e   About an hour ago   684MB
		coupon_app    latest    f699ec3c108e   2 hours ago         684MB
		mysql         latest    c0cdc95609f1   5 weeks ago         556MB
		java          8         d23bdf5b1b1b   4 years ago         643MB

#### Command to link container:
		docker run -t --name=coupon_app --link docker-mysql:mysql -p 10555:9091 coupon_app
		docker run -t --link docker-mysql:mysql -p 10666:9090 product_app
		docker run -t --link docker-mysql:mysql --link coupon-app:coupon_app -p 10666:9090 product_app


		curl -d '{"code":"SUPERSALE","discount":"100","expDate":"10/10/2022"}' -H "Content-Type: application/json" -X POST http://18.118.169.12:10555/couponapi/coupons
		curl -H "Content-Type: application/json" -X GET http://18.118.169.12:10555/couponapi/coupons/SUPERSALE
		curl -d '{"name":"MAC","description":"Its Cool","price":2000,"couponCode":"SUPERSALE"}' -H "Content-Type: application/json" -X POST http://18.118.169.12:10666/productapi/products
