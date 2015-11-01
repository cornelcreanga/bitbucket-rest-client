Stash Rest Java Wrapper
-------------------------------


Tiny Java library acting as a wrapper over the Stash (Bitbucket Server) REST API.
 
Work in progress. For the moment I plan to obtain pull requests statistics so I will mostly implement the GET methods

Getting Started
---------------

Until the project will be deployed on the Maven Central you have to do the following:

* Clone the project on your machine
* Run maven clean install
* Add the following Maven dependency into your project
	<pre>&lt;dependency>
    		&lt;groupId>com.cc.bitbucket&lt;/groupId>
    		&lt;artifactId>bitbucket-rest-client&lt;/artifactId>
    		&lt;version>1.0-SNAPSHOT&lt;/version>
		&lt;/dependency>
	</pre>

Samples
-------

<pre>

//Getting all the projects

BitBucketClientFactory factory = BitBucketClientFactory(new URL(bitBucketUrl),new BitBucketCredentials(bitBucketUser,bitBucketPassword));

ProjectClient projectClient = factory.getProjectClient();

System.out.println(projectClient.getProjects(new Limit(0,100));

</pre>