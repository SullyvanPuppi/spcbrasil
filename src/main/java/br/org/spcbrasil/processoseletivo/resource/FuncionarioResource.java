package br.org.spcbrasil.processoseletivo.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.org.spcbrasil.processoseletivo.model.Funcionario;
import br.org.spcbrasil.processoseletivo.repository.FuncionarioRepository;
import io.swagger.annotations.ApiOperation;

/**
 * @author Sullyvan Puppi
 * spuppi.com - spuppi@gmail.com
 * 18 de jun de 2019
 *
 */
@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins="*")
public class FuncionarioResource {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@ApiOperation(value = "Retorna a lista de funcionários cadastrados")
	@GetMapping(produces = "application/json")
	public @ResponseBody List<Funcionario> listarFuncionarios(){
		List<Funcionario> listaFuncionarios = funcionarioRepository.findAll();
		return listaFuncionarios;
	}

	@ApiOperation(value = "Retorna o funcionário pelo ID informado na url")
	@GetMapping(value="/{id}", produces = "application/json")
	public Optional<Funcionario> buscarFuncionario(@PathVariable(value="id") Long id){
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		return funcionario;
	}

	@ApiOperation(value = "Cadastrar novo funcionário")
	@PostMapping
	public Funcionario adicionarFuncionario(@Valid @RequestBody Funcionario funcionario) {
		return funcionarioRepository.save(funcionario); 
	}

	@ApiOperation(value = "Atualizar cadastro do funcionário por Id")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long id, @Valid @RequestBody Funcionario funcionario){
		Funcionario funcionarioExistente = funcionarioRepository.getOne(id);

		if(funcionarioExistente == null) {
			return ResponseEntity.notFound().build();
		}

		BeanUtils.copyProperties(funcionario, funcionarioExistente, "id");

		funcionarioExistente = funcionarioRepository.save(funcionarioExistente);

		return ResponseEntity.ok(funcionarioExistente);
	}

	@ApiOperation(value = "Remover funcionário por Id")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> removerFuncionario(@PathVariable Long id){
		Funcionario funcionarioExistente = funcionarioRepository.getOne(id);

		if(funcionarioExistente == null) {
			return ResponseEntity.notFound().build();
		}

		funcionarioRepository.delete(funcionarioExistente);

		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Adiciona aumento salarial de 20% para funcionários com mais de 10 anos trabalhados")
	@PutMapping(value="/aumento/parametros")
	public ResponseEntity<Void> aumentoSalarial(){
		
		double aumento = 0.2;
		
		funcionarioRepository.aumentoSalarial(aumento);
		
		return ResponseEntity.noContent().build();
	}
}
