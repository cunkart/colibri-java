package colibri.util;

import java.util.Iterator;
import java.util.Set;

import colibri.lib.Edge;




public class LatticeEqualityTester {
	public static void main (String[] args) throws Exception {
		LatticeReader reader = new LatticeReader();
		EdgeLattice lattice1 = reader.read(args[0]);
		lattice1.computeEdges();
		Set<Edge> set1 = lattice1.getEdges();
		EdgeLattice lattice2 = reader.read(args[1]);
		lattice2.computeEdges();
		Set<Edge> set2 = lattice2.getEdges();
		
		System.out.println("Lattice 1 contains " + set1.size() + " edges");
		System.out.println("Lattice 2 contains " + set2.size() + " edges");
		
		if (set1.equals(set2)) {
			System.out.println("The edges are the same");
		}
		{
			Iterator<Edge> it;
			it = set1.iterator();
			while (it.hasNext()) {
				Edge edge = it.next();
				if (!set2.contains(edge)) {
					System.out.println ("Lattice 1 contains the additional edge " + edge);
				}
			}
			
			it = set2.iterator();
			while (it.hasNext()) {
				Edge edge = it.next();
				if (!set1.contains(edge)) {
					System.out.println ("Lattice 2 contains the additional edge " + edge);
				}
			}
		}
	}
}
