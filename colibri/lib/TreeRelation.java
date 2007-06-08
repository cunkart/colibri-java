package colibri.lib;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import experimental.lib.SafeComparableTreeSet;



/**
 * A class that implements the interface <code>Relation</code> using
 * <code>TreeSet</code> objects.
*/
public class TreeRelation extends StdRelation {
	/**
	 * Constructs a new empty relation sorted according to the natural order of its elements
	 */
	public TreeRelation () {
		objectMap = new TreeMap<Comparable, ComparableSet>();
		attributeMap = new TreeMap<Comparable, ComparableSet>();
		
		allObjects = new SafeComparableTreeSet();
		allAttributes = new SafeComparableTreeSet();
	}
	
	
	/**
	 * Returns a set of objects which have all the specified attributes.
	 * <p>
	 * More formally, this method returns a set that contains all objects
	 * <code>o</code> for which it is true that for all attributes
	 * <code>a</code> that are contained in <code>coll</code>
	 * the pair (<code>o</code>, <code>a</code>) is contained in
	 * this relation.
	 * @param set the set of attributes 
	 * @return the set of objects that all have the attributes contained in set
	 */
	public ComparableSet commonObjects(Collection<Comparable> attributes) throws IllegalArgumentException {
		if (attributes == null)
			throw new IllegalArgumentException("commonObjects may not be called with null argument");
		
		Iterator<Comparable> attributeIterator = attributes.iterator();
		if (!attributeIterator.hasNext()) {
			//return a set with all objects
			return getAllObjects();
		}
					
		ComparableSet objectSet = new SafeComparableTreeSet();
		
		try {
			Comparable attr = attributeIterator.next();
			objectSet.addAll(attributeMap.get(attr));
			
			while(attributeIterator.hasNext()) {
				attr = attributeIterator.next();
				objectSet.retainAll(attributeMap.get(attr));
			}
		} catch (NoSuchElementException e) {
			return objectSet;
		} catch (NullPointerException e) {
				throw new IllegalArgumentException();
		}
		
		return objectSet;
	}
	
	
	/**
	 * Returns a set of attributes which are associated with all the
	 * specified objects.
	 * <p>
	 * More formally, this method returns a set that contains all attributes
	 * <code>a</code> for which it is true that for all objects
	 * <code>o</code> that are contained in <code>coll</code>
	 * the pair (<code>o</code>, <code>a</code>) is contained in
	 * this relation.
	 * @param set the set of objects
	 * @return the set of attributes all objects contained in set have
	 */
	public ComparableSet commonAttributes(Collection<Comparable> objects) throws IllegalArgumentException {
		if (objects == null)
			throw new IllegalArgumentException("commonAttributes may not be called with null argument");
		
		Iterator<Comparable> objectIterator = objects.iterator();
		
		if (!objectIterator.hasNext()) {
			//return a set containing all attributes
			return getAllAttributes();
		}
		
		ComparableSet attributeSet = new SafeComparableTreeSet();
		
		try {
			Comparable obj = objectIterator.next();
			attributeSet.addAll(objectMap.get(obj));
			
			while(objectIterator.hasNext()) {
				obj = objectIterator.next();
				attributeSet.retainAll(objectMap.get(obj));
			}
		} catch (NoSuchElementException e) {
			return attributeSet;
		} catch (NullPointerException e) {
			throw new IllegalArgumentException();
		}
		
		return attributeSet;
	}
	
	
	/**
	 * Adds the pair (<code>object</code>, <code>attribute</code>) to this relation.
	 * <p>
	 * If <code>object</code> is not yet contained in the object set of this relation
	 * it will be added to that set. Similarly, if <code>attribute</code> is not yet
	 * contained in the attribute set of this relation it will be added to that
	 * set.
	 * <p>
	 * If <code>object</code> is <code>null</code> no pair will be added to this
	 * relation but <code>attribute</code> will be added to the attribute set of this relation.
	 * Similarly, if <code>attribute</code> is <code>null</code> no pair will be
	 * added to this relation but <code>object</code> will be added to the
	 * attribute set of this relation.
	 * @param object the object. If this is <code>null</code>, only the attribute will be added
	 * @param attribute the attribute. If this is null, only the object will be added to the
	 * 
	 */
	public void add(Comparable object, Comparable attribute) throws ClassCastException {
		
		if (changesDisallowed) {
			throw new UnsupportedOperationException();
		}
		
		//add the object
		if (object != null && !allObjects.contains(object)) {
			ComparableSet set = new SafeComparableTreeSet();
			objectMap.put(object, set);
			allObjects.add(object);
		}
		
		//add the attribute
		if (attribute != null && !allAttributes.contains(attribute)) {
			ComparableSet set = new SafeComparableTreeSet();
			attributeMap.put(attribute, set);
			allAttributes.add(attribute);
		}
		
		//add the pair
		if (object != null && attribute != null) {
			ComparableSet attributeSet = objectMap.get(object);
			attributeSet.add(attribute);			
			ComparableSet objectSet = attributeMap.get(attribute);
			objectSet.add(object);			
		}	
	}
}
