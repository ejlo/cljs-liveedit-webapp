# cljs-liveedit-webapp

A leiningen template for starting developing and live editing a clojurescript web app.


## About

This is a collections of tools for getting started developing web apps with clojurescript. It is mostly an example, since there are so many way to set it up, so you will likely need to do a bit of configuring to get it how you like it.


## Dependencies

* nodejs, npm, bower, phantomjs


## Install

Clone the repo and in its root directory, run

```bash
lein install
```

## Usage

```bash
lein new cljs-liveedit-webapp myapp
cd myapp
scripts/install_deps.sh
grunt
firefox http://localhost:3449/index.html
```

### Emacs browser repl

* Put the contents of scrips/clojurescript in your .emacs and evaluate it
* Start the repl with ``M-x cider-jack-in``
* Start the browser repl with ``(broswer-repl)``
* Reload the broswer

## Features

* automatically run tests
* live reload of css and js files using lein-figwheel
* clojurescript brower-repl
* sourcemaps

## License

Copyright (C) 2014 Erik Ouchterlony

Distributed under the Eclipse Public License, the same as Clojure.
