app
    .controller('AddCompanyController', function ($scope, $stateParams, serverService) {
        
                    $scope.newCompany = {
                        id: null,
                        name: '',
                        profit: 0,
                        parentCompanies: null
                    };

        $scope.save = function () {
            serverService.saveCompany($scope.newCompany)

        };

    });