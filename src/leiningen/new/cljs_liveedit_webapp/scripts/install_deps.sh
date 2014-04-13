#!/bin/bash


command -v phantomjs >/dev/null 2>&1 || {
    echo "phantomjs is required but it's not installed."
    echo "Install with:"
    echo "sudo npm install phantomjs -g"
    exit 1;
}

command -v gem >/dev/null 2>&1 || {
    echo "rubygems is required but it's not installed."
    echo "See: http://rubygems.org/pages/download"
    exit 1;
}

command -v npm >/dev/null 2>&1 || {
    echo "nodejs is required but it's not installed."
    echo "See: http://nodejs.org/download/"
    exit 1;
}

gem install bundler
bundle install

npm install connect # http://www.senchalabs.org/connect/
#npm install bower   # https://github.com/bower/bower
#npm install mu2     # https://github.com/raycmorgan/Mu

cd resources/
mkdir -p public/js/lib

#bower install jquery
#cp components/jquery/jquery.min.js public/js/lib

bower install react
cp components/react/react.js public/js/lib

echo -e "\n\nAll deps installed. We are ready to start!\n\n"
