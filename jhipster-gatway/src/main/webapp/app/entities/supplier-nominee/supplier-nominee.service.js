(function() {
    'use strict';
    angular
        .module('jhipstergatwayApp')
        .factory('SupplierNominee', SupplierNominee);

    SupplierNominee.$inject = ['$resource'];

    function SupplierNominee ($resource) {
        var resourceUrl =  'supplierregisteration/' + 'api/supplier-nominees/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
