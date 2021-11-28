package unicundi.edu.co.libreria.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cerrarSesion")
@CrossOrigin
public class CerrarSesionController {

	@Autowired
	private ConsumerTokenServices service;
	
	
	@GetMapping("/anular/{tokenId:.*}")
	public void anularToken(@PathVariable("tokenId") String token) {
		service.revokeToken(token);
	}
}
