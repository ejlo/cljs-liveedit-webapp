'use strict';

module.exports = function (grunt) {

  require('load-grunt-tasks')(grunt);

  grunt.initConfig({
    nodewebkit: {
      options: {
        build_dir: './target/nw-build/nw-bin',
        mac: true,
        win: true,
        linux32: true,
        linux64: true
      },
      src: ['./target/nw-build/nw-app/**/*']
    },

    sass: {
      options: {
        outputStyle: 'nested',
        includePaths: ["resources/templates/style"],
        imagePath: "resources/public/img",
      },
      dev: {
        options: {
          sourceComments: "map"
        },
        files: {
          'resources/public/style/app.css': 'resources/templates/style/app.scss'
        },
      },
      release: {
        options: {
          sourceComments: "none"
        },
        files: {
          'resources/public/style/app.css': 'resources/templates/style/app.scss'
        },
      },
    },

    watch: {
      css_dev: {
        files: ['resources/templates/style/**/*.scss'],
        tasks: ["css_dev"],
        options: {
          livereload: 35729,
        },
      },
      css_release: {
        files: ['resources/templates/style/**/*.scss'],
        tasks: ["css_release"],
      },
      html_dev: {
        files: ['resources/templates/index.html.whiskers'],
        tasks: ["generate_dev_html"],
        options: {
          livereload: 35729,
        },
      },
      html_release: {
        files: ['resources/templates/index.html.whiskers'],
        tasks: ["generate_release_html"],
      },
    },

    concurrent: {
      options: {
        logConcurrentOutput: true
      },
      build_and_watch_dev: ['cljsbuild_dev', 'watch:css_dev', 'watch:html_dev'],
      build_and_watch_release: ['cljsbuild_release', 'watch:css_release', 'watch:html_release'],
    },

    connect: {
      server: {
        options: {
          hostname: "localhost",
          port: 63770,
          base: 'resources/public/'
        },
      },
    },

    shell: {
      options: {
        stdout: true
      },
      cljsbuild_dev: {
        command: 'lein do cljsbuild clean, cljsbuild auto dev test'
      },
      cljsbuild_release: {
        command: 'lein do cljsbuild clean, cljsbuild auto release'
      },
      nwapp: {
        command: 'scripts/build_nw_app.sh'
      },
    },

    consolidate: {
      options: {
        engine: 'whiskers'
      },
      dev: {
        options: {
          local: { dev: true }
        },
        files : [{
            expand: true,
            cwd: 'resources/templates/',
            src: ['{,*/}*.whiskers'],
            dest: 'resources/public/',
            ext: '.html',
        }]
      },
      release: {
        options: {
          local: { dev: false }
        },
        files : [{
            expand: true,
            cwd: 'resources/templates/',
            src: ['{,*/}*.whiskers'],
            dest: 'resources/public/',
            ext: '.html',
          }]
      },
    },
  });

  grunt.registerTask('generate_dev_html', ['consolidate:dev']);
  grunt.registerTask('generate_release_html', ['consolidate:release']);
  grunt.registerTask('build_nw_app', ['shell:build_nw_app']);
  grunt.registerTask('cljsbuild_dev', ['shell:cljsbuild_dev']);
  grunt.registerTask('cljsbuild_release', ['shell:cljsbuild_release']);
  grunt.registerTask('webserver', ['connect']);
  grunt.registerTask('css_dev', ['sass:dev']);
  grunt.registerTask('css_release', ['sass:release']);
  grunt.registerTask('build_and_watch_dev', ['concurrent:build_and_watch_dev']);
  grunt.registerTask('build_and_watch_release', ['concurrent:build_and_watch_release']);


  grunt.registerTask('release', ['generate_release_html', 'css_release', 'build_nw_app']);

  grunt.registerTask('default', ['generate_dev_html', 'css_dev', 'webserver', 'build_and_watch_dev']);
};
