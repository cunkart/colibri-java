package colibri.lib;

import experimental.lib.SafeComparableTreeSet;

/**
 * A <code>Lattice</code> that uses the relation as it is passed to it for all
 * computations without any translation.
 *
 */
public class RawLattice extends LatticeImpl {
	/**
	 * Constructs a lattice from the passed <code>relation</code>.
	 * <p>
	 * Note that changing a relation after it has been passed
	 * to this constructor will lead to wrong computations by
	 * the <code>Lattice</code> object constructed by this constructor.
	 * Therefore, this constructor will attempt to disable writing access\
	 * to the relation passed to it, i.e. if the <code>relation</code>
	 * supports write protection, write protection will be activated by
	 * this constructor.
	 * @param relation the relation from which the lattice shall be
	 * constructed.
	 */	
	public RawLattice(Relation relation) {
		this.relation = relation;
		allObjects = relation.getAllObjects();
		allAttributes = relation.getAllAttributes();
		
		relation.disallowChanges();
		
		top = conceptFromAttributes(new SafeComparableTreeSet());			
		bottom = conceptFromObjects(new SafeComparableTreeSet());
	}
}
