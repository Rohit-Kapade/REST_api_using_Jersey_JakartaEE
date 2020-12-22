package com.example.jerseyrestapi;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

	private static final String MOXY_JAXB_CONTEXT = "org.eclipse.persistence.jaxb.JAXBContext";
    private static final String METRO_JAXB_CONTEXT = "com.sun.xml.bind.v2.runtime.JAXBContextImpl";

    
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
    	
    	JAXBContext jc;
		try {
			jc = JAXBContext.newInstance(Employee.class);
			String jaxbContextImpl = jc.getClass().getName();
	        if(MOXY_JAXB_CONTEXT.equals(jaxbContextImpl)) {
	            System.out.println("EclipseLink MOXy");
	        } else if(METRO_JAXB_CONTEXT.equals(jaxbContextImpl)) {
	            System.out.println("Metro");
	        } else {
	            System.out.println("Other");
	        }
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
        
        return "Got it!";
    }
}
