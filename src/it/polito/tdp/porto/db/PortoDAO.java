package it.polito.tdp.porto.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.porto.model.Adiacenza;
import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Paper;

public class PortoDAO {

	/*
	 * Dato l'id ottengo l'autore.
	 */
	public Author getAutore(int id) {

		final String sql = "SELECT * FROM author where id=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				return autore;
			}

			return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public List<Author> getAllAuthors() {

		final String sql = "SELECT DISTINCT * FROM author";
		List<Author> autori = new ArrayList<Author>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				autori.add(autore);
			}

			Collections.sort(autori);
			return autori;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato l'id ottengo l'articolo.
	 */
	public Paper getArticolo(int eprintid) {

		final String sql = "SELECT * FROM paper where eprintid=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, eprintid);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				return paper;
			}

			return null;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public List<Paper> getAllArticoli() {

		final String sql = "SELECT DISTINCT * FROM paper";
		List<Paper> articoli = new ArrayList<Paper>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Paper articolo = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"), rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				articoli.add(articolo);
			}

			return articoli;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	public List<Adiacenza> getAdiacenze(Map<Integer, Author> autoriIdMap, Map<Integer, Paper> paperIdMap){
		final String sql = "SELECT c1.authorid as a1, c2.authorid as a2, c1.eprintid as paper " + 
				"FROM creator c1, creator c2 " + 
				"WHERE c1.eprintid = c2.eprintid AND c1.authorid>c2.authorid";
		
		List<Adiacenza> adiacenze = new ArrayList<>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Adiacenza adiacenza = new Adiacenza(autoriIdMap.get(rs.getInt("a1")), autoriIdMap.get(rs.getInt("a2")), paperIdMap.get(rs.getInt("paper")));
				adiacenze.add(adiacenza);
			}

			return adiacenze;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
}