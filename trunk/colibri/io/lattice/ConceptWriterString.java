package colibri.io.lattice;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import colibri.lib.Concept;
import colibri.lib.Lattice;
import colibri.lib.Traversal;


public class ConceptWriterString {
	public void write (Lattice lattice, File file, Traversal traversal) throws IOException {
		FileWriter writer = new FileWriter(file);
		
		try {
			Iterator<Concept> conceptIterator = lattice.conceptIterator(traversal);
			
			while(conceptIterator.hasNext()) {
				Concept next = conceptIterator.next();
				writer.write(next.toString().replace("objects:[", "").replace("], attributes:[", "\t").replace("]", "").replace(",", "") + "\n");
			}
		} finally {
			writer.close();
		}
		
	}

}
