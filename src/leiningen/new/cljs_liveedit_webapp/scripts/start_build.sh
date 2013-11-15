#!/bin/bash

usage ()
{
    echo "Usage: scripts/start_build.sh <dev|release> [test]"
    exit
}

if [[ $# -eq 0
      || $# -gt 2
      || ! ( $1 =~ ^(dev|release)$ )
      || $# -eq 2 && $2 != "test" ]]; then
    usage
fi

echo "run: scripts/generate_index_html.rb $1"
scripts/generate_index_html.rb $1

echo "run: lein do cljsbuild clean, cljsbuild auto $@"
lein do cljsbuild clean, cljsbuild auto $@
