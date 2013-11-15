#!/usr/bin/node

var connect = require('connect');
var public_dir = "resources/public/";

connect()
  .use(connect.static(public_dir))
  .listen(63770);
