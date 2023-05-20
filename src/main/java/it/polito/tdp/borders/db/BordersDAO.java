package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public List<Country> loadAllCountries() {

		String sql = "SELECT CCode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				//System.out.format("%d %s %s\n", rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
				Country c = new Country( rs.getString("StateAbb"), rs.getInt("CCode"), rs.getString("StateNme"));
				result.add(c);
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	/**
	 * metodo che prende dalla tabella contiguity (del DB countries) tutti i confini (validi entro un anno specificato)
	 * @param anno è l'anno inserito dall'utente (entro il quale i cambiamenti di confini storici sono validi)
	 * @param countriesIdMap è la idMap che associa ogni Country al suo id
	 * @return una lista contenente tutti i confini
	 */
	public List<Border> getCountryPairs(int anno, Map<Integer, Country>countriesIdMap) {
		
		String sql = "SELECT state1no, state2no "
				+ "FROM contiguity c "
				+ "WHERE c.conttype = 1 AND c.year <= ? ";
		List<Border>allBorders = new ArrayList<Border>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int nStato1 = rs.getInt("state1no");
				int nStato2 = rs.getInt("state2no");
				Country c1 = countriesIdMap.get(nStato1);
				Country c2 = countriesIdMap.get(nStato2);
				Border b = new Border(c1, c2);
				allBorders.add(b);
			
			}
			
			conn.close();
			return allBorders;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	
	/*public void trovaConfinantiDAO(Country c) {
		String sql = "";
		List<Border>allBorders = new ArrayList<Border>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			//st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
			
			}
			
			conn.close();
			return ;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}*/
	}
