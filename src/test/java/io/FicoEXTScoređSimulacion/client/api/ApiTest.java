package io.FicoEXTScoređSimulacion.client.api;

import io.FicoEXTScoređSimulacion.client.ApiClient;
import io.FicoEXTScoređSimulacion.client.ApiException;
import io.FicoEXTScoređSimulacion.client.api.FicoExtendedScoreSandboxApi;
import io.FicoEXTScoređSimulacion.client.model.CatalogoEstados;
import io.FicoEXTScoređSimulacion.client.model.Domicilio;
import io.FicoEXTScoređSimulacion.client.model.Persona;
import io.FicoEXTScoređSimulacion.client.model.Peticion;
import io.FicoEXTScoređSimulacion.client.model.Respuesta;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiTest {
    
	private Logger logger = LoggerFactory.getLogger(ApiTest.class.getName());
	private final FicoExtendedScoreSandboxApi api = new FicoExtendedScoreSandboxApi();
	private ApiClient apiClient = null;

	@Before()
	public void setUp() {
		this.apiClient = api.getApiClient();
		this.apiClient.setBasePath("the_url");
		OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
		apiClient.setHttpClient(okHttpClient);
	}
	
    @Test
    public void getReporteTest() throws ApiException {
    	
    	String xApiKey = "your_api_key";
        
        Peticion peticion = null;
        Persona persona = null;
        Domicilio domicilio = null;
        
        try {
        	
        	peticion = new Peticion();
        	persona = new Persona();
        	domicilio = new Domicilio();
        	
        	peticion.setFolio("1235");
        	
			persona.setApellidoPaterno("OLIVOS");
			persona.setApellidoMaterno("JIMENEZ");
			persona.setApellidoAdicional(null);
			persona.setNombres("HECTOR");
			persona.setFechaNacimiento("1966-05-13");
			persona.setRFC("CUPU800825569");
			persona.setCURP(null);
			persona.setNacionalidad(null);
			persona.setResidencia(null);
			persona.setEstadoCivil(null);
			persona.setSexo(null);
			persona.setNumeroDependientes(null);
			persona.setFechaDefuncion(null);
    		
			domicilio.setDireccion("san joaquin");
			domicilio.setColoniaPoblacion("el arenal");
			domicilio.setDelegacionMunicipio("iztapalapa");
			domicilio.setCiudad("mexico");
			domicilio.setEstado(CatalogoEstados.CDMX);
			domicilio.setCP("12345");
			domicilio.setFechaResidencia(null);
			domicilio.setNumeroTelefono(null);
			domicilio.setTipoDomicilio(null);
			domicilio.setTipoAsentamiento(null);
			domicilio.setFechaRegistroDomicilio(null);
			domicilio.setTipoAltaDomicilio(null);
			domicilio.setIdDomicilio(null);
        	
			persona.setDomicilio(domicilio);
			peticion.setPersona(persona);
			
			Respuesta response = api.getReporte(xApiKey, peticion);

			Assert.assertTrue(response.getFolioConsulta() != null);
			logger.info(response.toString());

		} catch (ApiException e) {
			logger.error(e.getResponseBody());
		}
        
    }
    
}
