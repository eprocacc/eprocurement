(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .factory('TaluqSearch', TaluqSearch);

    TaluqSearch.$inject = ['$resource'];

    function TaluqSearch($resource) {
        var resourceUrl =  'supplierregisteration/' + 'api/_search/taluqs/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
