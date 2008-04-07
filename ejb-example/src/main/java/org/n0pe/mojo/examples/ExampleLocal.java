package org.n0pe.mojo.examples;


import javax.ejb.Local;


@Local
public interface ExampleLocal {


    String sayHelloTo(String givenName);


}
