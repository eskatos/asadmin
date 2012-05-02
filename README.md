asadmin-java
============

asadmin-java is a java api to invoke glassfish V3 and V2 asadmin commands

The project is hosted in maven central.
[here](http://search.maven.org/#search%7Cga%7C1%7Casadmin-java) you'll find a quick copy/paste for the dependency.


asadmin-maven-plugin
====================

asadmin-maven-plugin is a maven plugin based on asadmin commands

The project is hosted in maven central, to use it add the following snippet to your pom.xml:


	<build>
	  <plugins>
 	    <plugin>
	      <groupId>org.codeartisans.asadmin</groupId>
	      <artifactId>asadmin-maven-plugin</artifactId>
	      <version>0.9</version>
	    </plugin>
	  </plugins>
	</build>

The following goals are available:

    mvn asadmin:start-database
    mvn asadmin:stop-database

    mvn asadmin:start-domain
    mvn asadmin:stop-domain

    mvn asadmin:start-cluster
    mvn asadmin:stop-cluster

    mvn asadmin:deploy
    mvn asadmin:redeploy
    mvn asadmin:undeploy

    mvn asadmin:uptime
    mvn asadmin:get-health

    mvn asadmin:get
    mvn asadmin:set
    
    mvn asadmin:set-ports # To change the http listeners ports

    mvn asadmin:create-jdbc-connection-pool
    mvn asadmin:ping-connection-pool
    mvn asadmin:delete-jdbc-connection-pool

    mvn asadmin:add-resources
    mvn asadmin:create-jdbc-resource
    mvn asadmin:delete-jdbc-resource

    mvn asadmin:create-auth-realm
    mvn asadmin:list-file-users
    mvn asadmin:create-file-user
    mvn asadmin:update-file-user
    mvn asadmin:delete-file-user

    mvn asadmin:create-message-security-provider
    mvn asadmin:delete-message-security-provider

By default the asadmin-maven-plugin use the following settings:

    user: admin
    password-file: ~/.asadmintruststore
    admin-host: localhost
    admin-port: 4848
    domain: domain1
    contextRoot: ${finalName} (only for WAR projects)


By default, the deploy and undeploy mojos use the archive (jar/war/ear) produced by your maven project and deploy it using your project's artifactId as module name.

Here is a pom example expressing the default configuration :

    <?xml version="1.0"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
        <modelVersion>4.0.0</modelVersion>

        [ ... ]

        <build>
            <plugins>
                <plugin>
                    <groupId>org.codeartisans.asadmin</groupId>
                    <artifactId>asadmin-maven-plugin</artifactId>
                    <version>0.9</version>
                    <configuration>
                        <glassfishHome>/path/to/your/glassfish/installation/folder</glassfishHome>
                        <domain>domain1</domain>
                        <host>localhost</host>
                        <port>4848</port>
                        <user>admin</user>
                        <passwordfile>~/.asadmintruststore</passwordfile>
                        <appArchive>${project.build.directory}/${project.build.finalName}.${project.artifact.artifactHandler.extension}</appArchive>
                        <contextRoot>war-exemple</contextRoot>
                    </configuration>
                </plugin>
            </plugins>
        </build>

        [ ... ]

    </project>


------------------

This code was previously hosted [here](http://code.google.com/p/asadmin-maven-plugin/).

