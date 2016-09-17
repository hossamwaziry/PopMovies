package com.hossam.popularmovies.DataProcess;


public class Encap {

    private String PosterFilm;
    private String TitleFilm;
    private String OverviewFilm;
    private String VoteFilm;
    private String ReleaseFilm;
    private String PopularityFilm;
    private String API;
    private String Reviews;
    private String ID; //film id
    private String Key;


    public String getPosterFilm() {

        String img = "http://image.tmdb.org/t/p/w500/" + PosterFilm;
        return img;
    }

    public void setPosterFilm(String posterFilm) {
        PosterFilm = posterFilm;
    }

    public String getAPI() {
        if (API == null) {
            API = KeyTags.Api_url_pop;
        }
        return API;
    }

    public void setAPI(String API) {
        this.API = API;
    }

    public String getReviews() {
        return Reviews;
    }

    public void setReviews(String reviews) {
        Reviews = reviews;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitleFilm() {
        return TitleFilm;
    }

    public void setTitleFilm(String titleFilm) {
        TitleFilm = titleFilm;
    }

    public String getOverviewFilm() {
        return OverviewFilm;
    }

    public void setOverviewFilm(String overviewFilm) {
        OverviewFilm = overviewFilm;
    }

    public String getVoteFilm() {
        return VoteFilm;
    }

    public void setVoteFilm(String voteFilm) {
        VoteFilm = voteFilm;
    }

    public String getReleaseFilm() {
        return ReleaseFilm;
    }

    public void setReleaseFilm(String releaseFilm) {
        ReleaseFilm = releaseFilm;
    }

    public String getPopularityFilm() {
        return PopularityFilm;
    }

    public void setPopularityFilm(String popularityFilm) {
        PopularityFilm = popularityFilm;
    }
}
