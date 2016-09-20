app
    .factory('serverService', function ($http, $q) {
        var server = {
            companiesGET: '/api/companies',
            companyGET: '/api/company',
            companyDELETE: '/api/company',
            companyPUT: '/api/company',
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
            },

            getCompany: function(companyId) {
                var deferred = $q.defer();
                $http.get(server.companyGET + "/" + companyId).then(function (response) {
                    deferred.resolve(response.data)
                });
                return deferred.promise;
            },

            deleteCompany: function(companyId) {
                var deferred = $q.defer();
                $http.delete(server.companyDELETE + "/" + companyId).then(function (response) {
                    deferred.resolve(response.data)
                });
                return deferred.promise;
            },

            updateCompany: function(company) {
                var deferred = $q.defer();
                $http.put(server.companyPUT, company).then(function (response) {
                    deferred.resolve(response.data)
                });
                return deferred.promise;
            }

        }
    });