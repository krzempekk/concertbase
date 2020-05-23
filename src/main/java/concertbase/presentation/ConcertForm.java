package concertbase.presentation;

import javax.validation.constraints.NotNull;

public class ConcertForm {
    @NotNull
    private String name;

    @NotNull
    private String date;

    @NotNull
    private String organizerWebsite;

    @NotNull
    private String artistName;

    @NotNull
    private String startTime;

    @NotNull
    private String endTime;

    private long venueId;

    private String website;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrganizerWebsite() {
        return organizerWebsite;
    }

    public void setOrganizerWebsite(String organizerWebsite) {
        this.organizerWebsite = organizerWebsite;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public long getVenueId() {
        return venueId;
    }

    public void setVenueId(long venueId) {
        this.venueId = venueId;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
