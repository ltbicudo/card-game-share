## Synopsis

At the top of the file there should be a short introduction and/ or overview that explains **what** the project is. This description should match descriptions added for package managers (Gemspec, package.json, etc.)

## Code Example

Show what the library does as concisely as possible, developers should be able to figure out **how** your project solves their problem by looking at the code example. Make sure the API you are showing off is obvious, and that your code is short and concise.

## Motivation

A short description of the motivation behind the creation and maintenance of the project. This should explain **why** the project exists.

## Installation

1. Download TomEE from [Apache Page](http://tomee.apache.org/apache-tomee.html)
  1. Version apache-tomee-webprofile-1.7.4
2. Configure Application Servers at IntelliJ IDEA 
3. Open system.properties
  1. Change tomee.serialization.class.blacklist pro from * to -
4. Add the card-game-share-web:exploded artifact into server
5. Configure the context root to card-game-share
6. Run the server
7. Open http://localhost:8080/card-game-share

## API Reference

Depending on the size of the project, if it is small and simple enough the reference docs can be added to the README. For medium size to larger projects it is important to at least provide a link to where the API reference docs live.

## Tests

Describe and show how to run the tests with code examples.

## Contributors

* Lucas Tavares Bicudo
* Flavia Stockchneider

## License

A short snippet describing the license (MIT, Apache, etc.)
