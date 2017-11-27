package persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.*;


public class PersistenceUtil implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	protected static EntityManagerFactory emf = 
			Persistence.createEntityManagerFactory("dt354rel"); 	
	
	
	public static void persist(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
	}

	public static void remove(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Object mergedEntity = em.merge(entity);
		em.remove(mergedEntity);
		em.getTransaction().commit();
		em.close();
	}
	
	public static Object merge(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		entity = em.merge(entity);
		em.getTransaction().commit();		
		em.close();
		return entity;
	}

	public static EntityManager createEM() {
		return emf.createEntityManager();
	}
	
	


	public static List<User> findAllStudents(){
		EntityManager em = emf.createEntityManager();
		List<User> s = (List<User>)
				em.createNamedQuery("User.findAll").getResultList();
		em.close();

		return s;

	}



	public static User findUserById(int id){
		EntityManager em = emf.createEntityManager();
		List<User> sm = (List<User>)
				em.createNamedQuery("User.findById").
						setParameter("id", id).getResultList();
		em.close();

		if (sm.size() == 0)
			return null;
		else
			return sm.get(0);
	}

	public static User findUserByEmail(String email){
		EntityManager em = emf.createEntityManager();
		List<User> sm = (List<User>)
				em.createNamedQuery("User.findByEmail").
						setParameter("email", email).getResultList();
		em.close();

		if (sm.size() == 0)
			return null;
		else
			return sm.get(0);
	}

	public static List<Track> findAllTracks(){
		EntityManager em = emf.createEntityManager();
		List<Track> subscribers = (List<Track>)
				em.createNamedQuery("Track.findAllTracks").getResultList();
		em.close();

		return subscribers;

	}

	public static Track findTrackByid(int trackId){

		EntityManager em = emf.createEntityManager();
		List<Track> tracks= (List<Track>)
				em.createNamedQuery("Track.findById").
						setParameter("trackId",trackId ).getResultList();
		em.close();

		if (tracks.size() == 0)
			return null;
		else
			return tracks.get(0);
	}

	public static Track findTrackByName(String name){

		EntityManager em = emf.createEntityManager();
		List<Track> tracks= (List<Track>)
				em.createNamedQuery("Track.findByName").
						setParameter("name",name ).getResultList();
		em.close();

		if (tracks.size() == 0)
			return null;
		else
			return tracks.get(0);
	}


	public static Playlist findPlaylistByName(String name){

		EntityManager em = emf.createEntityManager();
		List<Playlist> playlists= (List<Playlist>)
				em.createNamedQuery("Playlist.findByName").
						setParameter("name",name ).getResultList();
		em.close();

		if (playlists.size() == 0)
			return null;
		else
			return playlists.get(0);
	}

	public static List<Playlist> findAllPlaylists(){
		EntityManager em = emf.createEntityManager();
		List<Playlist> playlists = (List<Playlist>)
				em.createNamedQuery("Playlist.findAllPlaylists").getResultList();
		em.close();

		return playlists;

	}


	public static Playlist findPlaylistById(int playlistId){

		EntityManager em = emf.createEntityManager();
		List<Playlist> playlists= (List<Playlist>)
				em.createNamedQuery("Playlist.findById").
						setParameter("playlistId",playlistId ).getResultList();
		em.close();

		if (playlists.size() == 0)
			return null;
		else
			return playlists.get(0);
	}

	public static Library findLibraryById(String library){

		EntityManager em = emf.createEntityManager();
		List<Library> libraries= (List<Library>)
				em.createNamedQuery("Library.findById").
						setParameter("library", library ).getResultList();
		em.close();

		if (libraries.size() == 0)
			return null;
		else
			return libraries.get(0);
	}

	public static List<Library> findAllLibraries(){
		EntityManager em = emf.createEntityManager();
		List<Library>libraries = (List<Library>)
				em.createNamedQuery("Library.findAll").getResultList();
		em.close();

		return libraries;

	}




}

