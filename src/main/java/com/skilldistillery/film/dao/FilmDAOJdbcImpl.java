package com.skilldistillery.film.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;


@Component
public class FilmDAOJdbcImpl implements FilmDAO {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static String user = "student";
	private static String pass = "student";

	public FilmDAOJdbcImpl() {
		
	}

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {

		if (filmId < 1)
			return null;
		try (Connection conn = DriverManager.getConnection(URL, user, pass)) {
			String sql = "SELECT * FROM film join language on film.language_id = language.id where film.id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, filmId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				return new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),
						rs.getInt(6), rs.getDouble(7), rs.getInt(8), rs.getDouble(9), rs.getString(10),
						rs.getString(11), findActorsByFilmId(rs.getInt(1)),  rs.getString("language.name"), getCategory(rs.getInt(1)));
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		return null;
	}

	@Override
	public Actor findActorById(int actorId) {

		if (actorId < 1)
			return null;

		try (Connection conn = DriverManager.getConnection(URL, user, pass)) {
			String sql = "SELECT * FROM actor where actor.id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, actorId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				return new Actor(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		} catch (SQLException e) {
			System.out.println(e);
		}

		return null;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {

		if (filmId < 1)
			return null;

		try (Connection conn = DriverManager.getConnection(URL, user, pass)) {
			String sql = "select * from film join film_actor on film.id = film_actor.film_id join actor on film_actor.actor_id = actor.id where film.id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, filmId);

			ResultSet rs = ps.executeQuery();

			List<Actor> cast = new LinkedList<>();
			while (rs.next()) {

				cast.add(new Actor(rs.getInt("actor.id"), rs.getString("actor.first_name"),
						rs.getString("actor.last_name")));
			}

			return cast;
		} catch (SQLException e) {
			System.out.println(e);
		}

		return null;
	}

	public List<Film> findFilmByKeyword(String keyword) {

		List<Film> filmsMatchedKeyword = new LinkedList<>();

		try (Connection conn = DriverManager.getConnection(URL, user, pass)) {
			String sql = "select * from film join language on film.language_id = language.id where film.title like ? or film.description like ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ps.setString(2, "%" + keyword + "%");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				filmsMatchedKeyword.add(new Film(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getInt(5), rs.getInt(6), rs.getDouble(7), rs.getInt(8), rs.getDouble(9),
						rs.getString(10), rs.getString(11), findActorsByFilmId(rs.getInt(1)),

						rs.getString("language.name"), getCategory(rs.getInt("id"))));

			}

