# Content Based Recommender

Implements a content-based recommender via an web app by recommending similarity based Java programming wikibooks and Oracle TM Java Tutorials content to Stackoverflow.com data set queries. 

## Getting Started

Deploy the war file to local Tomcat server. To execute the app online  [Click Here]( http://asgn3rohank.herokuapp.com/) 
For offline execution start the server and open localhost:<port number>/asgn3rohank 

### Implementation

1. Crawl Java Programming Wikibook pages and Oracle Java Tutorials using BoilerPipe and Jsoup to extract main content from articles.
2. Index Crawled Documents using Apache Lucene
3. For each query display top 10 recommendations.

### Running the app

Initially there is no crawled data, click on crawl button to generate crawled content documents. Submit the query to view top 10 recommendations.

## License

N/A



