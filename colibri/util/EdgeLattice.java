package colibri.util;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import colibri.lib.ComparableSet;
import colibri.lib.ComparableTreeSet;
import colibri.lib.Concept;
import colibri.lib.Edge;



/**
 * Used for lattice equality testing.
 *
 */
public class EdgeLattice {
	
	private static class Node {
		
		public ComparableSet objects = new ComparableTreeSet();
		public ComparableSet attributes = new ComparableTreeSet();
		public TreeSet<Integer> subconcepts = new TreeSet<Integer>();
		public TreeSet<Integer> superconcepts = new TreeSet<Integer>();
		public TreeSet<Integer> upperNeighbors;
		public TreeSet<Integer> lowerNeighbors;
		public Concept concept;
	}
	
	
	private Vector<Node> vector = new Vector<Node>();
	private Set<Edge> edges = new HashSet<Edge>();
	
	
	public void addConcept(Integer concept) {
		Node node = new Node();
		vector.add(concept.intValue(), node);
	}
	
	
	public void addObject(Integer concept, String object) {
		vector.get(concept.intValue()).objects.add(object);
	}
	
	
	public void addAttribute(Integer concept, String attribute) {
		vector.get(concept.intValue()).attributes.add(attribute);
	}
	
	
	public void addSubconcept(Integer concept, Integer subconcept) {
		if (!concept.equals(subconcept))
			vector.get(concept.intValue()).subconcepts.add(subconcept);
	}
	
	
	public void addSuperconcept(Integer concept, Integer superconcept) {
		if (!concept.equals(superconcept))
			vector.get(concept.intValue()).superconcepts.add(superconcept);
	}
	
	
	public void addEdge(Edge edge) {
		if (!edges.add(edge))
			System.out.println("WARNING: the edge " + edge + " is already contained in the lattice");	//TODO can be removed after test is complete
	}
	
	
	public void computeEdges() {
		for (int i = 0; i < vector.size(); i++) {
			if (vector.get(i).concept == null) {
				vector.get(i).concept = new Concept(vector.get(i).objects, vector.get(i).attributes);
			}
		}
		for (int i = 0; i < vector.size(); i++) {
			
			vector.get(i).lowerNeighbors = new TreeSet<Integer>(vector.get(i).subconcepts);
			Iterator<Integer> it1 = vector.get(i).subconcepts.iterator();
			while (it1.hasNext()) {
				vector.get(i).lowerNeighbors.removeAll(vector.get(it1.next()).subconcepts);
			}
			
			Iterator<Integer> it2 = vector.get(i).lowerNeighbors.iterator();
			while (it2.hasNext()) {
				edges.add(new Edge(vector.get(i).concept, vector.get(it2.next().intValue()).concept));
			}
		}
	}
	
	
	public Set<Edge> getEdges () {
		return edges;
	}
}
