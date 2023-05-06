package br.com.joaquina.m3s01projetorevisao.repositories;

import br.com.joaquina.m3s01projetorevisao.entities.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandRepository extends JpaRepository<Demand, Long> {
}
