package uk.gov.nhsdigital.cofe.generate;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.Composition.CompositionEventComponent;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.ExtensionDt;
import ca.uhn.fhir.model.api.ResourceMetadataKeyEnum;
import ca.uhn.fhir.model.api.TemporalPrecisionEnum;
import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.Extension;
import ca.uhn.fhir.model.base.resource.ResourceMetadataMap;
import ca.uhn.fhir.model.dstu.resource.Composition.Event;
import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.composite.ContactPointDt;
import ca.uhn.fhir.model.dstu2.composite.HumanNameDt;
import ca.uhn.fhir.model.dstu2.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu2.composite.NarrativeDt;
import ca.uhn.fhir.model.dstu2.composite.PeriodDt;
import ca.uhn.fhir.model.dstu2.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Bundle.Entry;
import ca.uhn.fhir.model.dstu2.resource.Communication;
import ca.uhn.fhir.model.dstu2.resource.Composition;
import ca.uhn.fhir.model.dstu2.resource.Composition.Attester;
import ca.uhn.fhir.model.dstu2.resource.Composition.Section;
import ca.uhn.fhir.model.dstu2.resource.Condition;
import ca.uhn.fhir.model.dstu2.resource.MessageHeader;
import ca.uhn.fhir.model.dstu2.resource.MessageHeader.Destination;
import ca.uhn.fhir.model.dstu2.resource.MessageHeader.Source;
import ca.uhn.fhir.model.dstu2.resource.Organization;
import ca.uhn.fhir.model.dstu2.resource.Parameters;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.resource.Parameters.Parameter;
import ca.uhn.fhir.model.dstu2.resource.Practitioner;
import ca.uhn.fhir.model.dstu2.resource.Practitioner.PractitionerRole;
import ca.uhn.fhir.model.dstu2.valueset.AddressUseEnum;
import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;
import ca.uhn.fhir.model.dstu2.valueset.BundleTypeEnum;
import ca.uhn.fhir.model.dstu2.valueset.CompositionAttestationModeEnum;
import ca.uhn.fhir.model.dstu2.valueset.CompositionStatusEnum;
import ca.uhn.fhir.model.dstu2.valueset.ConditionVerificationStatusEnum;
import ca.uhn.fhir.model.dstu2.valueset.ContactPointSystemEnum;
import ca.uhn.fhir.model.dstu2.valueset.ContactPointUseEnum;
import ca.uhn.fhir.model.dstu2.valueset.IdentifierUseEnum;
import ca.uhn.fhir.model.dstu2.valueset.NameUseEnum;
import ca.uhn.fhir.model.primitive.BoundCodeDt;
import ca.uhn.fhir.model.primitive.CodeDt;
import ca.uhn.fhir.model.primitive.DateDt;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.model.primitive.InstantDt;
import ca.uhn.fhir.model.primitive.StringDt;
import ca.uhn.fhir.narrative.CustomThymeleafNarrativeGenerator;
import ca.uhn.fhir.narrative.DefaultThymeleafNarrativeGenerator;

public class CreateXMLDocument {

