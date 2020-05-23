package concertbase.presentation;

import javax.validation.constraints.NotEmpty;

public class VerySimpleSearchForm {
    @NotEmpty(message = "Search string may not be empty")
    private String searchString;

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
}
