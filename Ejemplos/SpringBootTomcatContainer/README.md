
En el contenedor de aplicaciones se pueden crear servlets, listeners y filtros customizados.

Para crear un Servlet customizado es necesario indicar la anotación *@ServletComponentScan* en la aplicación SB del siguiente modo:

```java
@SpringBootApplication
@EnableAutoConfiguration
@ServletComponentScan
public class SpringBootTomcatContainerApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTomcatContainerApplication.class, args);
	}
	
}
```

Una vez agregada la anotación se podrá agregar un clase que defina el nuevo servlet a agregar como por ejemplo:

```java
@WebServlet(value = "/servlet", name = "servlet")
public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            response
              .getOutputStream()
              .write("Hola Mundo!".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

Los recursos estáticos en una aplicación SpringBoot se pueden almacenar en las carpetas por defecto configuradas para ello:

- /src/main/resources/static/
- /src/main/resources/public/
- /src/main/resources/META-INF/resources/

Se ha dejado en cada una de ellas un recurso de prueba accesible respectivamente en las siguientes urls:

- http://localhost:8080/image_static.jpeg
- http://localhost:8080/image_public.jpeg
- http://localhost:8080/image_metainf.jpeg


