package org.acme.repository;

import org.acme.entity.Warna;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WarnaRepository implements PanacheRepository<Warna> {
}
