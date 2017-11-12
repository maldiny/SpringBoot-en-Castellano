# Springboot-en-Castellano

SpringBoot es un framework de Spring que permite crear aplicaciones standalone, sin necesidad de un servidor de aplicaciones externo a ella.

Para iniciar una aplicación SB (SpringBoot a partir de ahora) bastará con ejecutar el comando "java -jar nombre_de_la_aplicacion.jar"

## Índice
  1. introducción
  2. instalación
  3. creación de una aplicación base y ejecución
  4. starters
  5. configuración mediante clases y autoconfiguración (15+16+24.7+44)
  6. beans e inyección de depencias (17)
  7. YAML como fichero de configuración
  8. CORS support
  9. seguridad
  10. configuración del contenedor embebido
  11. acceso a bases de datos SQL y NoSQL
  12. testeo de aplicaciones
  13. websockets
  14. webservices
  15. springboot actuator
  16. despliegue en cloud (cloud foundry, heroku, openshift, amazon y Google Cloud)
  17. springboot maven plugin
  18. FAQs
  19. referencias

## Introducción

[`Spring Boot`] es un framework enfocado a la creación de aplicaciones basadas en Spring sin necesidad de contenedor de aplicaciones. 

A pesar de poder ejecutar aplicaciones SB en Java 6 y Java 7 se recomienda su uso a partir de la versión de Java 8.

Los servidores de aplicaciones enbebidos que permite ejecutar SB por defecto son los siguientes:

- Tomcat 8 empleando Java 7+.
- Tomcat 7 empleando Java 6+.
- Jetty 9.3 empleando Java 8+.
- Jetty 9.2 empleando Java 7+.
- Jetty 8 empleando Java 6+.
- Udertown 1.3 empleando Java 7+.