	@SuppressWarnings("unchecked")
	public static Bundle createBundle(){
		
		final Bundle bndl = new Bundle();
		
		bndl.setId("14daadee-26e1-4d6a-9e6a-7f4af9b58877");
		
		ResourceMetadataMap meta = new ResourceMetadataMap();
		meta.put(ResourceMetadataKeyEnum.PROFILES, "http://fhir.nhs.net/StructureDefinition/cofe-document-bundle-1");
		bndl.setResourceMetadata(meta);
		//SWK bundle type
		//These valuesets must either be empty, or be populated with a value drawn from the list of allowable values 
		//defined by FHIR. HAPI provides special typesafe Enums to help in dealing with these fields.
		// HAPI provides Java enumerated types which make it easier to
		// deal with coded values. This code achieves the exact same result
		bndl.setType(BundleTypeEnum.DOCUMENT);
		// end bundle type
		return bndl;
	}
	
	
	@SuppressWarnings("unchecked")
	public static Patient createPatient(){
		
		Patient pat = new Patient();
		
		pat.setId("22daadee-26e1-4d6a-9e6a-7f4af9b58800");
		
		ResourceMetadataMap meta = new ResourceMetadataMap();
		meta.put(ResourceMetadataKeyEnum.PROFILES, "http://fhir.nhs.net/StructureDefinition/cofe-patient-1");
		pat.setResourceMetadata(meta);
		
		pat.addIdentifier().setUse(IdentifierUseEnum.OFFICIAL).setSystem("http://fhir.nhs.net/Id/nhs-number").setValue("6305786860");
		pat.addName().setUse(NameUseEnum.OFFICIAL).addFamily("Hembury").addGiven("Diane").addPrefix("Mrs");
		pat.addName().setUse(NameUseEnum.USUAL).addGiven("Diane");
		pat.setGender(AdministrativeGenderEnum.FEMALE);
		pat.setBirthDate(new DateDt("1977-05-01"));
		pat.addAddress().setUse(AddressUseEnum.HOME).addLine("21, Grove Street").setCity("Overtown").setDistrict("Bath").setPostalCode("BA21 1PF");
		pat.addAddress().setUse(AddressUseEnum.TEMPORARY).addLine("15 Lucius Street").setCity("Foxtown").setDistrict("Bath").setPostalCode("BA31 1BT");
		pat.addAddress().setUse(AddressUseEnum.WORK).addLine("Room 8").addLine("2nd floor").addLine("Flat 2").addLine("1 Lansdown Place").setDistrict("East Bath").setPostalCode("BA1 5ET");
		
		//Communication BROKEN
		//Communication comm = new Communication();
		//comm.setLanguage(new CodeDt("en"));
		pat.setLanguage(new CodeDt("en"));
		pat.getCommunicationFirstRep().setPreferred(true);
		
		 ResourceReferenceDt rr4 = new ResourceReferenceDt();
		 rr4.setReference("Organization/43daadee-26e1-4d6a-9e6a-7f4af9b58800");
		 List<ResourceReferenceDt> rr4list = new ArrayList<ResourceReferenceDt>();
		 rr4list.add(rr4);
		 pat.setCareProvider(rr4list);
		
		//Works
		//HumanNameDt name = pat.addName();
		//name.setUse(IdentifierUseEnum.OFFICIAL)
		//name.addFamily().setValue("Hembury");
		//name.addGiven().setValue("Diane");
		//name.addPrefix().setValue("Mrs");
			
		// create a context for DSTU2
		//FhirContext ctx = FhirContext.forDstu2();
	    // Use the narrative generator
		//ctx.setNarrativeGenerator(new DefaultThymeleafNarrativeGenerator());
			 
		// Encode the output, including the narrative
		//String output9 = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(pat);
		//	System.out.println(output9);
			
		return pat;
	}

	
	@SuppressWarnings("unchecked")
	public static Practitioner createPractitioner(){
		
		Practitioner pract = new Practitioner();
		
		pract.setId("10daadee-26e1-4d6a-9e6a-7f4af9b58870");
		
		ResourceMetadataMap meta = new ResourceMetadataMap();
		meta.put(ResourceMetadataKeyEnum.PROFILES, "http://fhir.nhs.net/StructureDefinition/cofe-practitioner-1");
		pract.setResourceMetadata(meta);
		
		//pract.addIdentifier().setSystem("http://fhir.nhs.net/Id/sds-user-id").setValue("123456");
		//pract.addIdentifier().setSystem("http://fhir.nhs.net/Id/sds-role-profile-id").setValue("abc123");
		
		//pract.getName().setUse(NameUseEnum.OFFICIAL).addFamily("Kirk").addGiven("Bill").addPrefix("Dr");
		//pract.addAddress().setUse(AddressUseEnum.WORK).addLine("Highfield GP Practice").addLine("11, Grove Street").setCity("Overtown").setDistrict("Bath").setPostalCode("BA21 1PF");
	
	
        List<IdentifierDt> identifierList = new ArrayList<IdentifierDt>();
		
		identifierList.add(0,new IdentifierDt().setUse(IdentifierUseEnum.OFFICIAL).setSystem("http://fhir.nhs.net/Id/sds-user-id").setValue("G12345678"));
		identifierList.add(1,new IdentifierDt().setUse(IdentifierUseEnum.OFFICIAL).setSystem("http://fhir.nhs.net/Id/sds-role-profile-id").setValue("PT1234"));
		pract.setIdentifier(identifierList);
		
		pract.setName(new HumanNameDt().addFamily("Wood").addGiven("Town").addPrefix("Dr.").setUse(NameUseEnum.OFFICIAL));
		
		List<PractitionerRole> PracRoleList = new ArrayList<PractitionerRole>();
		
		PractitionerRole pr1 = new PractitionerRole();
		pr1.setManagingOrganization(new ResourceReferenceDt().setReference("Organization/43daadee-26e1-4d6a-9e6a-7f4af9b58800"));
		
		pr1.getRole().getCodingFirstRep().setSystem("http://fhir.nhs.net/ValueSet/sds-job-role-name-1-0").setCode("R0090").setDisplay("Hospital Practitioner");
		
		PracRoleList.add(pr1);
		
		pract.setPractitionerRole(PracRoleList);
		
		return pract;
		
	}

	
	public static Organization createOrganization(){
		
		Organization org = new Organization();
		org.setId("43daadee-26e1-4d6a-9e6a-7f4af9b58800");
		org.setName("THE WHITTINGTON HOSPITAL NHS TRUST");
		
		ResourceMetadataMap meta = new ResourceMetadataMap();
		meta.put(ResourceMetadataKeyEnum.PROFILES, "http://fhir.nhs.net/StructureDefinition/spine-organization-1-0");
		org.setResourceMetadata(meta);
		
		IdentifierDt rr2 = new IdentifierDt();
		rr2.setSystem("http://fhir.nhs.net/Id/ods-organization-code").setValue("RKE");
		List<IdentifierDt> idenList = new ArrayList<IdentifierDt>();
		idenList.add(rr2);
		org.setIdentifier(idenList);
		return org;
	}
	
	
	public static Condition createCondition(){
		
		Condition cond1 = new Condition();
		cond1.setId("a0ca3120-0cf1-4b3f-a3d3-4324cb7647cc");
		
		ResourceMetadataMap meta = new ResourceMetadataMap();
		meta.put(ResourceMetadataKeyEnum.PROFILES, "http://fhir.nhs.net/StructureDefinition/cofe-diagnosis-condition-1");
		cond1.setResourceMetadata(meta);
		//Subject
		cond1.setPatient((new ResourceReferenceDt().setReference("Patient/22daadee-26e1-4d6a-9e6a-7f4af9b58800").setDisplay("Mrs Diane Hembury")));
		
		// Coded types can naturally be set using plain strings
		CodingDt condcode = cond1.getCode().addCoding();
		condcode.setSystem("http://snomed.info/sct");
		condcode.setCode("405843009:106229004=8319008");
		condcode.setDisplay("405843009 |Widespread metastatic malignant neoplastic disease|:106229004 |Qualifier for type of diagnosis| = 8319008 |Principal diagnosis|");
		
		// Coded types can naturally be set using plain strings
		CodingDt condcat = cond1.getCode().addCoding();
		condcat.setSystem("http://hl7.org/fhir/ValueSet/condition-category");
		condcat.setCode("diagnosis");
		condcat.setDisplay("diagnosis");
		cond1.setVerificationStatus(ConditionVerificationStatusEnum.CONFIRMED);
		return cond1;
	}
	
