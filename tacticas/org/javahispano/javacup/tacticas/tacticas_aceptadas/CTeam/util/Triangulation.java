package org.javahispano.javacup.tacticas.tacticas_aceptadas.CTeam.util;

/*
 * Adaptado del trabajo de Paul Chew
 * 
 * Copyright (c) 2005, 2007 by L. Paul Chew.
 *
 * Permission is hereby granted, without written agreement and without
 * license or royalty fees, to use, copy, modify, and distribute this
 * software and its documentation for any purpose, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.javahispano.javacup.tacticas.tacticas_aceptadas.CTeam.tactica.PosicionCT;

/**
 * A 2D Delaunay Triangulation (DT) with incremental site insertion.
 * 
 * This is not the fastest way to build a DT, but it's a reasonable way to build
 * a DT incrementally and it makes a nice interactive display. There are several
 * O(n log n) methods, but they require that the sites are all known initially.
 * 
 * A Triangulation is a Set of Triangles. A Triangulation is unmodifiable as a
 * Set; the only way to change it is to add sites (via delaunayPlace).
 * 
 * @author Paul Chew
 * 
 *         Created July 2005. Derived from an earlier, messier version.
 * 
 *         Modified November 2007. Rewrote to use AbstractSet as parent class
 *         and to use the Graph class internally. Tried to make the DT algorithm
 *         clearer by explicitly creating a cavity. Added code needed to find a
 *         Voronoi cell.
 */
public class Triangulation extends AbstractSet<Triangle> {

	private final Triangle initial;
	private Triangle mostRecent = null; // Most recently "active" triangle
	private Graph<Triangle> triGraph; // Holds triangles for navigation

	/**
	 * All sites must fall within the initial triangle.
	 * 
	 * @param triangle
	 *            the initial triangle
	 */
	public Triangulation(Triangle triangle) {
		initial = triangle;
		triGraph = new Graph<Triangle>();
		triGraph.add(triangle);
		mostRecent = triangle;
	}

	/* The following two methods are required by AbstractSet */

	@Override
	public Iterator<Triangle> iterator() {
		return triGraph.nodeSet().iterator();
	}

	@Override
	public int size() {
		return triGraph.nodeSet().size();
	}

	@Override
	public String toString() {
		return "Triangulation with " + size() + " triangles";
	}

	/**
	 * True iff triangle is a member of this triangulation. This method isn't
	 * required by AbstractSet, but it improves efficiency.
	 * 
	 * @param triangle
	 *            the object to check for membership
	 */
	public boolean contains(Object triangle) {
		return triGraph.nodeSet().contains(triangle);
	}

	/**
	 * Report neighbor opposite the given vertex of triangle.
	 * 
	 * @param site
	 *            a vertex of triangle
	 * @param triangle
	 *            we want the neighbor of this triangle
	 * @return the neighbor opposite site in triangle; null if none
	 * @throws IllegalArgumentException
	 *             if site is not in this triangle
	 */
	public Triangle neighborOpposite(Pnt site, Triangle triangle) {
		if (!triangle.contains(site))
			throw new IllegalArgumentException("Bad vertex; not in triangle");
		for (Triangle neighbor : triGraph.neighbors(triangle)) {
			if (!neighbor.contains(site))
				return neighbor;
		}
		return null;
	}

	/**
	 * Return the set of triangles adjacent to triangle.
	 * 
	 * @param triangle
	 *            the triangle to check
	 * @return the neighbors of triangle
	 */
	public Set<Triangle> neighbors(Triangle triangle) {
		return triGraph.neighbors(triangle);
	}

