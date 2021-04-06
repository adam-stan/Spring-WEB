package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Badges {

    @JsonProperty("votes")
    private String votes = "0";

    @JsonProperty("attachmentsByType")
    private AttachmentsByType attachmentsByType;
}
