package co.diplomado.iudigital.helpmeiud.app.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import co.diplomado.iudigital.helpmeiud.app.models.Usuario;
import co.diplomado.iudigital.helpmeiud.app.services.ifaces.IUsuarioService;

import java.util.HashMap;
import java.util.Map;


@Component
public class TokenMoreInfo implements TokenEnhancer {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		Map<String, Object> info = new HashMap<>();
		info.put("id_usuario", ""+usuario.getId());
		info.put("nombre", usuario.getNombre());
		info.put("image", usuario.getImage());
		info.put("fecha_nacimiento", ""+usuario.getFechaNacimiento());
		((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