	/**
	 * Report triangles surrounding site in order (cw or ccw).
	 * 
	 * @param site
	 *            we want the surrounding triangles for this site
	 * @param triangle
	 *            a "starting" triangle that has site as a vertex
	 * @return all triangles surrounding site in order (cw or ccw)
	 * @throws IllegalArgumentException
	 *             if site is not in triangle
	 */
	public List<Triangle> surroundingTriangles(Pnt site, Triangle triangle) {
		if (!triangle.contains(site))
			throw new IllegalArgumentException("Site not in triangle");
		List<Triangle> list = new ArrayList<Triangle>();
		Triangle start = triangle;
		Pnt guide = triangle.getVertexButNot(site); // Affects cw or ccw
		while (true) {
			if(list.size()>500) {
				throw new RuntimeException("Something happened");
			}
			list.add(triangle);
			Triangle previous = triangle;
			triangle = this.neighborOpposite(guide, triangle); // Next triangle
			guide = previous.getVertexButNot(site, guide); // Update guide
			if (triangle == start)
				break;
		}
		return list;
	}

	/**
	 * Locate the triangle with point inside it or on its boundary.
	 * 
	 * @param point
	 *            the point to locate
	 * @return the triangle that holds point; null if no such triangle
	 */
	public Triangle locate(Pnt point) {
		Triangle triangle = mostRecent;
		if (!this.contains(triangle))
			triangle = null;

		// Try a directed walk (this works fine in 2D, but can fail in 3D)
		Set<Triangle> visited = new HashSet<Triangle>();
		while (triangle != null) {
			if (visited.contains(triangle)) { // This should never happen
				break;
			}
			visited.add(triangle);
			// Corner opposite point
			Pnt corner = point.isOutside(triangle.toArray(new Pnt[0]));
			if (corner == null)
				return triangle;
			triangle = this.neighborOpposite(corner, triangle);
		}
		// No luck; try brute force
		for (Triangle tri : this) {
			if (point.isOutside(tri.toArray(new Pnt[0])) == null)
				return tri;
		}
		// No such triangle
		return null;
	}

	public void agregarPunto(PosicionCT pos) {
		delaunayPlace(new Pnt(pos.getX(), pos.getY()));
	}

	/**
	 * Place a new site into the DT. Nothing happens if the site matches an
	 * existing DT vertex.
	 * 
	 * @param site
	 *            the new Pnt
	 * @throws IllegalArgumentException
	 *             if site does not lie in any triangle
	 */
	public void delaunayPlace(Pnt site) {
		// Uses straightforward scheme rather than best asymptotic time

		// Locate containing triangle
		Triangle triangle = locate(site);
		// Give up if no containing triangle or if site is already in DT
		if (triangle == null)
			throw new IllegalArgumentException("No containing triangle");
		if (triangle.contains(site))
			return;

		// Determine the cavity and update the triangulation
		Set<Triangle> cavity = getCavity(site, triangle);
		mostRecent = update(site, cavity);
	}

	/**
	 * Determine the cavity caused by site.
	 * 
	 * @param site
	 *            the site causing the cavity
	 * @param triangle
	 *            the triangle containing site
	 * @return set of all triangles that have site in their circumcircle
	 */
	private Set<Triangle> getCavity(Pnt site, Triangle triangle) {
		Set<Triangle> encroached = new HashSet<Triangle>();
		Queue<Triangle> toBeChecked = new LinkedList<Triangle>();
		Set<Triangle> marked = new HashSet<Triangle>();
		toBeChecked.add(triangle);
		marked.add(triangle);
		while (!toBeChecked.isEmpty()) {
			triangle = toBeChecked.remove();
			if (site.vsCircumcircle(triangle.toArray(new Pnt[0])) == 1)
				continue; // Site outside triangle => triangle not in cavity
			encroached.add(triangle);
			// Check the neighbors
			for (Triangle neighbor : triGraph.neighbors(triangle)) {
				if (marked.contains(neighbor))
					continue;
				marked.add(neighbor);
				toBeChecked.add(neighbor);
			}
		}
		return encroached;
	}

