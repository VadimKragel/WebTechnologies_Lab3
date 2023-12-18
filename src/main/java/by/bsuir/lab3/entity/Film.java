package by.bsuir.lab3.entity;

import javax.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "films")
public class Film implements Serializable {

	@Serial
	private static final long serialVersionUID = -241014190878434680L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "filmName")
	private String filmName;

	@Column(name = "description")
	private String description;

	@Column(name = "posterUrl")
	private String posterUrl;

	@Fetch(FetchMode.JOIN)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "film")
	@OrderBy("date ASC, time ASC")
	private Set<FilmSession> filmSessions;

	@Fetch(FetchMode.JOIN)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "films_genres", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private Set<Genre> genres;

	public Film() {
	}

	public Film(int id, String filmName, String description, String posterUrl, Set<FilmSession> filmSessions,
			Set<Genre> genres) {
		super();
		this.id = id;
		this.filmName = filmName;
		this.description = description;
		this.posterUrl = posterUrl;
		this.filmSessions = filmSessions;
		this.genres = genres;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPosterUrl() {
		return posterUrl;
	}

	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}

	public Set<FilmSession> getFilmSessions() {
		return filmSessions;
	}

	public void setFilmSessions(Set<FilmSession> filmSessions) {
		this.filmSessions = filmSessions;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((filmName == null) ? 0 : filmName.hashCode());
		result = prime * result + id;
		result = prime * result + ((posterUrl == null) ? 0 : posterUrl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (filmName == null) {
			if (other.filmName != null)
				return false;
		} else if (!filmName.equals(other.filmName))
			return false;
		if (id != other.id)
			return false;
		if (posterUrl == null) {
			if (other.posterUrl != null)
				return false;
		} else if (!posterUrl.equals(other.posterUrl))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", filmName=" + filmName + ", description=" + description + ", posterUrl=" + posterUrl
				+ "]";
	}

}
