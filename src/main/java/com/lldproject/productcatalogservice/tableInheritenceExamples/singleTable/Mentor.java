package com.lldproject.productcatalogservice.tableInheritenceExamples.singleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="st_mentor")
@DiscriminatorValue(value = "MENTOR")
public class Mentor extends User {
    private Double ratings;
}
