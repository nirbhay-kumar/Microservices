application.properties - Couponservices:
========================================
```java
spring.datasource.url=jdbc:mysql://docker-mysql:3306/mydb
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
server.port= 9091
```

application.properties - Productservices:
=========================================
```shell-script
spring.datasource.url=jdbc:mysql://docker-mysql:3306/mydb
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
server.port=9090
couponService.url = http://coupon-app:9091/couponapi/coupons/
```
Connect to SSH:
===============
```ssh
ssh -i "C:/Users/nirbkuma/Downloads/DockerKeyPair.pem" ec2-user@ec2-18-118-169-12.us-east-2.compute.amazonaws.com

sudo su
#!/bin/bash
chown root:root /home
chmod 755 /home
chown ec2-user:ec2-user /home/ec2-user -R
chmod 700 /home/ec2-user /home/ec2-user/.ssh
chmod 600 /home/ec2-user/.ssh/authorized_keys
```
Copy couponservice and productservice jar:
==========================================
```Shell
scp -i C:/Users/nirbkuma/Downloads/DockerKeyPair.pem C:/Users/nirbkuma/GIT/Microservices/Project_Demos/16.Miicroservices-Couponservice-EC2-Docker/target/couponservice-1.0.jar ec2-user@18.118.169.12:/home/ec2-user

scp -i C:/Users/nirbkuma/Downloads/DockerKeyPair.pem C:/Users/nirbkuma/GIT/Microservices/Project_Demos/17.Miicroservices-Productservice-EC2-Docker/target/productservice-1.0.jar ec2-user@18.118.169.12:/home/ec2-user
```
Install Docker:
===============
sudo yum install docker -y
service docker start
docker --version
docker run hello-world
docker images

Dockerfile - couponservice
==========================
FROM java:8
ADD couponservice-1.0.jar  couponservice.jar
ENTRYPOINT ["java","-jar","couponservice.jar"]

$docker build -f Dockerfile -t coupon_app .

Dockerfile - productservice
===========================
FROM java:8
ADD productservice-1.0.jar  productservice.jar
ENTRYPOINT ["java","-jar","productservice.jar"]

$docker build -f Dockerfile -t product_app .

Start MySQL container:
======================
$docker run -d -p 6666:3306 --name=docker-mysql --env="MYSQL_ROOT_PASSWORD=password" --env="MYSQL_DATABASE=mydb" mysql

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

Command to link container:
==========================
docker run -t --name=coupon_app --link docker-mysql:mysql -p 10555:9091 coupon_app
docker run -t --link docker-mysql:mysql --link coupon_app:coupon-app -p 10666:9090 product_app

[root@ip-172-31-35-229 ec2-user]# docker ps
CONTAINER ID   IMAGE         COMMAND                  CREATED              STATUS              PORTS                               NAMES
b44ac7d5dff3   product_app   "java -jar productse…"   About a minute ago   Up About a minute   0.0.0.0:10666->9090/tcp             product_app
c0f6e01a80a9   coupon_app    "java -jar couponser…"   About a minute ago   Up About a minute   0.0.0.0:10555->9091/tcp             coupon_app
a7a4b959a7c1   mysql         "docker-entrypoint.s…"   2 minutes ago        Up 2 minutes        33060/tcp, 0.0.0.0:6666->3306/tcp   docker-mysql

curl -d '{"code":"SUPERSALE","discount":"100","expDate":"10/10/2022"}' -H "Content-Type: application/json" -X POST http://18.118.169.12:10555/couponapi/coupons
curl -H "Content-Type: application/json" -X GET http://18.118.169.12:10555/couponapi/coupons/SUPERSALE
curl -d '{"name":"MAC","description":"Its Cool","price":2000,"couponCode":"SUPERSALE"}' -H "Content-Type: application/json" -X POST http://18.118.169.12:10666/productapi/products
