(function() {
    'use strict';
    angular
        .module('jhipstergatwayApp')
        .factory('Taluq', Taluq);

    Taluq.$inject = ['$resource'];

    function Taluq ($resource) {
        var resourceUrl =  'supplierregisteration/' + 'api/taluqs/:id';

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
