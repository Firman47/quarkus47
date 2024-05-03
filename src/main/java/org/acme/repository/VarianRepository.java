package org.acme.repository;

import org.acme.entity.Varian;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VarianRepository implements PanacheRepository<Varian> {
}