package com.phonebookbackend.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.validation.constraints.NotEmpty;

@Document("info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactPerson {
    @Id
    String id;
    @ApiModelProperty(value = "First name")
    @Field("name")
    String name;

    @ApiModelProperty(value = "Last name")
    @Field("lastName")
    String lastName;

    @ApiModelProperty(value = "Phone Number")
    @NotEmpty(message = "PhoneNumber should not be empty")
    @Field("phoneNumber")
    String phoneNumber;

}
