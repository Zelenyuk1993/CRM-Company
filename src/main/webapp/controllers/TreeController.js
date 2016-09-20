app
    .controller('TreeController', function ($scope, serverService) {
            
        var masDelete = [];

            serverService.getCompanies().then(function (answer) {
                $scope.companies = answer;

                _.filter($scope.companies, function (company, key) {
                    if (company.parentCompanies != undefined){
                            _.filter($scope.companies, function (companyParent) {
                                if (companyParent.id == company.parentCompanies.id){
                                    if (companyParent.childrenClient == undefined){
                                        companyParent.childrenClient = [];
                                    }
                                    companyParent.childrenClient.push(company);
                                    masDelete.push(key);
                                }
                            })
                    }
                });
                for (var i = masDelete.length - 1; i >= 0 ; i --){
                    $scope.companies.splice(masDelete[i], 1)
                }
            });

    });