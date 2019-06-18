package br.org.spcbrasil.processoseletivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.spcbrasil.processoseletivo.model.Convenio;

/**
 * @author Sullyvan Puppi
 * spuppi.com - spuppi@gmail.com
 * 18 de jun de 2019
 *
 */
public interface ConvenioRepository extends JpaRepository<Convenio, Long> {
	
	Convenio findById(long id);

}
