package uk.gov.nhsdigital.cofe.generate;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Flag;
import ca.uhn.fhir.model.dstu2.resource.Bundle.Entry;
import ca.uhn.fhir.model.dstu2.resource.Parameters.Parameter;
import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.narrative.DefaultThymeleafNarrativeGenerator;
import ca.uhn.fhir.model.dstu2.resource.Composition;
import ca.uhn.fhir.model.dstu2.resource.Condition;
import ca.uhn.fhir.model.dstu2.resource.MessageHeader;
import ca.uhn.fhir.model.dstu2.resource.OperationOutcome;
import ca.uhn.fhir.model.dstu2.resource.Organization;
import ca.uhn.fhir.model.dstu2.resource.Parameters;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.resource.Practitioner;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.narrative.BaseThymeleafNarrativeGenerator;
import ca.uhn.fhir.narrative.CustomThymeleafNarrativeGenerator;
import ca.uhn.fhir.narrative.INarrativeGenerator;
import ca.uhn.fhir.parser.IParser;


public class GenerateExamplar {
	

public static void main(String[] args) {

	    /*
	     * This class contains SWK003FHIRClient test calls
	    */

	    // create a context for DSTU2
		FhirContext ctx = FhirContext.forDstu2();
		// Create EOL XML Document
		Bundle bndl = CreateXMLDocument.createBundle();
		Composition cp = CreateXMLDocument.createComposition();
		Patient pt = CreateXMLDocument.createPatient();
		Practitioner prac = CreateXMLDocument.createPractitioner();
		Organization orgn = CreateXMLDocument.createOrganization();
		Condition cond = CreateXMLDocument.createCondition();
		
		final Entry entryBundle = bndl.addEntry();		
		final Entry entryPat = bndl.addEntry();
		final Entry entryPrac = bndl.addEntry();
		final Entry entryOrg = bndl.addEntry();
		final Entry entryCond = bndl.addEntry();
		
	    entryBundle.setResource(cp);
	    entryPat.setResource(pt);
	    entryPrac.setResource(prac);
	    entryOrg.setResource(orgn);
	    entryCond.setResource(cond);
		
		// We can now use a parser to encode this resource into a string.
		String output = ctx.newXmlParser().setPrettyPrint(true).encodeResourceToString(bndl);
		System.out.println(output);
		
		//create client
	    String serverBaseUrl = "http://fhirtest.uhn.ca/baseDstu2";
	    //String serverBaseUrl = "http://fhir3.healthintersections.com.au/open";
		IGenericClient client = ctx.newRestfulGenericClient(serverBaseUrl);
		
		//use client to store a new resource instance
		//MethodOutcome outcome = client.create().resource(bndl).execute();
		//System.out.println(outcome.getId());
		
		//use the client to read back new instance using the ID we retrieved from the read
		//Bundle bndl1 = client.read(Bundle.class, "112748");
		//print ID of newly created resource
        //System.out.println(bndl.getId());
		//change the bundle id and send update to the server
        //bndl.setId("15daadee-26e1-4d6a-TEST-7f4af9b58877");
		
		MethodOutcome outcome1 = client.update().resource(bndl).execute();
		//print the ID of newly created resource
		System.out.println(outcome1.getId());
		
        //Output json format bundle
        //FhirContext ctx1 = FhirContext.forDstu2();
        //String output3 = ctx1.newJsonParser().setPrettyPrint(true).encodeResourceToString(bndl);
        //System.out.println(output3);
		
		//build a search and execute
		//Bundle response = client.search()
			//	.forResource(arg0)
		
}
}