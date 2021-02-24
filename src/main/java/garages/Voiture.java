package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final List<Stationnement> myStationnements = new LinkedList<>();

	public Voiture(String i) {
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}

		immatriculation = i;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * Fait rentrer la voiture au garage 
         * Précondition : la voiture ne doit pas être déjà dans un garage
	 *
	 * @param g le garage où la voiture va stationner
	 * @throws java.lang.Exception Si déjà dans un garage
	 */
	public void entreAuGarage(Garage g) throws Exception {
		// Et si la voiture est déjà dans un garage ?
		if(this.estDansUnGarage()) {
			throw new IllegalStateException("La voiture est actuellement dans un garage.");
		}
		Stationnement s = new Stationnement(this, g);
		myStationnements.add(s);
	}

	/**
	 * Fait sortir la voiture du garage 
         * Précondition : la voiture doit être dans un garage
	 *
	 * @throws java.lang.Exception si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws Exception {
		if(!this.estDansUnGarage()) {
			throw new IllegalStateException("La voiture n'est pas dans un garage.");
		}
		this.myStationnements.get(myStationnements.size()-1).setFin(new Date());
	}

	/**
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public Set<Garage> garagesVisites() {
		
		Set<Garage> garagesVisites = new TreeSet<>();
		
		for(Stationnement s : this.myStationnements) {
			
				garagesVisites.add(s.getGarage());
		}
		return garagesVisites;
	}

	/**
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
	public boolean estDansUnGarage() {
		// Si le date de fin d'un stationnement est "null" alors le stationnement est en cours
		// Si le dernier stationnement est en cours, alors la voiture est dans un garage
		if(this.myStationnements.size() == 0) {
			return false;
		}
		if(this.myStationnements.get(myStationnements.size()-1).getFin() == null) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des dates d'entrée / sortie dans ce
	 * garage
	 * <br>Exemple :
	 * <pre>
	 * Garage Castres:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 *		Stationnement{ entree=28/01/2019, en cours }
	 *  Garage Albi:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 * </pre>
	 *
	 * @param out l'endroit où imprimer (ex: System.out)
	 */
	public void imprimeStationnements(PrintStream out) {
		
		Set<Garage> garagesVisites = this.garagesVisites();
		
		out.println("");
		out.println("Garages visités : ");
		for(Garage g : garagesVisites) {
			out.println(g.getName());
			for(Stationnement s : this.myStationnements) {
				if(s.getGarage() == g) {
					out.println(s.toString());
				}
			}
		}
		
	}

}