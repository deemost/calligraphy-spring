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
* We use heroku-git method of deployment (via Heroku CLI)
  * Create a `heroku` git remote (in addition to the repo's `origin` remote)
  * Push the same changes to the `heroku` remote  
* See: https://devcenter.heroku.com/articles/deploying-spring-boot-apps-to-heroku

#### Add heroku remote (one-time setup):
```
> heroku login

# one time only, create heroku remote for this repo
> heroku create
```

#### To deploy code changes:
```
# push code changes to this github repo (in IntelliJ, or whatever)

# push the same code to the heroku remote, which will trigger a deployment
> heroku login
> git push heroku main
```

#### Notes:
- if you run into trouble with the heroku git remote url, best to re-create it:
```
# list all the remotes
> git remote -v

# remove remote url
> git remote rm heroku

# re-add it
> git remote add heroku git@heroku.com:calligraphy-api.git
> heroku git:remote -a calligraphy-api
```
