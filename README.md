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
	      <groupId>org.codeartisans.asadmin-maven-plugin</groupId>
	      <artifactId>asadmin-maven-plugin</artifactId>
	      <version>0.6</version>
	    </plugin>
	  </plugins>
	</build>