	/**
	 * Update the triangulation by removing the cavity triangles and then
	 * filling the cavity with new triangles.
	 * 
	 * @param site
	 *            the site that created the cavity
	 * @param cavity
	 *            the triangles with site in their circumcircle
	 * @return one of the new triangles
	 */
	private Triangle update(Pnt site, Set<Triangle> cavity) {
		Set<Set<Pnt>> boundary = new HashSet<Set<Pnt>>();
		Set<Triangle> theTriangles = new HashSet<Triangle>();

		// Find boundary facets and adjacent triangles
		for (Triangle triangle : cavity) {
			theTriangles.addAll(neighbors(triangle));
			for (Pnt vertex : triangle) {
				Set<Pnt> facet = triangle.facetOpposite(vertex);
				if (boundary.contains(facet))
					boundary.remove(facet);
				else
					boundary.add(facet);
			}
		}
		theTriangles.removeAll(cavity); // Adj triangles only

		// Remove the cavity triangles from the triangulation
		for (Triangle triangle : cavity)
			triGraph.remove(triangle);

		// Build each new triangle and add it to the triangulation
		Set<Triangle> newTriangles = new HashSet<Triangle>();
		for (Set<Pnt> vertices : boundary) {
			vertices.add(site);
			Triangle tri = new Triangle(vertices);
			triGraph.add(tri);
			newTriangles.add(tri);
		}

		// Update the graph links for each new triangle
		theTriangles.addAll(newTriangles); // Adj triangle + new triangles
		for (Triangle triangle : newTriangles)
			for (Triangle other : theTriangles)
				if (triangle.isNeighbor(other))
					triGraph.add(triangle, other);

		// Return one of the new triangles
		return newTriangles.iterator().next();
	}

	private Set<PosicionCT> centros;
	private Map<PosicionCT, List<PosicionCT>> voronoi;
	private Map<PosicionCT, List<PosicionCT>> grafo;

	public synchronized Map<PosicionCT, List<PosicionCT>> voronoi() {
		if (voronoi != null) {
			return voronoi;
		}

		// Keep track of sites done; no drawing for initial triangles sites
		voronoi = new LinkedHashMap<PosicionCT, List<PosicionCT>>();
		HashSet<Pnt> done = new HashSet<Pnt>(initial);
		centros = new LinkedHashSet<PosicionCT>();
		grafo = new HashMap<PosicionCT, List<PosicionCT>>();

		for (Triangle triangle : this) {
			for (Pnt site : triangle) {
				if (done.contains(site)) {
					continue;
				}
				done.add(site);
				List<Triangle> list = surroundingTriangles(site, triangle);
				List<PosicionCT> poligono = new ArrayList<PosicionCT>();
				PosicionCT ultima = null;
				for (Triangle tri : list) {
					Pnt centro = tri.getCircumcenter();
					PosicionCT centrosPos = new PosicionCT(centro.coord(0), centro.coord(1));

					// no colocar multiples centros que esten demasiado cerca
					for (PosicionCT otro : centros) {
						if (otro.distancia(centrosPos) < 3) {
							centrosPos = otro;
							break;
						}
					}

					if (centrosPos.isDentroCampoJuego(-3)) {
						centros.add(centrosPos);

						if (!grafo.containsKey(centrosPos)) {
							grafo.put(centrosPos, new LinkedList<PosicionCT>());
						}
						if (ultima != null) {
							grafo.get(centrosPos).add(ultima);
							grafo.get(ultima).add(centrosPos);
						}
						ultima = centrosPos;
					}

					poligono.add(centrosPos);
				}
				PosicionCT sitio = new PosicionCT(site.coord(0), site.coord(1));
				voronoi.put(sitio, poligono);
			}
		}
		return voronoi;
	}

	public Set<PosicionCT> centros() {
		if (centros == null) {
			voronoi();
		}
		return centros;
	}

	public Map<PosicionCT, List<PosicionCT>> grafoCentros() {
		if (grafo == null) {
			voronoi();
		}
		return grafo;
	}

}