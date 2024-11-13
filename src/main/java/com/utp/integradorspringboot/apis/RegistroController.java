package com.utp.integradorspringboot.apis;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.utp.integradorspringboot.models.DatosVerificacion;
import com.utp.integradorspringboot.models.ResultadoAPI;
import com.utp.integradorspringboot.models.RootObject;

@RestController
@RequestMapping("/api-ext/usuarios")
public class RegistroController {

    private final String token = "cGVydWRldnMucHJvZHVjdGlvbi5maXRjb2RlcnMuNjcwODk5YTU5ZmE0MTczZjYxMzIwM2U5";
    private final String API_URL = "https://api.perudevs.com/api/v1/dni/complete?document=";

    private RootObject consultarApiExterna(String documento) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RootObject> response = restTemplate.getForEntity(API_URL + documento + "&key=" + token, RootObject.class);
        return response.getBody();
    }

    @PostMapping("/verificar")
    public ResponseEntity<?> verificarDatos(@RequestBody DatosVerificacion datos) {
        try {
            RootObject resultadoApi = consultarApiExterna(datos.getDocumento());
            if (resultadoApi != null && resultadoApi.isEstado()) {
                ResultadoAPI resultado = resultadoApi.getResultado();
                if (resultado.getFecha_nacimiento().equals(datos.getFecha_nacimiento())
                        && resultado.getCodigo_verificacion().equals(datos.getCodigoVerificacion())) {
                    // Devolver los datos obtenidos de la API
                    return new ResponseEntity<>(resultado, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Datos no coinciden: Fecha de nacimiento o código verificador incorrectos", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Error al consultar la API externa: Estado no válido o resultado nulo", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Registrar el error en los logs del servidor
            return new ResponseEntity<>("Error interno del servidor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}