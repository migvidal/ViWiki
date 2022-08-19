# ENGLISH - ViWiki
A basic but very visual Wikipedia client. Demo app to show my programming and Android / Kotlin knowledge.

![viwiki_poster](https://user-images.githubusercontent.com/68684733/185612652-68e886af-4fe1-4759-ba7c-90c21a15bfda.png)

## Issues and discoveries (will update during the development)

### Kotlin and Android
- === compares reference, == compares data. Careful!!
- Don't forget to add `android:name` to manifest when using a custom Application class
- To implement a regular SearchView, an additional Activity must be used. Thus, this won't be a single activity app.
- Drawable icons can be customized for night theme by adding the night version in a new folder "drawable-night-<dpi>". I had to do it with the 'X' icon in the ArticleFragment's Action Bar. 

### Programming tips
- When the stack trace doesn't go deep enough, add a try-catch(print) block in the error line.

### Workarounds and tricks
- To communicate SearchActivity with ArticleFragment, I first instantiated a new ArticleFragment with the title as an argument.
  Then, I changed it to a simpler way:
    1. SearchActivity starts MainActivity with a new intent (passing the articleTitle as an extra).
    2. MainActivity grabs the intent and saves the articleTitle in a bundle.
    3. Navigate to the ArticleFragment using the safeArg and passing the bundle.
  
- Making a dynamic action bar label: remove the label in the manifest/navGraph, then set it programmatically.

- A binding adapter can serve as a middleman (e.g., to remove HTML tags). (In the end this was not needed).
- The value parameter(s) in a binding adapter must be nullable. Then null-checked inside the method. If they aren't nullable, a binding error comes up.

### Libraries
- You can use "listener" to log errors in Coil.

### Wikipedia API
- Some REST APIs, (like this one) are quite messy and poorly documented.
- Need to add the paremeter `pilicense=any` to stop some images from getting blocked.


## Naming decisions
- Fetch or Get?
  - In the ViewModel: I call a function "fetch<X>" because it just loads the data into a class property.
  - In the Api service: I call it "get<X>" since it both fetches and returns the data.
- To distinguish Wikimedia from Wikipedia, I do this:
  - "Wikipedia": only the 'W' capitalized.
  - "WikiMedia": capitalize both the 'W' and the 'M'.
  That way, I can tell them apart more easily at a glance.

## Style
- I do my best to avoid unsafe casting (X as Y) and asserted calls (!!), or at least to reduce their damage.
  - Example: the function `getMainActivity()`, which first checks the type and then casts it.
- I prefer to leave the SAM constructor, even if redundant, for better legibility.
  `viewModel.featuredArticleResponse.observe(viewLifecycleOwner, Observer {`
- I leave code blocks inline with comments, rather than in small functions, unless I need to reuse them. This makes it easier to follow the execution flow.
- I try to docblock / comment everything, even if redundant. It helps other devs understand the code, makes features easier to find (ctrl + F), and forces me to make sure I understand my code. 


-- ** --

# ESPAÑOL - ViWiki

Un cliente de Wikipedia básico, pero muy visual. App de demostración para mostrar mis conocimientos de programación y Android / Kotlin.

## Problemas y descubrimientos (lo actualizaré durante el desarrollo)

### Kotlin and Android

- === compara la referencia, == compara los datos. ¡Cuidado!
- No olvidar añadir `android:name` al manifiesto al usar una clase Application propia
- Para implementar una SearchView estándar, se debe utilizar una actividad adicional. Por tanto, esta app no será de una sola actividad.
- Los iconos "drawable" se pueden personalizar para el modo noche añadiendo la versión de noche a una nueva carpeta "drawable-night-<dpi>". Tuve que hacerlo para el icono 'X' de la Barra de Acción del ArticleFragment.

### Consejos de programación

- Cuando el __stack trace_ no tiene el alcance suficiente, añadir un bloque try-catch(print) en la línea donde sucede el error.

### "Workarounds" y trucos

- Para comunicar SearchActivity con ArticleFragment, al principio instanciaba un nuevo ArticleFragment con el título como argumento.Luego, lo cambié por una forma más simple:

  1. SearchActivity inicia MainActivity con un intent (pasando el articleTitle como _extra_).
  2. MainActivity recobra el intent y guarda el articleTitle en un _bundle_.
  3. Navgar al ArticleFragment usando el safeArg y pasando el _bundle_.
- Crear un título dinámico para la Barra de Acción: quitar la _label_ del manifiesto/navGraph, y luego establecerla programáticamente.

- Un _binding adapter_ puede servir de interemediario (ej., para quitar etiquetas HTML). (Al final este ejemplo no fue necesario).

- Los parámetros de valor en un _binding adapter_ deben ser _nullable_. Si no lo son, se produce un error de vinculación.


### Bibliotecas

- Se puede usar "listener" para loggear errores en Coil.

### API de Wikipedia

- Algunas APIs REST, (como esta) son complicadas y están poco documentadas.
- Necesidad de agregar el parámetro `pilicense=any` para evitar que algunas imágenes sean bloqueadas.

## Decisiones respecto a nombres

- Fetch o Get?
  - En el ViewModel: llamo a la function "fetch<X>" porque solamente carga los datos en una propiedad de clase.
  - En el servicio de la Api: lo llamo "get<X>", ya que recibe y luego retorna los datos.
-Para distinguir Wikimedia de Wikipedia, hago lo siguiente:
  - "Wikipedia": sólo la 'W' en mayúsculas.
  - "WikiMedia": tanto la 'W' como la 'M' en mayúsculas. Así se pueden distinguir de un vistazo.

## Estilo

- Siempre que puedo, trato de evitar la conversión de tipos insegura (`X as Y`) y las llamadas _asserted_ (`!!`), o al menos de reducir su impacto.
  - Ejemplo: la función `getMainActivity()`, que primero comprueba el tipo y después lo convierte.
- Dejo bloques de código en líea con comentarios, en lugar de en pequeñas funciones, a no ser que necesite reutilizarlos. Esto facilita seguir el flujo de ejecución.
- Trato de documentar con _docblock_ / comentarlo todo, incluso aunque parezca redundante. Creo que ayuda a otros desarrolladores a entender el código, hace que las funciones sean más fáciles de encontrar (con ctrl + F), y me obliga a asegurarme de que entiendo completamente mi código.
