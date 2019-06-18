package br.org.spcbrasil.processoseletivo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.org.spcbrasil.processoseletivo.model.Paciente;

/**
 * @author Sullyvan Puppi
 * spuppi.com - spuppi@gmail.com
 * 18 de jun de 2019
 *
 */
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	
	Paciente findById(long id);
	
	@Query(nativeQuery = true, value = "SELECT AVG(YEAR(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(nascimento)))) AS media FROM tb_paciente WHERE sexo = :sexo")
	Double findMediaIdadeHomens(String sexo);

	@Query(nativeQuery = true, value = "SELECT * FROM tb_paciente WHERE sexo = :sexo AND altura >= :alturaMin AND altura <= :alturaMax AND peso >= :pesoMin")
	List<Paciente> findBySexo(String sexo, double alturaMin, double alturaMax, double pesoMin);

	@Query(nativeQuery = true, value = "SELECT * FROM tb_paciente WHERE nascimento = (SELECT min(nascimento) FROM tb_paciente)")
	Paciente findByIdadeMax();

	@Query(nativeQuery = true, value = "SELECT *, min(altura) FROM tb_paciente WHERE sexo = :sexo")
	Paciente findBySexoAndAlturaMin(String sexo);
	
	@Query(nativeQuery = true, value = "SELECT * FROM tb_paciente WHERE (SELECT YEAR(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(nascimento))) AS idade ) > 18 AND (SELECT YEAR(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(nascimento))) AS idade ) < 25")
	List<Paciente> findPacientesParametros();
	
	
	
}
