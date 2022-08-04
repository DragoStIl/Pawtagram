package project.entities.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddPhotoDTO {


    @NotNull
    @NotBlank
    private String url;


    private String description;
    private long petId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }
}
