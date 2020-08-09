#Problema

Actualmente la aplicacion está lanzando la siguiente excepcion (No se esta detectando un bean del tipo:
com.neosuniversity.videolibrary.business.AdminService

Una vez que corrija el error deberá de utilizar la implementación SmsNotificacion en el servicio
AdminService (Tip Utilize @Qualifier)

```
[2m2020-08-05 16:55:43.187  INFO 57661 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2020-08-05 16:55:43.197  INFO 57661 --- [  restartedMain] c.n.v.VideoLibraryApplication            : Started VideoLibraryApplication in 1.392 seconds (JVM running for 2.25)
Exception in thread "restartedMain" java.lang.reflect.InvocationTargetException
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:566)
	at org.springframework.boot.devtools.restart.RestartLauncher.run(RestartLauncher.java:49)
Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.neosuniversity.videolibrary.business.AdminService' available
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:352)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:343)
	at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1127)
	at com.neosuniversity.videolibrary.VideoLibraryApplication.main(VideoLibraryApplication.java:16)
	... 5 more

```

## Solución

### Es necesario agregar la anotacion Service a AdminService para que lo reconozca como un bean Spring
``` js
import com.neosuniversity.videolibrary.services.Notificacion;

@Service
public class AdminService {
```


## Es necesario colocar el valor SmsNotificacion o bien otro identificador que pueda ser referenciado
``` js
@Service("SmsNotificacion")
public class SmsNotificacion implements Notificacion {
```

### Es necesario agregar la anotacion @Qualifier para indicar a Spring que implementación necesitamos
``` js
    @Autowired
	@Qualifier("SmsNotificacion")
	private Notificacion notificacion;
```
