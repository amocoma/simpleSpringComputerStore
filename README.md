# Getting started on Heroku with a simple sample
## Overview
Abou the project:

- java / Spring4
- usage of spring cloud framework
- Hibernate
- JPA
- Persistence layer: In-memory, Postgres, mongo
- Auto-detection of: persistence layer, heroku connect, canvas integration

## Explore Heroku's functionalities
####Clone the git repository
	git clone https://github.com/amocoma/simpleSpringComputerStore.git

####Change into the directory
	cd simpleSpringComputerStore
####Create heroku app and add heroku git remote to the git config
	heroku create
####Open up the browser and take a look at the app
	heroku open
####Oops: an error :-(
####We have to push the app ... :-) 
	git push heroku master
####Open up the browser and take a look at the app: 
	heroku open
####Oops: an error :-(
####Check what is running
	heroku ps
####Ahhh: there is no process running ... we need a Procfile: 
	mv Procfile_ Procfile
####Checkin the changes
	git add Procfile
	git commit -a -m "Procfile added"
	git push heroku master
####Open up the browser and take a look at the app: 
	heroku open
####Oops: an error :-(
####Check what's running
	heroku ps
####We need at least one process to start the app ... so let's scale to one: 
	heroku ps:scale web:1
####Open up the browser and take a look at the app
	heroku open
####Oops: an error :-(
####Check the logs: 
	`heroku logs -t`
####Ahhh the postgres lib is missing ... 
	vi pom.xml >> include the psql dependency
####Checkin the changes
	git add pom.xml
	git commit -a -m "PSQL jdbc added"
	git push heroku master
####Open up the browser and take a look at the app
	heroku open
####Yes, the app is running ;-)
- Check also the /api resource ....

## NewRelic - Add-on
#### Enable the NewRelic
	heroku addons:add newrelic:stark
#### Bootstrap the Agent
	heroku config:set JAVA_OPTS='-Xmx384m -Xss512k -XX:+UseCompressedOops -javaagent:target/dependency/newrelic-agent.jar'
Do you recognized, that the app was restarted automatically because you changed teh env vars?!
#### Open New Relic through SSO from http://heroku.com
## Heroku Connect - Add-on
	heroku addons:add herokuconnect:test
## Configure Heroku Connect
	heroku addons:open herokuconnect
	