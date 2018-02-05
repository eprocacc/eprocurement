(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .factory('SupplierSearch', SupplierSearch);

    SupplierSearch.$inject = ['$resource'];

    function SupplierSearch($resource) {
        var resourceUrl =  'supplierregisteration/' + 'api/_search/suppliers/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
