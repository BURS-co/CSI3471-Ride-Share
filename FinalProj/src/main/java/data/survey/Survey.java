package data.survey;

import enums.Ratings;

/**
 * @author Andrew Ammentorp, Joseph Perez Class responsible for a user survey
 */
public class Survey {
	private String name;
	private String target;
	private String comments;
	private Ratings rating;

	/*
	 * enum ratings { noStars, oneStar, twoStars, threeStars, fourStars, fiveStars
	 * };
	 */
	/**
	 * Initialize the survey
	 */
	public Survey(String name, String target, String comments, int rating) {
		this.name = name;
		this.target = target;
		this.comments = comments;

		switch (rating) {
		case (0):
			this.rating = Ratings.noStars;
			break;
		case (1):
			this.rating = Ratings.oneStar;
			break;
		case (2):
			this.rating = Ratings.twoStars;
			break;
		case (3):
			this.rating = Ratings.threeStars;
			break;
		case (4):
			this.rating = Ratings.fourStars;
			break;
		case (5):
			this.rating = Ratings.fiveStars;
			break;
		default:
			this.rating = null;
		}

	}

	public Survey() {

	}

	/**
	 * gets the name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name to the name of user answering survey
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String s) {
		this.target = s;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String s) {
		this.comments = s;
	}

	public int getRating() {
		switch (rating) {
		case noStars:
			return 0;
		case oneStar:
			return 1;
		case twoStars:
			return 2;
		case threeStars:
			return 3;
		case fourStars:
			return 4;
		case fiveStars:
			return 5;
		default:
			return -1;
		}
	}

	public void setRating(int r) {
		switch (r) {
		case (0):
			this.rating = Ratings.noStars;
			break;
		case (1):
			this.rating = Ratings.oneStar;
			break;
		case (2):
			this.rating = Ratings.twoStars;
			break;
		case (3):
			this.rating = Ratings.threeStars;
			break;
		case (4):
			this.rating = Ratings.fourStars;
			break;
		case (5):
			this.rating = Ratings.fiveStars;
			break;
		default:
			this.rating = null;
		}
	}

	@Override
	public String toString() {

		return this.name + ",,," + this.target + ",,," + getRating() + ",,," + this.comments + "\n";
	}

}
