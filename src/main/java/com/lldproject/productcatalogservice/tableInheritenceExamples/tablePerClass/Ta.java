package com.lldproject.productcatalogservice.tableInheritenceExamples.tablePerClass;

import jakarta.persistence.Entity;

@Entity(name="tpc_ta")
public class Ta extends User{
    private Long numOfHelpRequests;
}
