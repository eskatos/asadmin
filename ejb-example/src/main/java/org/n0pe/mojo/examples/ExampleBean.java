package org.n0pe.mojo.examples;


import javax.ejb.Stateless;

import org.apache.commons.lang.StringUtils;


@Stateless
public class ExampleBean
        implements ExampleLocal {


    public String sayHelloTo(String givenName) {
        return "Hello " + (StringUtils.isEmpty(givenName)
                ? "null"
                : givenName) + "!";
    }


}
