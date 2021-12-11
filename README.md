# IOTDeviceWebservice
IOTDeviceWebservice

----------------------------------------------------------------
Welcome to IOTDeviceWebservice guide

I. Install JDK and Maven
	1. Please make sure that JDK and Maven were install
	2. Follow the step of the Installation Document.docx

II. Design/Pattern
	This system is designed base on the MVC model. 
	The DAO layer will handle the data mapping with persistence layer or database(but in this project we don't have them).
	Service layer is on top of DAO, will handle the logic of the webservice, communicate with DAO to retrieve or save data.
	And the controller will handle endpoint mapping, controller will get data from request, sent it to service and return the response.
	---
	The reason that I choose this design because:
	Separate components
	Easier to maintain, because all modules are separate
	Easier for develops to handle separate tasks
	It supports TTD (test-driven development). We can create an application with unit test. We can write own test case
	SOLID tells you to split out responsibilities, and model-view-controller (MVC) indicates where those splits should be. 
	It is the basis of your system's architecture. Each class in the system falls into one of the three categories and therefore has a specific purpose.
	
III. Summary
	Preparation: 1 hour
		Clear requirement, create project, setup environment
	Coding: 6 hours
		Implement requirement: 4h30m
		Coding Unit test 20m
		Developer testing: 40m 
	Documentation: 30m	
	Check in code building and testing:30m
	Grand total: 8 hours 


	
	