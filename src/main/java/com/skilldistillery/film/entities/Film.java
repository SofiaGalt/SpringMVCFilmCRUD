package com.skilldistillery.film.entities;

import java.util.List;
import java.util.Objects;

public class Film {
	
	private int id;
	private String title;
	private String description;
	private String releaseYear;
	
	private int languageId;
	private int rentalDuration;
	private double rentalRate;
	private Integer length;
	private double replacementCost;
	private Rating rating;
	private String specialFeatures;
	private String language;
	
	private List<Actor> cast;
	private List<Category> categories;
	
	public Film() {}
	
	public Film(String title) {
		super();
		this.title = title;
	}

	public Film(int id, String title, String description, String releaseYear, int languageId, int rentalDuration,
			double rentalRate, Integer length, double replacementCost, Rating rating, String specialFeatures, List<Actor> cast, String language, List<Category> categories) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.length = length;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		this.cast = cast;
		this.language = language;
		this.categories = categories;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Film [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", description=");
		builder.append(description);
		builder.append(", releaseYear=");
		builder.append(releaseYear);
		builder.append(", languageId=");
		builder.append(languageId);
		builder.append(", rentalDuration=");
		builder.append(rentalDuration);
		builder.append(", rentalRate=");
		builder.append(rentalRate);
		builder.append(", length=");
		builder.append(length);
		builder.append(", replacementCost=");
		builder.append(replacementCost);
		builder.append(", rating=");
		builder.append(rating);
		builder.append(", specialFeatures=");
		builder.append(specialFeatures);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return id == other.id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public int getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return "" + rating;
	}

	public void setRating(String rating) {
		
		rating.toUpperCase();
		
		switch(rating) {
			case "G":
				this.rating = Rating.G;
				break;
			case "PG":
				this.rating = Rating.PG;
				break;
			case "PG13":
			case "PG-13":
			case "PG 13":
				this.rating = Rating.PG13;
				break;
			case "R":
				this.rating = Rating.R;
				break;
			case "NC17":
			case "NC-17":
			case "NC 17":
				this.rating = Rating.NC17;
				break;
		}
		
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public List<Actor> getCast() {
		return cast;
	}
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCast(List<Actor> cast) {
		this.cast = cast;
	}
	
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	
}
