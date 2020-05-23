package concertbase.presentation;

import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class ConcertForm {
    @NotEmpty(message = "Name may not be empty")
    private String name;

    @NotEmpty(message = "Date may not be empty")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date not in valid format")
    private String date;

    @NotEmpty(message = "Organizer website may not be empty")
    @URL(message = "This is not a valid URL")
    private String organizerWebsite;

    @NotEmpty(message = "Artist name may not be empty")
    private String artistName;

    @NotEmpty(message = "Start time may not be empty")
    @Pattern(regexp = "\\d{2}:\\d{2}", message = "Start time not in valid format")
    private String startTime;

    @NotEmpty(message = "End time may not be empty")
    @Pattern(regexp = "\\d{2}:\\d{2}", message = "End time not in valid format")
    private String endTime;

    private Long venueId;

    @URL(message = "This is not a valid URL")
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

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
