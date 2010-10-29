asadmin-java
============

asadmin-java is a java api to invoke glassfish v2 asadmin commands

The project is hosted in maven central.
[here](https://repository.sonatype.org/index.html#nexus-search;quick~asadmin-java) you'll find a quick copy/paste for the dependency.


asadmin-maven-plugin
====================

asadmin-maven-plugin is a maven plugin based on asadmin commands

The project is hosted in maven central, to use it add the following snippet to your pom.xml:


	<build>
	  <plugins>
 	    <plugin>
	      <groupId>org.codeartisans.asadmin</groupId>
	      <artifactId>asadmin-maven-plugin</artifactId>
	      <version>0.7</version>
	    </plugin>
	  </plugins>
	</build>

asadmin commands are simply mapped to goals:

    mvn asadmin:start-database
    mvn asadmin:start-domain
    mvn asadmin:deploy
    mvn asadmin:redeploy
    mvn asadmin:undeploy
    mvn asadmin:stop-domain
    mvn asadmin:stop-database

By default the asadmin-maven-plugin use the following settings :

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
                    <version>0.7</version>
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

