(function() {
    'use strict';
    angular
        .module('jhipstergatwayApp')
        .factory('Supplier', Supplier);

    Supplier.$inject = ['$resource'];

    function Supplier ($resource) {
        var resourceUrl =  'supplierregisteration/' + 'api/suppliers/:id';

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
