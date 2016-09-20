app
    .controller('CompanyController', function ($scope, $stateParams, serverService) {

        getCompany();

        function getCompany() {
        serverService.getCompany($stateParams.companyId)
            .then(function (answer) {
                $scope.company  = answer;
                    if (($scope.company.childrenCompanies.length != 0) && ($scope.company.sum == undefined)) {
                        $scope.company.sum = _.sumBy($scope.company.childrenCompanies, function (children) {
                                return children.profit;
                            }) + $scope.company.profit;
                    }
                $scope.newCompany = {
                    id: null,
                    name: '',
                    profit: 0,
                    parentCompanies: {
                        id: $scope.company.id,
                        name: $scope.company.name,
                        profit: $scope.company.profit
                    }
                };

            });
        }



        $scope.updateCompany = function (company) {
            serverService.updateCompany(company).then(function () {
                getCompany();
            })
        };

        $scope.deleteCompany = function (companyId) {
            serverService.deleteCompany(companyId).then(function () {
                getCompany();
            });
        };

        $scope.save = function () {
            serverService.saveCompany($scope.newCompany).then(function () {
                getCompany();
            });
        };

        $scope.addFilial = function () {
            $scope.company.childrenCompanies.push($scope.newCompany);
        }

    });