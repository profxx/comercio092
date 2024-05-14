package br.com.senai.comercio092.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senai.comercio092.entity.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long>{

}
