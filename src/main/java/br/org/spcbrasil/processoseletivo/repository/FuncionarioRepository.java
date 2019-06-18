package br.org.spcbrasil.processoseletivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.org.spcbrasil.processoseletivo.model.Funcionario;

/**
 * @author Sullyvan Puppi
 * spuppi.com - spuppi@gmail.com
 * 18 de jun de 2019
 *
 */
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	Funcionario findById(long id);

	@Query(nativeQuery = true, value = "UPDATE tb_funcionario SET salario = salario + (salario * :aumento) WHERE (SELECT YEAR(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(data_admissao))) AS tempo) >= 10")
	void aumentoSalarial(double aumento);
	
}
