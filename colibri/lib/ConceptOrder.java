package colibri.lib;

public enum ConceptOrder {
	/**
	 * Compare by the order of the object set.
	 */
	OBJ_STD,
	
	/**
	 * Compare by the size of the object set.
	 * If the sizes of two object sets are equal,
	 * compare by the order of the object set.
	 */
	OBJ_SIZEFIRST,
	
	/**
	 * Compare by the order of the attribute set.
	 */
	ATTR_STD,
	
	
	/**
	 * Compare by the size of the attribute set.
	 * If the sizes of two attribute sets are equal,
	 * compare by the order of the attribute set.
	 */
	ATTR_SIZEFIRST;
}
