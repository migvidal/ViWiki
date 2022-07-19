# ViWiki
A basic but very visual Wikipedia client. Demo app to show my Android knowledge.

## Issues and discoveries (will update during the development)
- To implement a regular SearchView, an additional Activity must be used. Thus, this won't be a single activity app.
- When the stack trace doesn't go deep enough, add a try-catch(print) block in the error line.
- To distinguish Wikimedia from Wikipedia, I do this:
  - "Wikipedia": only the 'W' capitalized
  - "WikiMedia": capitalize the 'W' and the 'M'.
  That way, I can tell them apart more easily at a glance

- Fetch or Get?
  - In the ViewModel: I call a function "fetch<X>" because it just loads the data into a class property.
  - In the Api service: I call it "get<X>" since it both fetches and returns the data.