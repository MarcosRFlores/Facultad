package py.una.pol.sd.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.una.pol.sd.model.Persona;
import py.una.pol.sd.service.PersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {

	@Autowired
	PersonaService personaService;

	@GetMapping("/saludo")
	public String index() {
		return "Hola mundo caluroso de Springboot";
	}

	@GetMapping(value = "/listar", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Persona>> getPersonas() {
		List<Persona> r = personaService.getPersonas();

		return new ResponseEntity<>(r, HttpStatus.OK);
	}

	@PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> create(@RequestBody Persona per) {

		if (per != null && per.getCedula() != null) {
			System.out.println("Persona recepcionada " + per.getNombre());

			personaService.crear(per);

			return new ResponseEntity<>("Se recepcionó correctamente la persona: " + per.toString(), HttpStatus.OK);
		} else {

			System.out.println("Datos mal enviados por el cliente");
			return new ResponseEntity<>("Debe enviar el campo cédula. ", HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(value = "/actualizar/{cedula}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updatePersona(@PathVariable("cedula") Integer cedula, @RequestBody Persona per) {
		if (per == null || per.getCedula() == null || !cedula.equals(per.getCedula())) {
			return new ResponseEntity<>("Datos incorrectos o cédula no coincide", HttpStatus.BAD_REQUEST);
		}

		Optional<Persona> existingPersona = personaService.findByCedula(cedula);
		if (existingPersona.isPresent()) {
			personaService.actualizar(per);
			return new ResponseEntity<>("Persona actualizada correctamente: " + per.toString(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No se encontró la persona con la cédula proporcionada", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value = "/eliminar/{cedula}")
	public ResponseEntity<String> deletePersona(@PathVariable("cedula") Integer cedula) {
		Optional<Persona> existingPersona = personaService.findByCedula(cedula);
		if (existingPersona.isPresent()) {
			personaService.eliminar(cedula);
			return new ResponseEntity<>("Persona eliminada correctamente", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No se encontró la persona con la cédula proporcionada", HttpStatus.NOT_FOUND);
		}
	}

}
