package colibri.util;

import java.io.FileReader;
import java.io.IOException;


import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import colibri.lib.ComparableSet;
import colibri.lib.ComparableTreeSet;
import colibri.lib.Concept;
import colibri.lib.Edge;



public class LatticeReader extends DefaultHandler {
	
	private enum Section {
		CONCEPT,
		OBJECTS,
		ATTRIBUTES,
		SUBCONCEPTS,
		SUPERCONCEPTS,
		EDGE_UPPER,
		EDGE_LOWER,
	};
	
	
	Integer currentConcept;
	Section currentSection;
	private EdgeLattice lattice = null;
	
	private ComparableSet upperObjects;
	private ComparableSet lowerObjects;
	private ComparableSet upperAttributes;
	private ComparableSet lowerAttributes;
	
	
	public LatticeReader () {
		super();
	}

	
	public EdgeLattice read(String fileName) throws SAXException, IOException {
		lattice = new EdgeLattice();
		XMLReader reader = XMLReaderFactory.createXMLReader();
		reader.setContentHandler(this);
		reader.setErrorHandler(this);
		
		FileReader fileReader = new FileReader(fileName);
		try {
			reader.parse(new InputSource(fileReader));
		} finally {
			fileReader.close();
		}
		return lattice;
	}
	
	
	public void startElement (String uri, String name, String qName, Attributes atts) {
		if (qName.equals("concept")) {
			currentConcept = Integer.valueOf(atts.getValue("id"));
			lattice.addConcept(currentConcept);
		}
		if (qName.equals("objects")) {
			currentSection = Section.OBJECTS;
		}
		if (qName.equals("attributes")) {
			currentSection = Section.ATTRIBUTES;
		}
		if (qName.equals("subconcepts")) {
			currentSection = Section.SUBCONCEPTS;
		}
		if (qName.equals("superconcepts")) {
			currentSection = Section.SUPERCONCEPTS;
		}
		if (qName.equals("upper_concept")) {
			currentSection = Section.EDGE_UPPER;
			upperObjects = new ComparableTreeSet();
			upperAttributes = new ComparableTreeSet();
		}
		if (qName.equals("lower_concept")) {
			currentSection = Section.EDGE_LOWER;
			lowerObjects = new ComparableTreeSet();
			lowerAttributes = new ComparableTreeSet();
		}
		if (qName.equals("element")) {
			switch (currentSection) {
			case OBJECTS:
				lattice.addObject(currentConcept, atts.getValue("name"));
				break;
			case ATTRIBUTES:
				lattice.addAttribute(currentConcept, atts.getValue("name"));
				break;
			case SUBCONCEPTS:
				lattice.addSubconcept(currentConcept, Integer.valueOf(atts.getValue("name").replace(" ", "")));
				break;
			case SUPERCONCEPTS:
				lattice.addSuperconcept(currentConcept, Integer.valueOf(atts.getValue("name").replace(" ", "")));
				break;
			}
		}
		if (qName.equals("object")) {
			if (currentSection == Section.EDGE_UPPER) {
				upperObjects.add(atts.getValue("name"));
			}
			if (currentSection == Section.EDGE_LOWER) {
				lowerObjects.add(atts.getValue("name"));
			}
		}
		if (qName.equals("attribute")) {
			if (currentSection == Section.EDGE_UPPER) {
				upperAttributes.add(atts.getValue("name"));
			}
			if (currentSection == Section.EDGE_LOWER) {
				lowerAttributes.add(atts.getValue("name"));
			}
		}
	}
	
	
	public void endElement (String uri, String name, String qName) {
		if (qName.equals("concept")) {
			currentConcept = null;
		}
		if (qName.equals("edge")) {
			lattice.addEdge(new Edge(new Concept(upperObjects, upperAttributes), new Concept(lowerObjects, lowerAttributes)));
			upperObjects = null;
			lowerObjects = null;
			upperAttributes = null;
			lowerAttributes = null;
		}
		if (qName.equals("objects") || qName.equals("attributes") || qName.equals("subconcepts")
				|| qName.equals("superconcepts") || qName.equals("upper_concept") || qName.equals("lower_concept")) {
			currentSection = null;
		}
	}
}
