app
    .factory('serverService', function ($http, $q) {
        var server = {
            companiesGET: '/api/companies',
            companyPOST:  '/api/company'
        };
        
        return {
            getCompanies: function() {
                var deferred = $q.defer();
                $http.get(server.companiesGET).then(function (response) {
                    deferred.resolve(response.data)
                });
                return deferred.promise;
            },

            saveCompany: function(company) {
                var deferred = $q.defer();
                $http.post(server.companyPOST, company).then(function (response) {
                    deferred.resolve(response.data)
                });
                return deferred.promise;
            }
        }
    });