
## EC2 Connection Steps


- ssh -i "[KEY_PAIR_NAME.pem]" {DEFAULT-USERNAME}@ec2-{IPV4-ADDRESS}

##### (Install Java)
JAVA ~ sudo yum install java
##### (Install Docker)
- DOCKER + POSTGRES
(a) Installing postgres images --> sudo docker images ( see already installed images)
(b) sudo docker pull postgres --> it will get the latest image by default.

_Running the docker containers_
- docker run --name [NAME_OF_CONTAINER] -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD={dbpassword} -p 5432:5432 -v /data:/var/lib/postgresql/data -d postgres --> to start an image

_View running instances of docker_
- sudo docker ps --> lists all the instances running docker images.
	sudo docker stop [CONTAINER_ID] --> this will stop the running instance of the postgres database.

###### _Upload Application to EC2 using scp_

- scp -i MoneyGramKeyPair.pem {APP_NAME}.jar ec2-user@ec2-{dns_address}:~/.

pscp {APP_NAME}.jar ec2-user@{dns-address}: [FOR_WINDOWS]

##### Open Spring boot port and postgres port in security group.



_Other important points to note_

- To go inside postgres created by docker --> docker exec -it 05b3a3471f6f bash
- ps -ef | grep postgres
- sudo docker ps -a --> List all the containers of docker.
- nohup java -jar my-application.jar &
- kill -9 pid


docker run --name springBootApp -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD= -p 5432:5432 -v /data:/var/lib/postgresql/data -d postgres

- The Above code may not work please read below links to setup ec2-user linux AMI 2023 

https://dev.to/andre347/how-to-easily-create-a-postgres-database-in-docker-4moj

https://stackoverflow.com/questions/47854463/docker-got-permission-denied-while-trying-to-connect-to-the-docker-daemon-socke

sudo usermod -a -G docker ec2-user
