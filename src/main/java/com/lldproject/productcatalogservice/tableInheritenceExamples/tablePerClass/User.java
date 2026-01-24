package com.lldproject.productcatalogservice.tableInheritenceExamples.tablePerClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import java.util.UUID;

@Entity(name="tpc_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    private UUID id;
    private String name;
}
