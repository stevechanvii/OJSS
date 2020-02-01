# Online Job Seeking System (OJSS)

Online job seeking system for both recruiter and seekers. This project uses Java Web + JSP + MySQL as backend and HTML + JavaScript + CSS +jQuery for frontend, MVC model is introduced in the project and we strictly followed agile development.

### Job Seeker Management
The system allows new job seekers to create their accounts and manage skillsets and search for jobs based on the skills as well as various criteria such as locations, types of jobs, described compensation level. 

Job seekers can see the details of each job and apply the jobs listed, and they can also receive invitations for interview sent by the recruiters.

### Job Management
Recruiter can manage jobs, which must be assigned to relevant categories, job description includes relevant keywords such as location, salary, compensation level.

### Recruiter Management
Recruiters could create their accounts and log-in into the system to manage jobs, which can be selected for release, so that can be shown and searched by job seekers.

A recruiter can see who applied for a particular job, and see the details of the applied job seekers.

### Matching Management
The system could match job seekers to potential jobs with matching scores which is generated Matching Algorithm, and send an invitation message for interview to a highly-ranked job seeker.


## Getting Started

### Prerequisites

JDK 1.8.0_141

Backend:  
Server: glassfish 4.0  
Database: MySQL 14.14

IDE:  
Netbeans 5.2


### Installing
Create database by importing doc/mysql/OJSS_mysql.mwb

Setup database in src/java/DAO/Database.java, replace your local url and username, pwd

use glassfish hosting the project

## Example
Seeker searching jobs
![Screenshot](/doc/img/job_search.png)

Seeker apply jobs
![Screenshot](/doc/img/apply_job.png)

Recruiter register
![Screenshot](/doc/img/recruiter_management.png)

Recruiter create jobs
![Screenshot](/doc/img/create_job.png)

Recruiter send invitation
![Screenshot](/doc/img/send_invitations.png)

## Authors

* **Danyang Chen** - *Backend Developer & System Architect* - [SteveChanVII](https://github.com/stevechanvii/)
* **Dawei Tang** - *Backend Developer & System Architect*
* **Liwen Liang** - *Frontend Developer & UI Designer*

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

