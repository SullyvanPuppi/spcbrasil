package br.org.spcbrasil.processoseletivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.spcbrasil.processoseletivo.model.Funcionario;

/**
 * @author Sullyvan Puppi
 * spuppi.com - spuppi@gmail.com
 * 18 de jun de 2019
 *
 */
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	Funcionario findByCodFuncionario(long codFuncionario);
	
	Funcionario findById(long id);
	
}