**[Ir al índice](#Índice)**

## Instalación
SB es compatible con Maven 3.2 o superior. Se puede consultar los pasos para realizar su instalación en la siguiente dirección [http://maven.apache.org/](http://maven.apache.org/).

**SpringBoot CLI**

SB CLi es una herramienta de comandos que se puede utilizar para realizar prototipado con Spring. Adicionalmente permitirá ejecutar scripts de groovy.

Es una herramienta opcional y para seguir los ejemplos y guias descritas a continuación no es necesario instalarlo.

Para validar si se encuentra instalado basta con ejecutar el siguiente comando en una consola:

```cmd
spring -version
Spring Boot v1.5.8.RELEASE
```

Se puede descargar e instalar manualmente a través del siguiente enlace:

[spring-boot-cli-1.5.8.RELEASE-bin.zip](https://repo.spring.io/release/org/springframework/boot/spring-boot-cli/1.5.8.RELEASE/spring-boot-cli-1.5.8.RELEASE-bin.zip)

Para sistemas operativos OSX (MAC) se podrá iniciar su instalación mediante los siguientes comandos:

```cmd
brew tap pivotal/tap
brew install springboot
```

## Creación de una aplicación base y ejecución

Para iniciar la construcción de nuestra primera aplicación SB se plantean varias alternativas. A continuación se enumeran dos de ellas:

- **Online Spring Initializr:** A través del portal https://start.spring.io/ se puede iniciar la construcción de una aplicación introduciendo el nombre del proyecto, sus coordenadas maven y realizando la selección de las dependencias que conocemos de antemano que va a necesitar nuestro proyecto. Estas dependencias únicamente nos las agregará al fichero pom.xml por lo que en caso de no agregarlas en la definición del proyecto podremos agregarlas manualmente con gran facilidad. Para nuestro primero proyecto el formulario quedará del siguiente modo:

<p align="center"><img src="Imagenes//[Maldiny]_Spring_Initializr.png"></p>

Tras completar los datos se podrá descargar el proyecto generado en formato .zip.

<p align="center"><img src="Imagenes//[Maldiny]_Spring_Initializr_zip.png"></p>

- **STS plugin:** A través del propio ide STS que se puede descargar desde el siguiente [enlace](https://spring.io/tools/sts/all) se facilita de la posibilidad al usuario para crear un proyecto base. Para ello únicamente es necesario seguir los siguientes pasos:

<p align="center"><img src="Imagenes//[Maldiny]_STS_Spring_Starter_Project.png"></p>

Al igual que en la versión Online, únicamente es necesario completar los campos que se solicitan tal y como se ve a continuación:

<p align="center"><img src="Imagenes//[Maldiny]_STS_Spring_Starter_Project_data.png"></p>

Como se puede observar en el generador, el plugin integrado en el IDE finalmente realiza una invocación al endpoint online para generar el proyecto y descomprimirlo en el workspace que se este utilizando por lo que es fácilmente integrable en cualquier aplicación Ad hoc.

<p align="center"><img src="Imagenes//[Maldiny]_STS_Spring_Starter_Project_endpoint.png"></p>

Una vez generado, se descargarán todas las dependencias maven necesarias para desplegar un proyecto SB. En caso de ser la primera ocasión en la que se realiza este paso puede demorarse varios minutos.

<p align="center"><img src="Imagenes//[Maldiny]_Hola_Mundo.png"></p>

El proyecto hola_mundo esta preparado para poder ejecutarse. Para ello vamos a iniciar revisando las distintas formas que podemos emplear para lanzar el proyecto desde el STS:

1. Botón secundario sobre el proyecto > Run As > Spring Boot App 
2. En la parte inferior del IDE aparece la pestaña denominada *Boot Dashboard*, en ella aparecerán todos los proyectos de tipo SB que se encuentren importados en nuestro Workspace. Adicionalmente podremos agregar proyectos SB que se encuentren desplegados en la plataforma Cloud Foundry. Para lanzar un proyecto a través del *Boot Dashboard*, simplemente será necesario pulsar botón secundario sobre el proyecto y pulsar en (re)start.

<p align="center"><img src="Imagenes//[Maldiny]_Boot_Dashboard_start.png"></p>

A continuación podemos ver el resultado del arranque de esta primera aplicación:

<p align="center"><img src="Imagenes//[Maldiny]_Start_logs.png"></p>

Al no haber incorporado ningún codigo adicional, la aplicación no realiza operación alguna por lo que se inicia su ejecución y se finaliza tras el arranque. 

Vamos a incluir un bean en la clase *HolamundoApplication.java* que incluya la siguiente definición (más adelante veremos este tema):

```java
@SpringBootApplication
public class HolaMundoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HolaMundoApplication.class, args);
	}
	
	@Bean 
	public String saluda(){
		System.out.println("Hola mundo...");
		return "";
	}
	
}
```

Si arrancamos de nuevo la aplicación veremos cómo aparece en los logs la frase que hemos introducido "Hola mundo...".

<p align="center"><img src="Imagenes//[Maldiny]_Hola_Mundo_logs.png"></p>

El código de este primer ejemplo de hola mundo se encuentra en el siguiente 
[enlace](https://github.com/maldiny/SpringBoot-en-Castellano/tree/master/Ejemplos/SpringBootHolaMundo).

Por otro lado, también es posible ejecutar la aplicación desde fuera del IDE por ejemplo desde línea de comando. Para ello es necesario realizar la compilación y empaquetado del proyecto generando el fichero *.jar. Para realizar esta acción basta con seleccionar con botón secundario el proyecto y pulsar sobre *Run As > Maven install*, se iniciará la descarga de las dependencias necesarias y finalmente generará el fichero *SpringBootHolaMundo-0.0.1-SNAPSHOT.jar* en la carpeta target del proyecto como se ve en la siguiente imagen:

<p align="center"><img src="Imagenes//[Maldiny]_Hola_Mundo_jar.png"></p>

Una vez empaquetado el proyecto y generado los binarios se puede realizar su ejecución accediendo al directorio "target" del proyecto mediante un terminal (cmd) y ejecutando el siguiente comando:

```cmd
java -jar SpringBootHolaMundo-0.0.1-SNAPSHOT.jar
```

Se puede ver a continuación el resultado obtenido:

<p align="center"><img src="Imagenes//[Maldiny]_Hola_Mundo_jar_ejecucion.png"></p>

### Step (StepExecution)
**Step** encapsula cada una de las fases o **pasos de un batch**. De este modo un batch está compuesto por uno o más Steps.
Un Step podrá ser tan simple o complejo o de la tipología que el desarrollador determine oportuno.

Un **StepExecution** representa cada intento de ejecución de un determinado Step. Cada vez que se ejecuta un Step se creará un nuevo StepExecution.

Cada StepExecution está formado por un **ExecutionContext** que contendrá la información que se determine oportuna persistir durante la ejecución del Step como estadísticas o información necesaria del estado del Batch.
Destacar los siguientes campos relevantes:
* **Status:** Indica el estado en el que se encuentra un Step. Sus valores variarán entre STARTED, FAILED o COMPLETED.
* **exitStatus:** Contiene el código de salida del Step.

### ExecutionContext
**ExecutionContext** representa una colección de elementos clave/valor controlada por el framework en la que el desarrollador puede persistir información a nivel de **Step (StepExecution) o Job (JobExecution)**.

```java
executionContext.putLong(getKey(LINES_READ_COUNT), reader.getPosition());
```

```java
long lineCount = executionContext.getLong(getKey(LINES_READ_COUNT);
```

Se puede recuperar el ExecutionContext en cualquier punto de la ejecución de un batch del siguiente modo:

```java
ExecutionContext ecStep = stepExecution.getExecutionContext(); 
ExecutionContext ecJob = jobExecution.getExecutionContext(); 
```

### JobRepository
**JobRepository** es el mecanismo de persistencia para todos los elementos que forman un batch. El JobRepository provee de operaciones para la gestión del JobLauncher, Job y los Steps del batch.

En el momento en el que un Batch se ejecuta por primera vez, se genera un JobExecution a través del JobRepository y durante su ejecución los datos generados en los StepExecutions y JobExecution se persisten a través del JobRepository.

```xml
<job-repository id="jobRepository"/>
```

El JobRepository será el elemento que permitirá persistir la información referente a la ejecución del batch en la base de datos.

### JobLauncher

```java
public interface JobLauncher {
 public JobExecution run(Job job, JobParameters jobParameters)
 			throws JobExecutionAlreadyRunningException, JobRestartException;
}
```

JobLauncher representa una simple interfaz para lanzar ejecuciones de un Job con un conjunto de JobParameters como entrada.

```java
try {
	JobExecution execution = jobLauncher.run(job, new JobParameters());
	System.out.println("Exit Status : " + execution.getStatus());
} catch (Exception e) {
	e.printStackTrace();
}
```

### Item Readers, Item Writters, Item Processors

* **Item Readers:** Representa la fase de lectura de información para un Step. El ItemReader realizará la lectura elemento a elemento. Una vez concluya la lectua de todos los elementos de la fuente de información configurada retornará null.
* **Item Writter:** Representa la fase de salida o escritura de información de un Step, batch o chunk. Generalmente un ItemWriter no tiene conocimiento de la información que recibirá a continuación, únicamente del elemento que se encuentra procesando en cada instante. 
* **Item Processor:** Representa la lógica de negocio implementada para realizar el procesado de la información. Mientras que un ItemReader realiza la lectura de elementos y el ItemWriter se encarga de la persistencia de la información, ItemProcesor provee de elementos de transformación de la información entre la fase de lectura y posterior fase de escritura. En el caso de que un ItemProcesor retorne null indicará que para dicho elemento no es necesario que se realice la fase de escritura.
 
**[Ir al índice](#Índice)**

## Configuración a nivel de job

### Configurar el job

Un Job aparte de ser un contenedor de Steps, dispone de un gran número de parámetros de configuración. La configuración básica de un job será el siguiente:

```xml
<job id="nombreJob">
 <step id="nombreStep" parent="refParent" next="nombreStepSiguiente"/>
 <step id="nombreStepSiguiente" parent="refParent"/>
</job>
```

De forma adicional se podrá especificar el otros parámetros:

```xml
<job id="nombreJob" job-repository="specialRepository" restartable="false" parent="jobPadre"> 
    <listeners> 
        <listener ref="sampleListener"/>
    </listeners>
    <validator ref="paremetersValidator"/>
    ...
```

* **JobRepository:** Permite especificar el jobRepository al que hace referencia cada Job.
* **Restartable:** Permite especificar si un job puede reiniciar su ejecución o no.
* **Listeners/Interceptors:** Permite registrar escuchadores de eventos propios del job (inicio, fin…).
* **Parent:** Permite especificar un job padre del que hereda sus características de configuración.
* **Validator:** Permite validar que los parámetros de entrada de un job cumplen ciertas especificaciones.

### Configurar el JobRepository

Como se comentaba anteriormente, el **JobRepository** permitirá el acceso a la base de datos para almacenar la información relativa a la ejecución del batch y dotará de métodos a la infraestructura para gestionar el JobLauncher, el Job y los Steps.

```xml
<job-repository id="jobRepository" /* Obligatorio - Identificador del objeto que representa el jobRepository */
 data-source="dataSource" 		/* Datasource con acceso a la base de datos */
 transaction-manager="transactionManager" 
 isolation-level-for-create="SERIALIZABLE"  /* Permite almacenar metadatos del batch para su relanzamiento */
 table-prefix="BATCH_"		/* Define el schema de la base de datos */
max-varchar-length="1000"/>
```

A través de la definición del jobRepository podremos especificar que su información no sea persistida en **base de datos y se almacene en memoria**.

```xml
<bean id="jobRepository"  class="org.springframework.....MapJobRepositoryFactoryBean">
      <property name="transactionManager" ref="transactionManager"/>
</bean>
```

### Configurar el JobLauncher

La implementación más básica es la del SimpleJobLauncher ya que únicamente requiere de la referencia al JobRepository para iniciar una ejecución:

```xml
<bean id="jobLauncher" class="org.springframework.batch.core.launch.support.SimpleJobLauncher">
    <property name="jobRepository" ref="jobRepository" />
    <property name="taskExecutor"> <!-- Opcional Asíncrono -->
        <bean class="org.springframework.core.task.SimpleAsyncTaskExecutor" />
    </property>
</bean>
```

* **Ejecución Síncrona:**

Se espera por el retorno de la ejecución.

<p align="center"><img src="https://raw.githubusercontent.com/maldiny/Spring-Batch-en-Castellano/master/Imagenes/%5BMaldiny%5D_Ejecucion_Sincrona.png"></p>

* **Ejecución asíncrona:**

La ejecución del batch es asíncrona.

<p align="center"><img src="https://raw.githubusercontent.com/maldiny/Spring-Batch-en-Castellano/master/Imagenes/%5BMaldiny%5D_Ejecucion_Asincrona.png"></p>

### Ejecución de un Job

Para poder realizar la **ejecución de un Batch** se necesitan al menos dos cosas, el JobLauncher y el propio batch a ejecutar. Existen varios modos de realizar la ejecución de un batch, entre ellos los más empleados se encuentra la ejecución desde la línea de comandos y la ejecución desde el propio contexto de ejecución del batch a ejecutar.

* Ejecución desde la línea de comandos

Opción empleada para aquellos casos en los que se quiera automatizar la ejecución programada de la ejecución de un batch. 

```java
java -cp "target/dependency-jars/*:target/your-project.jar" org.springframework.batch.core.launch.support.CommandLineJobRunner spring/batch/jobs/job-read-files.xml readJob param1=value1
```

* Ejecución desde el contexto de ejecución del batch
 
Opción que permite iniciar la ejecución de un proceso batch mediante una petición **HttpRequest**, para ello se requiere la creación de un Controlador MVC del modo expuesto. 

El batch se ejecutará de manera **asíncrona** sin necesidad de que la petición HTTP espere por el retorno de la ejecución del proceso batch.

```java
@Controller
public class JobLauncherController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @RequestMapping("/jobLauncher.html")
    public void handle() throws Exception{
        jobLauncher.run(job, new JobParameters());
    }
}
```

**[Ir al índice](#Índice)**

## Configuración a nivel de step

### Configurar un step

Un **Step** encapsula una fase independiente de funcionalidad y contiene toda la información necesaria para definir y controlar la ejecución del batch. **Todo batch debe tener al menos un step.**

```xml
<step id="step1">
    <tasklet ref="myTasklet" />
</step>
```

Un step puede estar compuesto de tres elementos: reader, writer y processor:

* **ItemReader:** Elemento responsable de leer datos de una fuente de datos (BBDD, fichero, cola de mensajes, etc…).
* **ItemProcessor:** Elemento responsable tratar la información obtenida por el reader. No es obligatorio su uso.
* **ItemWriter:** Elemento responsable guardar la información leída por el reader o tratada por el processor. Si hay un reader debe haber un writer.

<p align="center"><img src="https://raw.githubusercontent.com/maldiny/Spring-Batch-en-Castellano/master/Imagenes/%5BMaldiny%5D_Configurar_Un_Step.png"></p>

### Chunks

Un **Chunk** se corresponde con la tipología de steps más utilizada en los procesos batch. Consisten en la construcción de un componente especializado en la lectura de elementos **(ItemReader)**, un componente encargado de su procesamiento opcional **(ItemProcessor)** y un componente que se encarga de la persistencia **(ItemWriter)**.

<p align="center"><img src="https://raw.githubusercontent.com/maldiny/Spring-Batch-en-Castellano/master/Imagenes/%5BMaldiny%5D_Chunks.png"></p>

Los distintos elementos que constituyen un chunk podrán sobreescribirse para customizarse en función de las necesidades de negocio. El flujo de un chunk se complementará al introducir políticas de reintento y omisión de registros.

### Tasklets 

Un **tasklet** es un objeto que contiene cualquier lógica que será ejecutada como parte de un trabajo. Se construyen mediante la implementación de la interfaz Tasklet y son la forma más simple para ejecutar código. 

La **interfaz Tasklet** contiene únicamente un método execute que será ejecutado repetidamente mientras el retorno del Tasklet sea distinto a RepeatStatus.FINISHED o bien se lance una excepción.

```xml
<step id="step1">
    <tasklet ref="myTasklet" />
</step>
```

Se puede emplear el TaskletAdapter para customizar el método al que invocar del siguiente modo:

```xml
<bean id="myTasklet" class="org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter">
 <property name="targetObject">
 <bean class="my.class.CustomTaskletAdapterClass"/>
 </property>
 <property name="targetMethod" value="myCustomMethod" />
</bean>
```

### Flujo de Steps

A través del **control de flujo** de ejecución de Steps es posible definir lógicas de negocio en función del estado de salida de otros Steps.

<p align="center"><img src="https://raw.githubusercontent.com/maldiny/Spring-Batch-en-Castellano/master/Imagenes/%5BMaldiny%5D_Steps_Secuenciales.png"></p>

```xml
<job id="job">
    <step id="stepA" next="stepB”/>
    <step id="stepB" next="stepC”/>
    <step id="stepC"/>
</step>
```

<p align="center"><img src="https://raw.githubusercontent.com/maldiny/Spring-Batch-en-Castellano/master/Imagenes/%5BMaldiny%5D_Steps_Condicionales.png"></p>

```xml
<job id="job">
    <step id="stepA">
    	<next on="*" to="stepB"> // * -> 0 o más caracteres
    	<next on="FAILED" to="stepC"> 
    </step>
    <step id="stepB"/>
    <step id="stepC"/>
</step>
```

Otros elementos a tener en cuenta en la definición de flujos entre steps:

* **BatchStatus**: Representa el estado de un Job o Step (COMPLETED, STARTED, FAILED,...)
* **ExitStatus**: Representa el estado de un Step al finalizar su ejecución.
* Tag **end**: Determina la finalización inmediata del Job tras cumplir su condición. ExitStatus y BatchStatus en estado COMPLETED.
* Tag **fail**: Determina la finalización inmediata del Job tras cumplir su condición. ExitStatus y BatchStatus en estado FAILED.
* Tag **stop**: Determina la parada inmediata del Job tras cumplir su condición. BatchStatus en estado STOPPED.

### Scopes (Job/Step)

El Scope permite definir el ámbito en el que se desea crear un bean y en qué momento de la fase de generación de objetos del contenedor de Spring se va a crear permitiendo de este modo definir el orden de generación.

Es necesario incorporar al XML el siguiente namespace:

```xml
<beans ….  xmlns:batch="http://www.springframework.org/schema/batch"...
```

* **Step Scope:** Se requiere esta configuración en el caso de que sea necesario que el Step se inicie antes de que se cree la instancia del Bean (carga de propiedades, enlace con base de datos,...)

```xml
<bean id="step1" scope=”step”>
……
</bean>
```

* **Job Scope:** Sólo permitirá la creación de un determinado bean por job. Permitirá recuperar propiedades del job, jobExecutionContext o jobParameters.

```xml
<bean id="step1" scope=”job”>
……
</bean>
```

**[Ir al índice](#Índice)**

## ItemReaders, itemWriters y itemProcesors

### FlatFileItemReaders

Componente genérico de Spring Batch que permite realizar la **obtención de información en un fichero o stream.**
Este componente genérico permite configurar los siguientes aspectos de su implementación:

```xml
<bean id="cvsFileItemReader" class="org.springframework....file.FlatFileItemReader">
    <property name="resource" value="file:csv/inputs/report.csv" />
    <property name="lineMapper">
        <bean class="org.springframework.batch.item.file.mapping.DefaultLineMapper">
        <property name="lineTokenizer">
            <bean class="org.springframework.batch....transform.DelimitedLineTokenizer">
                <property name="names" value="id,name" />
            </bean>
        </property>
        <property name="fieldSetMapper">
            <bean class="com.everis.uco.spring.batch….MyObjectFieldSetMapper" />
        </property>
        </bean>
    </property>
</bean>
```

* **Resource:** Recurso de entrada (fichero).
* **LineMapper:** Permite realizar la lectura de información. Se podrán realizar numerosas configuraciones sobre este elemento para determinar el número de campos a obtener, líneas que ignorar,...
* **FieldSetMapper:** Componente que permite realizar el mapeo de la información obtenida a objetos generados con una determinada clase.

### FlatFileItemWriters

Componente genérico de Spring Batch que permite realizar la persistencia de información en un fichero o stream. Este componente genérico permite configurar los siguientes aspectos de su implementación:

```xml
<bean id="itemWriter" class="org.springframework.batch.item.file.FlatFileItemWriter">
  <property name="resource" ref="outputResource" />
  <property name="lineAggregator">
  <bean class="org.spr...FormatterLineAggregator">
  <property name="fieldExtractor">
  <bean class="org.spr...BeanWrapperFieldExtractor">
  <property name="names" value="name,credit" />
  </bean>
  </property>
  <property name="format" value="%-9s%-2.0f" />
  </bean>
  </property>
</bean>
```

* **Resource:** Recurso de salida (fichero).
* **LineAggregator:** Permite agregar varios campos en una única fila (String). Es el opuesto al LineTokenizer. Implementará el método aggregate(T item).
* **FieldExtractor:** Componente genérico que permite extraer parámetros de un bean. Su utilización junto a BeanWrapperFieldExtractor permitirá especificar a través de la propiedad names el nombre de los atributos del bean que extraer para poder generar la salida.
 
### XML Item Readers y Writers

Spring Batch facilita utilidades para realizar la lectura y escritura de información en XMLs. A continuación se detalla cómo realizarlo a través del StAX API.

* **StaxEventItemReader** 

```xml
<bean id="itemReader" class="org.springframework.batch.item.xml.StaxEventItemReader">
    <property name="fragmentRootElementName" value="trade" />
    <property name="resource" value="data/iosample/input/input.xml" />
    <property name="unmarshaller" ref="tradeMarshaller" />
</bean>
```

**fragmentRootElementName:** Elemento padre del XML (root-element).
**resource:** Acceso al fichero de entrada que contiene la información en formato XML.
**unmarshaller:** Facilidad OXM que permite realizar el mapeo de los campos definidos en el XML en los campos de objetos Java para su posterior tratamiento y manejo.

```xml
<bean id="tradeMarshaller"
      class="org.springframework.oxm.xstream.XStreamMarshaller">
    <property name="aliases">
        <util:map id="aliases">
            <entry key="trade"  value="org.springframework.batch.sample.domain.Trade" />
            <entry key="price" value="java.math.BigDecimal" />
            <entry key="name" value="java.lang.String" />
        </util:map>
    </property>
</bean>
```

* **StaxEventItemWriter**

```xml
<bean id="itemWriter" class="org.springframework.batch.item.xml.StaxEventItemWriter">
    <property name="resource" ref="outputResource" />
    <property name="marshaller" ref="customerCreditMarshaller" />
    <property name="rootTagName" value="customers" />
    <property name="overwriteOutput" value="true" />
</bean>
```

**rootTagName:** Elemento padre del XML (root-element).
**resource:** Acceso al fichero de entrada que contiene la información en formato XML.
**marshaller:** Facilidad OXM que permite realizar el mapeo de los campos de los objetos Java en los campos del XML.
**overwriteOutput:** Sobreescribe el fichero de salida en caso de existir.

```xml
<bean id="customerCreditMarshaller"
      class="org.springframework.oxm.xstream.XStreamMarshaller">
    <property name="aliases">
        <util:map id="aliases">
            <entry key="custom" value="org.springframework.batch.sample.domain.CustomerCredit" />
            <entry key="credit" value="java.math.BigDecimal" />
            <entry key="name" value="java.lang.String" />
        </util:map>
    </property>
</bean>
```

### Entrada desde varios ficheros

Es un requisito habitual procesar varios ficheros como entrada de un único Step. Si asumimos que todos los ficheros tienen el mismo formato, **MultiResourceItemReader** permite realizar este tipo de entrada tanto para XML como para un FlatFileItemReader.

```xml
<bean id="multiResourceReader" class="org.spr...MultiResourceItemReader">
    <property name="resources" value="classpath:data/input/file-*.txt" />
    <property name="delegate" ref="flatFileItemReader" />
</bean>
```

Este ejemplo se apoyará en el uso de un **FlatFileItemReader**. Esta configuración de entrada para ambos ficheros, maneja tanto el rollback como el reinicio del step de manera controlada.

Se recomienda que cada Job trabaje con su propio **directorio de forma individual** hasta que se complete la ejecución.

### Database ItemReaders y ItemWriters

En la mayoría de sistemas corporativos, los datos se alojan en sistemas de persistencia basados en bases de datos. A continuación se detallan los principales componentes disponibles:

```xml
<bean id="itemReader" class="org.spr...JdbcCursorItemReader">
   <property name="dataSource" ref="dataSource" />
   <property name="sql" value="select ID, NAME, CREDIT from CUSTOMER" />
   <property name="rowMapper"> <bean class="org.springframework.batch.sample.domain.CustomerCreditRowMapper" /> </property>
</bean>
```

* **JdbcCursorItemReader:** Lee de un cursor de base de datos a través de JDBC.
* **HibernateCursorItemReader:** Lee de un cursor de base de datos a través de HQL.
* **StoredProcedureItemReader:** Lee de un cursor de base de datos a través de un proceso almacenado (ej: PL/SQL).
* **JdbcPagingItemReader:** A partir de una sentencia SQL, pagina los resultados que pueden leerse sin verse afectada la memoria del proceso ante grandes volúmenes de datos.
* **JpaPagingItemReader:** A partir de una sentencia JSQL, pagina los resultados que pueden leerse sin verse afectada la memoria del proceso ante grandes volúmenes de datos.
* **IbatisPagingItemReader:** A partir de una sentencia iBATIS, pagina los resultados que pueden leerse sin verse afectada la memoria del proceso ante grandes volúmenes de datos.
* **HibernatePagingItemReader:** Lee a partir de una sentencia HQL paginada.
* **MongoItemReader:** A partir de un operador de mongo y una sentencia JSON válida de MongoDB, realiza la lectura de elementos de la base de datos.

### Database ItemReaders y ItemWriters

Los **ItemWriters** definirán el modo en el que la información tras ser procesada será almacenada en los sistemas de persistencia.

```xml
<bean id="databaseItemWriter" class="org.springframework.batch.item.database.JdbcBatchItemWriter">
	<property name="dataSource" ref="dataSource" />
	<property name="sql">
    		<value>
			<![CDATA[ insert into EXAM_RESULT(STUDENT_NAME, DOB, PERCENTAGE) values (?, ?, ?)]]>
    		</value>
	</property>
	<property name="ItemPreparedStatementSetter">
    		<bean class="com.everis.....CustomItemSetter" />
	</property>
</bean>
```

* **HibernateItemWriter:** Utiliza una sesión de hibernate para manejar la transaccionalidad de la persistencia de la información.
* **JdbcBatchItemWriter:** Utiliza sentencias de tipología PreparedStatement y puede utilizar steps rudimentarios para localizar fallos en la persistencia de la información.
* **JpaItemWriter:** Utiliza un EntityManager de JPA para poder manejar la transaccionalidad en la persistencia de la información. 
* **MongoItemWriter:** A partir de un objeto de tipo MongoOperations, permite realizar la persistencia de la información en bases de datos MongoDB. La escritura de la información se retrasa hasta el último momento antes de realizar la validación de la persistencia de la información.

### ItemReaders y ItemWriters customizados

Una vez vistos los distintos componentes genéricos facilitados por Spring Batch, hay muchos escenarios que pueden no estar cubiertos por estas implementaciones. En estos casos podremos crear nuestras implementaciones customizadas de **ItemReader** e **ItemWriter**.

* **Custom ItemReader**

Bean que realizará la obtención de la información cuya implementación será definida por el usuario.
Implementa la interfaz ItemReader<T> cuyo método read que obtendrá los objetos a tratar durante la fase de escritura.

```java
public class CustomReader implements ItemReader<MyObject> {

	@Override
	public MyObject read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub		
return null;
}
}
```

* **Custom ItemWriter**

Bean que realizará la persistencia de la información cuya implementación será definida por el usuario.
Implementa la interfaz ItemWriter<T> cuyo método write recibirá el listado de objetos a tratar durante la fase de escritura.

```java
public class CustomWriter implements ItemWriter<MyObject> {

	@Override
	public void write(List<? extends MyObject> items) throws Exception {
// TODO Auto-generated method stub
	}
}
```

**[Ir al índice](#Índice)**

## Escalado y paralelización

### Steps multihilo

Para configurar la ejecución de un mismo step por varios hilos la forma más simple es la creación de un pool de hilos mediante la configuración de un TaskExecutor. Su definición se realizará del siguiente modo:

```xml
<step id="step1">
    <tasklet 
         task-executor="taskExecutor"
  throttle-limit="20">
….
….
    </tasklet>
</step>
```

La implementación del objeto "taskExecutor" podrá cualquier implementación de la interfaz TaskExecutor, por ejemplo, **SimpleAsyncTaskExecutor**.

En este caso, cada hilo realizará la ejecución del mismo step de forma independiente, pudiendo realizarse el procesado de elementos de manera no consecutiva. En algunas situaciones será necesario limitar el número de hilos, para ello se especificará el parámetro **throttle-limit**.

**IMPORTANTE: Verificar que los componentes utilizados sean “thread safe” y se puedan utilizar en steps multihilo.**

### Steps paralelos

En la definición de la estructura de determinados batchs es posible identificar cierta lógica u operativa que es necesaria **paralelizar**. Para ello es posible particionar y delegar responsabilidades de la operativa asignándoles **steps individuales** que poder paralelizar en un único proceso. La **configuración** necesaria para poder paralelizar steps sería la siguiente:

```xml
<job id="job1">
 <split id="split1" task-executor="taskExecutor" next="step4">
 <flow>
 <step id="step1" parent="s1" next="step2"/>
 <step id="step2" parent="s2"/>
</flow>
 <flow>
 <step id="step3" parent="s3"/>
 </flow>
 </split>
</job>
<beans:bean id="taskExecutor" class="org.spr...SimpleAsyncTaskExecutor"/>
```

Como se puede ver en el código, es necesario realizar la definición de un elemento "taskExecutor" que hace referencia a la implementación del **TaskExecutor** a emplear para ejecutar cada uno de los flujos de trabajo.

SyncTaskExecutor es la implementación por defecto de TaskExecutor. 

**El job no finalizará su estado como completo hasta que puede agregar el estado de salida de cada uno de los flujos.**

### Remote chunking

La técnica denominada **Remote chunking** consiste en derivar el procesado del step a través de múltiples procesos remotos comunicados entre sí a través de un middleware. El patrón del sistema sería el siguiente:

<p align="center"><img src="https://raw.githubusercontent.com/maldiny/Spring-Batch-en-Castellano/master/Imagenes/%5BMaldiny%5D_Remote_Chunking.png"></p>

El **máster** sustituye el ItemWriter por una versión que realiza el envío de elementos al middleware, mientras que los **esclavos** sustituyen el ItemReader por listeners al middleware para procesar los elementos.

**[Ir al índice](#Índice)**

## Otros

### Spring Batch Admin

**Spring Batch Admin** como su nombre indica es una Consola Web (Spring MVC) de Administración para aplicaciones y sistemas Spring Batch. 

<p align="center"><img src="https://raw.githubusercontent.com/maldiny/Spring-Batch-en-Castellano/master/Imagenes/%5BMaldiny%5D_Spring_Batch_admin.png"></p>

Esta consola **permite** realizar las siguientes operativas:

* Consultar el estado de los jobs.
* Lanzar la ejecución de jobs.
* Ver el estado de una ejecución.
* Ver el detalle de una ejecución y sus pasos.
* Detener una ejecución.

<p align="center"><img src="https://raw.githubusercontent.com/maldiny/Spring-Batch-en-Castellano/master/Imagenes/%5BMaldiny%5D_Spring_Batch_admin_Launcher.png"></p>

<p align="center"><img src="https://raw.githubusercontent.com/maldiny/Spring-Batch-en-Castellano/master/Imagenes/%5BMaldiny%5D_Spring_batch_admin_details.png"></p>

**[Ir al índice](#Índice)**

## Referencias

* [Introducing Spring Batch - Dave Sayer - Spring Source](http://jaoo.dk/dl/qcon-london-2008/slides/DaveSyer_IntroducingSpringBatch.pdf)
* [Spring Batch Reference Documentation 3.0 - Lucas Ward - Pivotal](http://docs.spring.io/spring-batch/reference/pdf/spring-batch-reference.pdf)
* [Spring Batch Admin User Guide - Lucas Ward - Pivotal](http://docs.spring.io/spring-batch-admin/reference/reference.xhtml)
* [Spring Batch - Reference Documentation](http://docs.spring.io/spring-batch/reference/htmlsingle/)
* [Transactions in Spring Batch – Part 1: The Basics](https://blog.codecentric.de/en/2012/03/transactions-in-spring-batch-part-1-the-basics/)
* [Transactions in Spring Batch – Part 2: Restart, cursor based reading and listeners](https://blog.codecentric.de/en/2012/03/transactions-in-spring-batch-part-2-restart-cursor-based-reading-and-listeners/)
* [Transactions in Spring Batch – Part 3: Skip Retry](https://blog.codecentric.de/en/2012/03/transactions-in-spring-batch-part-3-skip-and-retry/)
* [Aprender Spring Batch con ejemplos](http://www.adictosaltrabajo.com/tutoriales/aprende-spring-batch-con-ejemplos/)
* [Ejemplos de Spring Batch](https://github.com/spring-projects/spring-batch/tree/master/spring-batch-samples)

**[Ir al índice](#Índice)**


