package colibri.lib;


/**
 * Representation of a formal concept, i.e. a pair of an object set and an
 * attribute set.
 * @author Daniel N. Goetzmann
 * @version 1.0
 */
public class Concept {
	
	private ComparableSet objects = null;
	private ComparableSet attributes = null;
	
	
	/**
	 * Constructs a formal concept.
	 * @param objects the set of objects
	 * @param attributes the set of attributes
	 * @throws IllegalArgumentException if <code>objects</code>
	 * or <code>attributes</code> is <code>null</code>.
	 */
	public Concept (ComparableSet objects, ComparableSet attributes) {
		if (objects == null || attributes == null) {
			throw new IllegalArgumentException();
		}
		
		this.objects = (ComparableSet)objects;
		this.attributes = (ComparableSet)attributes;
		
		this.objects.disallowChanges();
		this.attributes.disallowChanges();
	}
	
	
	/**
	 * Returns the set of objects belonging to this concept.
	 * @return the set of objects belonging to this concept.
	 */
	public ComparableSet getObjects () {
		return objects;
	}
	
	
	/**
	 * returns the set of attributes belonging to this concept
	 * @return the set of attributes belonging to this concept
	 */
	public ComparableSet getAttributes () {
		return attributes;
	}
	
	
	/**
	 * returns a string representation of this concept.
	 */
	public String toString () {
		return ("objects:" + objects.toString() + ", attributes:" + attributes.toString());
	}
	
	
	/**
	 * Compares the specified object with this concept for equality.
	 * Returns <code> true iff the specified object is also a <code>Concept<code>
	 * and the object set and the attribute set of this concept are equal to
	 * the object set and the attribute set of the other concept, respectively.
	 * @return <code>true</code> iff the specified object is equal to this concept.
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Concept)) {
			return false;
		}
		else {
			return (objects.equals(((Concept)object).objects) && attributes.equals(((Concept)object).attributes));
		}
	}
	
	
	/**
	 * Returns the hash code value for this concept.
	 * <p>
	 * The hash code of a <code>Concept<code> is defined as the sum of the
	 * hash codes of its object and attribute sets.
	 * @returns the hash code value for this concept.
	 */
	public int hashCode() {
		return objects.hashCode() + attributes.hashCode();
	}
}
