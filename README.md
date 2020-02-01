# Online Job Seeking System

Online job seeking system for both recruiter and seekers.

## Job Seeker Management
The system should allow new job seekers to create their accounts and log-in into the system.
The system must provide functions for a job seeker to create/modify/delete its own skillsets
comprised of keywords.
A job seeker must be able to search for jobs based on his/her skills as well as various criteria in the
form of keywords such as locations, types of jobs, described compensation level, etc. The output must display a list of potentially relevant jobs to the queries which can be ranked according to their relevance.
Once the search is completed, the job seeker can see the details of each job and apply for some among the jobs listed. The applied jobs must be shown to the job seeker.
A job seeker must be able to see invitations for interview sent by the recruiters.

## Job Management
 A job can be created/modified/deleted by a recruiter.
 A recruiter can create a job by doing the following:
o its text-based description must be provided;
o its relevant categories should be assigned. Those categories must be managed
(created/modified/deleted) by the recruiter;
o relevant keywords should be assigned; and
o job related keywords (e.g. location, salary, compensation level, etc) must be given.
 Once a job is created, it must have its unique identifier.

## Recruiter Management
 The system should allow new recruiters to create their accounts and log-in into the system.
 A recruiter can create/modify/delete its own job categories.
 A recruiter can create a job.
 A recruiter must be able to see the jobs created, and can select some for advertisement. Once jobs are
advertised, these can be shown and searched by job seekers.
 The system must allow a recruiter to match job seekers to potential jobs with matching scores. The
recruiter can send an invitation message for interview to a highly-ranked job seeker.
 A recruiter can see who applied for a particular job, and see the details of the applied job seekers.

## Matching Management
 Matching between job seekers and jobs can be done based on the keywords assigned to them.
 The detailed algorithms for matching can be suggested by software engineers.
 Matching scores must be provided and be interpreted as relevance scores between queries and results.


![Screenshot](/src/assets/burger_app_1.png)

## Getting Started

### Prerequisites

VS Code

### Installing

Firstly, the environment should be setup, please refer to React offical site: https://reactjs.org

Download the code, go to project root directory and install packages, dependencies
```
npm install
```

## Firebase setup
Create your own Realtime Firebase, and replace the baseURL in axios-order.js

In authentication, enable Sign-in method to email/password. 
In database, change the rules to
```
{
  "rules": {
    "ingredients": {
        ".read": true,
        ".write": true
      },
      "orders": {
        ".read": "auth != null",
        ".write": "auth != null",
        ".indexOn": ["userId"]
      }
  }
}
```


## Running the tests
```
npm start
```
![Screenshot](/src/assets/burger_app_2.png)

[Jest](https://jestjs.io/docs/en/getting-started) and [Enzyme](https://airbnb.io/enzyme/docs/api/) is used to test NavigationItems.test.js, BurgerBuilder.test.js and auth.test.js, type following code in terminal to run the test
```
node scripts/test.js --env=jsdom
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [React](https://reactjs.org/) - The web framework used


## Versioning

[V0.1] 11/Jan/2020
* Add and remove ingredients of the burger
* Make the order and the order summary will be upload to databse

[V0.1.2] 16/Jan/2020
* Implement customer contact form, authenticated and upload to database

[V0.2] 25/Jan/2020
* Introducing redux to manage the state
* Using middleware to request data asynchronously

[V0.3] 30/Jan/2020
* Implement login and register function
* Manage and persist user token
* Imporve network security

[V0.3.2] 31/Jan/2020
* Add test cases
* Release https://react-my-burger-6732f.firebaseapp.com/

## Authors

* **Danyang Chen** - *Initial work* - [SteveChanVII](https://github.com/stevechanvii/)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

