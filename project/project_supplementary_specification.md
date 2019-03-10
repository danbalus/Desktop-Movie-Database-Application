# (Movie Database) Supplementary Specification

# Introduction
[The introduction of the Supplementary Specification provides an overview of the entire document.
The Supplementary Specification captures the system requirements that are not readily captured in the use cases of the use-case model. Such requirements include:

- Legal and regulatory requirements, including application standards.
- Quality attributes of the system to be built, including usability, reliability, performance, and supportability requirements.
- Other requirements such as operating systems and environments, compatibility requirements, and design constraints.]

# Non-functional Requirements

[Define system quality attributes in terms of scenarios according to the following template:
-	Quality attribute definition
-	Source of stimulus: the entity (human or another system) that generated the stimulus or event
-	Stimulus: a condition that determines a reaction of the system
-	Environment: the current condition of the system when the stimulus arrives
-	Artifact: is a component that reacts to the stimulus. It may be the whole system or some pieces of it
-	Response: the activity determined by the arrival of the stimulus
-	Response measure: the quantifiable indication of the response
-	Tactics
]
## Availability
-	Attribute definition: probability of a system to be operational when needed;
-	Source of stimulus: external;
-	Stimulus: 
    - timing: component fails to respond to an input;
    - omission: a component responds late.
-	Environment: defines the state of the system when the failure occurred.
-	Artifact: the resource that is required to be available.
-	Response: notification, logging.  
-	Response measure: the availability percentage.
## Performance
-	Attribute definition: : refers to the time it takes the system to respond to an event;
-	Source of stimulus: external;
-	Stimulus: events arrive rather randomly;
-	Environment: the response varies depending on the current state of the system because it can be in numerous operational modes;
-	Artifact: the system's service, which has to respond to the event;
-	Response: The system must process the arriving events.The response of the system can be characterized by:
		-  _latency_ = time between data transmission and system response;
                -  _data lost_ caused by busy system;
-	Response measure: include _latency_ , number of events that can be processed within a particular time interval, and specification of the events that can't be processed (data lost)
## Security
-	Attribute definition: system's ability to resist unauthorized usage, an attempt to breach security.
-	Source of stimulus: individual, Known identity because only employees can acces the DB server.
-	Stimulus: change/delete data, access system services and reduce availability to system services.
-	Environment: connect from local network or accept only the IP’s from company.
-	Artifact: system services.
-	Response: blocks access to data and services.
-	Response measure: 
## Testability
-	Attribute definition: The property of the system to provide expected results with a specified data as input.
-	Source of stimulus: :  developer, tester and user.
-	Stimulus: final stage.
-	Environment: : compile, design, development, run.
-	Artifact: design, code.
-	Response: : controlled and observed.
-	Response measure: time.
## Usability
-	Attribute definition:  user standards, particular goal and feel.
-	Source of stimulus: end user;
-	Stimulus: wish to feel comfortable with application;
-	Environment: runtime
-	Artifact: system
-	Response: provide ability 
-	Response measure: user satisfaction, time.

# Design Constraints
Application is implemented in Java programming language and it’s also based on SQL queries.
# Resources

* http://www.upedu.org/process/gdlines/md_srs.htm
* Example of Supplementary Specification: http://csis.pace.edu/~marchese/SE616_New/Samples/Example%20%20Supplementary%20Specification.htm
