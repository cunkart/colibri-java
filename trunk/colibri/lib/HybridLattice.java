package colibri.lib;

/**
 * A <code>Lattice</code> that uses a <code>HybridRelation</code>. The relation
 * passed to it in the constructor will first be translated into
 * a <code>HybridRelation</code>. Thus it is possible to benefit
 * from faster performance of bitsets.
 *
 */
public class HybridLattice extends LatticeImpl {
	/**
	 * Constructs a lattice from the passed <code>relation</code>.
	 * Note that the lattice object constructed by this constructor will not
	 * keep any reference to the <code>Relation</code> object specified.
	 * Therefore, changes to that <code>relation</code> will never affect
	 * the computations performed by this <code>Lattice</code> object.
	 * Since changes to the original <code>relation</code> will not affect
	 * the computations performed by this <code>Lattice</code> object,
	 * write protection on that <code>relation</code> will not be activated
	 * by this constructor.
	 * @param relation the relation from which the lattice shall be
	 * constructed.
	 */	
	public HybridLattice (Relation relation) {
		this.relation = new HybridRelation(relation);
		allObjects = this.relation.getAllObjects();
		allAttributes = this.relation.getAllAttributes();
		
		top = conceptFromAttributes(new HybridSet((((HybridRelation)(this.relation))).getAttributeTranslator()));			
		bottom = conceptFromObjects(new HybridSet((((HybridRelation)(this.relation))).getObjectTranslator()));
	}
	
}
