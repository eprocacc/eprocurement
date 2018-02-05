(function() {
    'use strict';

    angular
        .module('jhipstergatwayApp')
        .factory('SupplierGeneralInfoSearch', SupplierGeneralInfoSearch);

    SupplierGeneralInfoSearch.$inject = ['$resource'];

    function SupplierGeneralInfoSearch($resource) {
        var resourceUrl =  'supplierregisteration/' + 'api/_search/supplier-general-infos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
