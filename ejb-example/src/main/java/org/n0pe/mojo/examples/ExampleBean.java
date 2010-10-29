package org.n0pe.mojo.examples;

import javax.ejb.Stateless;

@Stateless
public class ExampleBean
        implements ExampleLocal
{

    public String sayHelloTo( String givenName )
    {
        return "Hello " + ( isEmpty( givenName )
                ? "null"
                : givenName ) + "!";
    }

    private boolean isEmpty( String s )
    {
        return s == null || s.length() <= 0;
    }

}
