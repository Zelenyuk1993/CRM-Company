const gulp = require('gulp');
const concat = require('gulp-concat');
const sourcemaps = require('gulp-sourcemaps');
const uglify = require('gulp-uglify');
const ngAnnotate = require('gulp-ng-annotate');
const htmlmin = require('gulp-htmlmin');
const sass = require('gulp-sass');
const newer = require('gulp-newer');
const autoprefixer = require('gulp-autoprefixer');
const ngTemplate = require('gulp-angular-templatecache');
const cssmin = require('gulp-cssmin');
const promise = require('es6-promise').Promise;

const staticDir = 'src/main/resources/static/';
const webAppDir = 'src/main/webapp/';

gulp.task('source-concat-js', function() {
    return gulp.src([
            'node_modules/lodash/lodash.js',
            'node_modules/angular/angular.min.js',
            'node_modules/ng-lodash/build/ng-lodash.js',
            'node_modules/angular-animate/angular-animate.min.js',
            'node_modules/angular-aria/angular-aria.min.js',
            'node_modules/angular-material/angular-material.min.js',
            'node_modules/angular-ui-router/release/angular-ui-router.min.js',
            'node_modules/angular-material-icons/angular-material-icons.min.js'
        ])
        .pipe(newer(staticDir + 'source.min.js'))
        .pipe(concat('source.min.js'))
        .pipe(gulp.dest(staticDir))
});
gulp.task('source-concat-css', function () {
    return gulp.src([
            'node_modules/angular-material/angular-material.min.css',
            'node_modules/angular-material-icons/angular-material-icons.css'
        ])
        .pipe(newer(staticDir + 'source.min.css'))
        .pipe(autoprefixer({
            browsers: ['last 2 versions'],
            cascade: false
        }))
        .pipe(cssmin())
        .pipe(concat('source.min.css'))
        .pipe(gulp.dest(staticDir))
});
gulp.task('app-concat', function () {
    return gulp.src([
            webAppDir + 'app.js',
            webAppDir + 'routes.js',
            webAppDir + 'controllers/*.js',
            webAppDir + 'services/*.js'
        ])
        .pipe(newer(staticDir + 'app.min.js'))
        .pipe(concat('app.min.js'))
        .pipe(ngAnnotate())
        .pipe(uglify())
        .pipe(gulp.dest(staticDir))
});
gulp.task('html-replace-index', function() {
    return gulp.src([
            webAppDir + 'index.html'
        ])
        .pipe(newer(staticDir))
        .pipe(htmlmin({collapseWhitespace: true}))
        .pipe(gulp.dest(staticDir))
});
gulp.task('html-replace', function() {
    return gulp.src([
            webAppDir + 'template/*.html'
        ])
        .pipe(newer(staticDir))
        .pipe(htmlmin({collapseWhitespace: true}))
        .pipe(gulp.dest(staticDir + '/template'))
});
gulp.task('css-transpile', function() {
    return gulp.src([
            webAppDir + 'scss/**/*.scss'
        ])
        .pipe(newer({dest: staticDir, ext: '.css'}))
        .pipe(sass({outputStyle: 'compressed'}))
        .pipe(autoprefixer({
            browsers: ['last 2 versions'],
            cascade: false
        }))
        .pipe(concat('app.min.css'))
        .pipe(gulp.dest(staticDir))
});

gulp.task('default', [
    'source-concat-js',
    'source-concat-css',
    'app-concat',
    'html-replace-index',
    'html-replace',
    'css-transpile',
    'template'
]);


gulp.task('template', function(){
    gulp.src([
                webAppDir + "**/*.html"
        ])
        .pipe(newer(staticDir + 'templates.min.js'))
        .pipe(ngTemplate({module: 'ERP', standalone: false}))
        .pipe(concat('templates.min.js'))
        .pipe(ngAnnotate())
        .pipe(gulp.dest(staticDir));
});

gulp.task('watch', function () {
    gulp.watch(webAppDir + '**/*.js', ['app-concat']);
    gulp.watch(webAppDir + 'template/**/*.html', ['html-replace']);
    gulp.watch(webAppDir + 'scss/**/*.scss', ['css-transpile']);
});