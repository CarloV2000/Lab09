package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private Graph<Country, DefaultEdge>grafo;
	private Map<Integer, Country>countryIdMap;
	List<Country>countries;
	
	public Model() {
		
	}

	public void creaGrafo(int anno) {
		//creo grafo
		this.grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
		this.countryIdMap = new HashMap<Integer, Country>();
		
		//aggiungo vertici
		BordersDAO dao = new BordersDAO();
		this.countries = dao.loadAllCountries();
		Graphs.addAllVertices(this.grafo, this.countries);
		
		//creo idMap
		for(Country c : this.countries) {
			this.countryIdMap.put(c.getcCode(), c);
		}
		//aggiungo archi 
		
		for(Border x : dao.getCountryPairs(anno, countryIdMap)) {
			this.grafo.addEdge(x.getPaese1(), x.getPaese2());
		}
			
	}
	
	/**
	 * metodo per stampare: (1)l'elenco di tutti i vertici(Country) con i loro confinanti e (2)il numero di componenti connesse nel grafo
	 * @param anno è l'anno inserito dall'utente (entro il quale i cambiamenti di confini storici sono validi)
	 * @return una stringa
	 */
	public String elencoStatiConNumeroStatiConfinanti(int anno) {
		String s = "";
		for(Country x : this.countries) {
			int contPaesiConfinanti = this.contaConfinantiDatoPaese(x, anno);
			s+= x.getStateName() + " confina con " + contPaesiConfinanti + " Paesi " + "\n\n";
		}
		
		return s;
	}
	/**
	 * metodo che conta i (Country) confinanti dato un (Country)
	 * @param c è il paese di cui calcolare il numero di confinanti
	 * @param anno è l'anno inserito dall'utente (entro il quale i cambiamenti di confini storici sono validi)
	 * @return un int rappresentante il numero di stati confinanti
	 */
	public int contaConfinantiDatoPaese(Country c, int anno) {
		int n = 0;
		BordersDAO dao = new BordersDAO();
		for(Border b : dao.getCountryPairs(anno, countryIdMap)) {
			if(b.getPaese1().equals(c)) {
				n++;
			}
		}
		return n;
	}
}
