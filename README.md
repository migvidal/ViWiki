# ViWiki
A basic but very visual Wikipedia client. Demo app to show my Android knowledge.

## Issues and discoveries (will update during the development)
- To implement a regular SearchView, an additional Activity must be used. Thus, this won't be a single activity app.
- When the stack trace doesn't go deep enough, add a try-catch(print) block in the error line.
- To communicate SearchActivity with ArticleFragment, I instantiate a new ArticleFragment with the title as an argument.
- === compares reference, == compares data. Careful!
- Making a dynamic action bar label: remove the label in the manifest/navgraph, then set it programatically
- A binding adapter can serve as a middleman (e.g., to remove HTML tags).
- The value parameter(s) in a binding adapter must to be nullable. Then null-checked inside the method. If they aren't nullable, a binding error comes up.
- Don't forget to add android:name to manifest when using a custom Application class

## Naming
- Fetch or Get?
  - In the ViewModel: I call a function "fetch<X>" because it just loads the data into a class property.
  - In the Api service: I call it "get<X>" since it both fetches and returns the data.
- To distinguish Wikimedia from Wikipedia, I do this:
  - "Wikipedia": only the 'W' capitalized
  - "WikiMedia": capitalize the 'W' and the 'M'.
  That way, I can tell them apart more easily at a glance

## Style
- I do my best to avoid unsafe casting (X as Y) and asserted calls (!!), or at least to reduce their damage
  - Example: the function `getActivitySafely()`, which first checks the type and then casts it
- I prefer to leave the SAM constructor, even if redundant, for better legibility
`viewModel.featuredArticleResponse.observe(viewLifecycleOwner, Observer {`
- I leave code blocks inline with comments, rather than in small functions, unless i need to reuse it. This makes it easier to follow the execution flow.
