package com.lldproject.productcatalogservice.tableInheritenceExamples.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="st_instructor")
@DiscriminatorValue(value = "INSTRUCTOR")
public class Instructor extends User {
    private String company;
}
