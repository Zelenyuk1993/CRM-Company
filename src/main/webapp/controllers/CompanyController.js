app
    .controller('CompanyController', function ($scope, serverService) {
        $scope.test = "Hello World!!!";

        getCompaniesAndSumProfit();

        function getCompaniesAndSumProfit() {
            serverService.getCompanies().then(function (answer) {
                $scope.companies = _.filter(answer, function (value) {
                    if (value.childrenCompanies.length != 0) {
                        return value.sum = _.sumBy(value.childrenCompanies, function (children) {
                            return children.profit;
                        });
                    } else {
                        return value;
                    }
                });
            });
        }
        $scope.newCompany = {
            id: null,
            name: '',
            profit: null,
            parentCompanies: null
        };
        
        $scope.save = function () {
            serverService.saveCompany($scope.newCompany).then(function () {
                getCompaniesAndSumProfit();
            });
        };
    });