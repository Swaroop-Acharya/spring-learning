
package com.swaroop.acharya.photo.clone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.processing.Generated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("PHOTOS")
public class PhotoEntity {

    @Id
    private Integer id;

    private String contentType;

    @NotEmpty
    private String fileName;


    @JsonIgnore
    private byte[] data;

}
