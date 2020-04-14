# fico-extended-score-simulacion-client-java

Simula la API de FICO Extended Score, la cual califica el nivel de cumplimiento de pago de un individuo considerando al grupo de personas con las que comparte domicilio.

## Requisitos

1. Java >= 1.7
2. Maven >= 3.3

## Instalación

Para la instalación de las dependencias se deberá ejecutar el siguiente comando:

```shell
mvn install -Dmaven.test.skip=true
```

> **NOTA:** Este fragmento del comando *-Dmaven.test.skip=true* evitará que se lance la prueba unitaria.


## Guía de inicio

### Paso 1. Agregar el producto a la aplicación

Al iniciar sesión seguir los siguientes pasos:

 1. Dar clic en la sección "**Mis aplicaciones**".
 2. Seleccionar la aplicación.
 3. Ir a la pestaña de "**Editar '@tuApp**' ".
    <p align="center">
      <img src="https://github.com/APIHub-CdC/imagenes-cdc/blob/master/edit_applications.jpg" width="900">
    </p>
 4. Al abrirse la ventana emergente, seleccionar el producto.
 5. Dar clic en el botón "**Guardar App**":
    <p align="center">
      <img src="https://github.com/APIHub-CdC/imagenes-cdc/blob/master/selected_product.jpg" width="400">
    </p>

### Paso 2. Capturar los datos de la petición

Los siguientes datos a modificar se encuentran en ***src/test/java/ApiTest.java***

Es importante contar con el setUp() que se encargará de inicializar la url. Modificar la URL ***('the_url')***, como se muestra en el siguiente fragmento de código:

```java
@Before()
public void setUp() {
	this.apiClient = api.getApiClient();
	this.apiClient.setBasePath("the_url");
	OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .readTimeout(30, TimeUnit.SECONDS)
            .build();
	apiClient.setHttpClient(okHttpClient);
}
```

En el archivo **ApiTest**, que se encuentra en ***src/test/java/io/ApiTest/client/api*** se deberá modificar el siguiente fragmento de código con los datos correspondientes:

```java
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
```

### Paso 3. Ejecutar la prueba unitaria

Teniendo los pasos anteriores ya solo falta ejecutar la prueba unitaria, con el siguiente comando:

```shell
mvn test -Dmaven.install.skip=true
```