			return filmsMatchedKeyword;

		} catch (SQLException e) {
			

		}

		return null;
	}

	public Actor createActor(Actor actor) {

		try (Connection conn = DriverManager.getConnection(URL, user, pass)) {

			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "INSERT INTO actor (first_name, last_name) " + " VALUES (?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, actor.getFirstName());
			stmt.setString(2, actor.getLastName());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newActorId = keys.getInt(1);
					actor.setId(newActorId);
					if (actor.getFilms() != null && actor.getFilms().size() > 0) {
						sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
						stmt = conn.prepareStatement(sql);
						for (Film film : actor.getFilms()) {
							stmt.setInt(1, film.getId());
							stmt.setInt(2, newActorId);
							updateCount = stmt.executeUpdate();
						}
					}
				}
			} else {
				actor = null;
			}
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			
			sqle.printStackTrace();
			throw new RuntimeException("Error inserting actor " + actor);
		}
		return actor;
	}

	public boolean saveActor(Actor actor) {

		try (Connection conn = DriverManager.getConnection(URL, user, pass)) {

			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "UPDATE actor SET first_name=?, last_name=? " + " WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, actor.getFirstName());
			stmt.setString(2, actor.getLastName());
			stmt.setInt(3, actor.getId());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				// Replace actor's film list
				sql = "DELETE FROM film_actor WHERE actor_id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, actor.getId());
				updateCount = stmt.executeUpdate();
				sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
				stmt = conn.prepareStatement(sql);
				for (Film film : actor.getFilms()) {
					stmt.setInt(1, film.getId());
					stmt.setInt(2, actor.getId());
					updateCount = stmt.executeUpdate();
				}
				conn.commit(); // COMMIT TRANSACTION
			}
		} catch (SQLException sqle) {
			
			sqle.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deleteActor(Actor actor) {

		try (Connection conn = DriverManager.getConnection(URL, user, pass)) {

			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "DELETE FROM film_actor WHERE actor_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actor.getId());
			int updateCount = stmt.executeUpdate();
			sql = "DELETE FROM actor WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actor.getId());
			updateCount = stmt.executeUpdate();
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			
			sqle.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Film createFilm(Film film) {

		try (Connection conn = DriverManager.getConnection(URL, user, pass)) {

			conn.setAutoCommit(false); // START TRANSACTION
			
			String sql = "insert into film(title, language_id) values (?, 1)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, film.getTitle());
			
			int updateCount = ps.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = ps.getGeneratedKeys();
				if (keys.next()) {
					int newActorId = keys.getInt(1);
					film.setId(newActorId);
					if (film.getCast() != null && film.getCast().size() > 0) {
						sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
						ps = conn.prepareStatement(sql);
						
						ps.setInt(1, film.getId());
						
						for (Actor actor : film.getCast()) {

							ps.setInt(2, actor.getId());
							updateCount = ps.executeUpdate();
						}
					}
				}
			} else {
				film = null;
			}
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			
			sqle.printStackTrace();
			throw new RuntimeException("Error inserting film " + film);
		}
		return film;
	}
	
	public boolean saveFilm(Film film) {

		try (Connection conn = DriverManager.getConnection(URL, user, pass)) {

			conn.setAutoCommit(false); // START TRANSACTION
			
			String sql = "UPDATE film SET title=?, rating=? , description=?, release_year=?, language_id=?, rental_duration=?, rental_rate=?, length=?, replacement_cost=?, special_features=? WHERE id=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getRating());
			stmt.setString(3, film.getDescription());
			stmt.setInt(4, film.getReleaseYear());
			stmt.setInt(5, film.getLanguageId());
			stmt.setInt(6, film.getRentalDuration());
			stmt.setDouble(7, film.getRentalRate());
			stmt.setInt(8, film.getLength());
			stmt.setDouble(9, film.getReplacementCost());
			stmt.setString(10, film.getSpecialFeatures());
			stmt.setInt(11, film.getId());
			
			int updateCount = stmt.executeUpdate();
			
			if (updateCount == 1) {
				
				// Replace actor's film list
				sql = "DELETE FROM film_actor WHERE film_id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, film.getId());
				updateCount = stmt.executeUpdate();
				sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, film.getId());
				
				List<Actor> cast = film.getCast();
				
				if(cast != null && cast.size() != 0) {
					for (Actor actor : film.getCast()) {
						
						stmt.setInt(2, actor.getId());
						updateCount = stmt.executeUpdate();
					}
				}
				
				conn.commit(); // COMMIT TRANSACTION
			}
		} catch (SQLException sqle) {
			
			sqle.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean deleteFilm(Film film) {

		try (Connection conn = DriverManager.getConnection(URL, user, pass)) {

			conn.setAutoCommit(false); // START TRANSACTION
			
			String sql = "DELETE FROM film_actor WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			
			int updateCount = stmt.executeUpdate();
			
			sql = "DELETE FROM film WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			
			updateCount = stmt.executeUpdate();
			
			conn.commit(); // COMMIT TRANSACTION
		
		} catch (SQLException sqle) {
			
			sqle.printStackTrace();
			return false;
		}
		return true;
	}
	
	private List<String> getCategory(int filmId) {
		
		try (Connection conn = DriverManager.getConnection(URL, user, pass)) {
			
			conn.setAutoCommit(false); // START TRANSACTION
			
			String sql = "select category.name from film join film_category on film.id = film_category.film_id join category on film_category.category_id = category.id where film.id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, filmId);

			ResultSet rs = ps.executeQuery();

			List<String> categories = new ArrayList<>();
			while (rs.next()) {
				
				categories.add(rs.getString(1));
			}

			return categories;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
}
