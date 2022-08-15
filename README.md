# ENGLISH - ViWiki
A basic but very visual Wikipedia client. Demo app to show my programming and Android / Kotlin knowledge.

## Issues and discoveries (will update during the development)

### Kotlin and Android
- === compares reference, == compares data. Careful!
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
  - Example: the function `getActivitySafely()`, which first checks the type and then casts it.
- I prefer to leave the SAM constructor, even if redundant, for better legibility.
  `viewModel.featuredArticleResponse.observe(viewLifecycleOwner, Observer {`
- I leave code blocks inline with comments, rather than in small functions, unless I need to reuse it. This makes it easier to follow the execution flow.
- I try to docblock / comment everything, even if redundant. It helps other devs understand the code, makes features easier to find (ctrl + F), and forces me to make sure I understand my code. 
