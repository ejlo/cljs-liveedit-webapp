#!/usr/bin/env ruby

require 'erubis'

def usage
  puts "Usage: scripts/generate_index_html.rb <dev|release>"
  exit
end

usage unless ARGV.count == 1
@build_type = ARGV[0]
usage unless @build_type == "dev" or @build_type == "release"

infile = 'resources/templates/index.html.erb'
outfile = 'resources/public/index.html'

`mkdir -p resources/public`

input = File.read(infile)
eruby = Erubis::Eruby.new(input)

res = eruby.result(binding())
File.open(outfile, 'w') { |file|
  file.write(res)
}
puts outfile + " generated"