	@SuppressWarnings("unchecked")
	public static <Text, Narrative> Composition createComposition() {
		// TODO Auto-generated method stub
		
		Composition comp = new Composition();
		
		// Create an extension
		//ExtensionDt ext = new ExtensionDt();
		//ext.setModifier(false);
		//ext.setUrl("http://example.com/extensions#someext");
		//ext.setValue(new DateTimeDt("2011-01-02T11:13:15"));
		//ext.getAllUndeclaredExtensions("http://example.com/extensions#someext");
		 
		// Add the extension to the resource
		//comp.addUndeclaredExtension(ext);
		
		ExtensionDt ext1 = new ExtensionDt();
		ext1.setModifier(false);
		ext1.setUrl("http://fhir.nhs.net/StructureDefinition/extension-cofe-data-enterer-1");
		ext1.setValue((new ResourceReferenceDt().setReference("Practitioner/10daadee-26e1-4d6a-9e6a-7f4af9b58870").setDisplay("Dr Wood")));
		
		// Add the extension to the resource
		comp.addUndeclaredExtension(ext1);
		
		ExtensionDt ext2 = new ExtensionDt();
		ext2.setModifier(false);
		ext2.setUrl("http://fhir.nhs.net/StructureDefinition/extension-cofe-information-recipient-1");
		ext2.setValue((new ResourceReferenceDt().setReference("Practitioner/10daadee-26e1-4d6a-9e6a-7f4af9b58870").setDisplay("Dr Wood")));
		
		// Add the extension to the resource
		comp.addUndeclaredExtension(ext2);
		
		comp.setId("13daadee-26e1-4d6a-9e6a-7f4af9b58878");
		
		ResourceMetadataMap meta = new ResourceMetadataMap();
		meta.put(ResourceMetadataKeyEnum.PROFILES, "http://fhir.nhs.net/StructureDefinition/cofe-composition-end-of-life-1");
		comp.setResourceMetadata(meta);
		
		//Needs NarrativeDt XHTML
		// NarrativeDt text1 = comp.getText();
	     //text1.setDiv("Plain text representation of the concept");
		
		comp.setDate(new DateTimeDt("2015-07-11T10:11:15+00:00"));
		
		// Coded types can naturally be set using plain strings
	    CodingDt doctype = comp.getType().addCoding();
		doctype.setSystem("http://fhir.nhs.net/ValueSet/correspondence-document-type-1");
		doctype.setCode("861421000000109");
		doctype.setDisplay("End of Life Care Coordination Summary");
		
		//Class
		CodingDt classtype = comp.getClassElement().addCoding();
		classtype.setSystem("http://hl7.org/fhir/ValueSet/doc-classcodes");
		//LOINC: LP173421-1 Report 
		classtype.setCode("LP173421-1");
		classtype.setDisplay("LP173421-1");
		       
		comp.setTitle("End Of Life Document");
		comp.setStatus(CompositionStatusEnum.FINAL);
		        
		//confidentiality		        
		comp.setConfidentiality("V");
		//comp.setConfidentiality(CompositionConfidentialityEnum.V);
		        
		//Subject
		comp.setSubject(new ResourceReferenceDt().setReference("Patient/22daadee-26e1-4d6a-9e6a-7f4af9b58800").setDisplay("Mrs Diane Hembury"));
		//Author
		ResourceReferenceDt crr2 = new ResourceReferenceDt();
		crr2.setReference("Practitioner/10daadee-26e1-4d6a-9e6a-7f4af9b58870").setDisplay("King's College Hospital");
		List<ResourceReferenceDt> crr2list = new ArrayList<ResourceReferenceDt>();
	    crr2list.add(crr2);
	    comp.setAuthor(crr2list);
		      
		//Attester 
		Attester att1 = new Attester();
		att1.addMode(CompositionAttestationModeEnum.PROFESSIONAL);
		att1.setTime(new DateTimeDt("2015-07-11T10:11:15+00:00"));
		att1.setParty(new ResourceReferenceDt().setReference("Practitioner/41fe704c-18e5-11e5-b60b-1697f925ec7b").setDisplay("Dr Town Wood"));
		comp.addAttester(att1);
		        
		//Custodian
		comp.setCustodian(new ResourceReferenceDt().setReference("Organization/43daadee-26e1-4d6a-9e6a-7f4af9b58800").setDisplay("Coordinate My Care"));
		//test this bit..but redundant
		// ResourceReferenceDt crr3 = new ResourceReferenceDt();
		// crr3.setReference("Organization/79600119-ebaf-4362-bb89-d473a33b1675").setDisplay("Wood Town GP practice");
		        
		//Event OPTIONAL BROKEN
		// Event docevt = new Event();
		//docevt.getPeriod().getStartElement().setValueAsString("2001-01-02");
		// ca.uhn.fhir.model.dstu.composite.CodingDt evtcode = docevt.addCode().addCoding();
		//evtcode.setSystem("http://hl7.org/fhir/ValueSet/v3-ActCode");
		//evtcode.setCode("_ActCareProvisionCode");
		//evtcode.setDisplay("_ActCareProvisionCode");
        //PeriodDt evtdt1 = new PeriodDt();
        //evtdt1.setStart(new DateTimeDt("2001-01-02T11:11:00"));
        //PeriodDt evtperiod = docevt.getPeriod()
        //docevt.getPeriodElement();
		        
	    // docevt.setPeriod(new PeriodDt().setStart(new DateTimeDt("2001-01-02T11:11:00")));
			    
		//old PeriodDt code
	    //enc.setPeriod(new PeriodDt().setStart(new DateTimeDt("2001-01-02T11:11:00")));
			        
	    //docevt.setDetail((List<ca.uhn.fhir.model.dstu.composite.ResourceReferenceDt>) new ResourceReferenceDt().setReference("Practitioner/41fe704c-18e5-11e5-b60b-1697f925ec7b"));
        //ResourceReferenceDt crr3 = new ResourceReferenceDt();
        //crr3.setReference("Practitioner/41fe704c-18e5-11e5-b60b-1697f925ec7b").setDisplay("Practitioner");
        //List<ResourceReferenceDt> crr3list = new ArrayList<ResourceReferenceDt>();
        //crr3list.add(crr3);
        //docevent.setDetail(crr3list);
		        
        //docevt.getDetail()
		       
		//docevt.setDetail(new ResourceReferenceDt().setReference("Practitioner/41fe704c-18e5-11e5-b60b-1697f925ec7b").setDisplay("Dr Town Wood"));
        //comp.addAttester(att1);
				        
		//Encounter - works just no requirement to populate
		comp.setEncounter(new ResourceReferenceDt().setReference("Encounter/79600119-ebaf-4362-bb89-d473a33b1675").setDisplay("Encounter"));
		//test this bit..but redundant
		ResourceReferenceDt crr4 = new ResourceReferenceDt();
		crr4.setReference("Encounter/79600119-ebaf-4362-bb89-d473a33b1675").setDisplay("Encounter");
		        
		//Patient Demographics Section
		Section sect1 = new Section();
		sect1.setTitle("Patient Demographics");
		        
		CodingDt sect1code = sect1.getCode().addCoding();
		sect1code.setSystem("http://snomed.info/sct");
		sect1code.setCode("886731000000109");
		sect1code.setDisplay("Patient demographics (record artifact)");
		sect1.getText().setDiv("<div>This is the narrative text<br/>this is line 2</div>");
		comp.addSection(sect1);
		    	
		//Individual Requirements Section
		Section sect2 = new Section();
		sect2.setTitle("Individual Requirements");
		        
		CodingDt sect2code = sect2.getCode().addCoding();
		sect2code.setSystem("http://snomed.info/sct");
		sect2code.setCode("886731000000109");
		sect2code.setDisplay("Patient demographics (record artifact)");
		sect2.getText().setDiv("<div>This is the narrative text<br/>this is line 2 <br/> <b>GP Practice Code</b></div>");
		comp.addSection(sect2);
		    	
		//Diagnoses Section
		Section sect3 = new Section();
		sect3.setTitle("Diagnoses");
		        
		CodingDt sect3code = sect3.getCode().addCoding();
		sect3code.setSystem("http://snomed.info/sct");
		sect3code.setCode("887161000000102");
		sect3code.setDisplay("Diagnoses (record artifact)");
		sect3.getText().setDiv("<div>This is the narrative text<br/>this is line 2</div>");
		    	
		//BROKEN
		//String propFile = "classpath:/com/foo/customnarrative.properties";
        //String propFile = "classpath:/uk/gov/hscic/fgm/generate/customnarrative.properties";
        //CustomThymeleafNarrativeGenerator gen = new CustomThymeleafNarrativeGenerator(propFile);
    	 
        //FhirContext ctx = FhirContext.forDstu2();
        //ctx.setNarrativeGenerator(gen);
		    	  
		comp.addSection(sect3);
		    	
		ResourceReferenceDt rr5 = new ResourceReferenceDt();
	    rr5.setReference("Condition/43daadee-26e1-4d6a-9e6a-7f4af9b58800");
	    List<ResourceReferenceDt> rr5list = new ArrayList<ResourceReferenceDt>();
		rr5list.add(rr5);
	    sect3.setEntry(rr5list);
				
	    //Relevant Past Medical, Surgical and Mental Health History Section
		Section sect3a = new Section();
		sect3a.setTitle("Relevant Past Medical, Surgical and Mental Health History");
		        
		 CodingDt sect3acode = sect3a.getCode().addCoding();
		 sect3acode.setSystem("http://snomed.info/sct");
		 sect3acode.setCode(" 933271000000106");
		 // sect3acode.setDisplay("Diagnoses (record artifact)");
		 sect3a.getText().setDiv("<div>This is the narrative text<br/>this is line 2</div>");
		 comp.addSection(sect3a);
		        
		 //Problems and Issues Section
		 Section sect3b = new Section();
		 sect3b.setTitle("Problems and Issues");
		        
		 CodingDt sect3bcode = sect3b.getCode().addCoding();
		 sect3bcode.setSystem("http://snomed.info/sct");
		 sect3bcode.setCode(" 887151000000100");
		 //sect3bcode.setDisplay("Diagnoses (record artifact)");
		 sect3b.getText().setDiv("<div>This is the narrative text<br/>this is line 2</div>");
		 comp.addSection(sect3b);
		        
	     //Medications And Medical Devices Section
		 Section sect4 = new Section();
		 sect4.setTitle("Medications And Medical Devices");
		        
		 CodingDt sect4code = sect4.getCode().addCoding();
		 sect4code.setSystem("http://snomed.info/sct");
		 sect4code.setCode("933361000000108");
		 // sect4code.setDisplay("Medications and medical devices (record artifact)");
		 sect4.getText().setDiv("<div>This is the narrative text<br/>this is line 2</div>");
		 comp.addSection(sect4);
		    	
		 //needs updating to point to meds profiles
		 ResourceReferenceDt rr6 = new ResourceReferenceDt();
	     rr6.setReference("Condition/43daadee-26e1-4d6a-9e6a-7f4af9b58800");
	     List<ResourceReferenceDt> rr6list = new ArrayList<ResourceReferenceDt>();
	     rr6list.add(rr6);
		 sect4.setEntry(rr6list);
				
		 //Allergies and Adverse Reactions Section
		 Section sect5 = new Section();
		 sect5.setTitle("Allergies and Adverse Reactions");
		        
		 CodingDt sect5code = sect5.getCode().addCoding();
		 sect5code.setSystem("http://snomed.info/sct");
		 sect5code.setCode("886921000000105");
		 //sect5code.setDisplay("Allergies and Adverse Reactions (record artifact)");
		 sect5.getText().setDiv("<div>This is the narrative text<br/>this is line 2</div>");
		 comp.addSection(sect5);
		    	
		 //needs updating to point to allergies profiles
		 ResourceReferenceDt rr7 = new ResourceReferenceDt();
	     rr7.setReference("Condition/43daadee-26e1-4d6a-9e6a-7f4af9b58800");
	     List<ResourceReferenceDt> rr7list = new ArrayList<ResourceReferenceDt>();
	     rr7list.add(rr7);
		 sect5.setEntry(rr7list);
				
		 //Safety Alerts Section  Section
		 Section sect6 = new Section();
		 sect6.setTitle("Safety Alerts");
		        
		 CodingDt sect6code = sect6.getCode().addCoding();
		 sect6code.setSystem("http://snomed.info/sct");
		 sect6code.setCode("886931000000107");
		 //sect6code.setDisplay("Social Context (record artifact)");
		 sect6.getText().setDiv("<div>This is the narrative text<br/>this is line 2</div>");
		 comp.addSection(sect6);
		    	
		 //needs updating to point to COFE-Accommodation profiles
		 ResourceReferenceDt rr8 = new ResourceReferenceDt();
		 rr8.setReference("COFE-Accommodation-QuestionnaireResponse-1/43daadee-26e1-4d6a-9e6a-7f4af9b58800");
		 List<ResourceReferenceDt> rr8list = new ArrayList<ResourceReferenceDt>();
		 rr8list.add(rr8);
		 sect6.setEntry(rr8list);
				
		//Legal Information  Section
		Section sect7 = new Section();
		sect7.setTitle("Legal Information");
		        
		CodingDt sect7code = sect7.getCode().addCoding();
		sect7code.setSystem("http://snomed.info/sct");
		sect7code.setCode("886961000000102");
		//sect7code.setDisplay("Legal Information (record artifact)");
		sect7.getText().setDiv("<div>This is the narrative text<br/>this is line 2</div>");
		comp.addSection(sect7);
		    	
		//needs updating to point to COFE-LastingPowerofAttorney profiles
		ResourceReferenceDt rr9 = new ResourceReferenceDt();
	    rr9.setReference("COFE-LastingPowerofAttorney-Observation-1/43daadee-26e1-4d6a-9e6a-7f4af9b58800");
	    List<ResourceReferenceDt> rr9list = new ArrayList<ResourceReferenceDt>();
	    rr8list.add(rr9);
		sect7.setEntry(rr9list);
				
		//Social Context Section
		 Section sect8 = new Section();
		 sect8.setTitle("Social context");
		        
		 CodingDt sect8code = sect8.getCode().addCoding();
		 sect8code.setSystem("http://snomed.info/sct");
		 sect8code.setCode("887051000000101");
		 //sect7code.setDisplay("Legal Information (record artifact)");
		 sect8.getText().setDiv("<div>This is the narrative text<br/>this is line 2</div>");
		 comp.addSection(sect8);
		    	
		 //needs updating to point to COFE-LastingPowerofAttorney profiles
		  ResourceReferenceDt rr10 = new ResourceReferenceDt();
		  rr10.setReference("COFE-LastingPowerofAttorney-Observation-1/43daadee-26e1-4d6a-9e6a-7f4af9b58800");
		  List<ResourceReferenceDt> rr10list = new ArrayList<ResourceReferenceDt>();
		  rr10list.add(rr10);
		  sect8.setEntry(rr10list);
		  
		  //FhirContext ctx1 = FhirContext.forDstu2();
		    	 
		  //Use the narrative generator
		  //ctx1.setNarrativeGenerator(new DefaultThymeleafNarrativeGenerator());
		    	 
		  //Encode the output, including the narrative
		  //String output = ctx1.newJsonParser().setPrettyPrint(true).encodeResourceToString(comp);
		  //System.out.println(output);
		        
		  return comp;
	}

	private static void entext(ResourceReferenceDt setDisplay) {
		// TODO Auto-generated method stub
		
	}
		
}

