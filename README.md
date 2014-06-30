# Getting started on Heroku with a simple sample
## Overview
Abou the project:

- java / Spring4
- usage of: spring cloud, spring data, speing mvc, etc. 
- Hibernate
- JPA
- Persistence layer: In-memory, Postgres, mongo
- Auto-detection of: persistence layer, heroku connect, canvas integration, treasure data Add-on
- UI: jsp, bootstrap, jquery

## Explore Heroku's functionalities
####Clone the git repository
	git clone https://github.com/amocoma/simpleSpringComputerStore.git

####Change into the directory
	cd simpleSpringComputerStore
####Create heroku app and add heroku git remote to the git config
	heroku create
####Open up the browser and take a look at the app
	heroku open
####Oops: an error :-( >> We have to push the app ... :-) 
	git push heroku master
####Open up the browser and take a look at the app: 
	heroku open
####Oops: an error :-( >> Check what is running
	heroku ps
####Ahhh: there is no process running ... we need a Procfile: 
	mv Procfile_ Procfile
####Checkin the changes
	git add Procfile
	git commit -a -m "Procfile added"
	git push heroku master
####Open up the browser and take a look at the app: 
	heroku open
####Oops: an error :-( >> Check what's running
	heroku ps
####We need at least one process to start the app ... so let's scale to one: 
	heroku ps:scale web:1
####Open up the browser and take a look at the app
	heroku open
####Oops: an error :-( >> Check the logs: 
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

##NewRelic - Add-on
####Enable the NewRelic
	heroku addons:add newrelic:stark
####Bootstrap the Agent
	heroku config:set JAVA_OPTS='-Xmx384m -Xss512k -XX:+UseCompressedOops -javaagent:target/dependency/newrelic-agent.jar'
Do you recognized, that the app was restarted automatically because you changed teh env vars?!
#### Open New Relic through SSO from https://dashboard.heroku.com
##Heroku Connect - Add-on
####Enable the Heroku Connect Add-On
	heroku addons:add herokuconnect:test
####Configure Heroku Connect
	heroku addons:open herokuconnect
Configure Heroku connect as follows:

- Environment: `Production`
- Schema: `salesforce`
- Sync `account` with at least the fields: `id` and `name` 

##Treasure Data - Add-on
####Enable the Treasure Data Add-on
	heroku addons:add treasure-data
####Install the plugin to pick-up the data
	heroku plugins:install https://github.com/treasure-data/heroku-td.git
####Open Treasure Data through SSO from https://dashboard.heroku.com to configure the hadoop 

- create database: `cloud`
- create table `computer` within db `cloud`

####Use the app 
and look at some single computer entries (for each view a record will be produced in treasure data)
####Open Treasure Data 
through SSO from https://dashboard.heroku.com and navigate to the table


##Salesforce Canvas integration
####Create a new connected app in SFDC
- goto: `Setup`>`Build`>`Create`>`Apps` >> `Connected Apps` >> `New`
- Use the following setup:

	Field              | Value 
	------------------ | --------------------------------------------------
	Connected App Name | `CoSto` 
	API Name           | `ComputerStore`
	Contact Email      | [YOUR_EMAIL]
	Contact Phone      | [YOUR_PHONE]
	Logo Image URL     | `https://[YOUR_APP_NAME].herokuapp.com/img/logo.png`
	Icon URL           | `https://[YOUR_APP_NAME].herokuapp.comimg/icon.png`
	Info URL           | [LEAVE_IT_BLANK]
	Description        | `Our integrated ComputerStore`
	
	- Enable OAuth Settings : `true`
	
	Field                  | Value 
	---------------------- | ---------------------------------------------------------------
	Callback URL           | `https://[YOUR_APP_NAME].herokuapp.com/html/canvas/callback.html`
	Selected OAuth Scopes  | [CHOOSE_ALL]
	
	- Force.com Canvas : `true`
	
	Field                         | Value 
	----------------------------- | ---------------------------------------------------------------
	Callback URL                  | `https://[YOUR_APP_NAME].herokuapp.com/canvas`
	Access Method                 | `Signed Request (POST)`
	Selected                      | [CHOOSE_ALL]
	Create Actions Automatically  | `true`
	Hide Publisher Header         | `true`

- `save`

####Let the app knowing that it is embedded:

- in SFDC goto: `Setup`>`Build`>`Create`>`Apps` >> `Connected Apps` >> the one you created a second ago
- copy the `Consumer Key` and the `Consumer Secret` and construct the following Json: 
	
	{"key":"`[ConsumerKey]`","secret":"`[ConsumerSecret]`"}
- back to the Console / Terminal add the following Env-variable:

	heroku config:set CANVAS_CONSUMER={"key":"`[ConsumerKey]`","secret":"`[ConsumerSecret]`"}
- in SFDC check out the chatter tab and also the global chatter actions!

##Recap
Check the info icon in the app:

- Profiles: [`herokuconnect`, `herokuconnect-cloud`, `postgres`, `postgres-cloud`, `treasuredata`, `treasuredata-cloud`] > the activated profiles through the detected services
- Services: [`HEROKUCONNECT_SCHEMA`, `HEROKU_POSTGRESQL_SILVER`, `TREASURE_DATA_API_KEY`] > the services detected by the spring.cloud framework
- IP: `172.17.85.210` > the ip adress of the app, should be differ from restart to restart
- Port: `6732`> the port on which the app is listening, should be differ from restart to restart