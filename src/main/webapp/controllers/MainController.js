app
    .controller('MainController', function ($scope, serverService) {
        $scope.test = "Hello World!!!";

        serverService.getCompanies().then(function (answer) {
            $scope.companies = answer;
        });
        
        $scope.newCompany = {
            id: null,
            name: 'Write name',
            childrenCompanies: null,
            parentCompanies: null
        };
        
        $scope.save = function () {
            serverService.saveCompany($scope.newCompany).then(function (answer) {
                console.log(answer);
                $scope.companies.push(answer);
            })
        };
    });