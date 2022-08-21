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

## Heroku deployments
see: https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku

One time setup: add heroku remote:
```
heroku login

# one time only
heroku create
```

To push code changes:
```
heroku login
git push heroku main
```

if you run into trouble with the heroku git remote url:
```
# list all the remotes
git remote -v

# remove remote url
git remote rm heroku

# re-add it
git remote add heroku git@heroku.com:calligraphy-api.git
```

