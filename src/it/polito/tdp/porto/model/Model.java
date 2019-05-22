package it.polito.tdp.porto.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.event.ConnectedComponentTraversalEvent;
import org.jgrapht.event.EdgeTraversalEvent;
import org.jgrapht.event.TraversalListener;
import org.jgrapht.event.VertexTraversalEvent;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	
	private Graph<Author, DefaultEdge> grafo;
	private List<Author> autori;
	private List<Paper> articoli;
	private Map<Integer, Paper> paperIdMap;
	private Map<Integer, Author> autoriIdMap;
	private List<Adiacenza> adiacenze;
	
	private class EdgeTraversedGraphListener implements TraversalListener<Author, DefaultEdge>{

		@Override
		public void connectedComponentFinished(ConnectedComponentTraversalEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void connectedComponentStarted(ConnectedComponentTraversalEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void edgeTraversed(EdgeTraversalEvent<DefaultEdge> ev) {
		}

		@Override
		public void vertexFinished(VertexTraversalEvent<Author> arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void vertexTraversed(VertexTraversalEvent<Author> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

	public List<Author> getAllAuthors() {
		PortoDAO dao = new PortoDAO();
		return dao.getAllAuthors();
	}
	
	public void creaGrafo(){
		this.grafo = new SimpleGraph<Author, DefaultEdge>(DefaultEdge.class);
		
		PortoDAO dao = new PortoDAO();
		autori = dao.getAllAuthors();
		articoli = dao.getAllArticoli();
		
		this.paperIdMap = new HashMap<>();
		for(Paper p : articoli)
			paperIdMap.put(p.getEprintid(), p);
		
		this.autoriIdMap = new HashMap<>();
		for(Author a : autori)
			autoriIdMap.put(a.getId(), a);
		
		adiacenze = dao.getAdiacenze(autoriIdMap, paperIdMap);
		
		Graphs.addAllVertices(grafo, autori);
		
		for(Adiacenza a : adiacenze) {
			grafo.addEdge(a.getA1(), a.getA2());
		}
		
//		System.out.println("Il grafo ha " + grafo.vertexSet().size() + " vertici e " + grafo.edgeSet().size() + " archi.\n");
		
	}

	public List<Author> getNeighbors(Author a) {
		return Graphs.neighborListOf(grafo, a);
	}
	
	public List<Author> camminoMinimo(Author source, Author target){
		DijkstraShortestPath<Author, DefaultEdge> dijkstra = new DijkstraShortestPath<>(this.grafo);
		GraphPath<Author, DefaultEdge> path = dijkstra.getPath(source, target);
		return path.getVertexList();
	}

}
