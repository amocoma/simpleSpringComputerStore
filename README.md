# Getting started on Heroku with a simple sample
## Overview
## Steps to explore Heroku's functionalities
1. Clone the git repository: git clone https://github.com/amocoma/simpleSpringComputerStore.git
2. Change into the directory: cd simpleSpringComputerStore
3. Create heroku app and add heroku git remote to the git config: heroku create
4. Open up the browser and take a look at the app: heroku open
5. Oops: an error :-(
6. We have to push the app ... :-) git push heroku master
7. Open up the browser and take a look at the app: heroku open
8. Oops: an error :-(
9. Check what is running: heroku ps
10. Ahhh: there is no process running ... we need a Procfile: mv Procfile_ Procfile
11. Checkin the changes: git add Procfile / git commit -a -m "Procfile added" / git push heroku master
12. Open up the browser and take a look at the app: heroku open
13. Oops: an error :-(
14. Check what's running: heroku ps
15. We need at least one process to start the app ... so let's scale to one: heroku ps:scale web:1
16. Open up the browser and take a look at the app: heroku open
17. Oops: an error :-(
18. Check the logs: heroku logs -t
19. Ahhh the postgres lib is missing ... vi pom.xml >> include the psql dependency
20. Checkin the changes: git add pom.xml / git commit -a -m "PSQL jdbc added" / git push heroku master
21. Open up the browser and take a look at the app: heroku open
22. Yes
23. Check also the /api resource ....
24. stay tuned




	
	Indent each line by at least 1 tab, or 4 spaces.
    var Mou = exactlyTheAppIwant; 