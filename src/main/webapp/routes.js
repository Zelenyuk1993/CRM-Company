app
    .config(function ($stateProvider, $urlRouterProvider) {
        //Main provider
        $stateProvider
        //Main abstract State
            .state('app', {
                abstract: true,
                url: "",
                controller: 'HeaderController',
                templateUrl: 'template/header.html'
            })
            //State for root URL
            .state('app.main', {
                url: "/",
                controller: 'TreeController',
                templateUrl: 'template/tree.html'
            })
                
            //State for company
            .state('app.company', {
                url: "/show/{companyId}",
                controller: 'CompanyController',
                templateUrl: 'template/companyShow.html'
            })
            //State for addCompany
            .state('app.addCompany', {
                url: "/addCompany",
                controller: 'AddCompanyController',
                templateUrl: 'template/addCompany.html'
        });

        $stateProvider
            .state('error', {
                abstract: true,
                url: "/error",
                template: '<ui-view/>'
            })
            //State for 404 error
            .state('error.404', {
                url: "^/404",
                templateUrl: 'template/404.html'
            });
        $urlRouterProvider.otherwise('/404');
    });