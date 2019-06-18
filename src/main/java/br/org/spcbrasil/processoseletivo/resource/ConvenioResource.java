package br.org.spcbrasil.processoseletivo.resource;

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

import br.org.spcbrasil.processoseletivo.model.Convenio;
import br.org.spcbrasil.processoseletivo.repository.ConvenioRepository;
import io.swagger.annotations.ApiOperation;

/**
 * @author Sullyvan Puppi
 * spuppi.com - spuppi@gmail.com
 * 18 de jun de 2019
 *
 */
@RestController
@RequestMapping("/convenios")
@CrossOrigin(origins="*")
public class ConvenioResource {

	@Autowired
	private ConvenioRepository convenioRepository;
	
	@ApiOperation(value = "Retorna a lista de convênios cadastrados")
	@GetMapping(produces = "application/json")
	public @ResponseBody Iterable<Convenio> listarConvenios(){
		Iterable<Convenio> listaConvenios = convenioRepository.findAll();
		return listaConvenios;
	}
	
	@ApiOperation(value = "Retorna o convênio pelo ID informado na url")
	@GetMapping(value="/{id}", produces = "application/json")
	public Optional<Convenio> buscarConvenio(@PathVariable(value="id") Long id){
		Optional<Convenio> convenio = convenioRepository.findById(id);
		return convenio;
	}
	
	@ApiOperation(value = "Cadastrar novo convênio")
	@PostMapping
	public Convenio adicionarConvenio(@Valid @RequestBody Convenio convenio) {
		return convenioRepository.save(convenio); 
	}
	
	@ApiOperation(value = "Atualizar cadastro do convênio por Id")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Convenio> atualizarConvenio(@PathVariable Long id, @Valid @RequestBody Convenio convenio){
		Convenio convenioExistente = convenioRepository.getOne(id);
		
		if(convenioExistente == null) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(convenio, convenioExistente, "id");
		
		convenioExistente = convenioRepository.save(convenioExistente);
		
		return ResponseEntity.ok(convenioExistente);
	}
	
	@ApiOperation(value = "Remover convenio por Id")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> removerConvenio(@PathVariable Long id){
		Convenio convenioExistente = convenioRepository.getOne(id);
		
		if(convenioExistente == null) {
			return ResponseEntity.notFound().build();
		}
		
		convenioRepository.delete(convenioExistente);
		
		return ResponseEntity.noContent().build();
	}
}
