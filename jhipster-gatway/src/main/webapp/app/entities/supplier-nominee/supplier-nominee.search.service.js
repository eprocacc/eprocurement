(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .factory('SupplierNomineeSearch', SupplierNomineeSearch);

    SupplierNomineeSearch.$inject = ['$resource'];

    function SupplierNomineeSearch($resource) {
        var resourceUrl =  'supplierregisteration/' + 'api/_search/supplier-nominees/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
