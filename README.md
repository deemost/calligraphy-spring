# calligraphy-spring

## Build and Run Locally

To compile:
```
mvn clean compile
```

To generate a jar file:
```
mvn clean package
```

To run:
```
mvn clean spring-boot:run
```

To build docker image:
```
mvn clean spring-boot:build-image
```

## AWS Beanstalk

### One time setup:
1. Create an AWS IAM user with fill, programmatic access to beanstalk
2. Set the Access Key/Secret in your terminal
```
$ export AWS_ACCESS_KEY_ID="<your aws access key>"
$ export AWS_SECRET_KEY="<your aws secret key>"
```
3. Setup S3 bucket for this app. If you have already deployed this app to beanstalk, you can use the same bucket (just delete its contents).
4. Configure the beanstalk plugin
see: http://beanstalker.ingenieux.com.br/beanstalk-maven-plugin/usage.html
see: https://maxrohde.com/2018/04/01/upload-elastic-beanstalk-application-using-maven/
see: mvn beanstalk:list-stacks

### First deployment to beanstalk:
*Note*: this is a workaround for failure to create the environment using the beanstalk maven plugin.

First, upload a bundle to s3
```
mvn clean package beanstalk:upload-source-bundle
```

Then, perform the following manually in the AWS console:
1. app-name=callig-api
2. source from s3: copy URL from S3 bucket (select jar file then click copy url)
3. app URL is found on AWS console > beanstalk > applications > callig-api > URL

Once the env starts in AWS, it will fail because the app does not have the correct active profile
1. AWS console > beanstalk > env > configuration > software > edit
2. add: `SPRING_PROFILES_ACTIVE` and set it to `prod`
3. this will restart the app, and it will succeed this time

### Deploying code changes to beanstalk (after the first deployment)
1. Make code changes and test locally
2. Run this to deploy:
```
mvn clean package beanstalk:upload-source-bundle beanstalk:create-application-version beanstalk:update-environment
```
Note: there will be a brief interruption of service with this approach.  
If this is not desired, once can create a separate environment (blue/green).
See: http://beanstalker.ingenieux.com.br/beanstalk-maven-plugin/usage.html

### To manage older versions of the app
1. AWS console > beanstalk > applications > callig-api > View Application Versions
2. select version(s) to delete, this will also remove jars in S3
