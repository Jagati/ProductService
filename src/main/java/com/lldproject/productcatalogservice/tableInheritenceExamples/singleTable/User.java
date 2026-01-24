package com.lldproject.productcatalogservice.tableInheritenceExamples.singleTable;

import jakarta.persistence.*;

import java.util.UUID;


@Entity(name = "st_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type",discriminatorType = DiscriminatorType.STRING)
public class User {
    @Id
    private UUID id;
    private String name;
}
