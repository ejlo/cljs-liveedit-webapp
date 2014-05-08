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
      options: {
        livereload: 35729,
      },
      css: {
        files: ['resources/templates/style/**/*.scss'],
        tasks: ["css_dev"],
      },
      html: {
        files: ['resources/templates/index.html.whiskers'],
        tasks: ["generate_dev_html"],
      },
      js: {
        files: ['resources/public/js/{{sanitized}}.js',
                'resources/public/js/out/{{sanitized}}/**/*.js'],
        tasks: [],
      },
    },

    concurrent: {
      options: {
        logConcurrentOutput: true
      },
      build_and_watch: ['cljsbuild_dev', 'watch'],
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
  grunt.registerTask('build_and_watch', ['concurrent:build_and_watch']);

  grunt.registerTask('release', ['generate_release_html', 'css_release', 'build_nw_app']);

  grunt.registerTask('default', ['generate_dev_html', 'css_dev', 'webserver', 'build_and_watch']);
};
